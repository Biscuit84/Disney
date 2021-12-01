package disney.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import disney.dto.BoutiqueDto;
import disney.dto.BoutiqueEtoileDto;
import disney.dto.BoutiquePersoAndLifeDto;
import disney.dto.PersonnageDto;
import disney.model.Admin;
import disney.model.Boutique;
import disney.model.Compte;
import disney.model.Etoile;
import disney.model.Joueur;
import disney.model.PersoObtenu;
import disney.model.Personnage;
import disney.model.Vie;
import disney.model.Views;
import disney.repository.IBoutiqueRepo;
import disney.repository.ICompteRepo;
import disney.repository.IEtoileRepo;
import disney.repository.IJoueurRepo;
import disney.repository.IPersoObtenuRepo;
import disney.repository.IPersonnageRepo;
import disney.repository.IVieRepo;


@RestController
@RequestMapping("/boutique")
@CrossOrigin("*")
public class BoutiqueRestController {

	@Autowired
	private IBoutiqueRepo boutiqueRepo;

	@Autowired
	private IJoueurRepo joueurRepo;

	@Autowired
	private ICompteRepo compteRepo;

	@Autowired
	private IVieRepo vieRepo;

	@Autowired
	private IEtoileRepo etoileRepo;

	@Autowired
	private IPersonnageRepo persoRepo;

	@Autowired
	private IPersoObtenuRepo obtenuRepo;

	@GetMapping("")
	@JsonView(Views.ViewsBoutique.class)
	public List<Boutique> findAll() {
		List<Boutique> boutiques = boutiqueRepo.findAll();

		return boutiques;
	}

	@GetMapping("/detail")
	@JsonView(Views.ViewsBoutiqueDetail.class)
	public List<Boutique> findAllWithPersonnages() {
		List<Boutique> boutiques = boutiqueRepo.findAllWithPersonnages();

		return boutiques;
	}

	//	@GetMapping("{id}")
	//	@JsonView(Views.ViewsBoutique.class)
	//	public Boutique find(@PathVariable Long id) {
	//		Optional<Boutique> optBoutique = boutiqueRepo.findById(id);
	//
	//		if (optBoutique.isPresent()) {
	//			return optBoutique.get();
	//		} else {
	//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
	//		}
	//	}

	@GetMapping("{id}/detail")
	@JsonView(Views.ViewsBoutiqueDetail.class)
	public Boutique findWithDetail(@PathVariable Long id) {
		Optional<Boutique> optBoutique = boutiqueRepo.findByIdWithPersonnages(id);

		if (optBoutique.isPresent()) {
			return optBoutique.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsBoutique.class)
	public Boutique create(@RequestBody Boutique boutique) {
		boutique = boutiqueRepo.save(boutique);

		return boutique;
	}

	//	@PutMapping("/{id}")
	//	@JsonView(Views.ViewsBoutique.class)
	//	public Boutique update(@PathVariable Long id, @RequestBody Boutique boutique) {
	//		if (!boutiqueRepo.existsById(id)) {
	//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
	//		}
	//
	//		boutique = boutiqueRepo.save(boutique);
	//
	//		return boutique;
	//	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!boutiqueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}

		boutiqueRepo.deleteById(id);
	}


	/**
	 * on récupère la boutique +on regarde si le joueur possede deja des persos. 
	 * Si oui:boolean true ==> pour que cote front on fasse en sorte qu'il n'achete pas deux fois le meme perso
	 *
	 * @param idJoueur : le player connecte
	 * @return la boutique au bon format pour savoir si le player a acheter des persos et la liste des persos non achetes
	 */


	@GetMapping("/{idJoueur}")
	@JsonView(Views.ViewsBoutique.class)
	public BoutiqueDto choixBoutique(@PathVariable Long idJoueur) {
		Compte compte = compteRepo.findById(idJoueur).get();

		if (compte instanceof Admin) {
			return boutiquePourAdmin();
		} else {
			return boutiquePourJoueur(idJoueur);
		}
	}


	/**
	 * Achat des items de la boutique avec des etoiles
	 * @param idJoueur
	 * @param bpalDto
	 * @return boutiquePoueJoueur si joueur et non null
	 */
	@PutMapping("/{idJoueur}")
	@JsonView(Views.ViewsBoutique.class)
	public BoutiqueDto achatPersoAndLife(@PathVariable Long idJoueur, @RequestBody BoutiquePersoAndLifeDto bpalDto) {
		Compte compte = compteRepo.findById(idJoueur).get();

		if (compte instanceof Admin) {
			System.out.println("un admin ne peut pas acheter dans la boutique, il fait juste de l'administration");
			return null;
		} else if (compte instanceof Joueur) {
			Joueur joueur = (Joueur) compte;
			// on recupere l'argent du joueur + calcul du montant du panier
			int joueurNbEtoiles = joueur.getNbEtoiles();
			int totalPanier = 0;

			List<Personnage> listePersos = new ArrayList<>();
			// pour chaque perso dans le panier ==> chercher en base le perso pour ajouter le prix au montant total
			for (Long idPerso : bpalDto.getIdPersonnages()) {
				Personnage p = persoRepo.findById(idPerso).get();
				listePersos.add(p);
				totalPanier += p.getPrixAchatPerso();
			}

			// pour chaque vie dans le panier ==> chercher en base la vie pour ajouter le prix au montant total
			List<Vie> listVie = new ArrayList<>();
			for (Long idVie : bpalDto.getIdVies()) {
				Vie v = vieRepo.findById(idVie).get();
				listVie.add(v);
				totalPanier += v.getPrix();
			}


			if (totalPanier > joueurNbEtoiles) {
				// si le joueur n'a pas assez d'argent
				// TODO REMONTER CETTE ERREUR AU FRONT!
				System.out.println("Solde du joueur insuffisant");
			} else {
				// si le joueur peut tout payer ==> mise a jour du nbre d'etoiles qui lui restent
				joueur.setNbEtoiles(joueurNbEtoiles - totalPanier);
				// on lui donne les persos qu'il  achete
				List<PersoObtenu> lpo = new ArrayList<>();
				for (Personnage p : listePersos) {
					PersoObtenu po = new PersoObtenu(p, joueur);
					po = obtenuRepo.save(po);
					lpo.add(po);
				}

				joueur.getPersos().addAll(lpo);

				// on augmente le nombre de vie du joueur
				int nbVieTotal = 0;
				for (Vie v : listVie) {
					nbVieTotal += v.getNombre();
				}
				joueur.setLife(joueur.getLife() + nbVieTotal);
				joueurRepo.save(joueur);
				return boutiquePourJoueur(idJoueur);
			}

		} else {
			// TODO REMONTER CETTE ERREUR AU FRONT
			System.out.println("je ne suis ni admin, ni joueur... qui suis-je ?!");
		}
		return null;
	}



	/**
	 * pas de validation car vrai argent
		TODO: faire une vraie validation du panier
	 * @param idJoueur
	 * @return
	 */
	@PutMapping("/{idJoueur}/etoiles")
	@JsonView(Views.ViewsBoutique.class)
	public BoutiqueDto achatEtoiles(@PathVariable Long idJoueur, @RequestBody BoutiqueEtoileDto boutiqueEtoileDto) {
		Compte compte = compteRepo.findById(idJoueur).get();

		if (compte instanceof Admin) {
			System.out.println("un admin ne peut pas acheter dans la boutique, il fait juste de l'administration");
			return null;
		} else if (compte instanceof Joueur) {
			Joueur joueur = (Joueur) compte;
			// on recupere l'argent du joueur + calcul du montant du panier
			int joueurNbEtoiles = joueur.getNbEtoiles();
			int totalPanier = 0;


			// pour chaque etoiles dans le panier ==> chercher en base la table etoile pour ajouter le prix au montant total
			for (Long idEtoile : boutiqueEtoileDto.getIdEtoile()) {
				Etoile etoile = etoileRepo.findById(idEtoile).get();
				//valider l'achat:
				joueur.setNbEtoiles(joueur.getNbEtoiles()+etoile.getNombre());
			}

			joueur=joueurRepo.save(joueur);
			return boutiquePourJoueur(idJoueur);

		} else {
			// TODO REMONTER CETTE ERREUR AU FRONT
			System.out.println("je ne suis ni admin, ni joueur... qui suis-je ?!");
		}
		return null;
	}

	/**
	 * retourne la boutique avec la liste des perso, liste de vie, liste d'étoiles
	 *
	 * @param boutiqueRepo : permet de communiquer avec la table boutique
	 * @return la boutique
	 */
	private Boutique choixBoutique() {
		// il n'y aura toujours qu'une seule ligne dans la boutique
		return boutiqueRepo.findAll().get(0);
	}

	/**
	 * recupere la boutique pour 1 joueur + boolean à true si le joueur possede certains persos
	 *
	 * @param idJoueur : id du joueur
	 * @return la boutique adaptee pour ce joueur
	 */
	private BoutiqueDto boutiquePourJoueur(Long idJoueur) {
		// on recupere la liste des persos obtenus du joueur
		List<PersoObtenu> lpo = joueurRepo.getById(idJoueur).getPersos();

		List<Personnage> lp = new ArrayList<>();
		// on le transforme en liste de persos pour le manipuler avec la boutique
		for (PersoObtenu perso : lpo) {
			lp.add(perso.getPerso());
		}
		Boutique boutique = choixBoutique();
		List<PersonnageDto> listPersonnageDto = new ArrayList<>();
		for (Personnage perso : boutique.getPersonnages()) {
			// si on a deja le perso alors on set le boolean a true
			if (lp.contains(perso)) {
				System.out.println("################ LE JOUEUR A DEJA ACHETER LE PERSO : " + perso);
				listPersonnageDto.add(new PersonnageDto(perso, true));
			} else {
				// sinon on set le boolean a faux
				System.out.println("################ LE JOUEUR NE POSSEDE PAS LE PERSO : " + perso);
				listPersonnageDto.add(new PersonnageDto(perso, false));
			}
		}

		// on retourne la boutique avec pour le joueur les personnages qu'il possede ou non
		return new BoutiqueDto(listPersonnageDto, boutique.getListVies(), boutique.getListEtoiles());
	}

	/**
	 * recupere la boutique pour un admin: celle presente en base, avec aucun personnage associe au compte
	 *
	 * @return
	 */
	private BoutiqueDto boutiquePourAdmin() {
		Boutique boutique = choixBoutique();
		List<PersonnageDto> listsPersosDTO = new ArrayList<>();
		for (Personnage perso : boutique.getPersonnages()) {
			// Admin: juste pour les administrer les perso, donc boolean a false par defaut
			listsPersosDTO.add(new PersonnageDto(perso, false));

		}

		return new BoutiqueDto(listsPersosDTO, boutique.getListVies(), boutique.getListEtoiles());
	}



}
