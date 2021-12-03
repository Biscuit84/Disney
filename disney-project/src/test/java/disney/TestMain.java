package disney;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import disney.model.Admin;
import disney.model.Boutique;
import disney.model.Carte;
import disney.model.Cases;
import disney.model.CasesPlateau;
import disney.model.Etoile;
import disney.model.Historique;
import disney.model.Joueur;
import disney.model.Partie;
import disney.model.PersoObtenu;
import disney.model.Personnage;
import disney.model.Plateau;
import disney.model.TypeCarte;
import disney.model.TypeCase;
import disney.model.Vie;
import disney.repository.IAdminRepo;
import disney.repository.IBoutiqueRepo;
import disney.repository.ICarteRepo;
import disney.repository.ICasesPlateauRepo;
import disney.repository.ICasesRepo;
import disney.repository.ICompteRepo;
import disney.repository.IEtoileRepo;
import disney.repository.IHistoriqueRepo;
import disney.repository.IJoueurRepo;
import disney.repository.IPartieRepo;
import disney.repository.IPersoObtenuRepo;
import disney.repository.IPersonnageRepo;
import disney.repository.IPlateauRepo;
import disney.repository.IVieRepo;

@SpringBootTest
class TestMain {


		// TODO Auto-generated method stub

//		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(AppConfig.class);
		
		@Autowired
		IJoueurRepo joueurRepo;
		@Autowired
		IAdminRepo adminRepo;
		@Autowired
		IPersonnageRepo persoRepo;
		@Autowired
		ICasesRepo casesRepo;
		@Autowired
		IBoutiqueRepo boutiqueRepo;
		@Autowired
		ICarteRepo carteRepo;
		@Autowired
		ICasesPlateauRepo casesPlateauRepo;
		@Autowired
		IHistoriqueRepo historiqueRepo;
		@Autowired
		IPartieRepo partieRepo;
		@Autowired
		IPersoObtenuRepo persoObtenuRepo;
		@Autowired
		IPlateauRepo plateauRepo;
		@Autowired
		ICompteRepo compteRepo;
		@Autowired
		IVieRepo vieRepo;
		@Autowired
		IEtoileRepo etoilesRepo;

		
//		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
//		
		@Test
		void contextLoads() throws ParseException {
////	
			
			Joueur ia1 = new Joueur("mickey.mickey@adresseMail.com","mickeyTropFortMickey","Mickey");
			Joueur ia2 = new Joueur("donald.donald@adresseMail.com","donaldTropFortDonald","Donald");
			Joueur ia3 = new Joueur("dingo.dingo@adresseMail.com","dingoTropFortDonald","Dingo");
			ia1 = joueurRepo.save(ia1);
			ia2 = joueurRepo.save(ia2);
			ia3 = joueurRepo.save(ia3);
			
		//JOUEURS
		Joueur joueur1 = new Joueur("joueur1", "1234", "Toto", "Titi", "toto.titi@gmail.com", "TotoTropFort", "noob", 3);
		joueur1=joueurRepo.save(joueur1);
		Joueur joueur2 = new Joueur("joueur2", "password", "Tartanpion", "Tintin", "Tart.tintin@gmail.com", "TintinTheBest", "champion", 3);
		joueur2=joueurRepo.save(joueur2);
		Joueur joueur3 = new Joueur("joueur3", "1234", "j3", "Titi", "j3@gmail.com", "TotoTropFort", "noob", 3);
		joueur3.setNbEtoiles(1000);
		joueur3 = joueurRepo.save(joueur3);
		
		
		
		
		List <Joueur>listeJoueurs = new ArrayList<Joueur>();
		listeJoueurs.add(joueur1);
		listeJoueurs.add(joueur2);
		listeJoueurs.add(joueur3);
		
		Set <Joueur>listeJoueursPartie = new HashSet<Joueur>();
		listeJoueursPartie.add(joueur1);
		listeJoueursPartie.add(joueur2);
		listeJoueursPartie.add(joueur3);
		
		//ADMIN
		Admin admin1 = new Admin("admin1", "987654321", "admin1", "admin1","admin1@gmail.com");
		admin1=adminRepo.save(admin1);
		Admin admin2 = new Admin("admin2", "123456789", "admin2", "admin2","admin2@gmail.com");
		admin2=adminRepo.save(admin2);
		
		//Personnages
		Personnage perso1 = new Personnage("Elsa", "Olaf", "Hans", "Glace",100);
		Personnage perso2 = new Personnage("Ariel", "Eric", "Ursula", "Eau",200);
		Personnage perso3 = new Personnage("Jasmine", "Aladdin", "Jafar", "feu",300);
		Personnage perso4 = new Personnage("Mulan", "Amoureux", "Atila", "terre",400);
		Personnage perso5 = new Personnage("Aurore", "Philippe", "Malefique", "terre",100);
		Personnage perso6 = new Personnage("Belle", "La Bête", "Gaston", "terre",100);
		Personnage perso7 = new Personnage("Blanche Neige", "Prince", "La méchante reine", "terre",100);
		Personnage perso8 = new Personnage("Cendrillon", "Prince charmant", "Mme de Trénaine", "terre",100);
		Personnage perso9 = new Personnage("Raiponce", "Eugene", "Gotel", "terre",100);
		Personnage perso10 = new Personnage("Tiana", "ray", "Maitre des ombres", "terre",100);
		perso1.setAvatar("../../assets/images/persoBoutique/elsa.jpg");
		perso2.setAvatar("../../assets/images/persoBoutique/arel.jpg");
		perso3.setAvatar("../../assets/images/persoBoutique/jasmine.jpg");
		perso4.setAvatar("../../assets/images/persoBoutique/mulan.jpg");
		perso5.setAvatar("../../assets/images/persoBoutique/aurore.jpg");
		perso6.setAvatar("../../assets/images/persoBoutique/belle.jpg");
		perso7.setAvatar("../../assets/images/persoBoutique/blanche-neige.jpg");
		perso8.setAvatar("../../assets/images/persoBoutique/Cendrillon.jpg");
		perso9.setAvatar("../../assets/images/persoBoutique/raiponce.jpg");
		perso10.setAvatar("../../assets/images/persoBoutique/tiana.jpg");
		
		List<Personnage> listeTotalePerso = new ArrayList<> ();
		listeTotalePerso.add(perso1);
		listeTotalePerso.add(perso2);
		listeTotalePerso.add(perso3);
		listeTotalePerso.add(perso4);
		listeTotalePerso.add(perso5);
		listeTotalePerso.add(perso6);
		listeTotalePerso.add(perso7);
		listeTotalePerso.add(perso8);
		listeTotalePerso.add(perso9);
		listeTotalePerso.add(perso10);
		
		Set<Personnage> listePersoPartie = new HashSet<>();
		listePersoPartie.add(perso1);
		listePersoPartie.add(perso2);
		listePersoPartie.add(perso3);
		listePersoPartie.add(perso4);
		listePersoPartie.add(perso5);
		listePersoPartie.add(perso6);
		listePersoPartie.add(perso7);
		listePersoPartie.add(perso8);
		listePersoPartie.add(perso9);
		listePersoPartie.add(perso10);
		
		perso1=persoRepo.save(perso1);
		perso2=persoRepo.save(perso2);
		perso3=persoRepo.save(perso3);
		perso4=persoRepo.save(perso4);
		perso5=persoRepo.save(perso5);
		perso6=persoRepo.save(perso6);
		perso7=persoRepo.save(perso7);
		perso8=persoRepo.save(perso8);
		perso9=persoRepo.save(perso9);
		perso10=persoRepo.save(perso10);
		
		//Cases
		Cases caseMechant = new Cases("Mechant",TypeCase.mechant);
		Cases caseGentil = new Cases("Gentil",TypeCase.prince);
		Cases casePrison = new Cases("Prison",TypeCase.prison);
		Cases caseVide = new Cases("Vide",TypeCase.vide);
		Cases caseDuel= new Cases("Duel",TypeCase.duel);
		Cases caseDeplacement = new Cases("Deplacement",TypeCase.deplacement);
		Cases caseDepart = new Cases("Depart",TypeCase.depart);
		Cases caseArrivee = new Cases("Arrivee",TypeCase.arrivee);
		Cases casePioche = new Cases("Pioche",TypeCase.pioche);
		caseMechant = casesRepo.save(caseMechant);
		caseGentil = casesRepo.save(caseGentil);
		casePrison = casesRepo.save(casePrison);
		caseVide = casesRepo.save(caseVide);
		caseDuel = casesRepo.save(caseDuel);
		caseDeplacement = casesRepo.save(caseDeplacement);
		caseDepart = casesRepo.save(caseDepart);
		caseArrivee = casesRepo.save(caseArrivee);
		casePioche = casesRepo.save(casePioche);
		
		//BOUTIQUE
		final List<Vie> listeTotaleVie = new ArrayList<>();
		listeTotaleVie.add(new Vie(1, 100));
		listeTotaleVie.add(new Vie(3, 275));
		listeTotaleVie.add(new Vie(5, 400));
		listeTotaleVie.add(new Vie(10, 750));
		vieRepo.saveAll(listeTotaleVie);

		final List<Etoile> listeTotaleEtoiles = new ArrayList<>();
		listeTotaleEtoiles.add(new Etoile(100, 5));
		listeTotaleEtoiles.add(new Etoile(300, 13));
		listeTotaleEtoiles.add(new Etoile(500, 22));
		listeTotaleEtoiles.add(new Etoile(1000, 40));
		etoilesRepo.saveAll(listeTotaleEtoiles);

		Boutique boutique = new Boutique(listeTotalePerso, listeTotaleVie, listeTotaleEtoiles);
		boutique = boutiqueRepo.save(boutique);
		
		//Cartes
		Carte c1 = new Carte("Carte Avance",TypeCarte.Bonus);
		Carte c2 = new Carte("Carte Recule",TypeCarte.Malus);
		Carte c3 = new Carte("Aller en Prison",TypeCarte.Prison);
		
		c1=carteRepo.save(c1);
		c2=carteRepo.save(c2);
		c3=carteRepo.save(c3);
		
		//Plateau
		Plateau plateau1 = new Plateau ("Plateau Demo", 20);
		plateau1=plateauRepo.save(plateau1);
		
		//CasesPlateau
		List <CasesPlateau> casesPlateauDemo = new ArrayList();
		
		CasesPlateau c1pDemo = new CasesPlateau(plateau1, caseDepart, 0);
		casesPlateauDemo.add(c1pDemo);
		CasesPlateau c2pDemo = new CasesPlateau(plateau1, caseVide, 1);
		casesPlateauDemo.add(c2pDemo);
		CasesPlateau c3pDemo = new CasesPlateau(plateau1, caseDeplacement, 2);
		casesPlateauDemo.add(c3pDemo);
		CasesPlateau c4pDemo = new CasesPlateau(plateau1, caseDuel, 3);
		casesPlateauDemo.add(c4pDemo);
		CasesPlateau c5pDemo = new CasesPlateau(plateau1, casePioche, 4);
		casesPlateauDemo.add(c5pDemo);
		CasesPlateau c6pDemo = new CasesPlateau(plateau1, casePrison, 5);
		casesPlateauDemo.add(c6pDemo);
		CasesPlateau c7pDemo = new CasesPlateau(plateau1, caseGentil, 6);
		casesPlateauDemo.add(c7pDemo);
		CasesPlateau c8pDemo = new CasesPlateau(plateau1, caseMechant, 7);
		casesPlateauDemo.add(c8pDemo);
		CasesPlateau c9pDemo = new CasesPlateau(plateau1, caseDeplacement, 8);
		casesPlateauDemo.add(c9pDemo);
		CasesPlateau c10pDemo = new CasesPlateau(plateau1, caseVide, 9);
		casesPlateauDemo.add(c10pDemo);
		CasesPlateau c11pDemo = new CasesPlateau(plateau1, caseGentil, 10);
		casesPlateauDemo.add(c11pDemo);
		CasesPlateau c12pDemo = new CasesPlateau(plateau1, caseDuel, 11);
		casesPlateauDemo.add(c12pDemo);
		CasesPlateau c13pDemo = new CasesPlateau(plateau1, casePioche, 12);
		casesPlateauDemo.add(c13pDemo);
		CasesPlateau c14pDemo = new CasesPlateau(plateau1, caseMechant, 13);
		casesPlateauDemo.add(c14pDemo);
		CasesPlateau c15pDemo = new CasesPlateau(plateau1, caseVide, 14);
		casesPlateauDemo.add(c15pDemo);
		CasesPlateau c16pDemo = new CasesPlateau(plateau1, caseGentil, 15);
		casesPlateauDemo.add(c16pDemo);
		CasesPlateau c17pDemo = new CasesPlateau(plateau1, caseDeplacement, 16);
		casesPlateauDemo.add(c17pDemo);
		CasesPlateau c18pDemo = new CasesPlateau(plateau1, casePioche, 17);
		casesPlateauDemo.add(c18pDemo);
		CasesPlateau c19pDemo = new CasesPlateau(plateau1, caseMechant, 18);
		casesPlateauDemo.add(c19pDemo);
		CasesPlateau c20pDemo = new CasesPlateau(plateau1, caseArrivee, 19);
		casesPlateauDemo.add(c20pDemo);
		
		c1pDemo=casesPlateauRepo.save(c1pDemo);
		c2pDemo=casesPlateauRepo.save(c2pDemo);
		c3pDemo=casesPlateauRepo.save(c3pDemo);
		c4pDemo=casesPlateauRepo.save(c4pDemo);
		c5pDemo=casesPlateauRepo.save(c5pDemo);
		c6pDemo=casesPlateauRepo.save(c6pDemo);
		c7pDemo=casesPlateauRepo.save(c7pDemo);
		c8pDemo=casesPlateauRepo.save(c8pDemo);
		c9pDemo=casesPlateauRepo.save(c9pDemo);
		c10pDemo=casesPlateauRepo.save(c10pDemo);
		c11pDemo=casesPlateauRepo.save(c11pDemo);
		c12pDemo=casesPlateauRepo.save(c12pDemo);
		c13pDemo=casesPlateauRepo.save(c13pDemo);
		c14pDemo=casesPlateauRepo.save(c14pDemo);
		c15pDemo=casesPlateauRepo.save(c15pDemo);
		c16pDemo=casesPlateauRepo.save(c16pDemo);
		c17pDemo=casesPlateauRepo.save(c17pDemo);
		c18pDemo=casesPlateauRepo.save(c18pDemo);
		c19pDemo=casesPlateauRepo.save(c19pDemo);
		c20pDemo=casesPlateauRepo.save(c20pDemo);
		c11pDemo=casesPlateauRepo.save(c11pDemo);
		c12pDemo=casesPlateauRepo.save(c12pDemo);
		
		plateau1.setCases(casesPlateauDemo);
		
		plateau1=plateauRepo.save(plateau1);
		
		//Partie
		Partie partie1 = new Partie(plateau1,listeTotalePerso);
		partie1.setJoueurs(listeJoueurs);
		partie1=partieRepo.save(partie1);
		
		//HISTORIQUE
		String DateDebutPartie1 = "13/11/2021 15:49";
		LocalDateTime localDateT1 = LocalDateTime.parse(DateDebutPartie1, formatter);

		LocalDateTime heureFinPartie = LocalDateTime.now();
		
		Historique historique1 = new Historique();
		historique1.setDateHeureDebutPartie(localDateT1);
		historique1.setDateHeureFinPartie(heureFinPartie);
		historique1=historiqueRepo.save(historique1);
		
		long minuteOfGameDuration = Duration.between(localDateT1, heureFinPartie).toMinutes();
		System.out.println("duree de la partie:"+minuteOfGameDuration);
		
		//PersoObtenus
		
		PersoObtenu perso1ObtenuJoueur2 = new PersoObtenu(perso2, joueur2);
		PersoObtenu perso2ObtenuJoueur2 = new PersoObtenu(perso1, joueur2);
		PersoObtenu perso3ObtenuJoueur2 = new PersoObtenu(perso3, joueur2);
		PersoObtenu perso4ObtenuJoueur2 = new PersoObtenu(perso4, joueur2);
		PersoObtenu perso1ObtenuJoueur1 = new PersoObtenu (perso1, joueur1);
		PersoObtenu perso2ObtenuJoueur1 = new PersoObtenu (perso2, joueur1);
		
		perso1ObtenuJoueur2=persoObtenuRepo.save(perso1ObtenuJoueur2);
		perso2ObtenuJoueur2=persoObtenuRepo.save(perso2ObtenuJoueur2);
		perso3ObtenuJoueur2=persoObtenuRepo.save(perso3ObtenuJoueur2);
		perso4ObtenuJoueur2=persoObtenuRepo.save(perso4ObtenuJoueur2);
		
		perso1ObtenuJoueur1=persoObtenuRepo.save(perso1ObtenuJoueur1);
		perso2ObtenuJoueur1=persoObtenuRepo.save(perso2ObtenuJoueur1);
		
		}
		
		//Compte connecte:
//		Compte connected = compteRepo.findByLoginAndPassword("joueur1", "1234");
//		
//		System.out.println("Compte connecte:"+connected);
//		
//		spring.close();
		


}
