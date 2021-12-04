package disney.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import disney.dto.TourDeJeuDto;
import disney.model.CasesPlateau;
import disney.model.Historique;
import disney.model.Joueur;
import disney.model.Partie;
import disney.model.Personnage;
import disney.model.Plateau;
import disney.model.Views;
import disney.repository.ICasesPlateauRepo;
import disney.repository.IHistoriqueRepo;
import disney.repository.IJoueurRepo;
import disney.repository.IPartieRepo;
import disney.repository.IPersonnageRepo;
import disney.repository.IPlateauRepo;


@RestController
@RequestMapping("/partie")
@CrossOrigin("*")
public class PartieRestController {

	static Random r = new Random();

	@Autowired
	private IPartieRepo partieRepo;

	@Autowired
	private IPlateauRepo plateauRepo;

	@Autowired
	private IPersonnageRepo persoRepo;

	@Autowired
	private IJoueurRepo joueurRepo;

	@Autowired
	private ICasesPlateauRepo casesPlateauRepo;

	@Autowired
	private IHistoriqueRepo historiqueRepo;


	@GetMapping("")
	@JsonView(Views.ViewsPartie.class)
	public List<Partie> findAll() {
		List<Partie> parties = partieRepo.findAll();

		return parties;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewsPartie.class)
	public Partie find(@PathVariable Long id) {
		Optional<Partie> optPartie = partieRepo.findById(id);

		if (optPartie.isPresent()) {
			return optPartie.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}

	@GetMapping("/detail/{id}")
	@JsonView(Views.ViewsPartieDetailPersos.class)
	public Partie findWithDetailPlateauAndPersos(@PathVariable Long id) {
		Optional<Partie> optPartie = partieRepo.findByIdWithDetailPlateauAndPersos(id);

		if (optPartie.isPresent()) {
			return optPartie.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}



	@PostMapping("")
	@JsonView(Views.ViewsPartie.class)
	public Partie create(@RequestBody Partie partie) {


		partie = partieRepo.save(partie);

		return partie;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsPartie.class)
	public Partie update(@PathVariable Long id, @RequestBody Partie partie) {
		if (!partieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		partie = partieRepo.save(partie);

		return partie;
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!partieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		partieRepo.deleteById(id);
	}

	
	@PutMapping("/effect")
	public void appliquerEffet() {
		//TODO appliquer l'effet déclanché dans "jouerUnTour(@PathVariable Long idJoueur, @PathVariable Long idPartie)"
	}

	// lance un tour de jeu
	@PutMapping("/tour/{idJoueur}/{idPartie}")
	public TourDeJeuDto jouerUnTour(@PathVariable Long idJoueur, @PathVariable Long idPartie) {
		TourDeJeuDto tourDeJeuDto = new TourDeJeuDto();
		int valueDice1 = rollOneDice();
		int valueDice2 = rollOneDice();
		int totalValueDice = valueDice1+valueDice2;
		System.out.println("############# totalValueDice : "+totalValueDice);

		tourDeJeuDto.setValueDice1(valueDice1);
		tourDeJeuDto.setValueDice2(valueDice2);

		//on récupère la partie en cours
		Partie partie = partieRepo.findById(idPartie).get();
		Historique historique = historiqueRepo.findByPartie(partie);
		//si la partie n'est pas terminée
		if(historique.getDateHeureFinPartie() == null) {

			System.out.println(historique);
			System.out.println(partie.getPlateau().getCases());
			
			List<CasesPlateau>listeOrdreCases = casesPlateauRepo.findAllByPlateau(partie.getPlateau());
			System.out.println("############# listeOrdreCases : "+listeOrdreCases);

			//calcul du tour du joueur
			int tourDeJeuEnCours = partie.getNbTourDeJeu();
			int indexPersoQuiJoue = tourDeJeuEnCours%4;
			System.out.println("## index du perso : "+indexPersoQuiJoue);
			tourDeJeuEnCours++;

			partie.setNbTourDeJeu(tourDeJeuEnCours);
			tourDeJeuDto.setTourDeJeuEnCours(tourDeJeuEnCours);

			//la liste des perso dans l'ordre de jeu
			List<Personnage> lp = partie.getPersonnages();
			Personnage persoQuiJoue = lp.get(indexPersoQuiJoue);
			System.out.println("## position avant : "+persoQuiJoue.getPosition());
			int positionFuturePersonnage = persoQuiJoue.getPosition() + totalValueDice;
			//TODO avec listeOrdreCases prévoir un deuxieme appel sur la methode "appliquerEffet" pour appliquer l'effet
			//cela permet de terminer le déplacement du perso, avant d'appliquer l'effet.
//			CasesPlateau cp = listeOrdreCases.get(indexPositionFuturePersonnage);
			//TODO calcul de l'effet
//			tourDeJeuDto.setEffetAActiver(true);
			
			if(positionFuturePersonnage >= partie.getPlateau().getNbCases()-1) {
				positionFuturePersonnage = partie.getPlateau().getNbCases();
				persoQuiJoue.setPosition(positionFuturePersonnage);
				//est-ce le joueur qui a gagne ou un ia ?
				//si le joueur a gagne
				System.out.println("historique.getPersonnage().getId() : " + historique.getPersonnage().getId());
				System.out.println("persoQuiJoue.getId() : " + persoQuiJoue.getId());
				if(historique.getPersonnage().getId()==persoQuiJoue.getId()) {
					//bravo
					historique.setVictoire(true);
				} else {
					//defaite
					historique.setVictoire(false);
				}
				
				//on calcul la postition du joueur
				if(historique.isVictoire()) {
					historique.setPositionArrivee(1);
					historique.setNbEtoilesGagnees(500);
				} else {
					int positionActuelle = 1;
					Personnage persoJoueur = historique.getPersonnage();
					for(Personnage p : lp) {
						if(p.getPosition() > persoJoueur.getPosition()) {
							positionActuelle++;
						}
					}
					historique.setPositionArrivee(positionActuelle);
					historique.setNbEtoilesGagnees(500-100*(positionActuelle-1));
					//on dit qu'il est premier
//					int positionActuelle = 1;
//					for(Personnage p : lp) {
//						int positionPerso = p.getPosition();
//
//						System.out.println("positionPerso : "+positionPerso);
//						System.out.println("positionPersoQuiJoue : "+persoQuiJoue.getPosition());
//						if(positionPerso > persoQuiJoue.getPosition()) {
//							positionActuelle ++;
//							System.out.println(positionActuelle);
//						}
//					}
//					historique.setPositionArrivee(positionActuelle);
//					historique.setNbEtoilesGagnees(500-100*(positionActuelle-1));
					
				}
				
				//on met la date de fin de partie
				historique.setDateHeureFinPartie(LocalDateTime.now());
				tourDeJeuDto.setFinPartie(true);
				System.out.println(" ############################# ");
				System.out.println(" ############################# ");
				System.out.println(" ############################# ");
				System.out.println(" #FIN DE PARTIE# : histo "+historique);
			}
			
			persoQuiJoue.setPosition(positionFuturePersonnage);
			System.out.println("## position apres : "+persoQuiJoue.getPosition());
			
			//		List<Personnage> lp = partie.getPersonnages();

			// fin de tour
			tourDeJeuDto.setPositionFutureJoueur(persoQuiJoue.getPosition());
			persoRepo.save(persoQuiJoue);
			partieRepo.save(partie);

			
			return tourDeJeuDto;
		}
		return null;
	}


	//creation de la partie:
	@PostMapping("/debuterLaPartie/{idJoueur}/{idPersoChoisi}/{idPlateauChoisi}")
	@JsonView(Views.ViewsPartieDetailPersos.class)
	public Partie debuterLaPartie(@PathVariable Long idJoueur, @PathVariable Long idPersoChoisi, @PathVariable Long idPlateauChoisi) {
		Historique historique = new Historique();
		Partie partie = new Partie();
		
		//	1. choix du plateau: afficher la liste des plateaux dispo grace a : PlateauRestController.findAll()
		//		Long idPlateau= (long) 1;
		Plateau p = plateauRepo.findById(idPlateauChoisi).get();
		partie.setPlateau(p);
		partie.setNbTourDeJeu(0);
		
		//	2. Creer une nouvelle partie et creation de la liste des joueurs:
		Set <Joueur> listeDesJoueurs = listeJoueursPartie(idJoueur, historique, partie);
		partie.setJoueurs(new ArrayList<>(listeDesJoueurs));

		//	3. choix du perso: 
		//		- afficher la liste des persos du joueur grace a: PersoObtenuRestController.findAllPersoObtenuByIdJoueur(idJoueur);
		//		- creation de la liste des persos de la partie:
		Set<Personnage> listePersoPartie = listePersosPartie(idPersoChoisi, historique, partie);
		partie.setPersonnages(new ArrayList<>(listePersoPartie));

		partie = partieRepo.save(partie);

		historique.setPartie(partie);
		historique.setDateHeureDebutPartie(LocalDateTime.now());
		historiqueRepo.save(historique);
		System.out.println(partie.getPersonnages());
		//		System.out.println();

		return partie;
	}

	//creation de la liste des joueurs:
	public Set<Joueur> listeJoueursPartie(Long idJoueur, Historique historique, Partie partie) {
		Set <Joueur> listeDesJoueurs = new HashSet <Joueur> ();
		Joueur IA1 = joueurRepo.findById((long) 1).get();
		Joueur IA2 = joueurRepo.findById((long) 2).get();
		Joueur IA3 = joueurRepo.findById((long) 3).get();
		Joueur joueurCourant = joueurRepo.findById(idJoueur).get();

		joueurCourant.setPartie(partie);
		listeDesJoueurs.add(joueurCourant);
		listeDesJoueurs.add(IA1);
		listeDesJoueurs.add(IA2);
		listeDesJoueurs.add(IA3);

		historique.setJoueur(joueurCourant);

		return listeDesJoueurs;
	}

	//creation de la liste des persos de la partie:
	public Set<Personnage> listePersosPartie(Long idPersoChoisi, Historique historique, Partie partie) {
		Set<Personnage> listePersoPartie = new HashSet <Personnage> ();
		//perso joueur:
		Personnage peJoueur = persoRepo.findById(idPersoChoisi).get();
		peJoueur.setPartie(partie);
		peJoueur.setPosition(0);
		historique.setPersonnage(peJoueur);

		//persosIA
		List <Personnage> listePersonnagesIA=persoRepo.findAll();
		listePersonnagesIA.remove(peJoueur);

		//tirage aleatoire pour choix des perso IA
		Set <Personnage> iAChoixPersonnage = new HashSet<> ();

		while(iAChoixPersonnage.size()<3) {
			for (int i=0;i<=2;i++) {
				int nombreAleatoireIA = r.nextInt(listePersonnagesIA.size());
				Personnage personnageIA= listePersonnagesIA.get(nombreAleatoireIA);
				personnageIA.setPartie(partie);
				personnageIA.setPosition(0);
				iAChoixPersonnage.add(personnageIA);
			}
		}

		listePersoPartie.add(peJoueur); 
		listePersoPartie.addAll(iAChoixPersonnage);

		return listePersoPartie;
	}


	/**
	 * permet de generer la valeur d'un de
	 * @return la valeur d'un de entre 1 et 6
	 */
	private int rollOneDice() {
		return r.nextInt(6)+1;
	}

}
