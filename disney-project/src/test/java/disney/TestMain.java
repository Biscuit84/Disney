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
import disney.model.Avatar;
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
import disney.repository.IAvatarRepo;
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
	IAvatarRepo avatarRepo;
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


		//Avatars:
		Avatar avatar1 = new Avatar("Elsa", "../../assets/images/avatar/avatar_elsa.png");
		Avatar avatar2 = new Avatar("Ariel", "../../assets/images/avatar/avatar_ariel.jpg");
		Avatar avatar3 = new Avatar("Aurore", "../../assets/images/avatar/avatar_aurore.jpg");
		Avatar avatar4 = new Avatar("Belle", "../../assets/images/avatar/avatar_belle.jpg");
		Avatar avatar5 = new Avatar("Blanche-neige", "../../assets/images/avatar/avatar_blanche_neige.jpg");
		Avatar avatar6 = new Avatar("Cendrillon", "../../assets/images/avatar/avatar_cendrillon.jpg");
		Avatar avatar7 = new Avatar("Jasmine", "../../assets/images/avatar/avatar_jasmine.jpg");
		Avatar avatar8 = new Avatar("Mulan", "../../assets/images/avatar/avatar_mulan.jpg");
		Avatar avatar9 = new Avatar("Raiponce", "../../assets/images/avatar/avatar_raiponce.jpg");
		Avatar avatar10 = new Avatar("Tiana", "../../assets/images/avatar/avatar_tiana.jpg");
		Avatar avatar11 = new Avatar("Mickey", "../../assets/images/avatar/avatar_mickey.png");
		Avatar avatar12 = new Avatar("Minnie", "../../assets/images/avatar/avatar_minnie.png");
		avatar1=avatarRepo.save(avatar1);
		avatar2=avatarRepo.save(avatar2);
		avatar3=avatarRepo.save(avatar3);
		avatar4=avatarRepo.save(avatar4);
		avatar5=avatarRepo.save(avatar5);
		avatar6=avatarRepo.save(avatar6);
		avatar7=avatarRepo.save(avatar7);
		avatar8=avatarRepo.save(avatar8);
		avatar9=avatarRepo.save(avatar9);
		avatar10=avatarRepo.save(avatar10);
		avatar11=avatarRepo.save(avatar11);
		avatar12=avatarRepo.save(avatar12);




		//IA 
		Joueur ia1 = new Joueur("mickey.mickey@adresseMail.com","mickeyTropFortMickey","Mickey");
		Joueur ia2 = new Joueur("donald.donald@adresseMail.com","donaldTropFortDonald","Donald");
		Joueur ia3 = new Joueur("dingo.dingo@adresseMail.com","dingoTropFortDonald","Dingo");
		ia1 = joueurRepo.save(ia1);
		ia2 = joueurRepo.save(ia2);
		ia3 = joueurRepo.save(ia3);

		//JOUEURS
		Joueur joueur1 = new Joueur("joueur1", "1234", "Toto", "Titi", "toto.titi@gmail.com", "TotoTropFort", "noob", 3);
		joueur1.setAvatar(avatar1);
		joueur1=joueurRepo.save(joueur1);
		Joueur joueur2 = new Joueur("joueur2", "password", "Tartanpion", "Tintin", "Tart.tintin@gmail.com", "TintinTheBest", "champion", 3);
		joueur2.setAvatar(avatar2);
		joueur2=joueurRepo.save(joueur2);
		Joueur joueur3 = new Joueur("joueur3", "1234", "j3", "Titi", "j3@gmail.com", "TotoTropFort", "noob", 3);
		joueur3.setAvatar(avatar11);
		joueur3.setNbEtoiles(1000);
		joueur3 = joueurRepo.save(joueur3);

		Joueur joueur4 = new Joueur("MinnieDu44", "1234", "Minnie", "Mouse", "minnie.mouse@gmail.com", "Princesse Minnie", "expert", 15);
		joueur4.setNbEtoiles(5000);
		joueur4 = joueurRepo.save(joueur4);		
		Joueur joueur5 = new Joueur("DaisyDu13", "1234", "Daisy", "Duck", "daisy.duck@gmail.com", "Imperatrice Daisy", "intermediaire", 2);
		joueur5.setNbEtoiles(100);
		joueur5 = joueurRepo.save(joueur5);	
		Joueur joueur6 = new Joueur("PicsouDu75", "1234", "Balthazar", "Picsou", "bigboss@gmail.com", "Scrooge McDuck", "dieu", 100);
		joueur6.setNbEtoiles(1000000);
		joueur6 = joueurRepo.save(joueur6);	



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
		Personnage perso7 = new Personnage("Blanche-Neige", "Prince", "La méchante reine", "terre",100);
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
		listeTotaleVie.add(new Vie(1, 100, "../../../assets/images/boutique/potion-1.png"));
		listeTotaleVie.add(new Vie(3, 275, "../../../assets/images/boutique/potion-3.png"));
		listeTotaleVie.add(new Vie(5, 400, "../../../assets/images/boutique/potion-5.png"));
		listeTotaleVie.add(new Vie(10, 750, "../../../assets/images/boutique/potion-10.png"));
		vieRepo.saveAll(listeTotaleVie);

		final List<Etoile> listeTotaleEtoiles = new ArrayList<>();
		listeTotaleEtoiles.add(new Etoile(100, 5, "../../../assets/images/boutique/etoile-1.png"));
		listeTotaleEtoiles.add(new Etoile(300, 13, "../../../assets/images/boutique/etoile-3.png"));
		listeTotaleEtoiles.add(new Etoile(500, 22, "../../../assets/images/boutique/etoile-5.png"));
		listeTotaleEtoiles.add(new Etoile(1000, 40, "../../../assets/images/boutique/etoile-10.png"));
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
		plateau1.setDisponible(true);

		plateau1=plateauRepo.save(plateau1);


		// plateau de test
		Plateau plateau2 = new Plateau ("Plateau Test", 10);
		plateau2.setDisponible(true);
		plateau2=plateauRepo.save(plateau2);


		//CasesPlateau
		List <CasesPlateau> casesPlateauTest = new ArrayList();

		CasesPlateau c1pTest = new CasesPlateau(plateau2, caseDepart, 0);
		casesPlateauTest.add(c1pTest);
		CasesPlateau c2pTest = new CasesPlateau(plateau2, caseVide, 1);
		casesPlateauTest.add(c2pTest);
		CasesPlateau c3pTest = new CasesPlateau(plateau2, caseDeplacement, 2);
		casesPlateauTest.add(c3pTest);
		CasesPlateau c4pTest = new CasesPlateau(plateau2, caseDuel, 3);
		casesPlateauTest.add(c4pTest);
		CasesPlateau c5pTest = new CasesPlateau(plateau2, casePioche, 4);
		casesPlateauTest.add(c5pTest);
		CasesPlateau c6pTest = new CasesPlateau(plateau2, casePrison, 5);
		casesPlateauTest.add(c6pTest);
		CasesPlateau c7pTest = new CasesPlateau(plateau2, caseGentil, 6);
		casesPlateauTest.add(c7pTest);
		CasesPlateau c8pTest = new CasesPlateau(plateau2, caseMechant, 7);
		casesPlateauTest.add(c8pTest);
		CasesPlateau c9pTest = new CasesPlateau(plateau2, caseDeplacement, 8);
		casesPlateauTest.add(c9pTest);
		CasesPlateau c10pTest = new CasesPlateau(plateau2, caseArrivee, 9);
		casesPlateauTest.add(c10pTest);


		c1pTest=casesPlateauRepo.save(c1pTest);
		c2pTest=casesPlateauRepo.save(c2pTest);
		c3pTest=casesPlateauRepo.save(c3pTest);
		c4pTest=casesPlateauRepo.save(c4pTest);
		c5pTest=casesPlateauRepo.save(c5pTest);
		c6pTest=casesPlateauRepo.save(c6pTest);
		c7pTest=casesPlateauRepo.save(c7pTest);
		c8pTest=casesPlateauRepo.save(c8pTest);
		c9pTest=casesPlateauRepo.save(c9pTest);
		c10pTest=casesPlateauRepo.save(c10pTest);

		plateau2.setCases(casesPlateauTest);

		plateau2=plateauRepo.save(plateau2);


		//Plateau 3
		Plateau plateau3 = new Plateau ("Plateau Regular", 63);
		plateau3.setDisponible(false);
		plateau3=plateauRepo.save(plateau3);

		//CasesPlateau
		List <CasesPlateau> casesPlateauRegular = new ArrayList();

		CasesPlateau c1pRegular = new CasesPlateau(plateau3, caseDepart, 0);
		casesPlateauRegular.add(c1pRegular);
		CasesPlateau c2pRegular = new CasesPlateau(plateau3, caseVide, 1);
		casesPlateauRegular.add(c2pRegular);
		CasesPlateau c3pRegular = new CasesPlateau(plateau3, caseDeplacement, 2);
		casesPlateauRegular.add(c3pRegular);
		CasesPlateau c4pRegular = new CasesPlateau(plateau3, caseDuel, 3);
		casesPlateauRegular.add(c4pRegular);
		CasesPlateau c5pRegular = new CasesPlateau(plateau3, casePioche, 4);
		casesPlateauRegular.add(c5pRegular);
		CasesPlateau c6pRegular = new CasesPlateau(plateau3, casePrison, 5);
		casesPlateauRegular.add(c6pRegular);
		CasesPlateau c7pRegular = new CasesPlateau(plateau3, caseGentil, 6);
		casesPlateauRegular.add(c7pRegular);
		CasesPlateau c8pRegular = new CasesPlateau(plateau3, caseMechant, 7);
		casesPlateauRegular.add(c8pRegular);
		CasesPlateau c9pRegular = new CasesPlateau(plateau3, caseDeplacement, 8);
		casesPlateauRegular.add(c9pRegular);
		CasesPlateau c10pRegular = new CasesPlateau(plateau3, caseVide, 9);
		casesPlateauRegular.add(c10pRegular);
		CasesPlateau c11pRegular = new CasesPlateau(plateau3, caseGentil, 10);
		casesPlateauRegular.add(c11pRegular);
		CasesPlateau c12pRegular = new CasesPlateau(plateau3, caseDuel, 11);
		casesPlateauRegular.add(c12pRegular);
		CasesPlateau c13pRegular = new CasesPlateau(plateau3, casePioche, 12);
		casesPlateauRegular.add(c13pRegular);
		CasesPlateau c14pRegular = new CasesPlateau(plateau3, caseMechant, 13);
		casesPlateauRegular.add(c14pRegular);
		CasesPlateau c15pRegular = new CasesPlateau(plateau3, caseVide, 14);
		casesPlateauRegular.add(c15pRegular);
		CasesPlateau c16pRegular = new CasesPlateau(plateau3, caseGentil, 15);
		casesPlateauRegular.add(c16pRegular);
		CasesPlateau c17pRegular = new CasesPlateau(plateau3, caseDeplacement, 16);
		casesPlateauRegular.add(c17pRegular);
		CasesPlateau c18pRegular = new CasesPlateau(plateau3, casePioche, 17);
		casesPlateauRegular.add(c18pRegular);
		CasesPlateau c19pRegular = new CasesPlateau(plateau3, caseMechant, 18);
		casesPlateauRegular.add(c19pRegular);
		CasesPlateau c20pRegular = new CasesPlateau(plateau3, caseVide, 19);
		casesPlateauRegular.add(c20pRegular);
		CasesPlateau c21pRegular = new CasesPlateau(plateau3, caseGentil, 20);
		casesPlateauRegular.add(c21pRegular);
		CasesPlateau c22pRegular = new CasesPlateau(plateau3, caseDuel, 21);
		casesPlateauRegular.add(c22pRegular);
		CasesPlateau c23pRegular = new CasesPlateau(plateau3, casePioche, 22);
		casesPlateauRegular.add(c23pRegular);
		CasesPlateau c24pRegular = new CasesPlateau(plateau3, caseMechant, 23);
		casesPlateauRegular.add(c24pRegular);
		CasesPlateau c25pRegular = new CasesPlateau(plateau3, caseVide, 24);
		casesPlateauRegular.add(c25pRegular);
		CasesPlateau c26pRegular = new CasesPlateau(plateau3, caseGentil, 25);
		casesPlateauRegular.add(c26pRegular);
		CasesPlateau c27pRegular = new CasesPlateau(plateau3, caseDeplacement, 26);
		casesPlateauRegular.add(c27pRegular);
		CasesPlateau c28pRegular = new CasesPlateau(plateau3, casePioche, 27);
		casesPlateauRegular.add(c28pRegular);
		CasesPlateau c29pRegular = new CasesPlateau(plateau3, caseMechant, 28);
		casesPlateauRegular.add(c29pRegular);
		CasesPlateau c30pRegular = new CasesPlateau(plateau3, caseVide, 29);
		casesPlateauRegular.add(c30pRegular);
		CasesPlateau c31pRegular = new CasesPlateau(plateau3, caseGentil, 30);
		casesPlateauRegular.add(c31pRegular);
		CasesPlateau c32pRegular = new CasesPlateau(plateau3, caseDuel, 31);
		casesPlateauRegular.add(c32pRegular);
		CasesPlateau c33pRegular = new CasesPlateau(plateau3, casePioche, 32);
		casesPlateauRegular.add(c33pRegular);
		CasesPlateau c34pRegular = new CasesPlateau(plateau3, caseMechant, 33);
		casesPlateauRegular.add(c34pRegular);
		CasesPlateau c35pRegular = new CasesPlateau(plateau3, caseVide, 34);
		casesPlateauRegular.add(c35pRegular);
		CasesPlateau c36pRegular = new CasesPlateau(plateau3, caseGentil, 35);
		casesPlateauRegular.add(c36pRegular);
		CasesPlateau c37pRegular = new CasesPlateau(plateau3, caseDeplacement, 36);
		casesPlateauRegular.add(c37pRegular);
		CasesPlateau c38pRegular = new CasesPlateau(plateau3, casePioche, 37);
		casesPlateauRegular.add(c38pRegular);
		CasesPlateau c39pRegular = new CasesPlateau(plateau3, caseMechant, 38);
		casesPlateauRegular.add(c39pRegular);
		CasesPlateau c40pRegular = new CasesPlateau(plateau3, caseVide, 39);
		casesPlateauRegular.add(c40pRegular);
		CasesPlateau c41pRegular = new CasesPlateau(plateau3, caseGentil, 40);
		casesPlateauRegular.add(c41pRegular);
		CasesPlateau c42pRegular = new CasesPlateau(plateau3, caseDuel, 41);
		casesPlateauRegular.add(c42pRegular);
		CasesPlateau c43pRegular = new CasesPlateau(plateau3, casePioche, 42);
		casesPlateauRegular.add(c43pRegular);
		CasesPlateau c44pRegular = new CasesPlateau(plateau3, caseMechant, 43);
		casesPlateauRegular.add(c44pRegular);
		CasesPlateau c45pRegular = new CasesPlateau(plateau3, caseVide, 44);
		casesPlateauRegular.add(c45pRegular);
		CasesPlateau c46pRegular = new CasesPlateau(plateau3, caseGentil, 45);
		casesPlateauRegular.add(c46pRegular);
		CasesPlateau c47pRegular = new CasesPlateau(plateau3, caseDeplacement, 46);
		casesPlateauRegular.add(c47pRegular);
		CasesPlateau c48pRegular = new CasesPlateau(plateau3, casePioche, 47);
		casesPlateauRegular.add(c48pRegular);
		CasesPlateau c49pRegular = new CasesPlateau(plateau3, caseMechant, 48);
		casesPlateauRegular.add(c49pRegular);
		CasesPlateau c50pRegular = new CasesPlateau(plateau3, caseVide, 49);
		casesPlateauRegular.add(c50pRegular);
		CasesPlateau c51pRegular = new CasesPlateau(plateau3, caseGentil, 50);
		casesPlateauRegular.add(c51pRegular);
		CasesPlateau c52pRegular = new CasesPlateau(plateau3, caseDuel, 51);
		casesPlateauRegular.add(c52pRegular);
		CasesPlateau c53pRegular = new CasesPlateau(plateau3, casePioche, 52);
		casesPlateauRegular.add(c53pRegular);
		CasesPlateau c54pRegular = new CasesPlateau(plateau3, caseMechant, 53);
		casesPlateauRegular.add(c54pRegular);
		CasesPlateau c55pRegular = new CasesPlateau(plateau3, caseVide, 54);
		casesPlateauRegular.add(c55pRegular);
		CasesPlateau c56pRegular = new CasesPlateau(plateau3, caseGentil, 55);
		casesPlateauRegular.add(c56pRegular);
		CasesPlateau c57pRegular = new CasesPlateau(plateau3, caseDeplacement, 56);
		casesPlateauRegular.add(c57pRegular);
		CasesPlateau c58pRegular = new CasesPlateau(plateau3, casePioche, 57);
		casesPlateauRegular.add(c58pRegular);
		CasesPlateau c59pRegular = new CasesPlateau(plateau3, caseMechant, 58);
		casesPlateauRegular.add(c59pRegular);
		CasesPlateau c60pRegular = new CasesPlateau(plateau3, caseVide, 59);
		casesPlateauRegular.add(c60pRegular);
		CasesPlateau c61pRegular = new CasesPlateau(plateau3, caseGentil, 60);
		casesPlateauRegular.add(c61pRegular);
		CasesPlateau c62pRegular = new CasesPlateau(plateau3, caseDuel, 61);
		casesPlateauRegular.add(c62pRegular);
		CasesPlateau c63pRegular = new CasesPlateau(plateau3, caseArrivee, 62);
		casesPlateauRegular.add(c63pRegular);

		c1pRegular=casesPlateauRepo.save(c1pRegular);
		c2pRegular=casesPlateauRepo.save(c2pRegular);
		c3pRegular=casesPlateauRepo.save(c3pRegular);
		c4pRegular=casesPlateauRepo.save(c4pRegular);
		c5pRegular=casesPlateauRepo.save(c5pRegular);
		c6pRegular=casesPlateauRepo.save(c6pRegular);
		c7pRegular=casesPlateauRepo.save(c7pRegular);
		c8pRegular=casesPlateauRepo.save(c8pRegular);
		c9pRegular=casesPlateauRepo.save(c9pRegular);
		c10pRegular=casesPlateauRepo.save(c10pRegular);
		c11pRegular=casesPlateauRepo.save(c11pRegular);
		c12pRegular=casesPlateauRepo.save(c12pRegular);
		c13pRegular=casesPlateauRepo.save(c13pRegular);
		c14pRegular=casesPlateauRepo.save(c14pRegular);
		c15pRegular=casesPlateauRepo.save(c15pRegular);
		c16pRegular=casesPlateauRepo.save(c16pRegular);
		c17pRegular=casesPlateauRepo.save(c17pRegular);
		c18pRegular=casesPlateauRepo.save(c18pRegular);
		c19pRegular=casesPlateauRepo.save(c19pRegular);
		c20pRegular=casesPlateauRepo.save(c20pRegular);
		c21pRegular=casesPlateauRepo.save(c21pRegular);
		c22pRegular=casesPlateauRepo.save(c22pRegular);
		c23pRegular=casesPlateauRepo.save(c23pRegular);
		c24pRegular=casesPlateauRepo.save(c24pRegular);
		c25pRegular=casesPlateauRepo.save(c25pRegular);
		c26pRegular=casesPlateauRepo.save(c26pRegular);
		c27pRegular=casesPlateauRepo.save(c27pRegular);
		c28pRegular=casesPlateauRepo.save(c28pRegular);
		c29pRegular=casesPlateauRepo.save(c29pRegular);
		c30pRegular=casesPlateauRepo.save(c30pRegular);
		c31pRegular=casesPlateauRepo.save(c31pRegular);
		c32pRegular=casesPlateauRepo.save(c32pRegular);
		c33pRegular=casesPlateauRepo.save(c33pRegular);
		c34pRegular=casesPlateauRepo.save(c34pRegular);
		c35pRegular=casesPlateauRepo.save(c35pRegular);
		c36pRegular=casesPlateauRepo.save(c36pRegular);
		c37pRegular=casesPlateauRepo.save(c37pRegular);
		c38pRegular=casesPlateauRepo.save(c38pRegular);
		c39pRegular=casesPlateauRepo.save(c39pRegular);
		c40pRegular=casesPlateauRepo.save(c40pRegular);
		c41pRegular=casesPlateauRepo.save(c41pRegular);
		c42pRegular=casesPlateauRepo.save(c42pRegular);
		c43pRegular=casesPlateauRepo.save(c43pRegular);
		c44pRegular=casesPlateauRepo.save(c44pRegular);
		c45pRegular=casesPlateauRepo.save(c45pRegular);
		c46pRegular=casesPlateauRepo.save(c46pRegular);
		c47pRegular=casesPlateauRepo.save(c47pRegular);
		c48pRegular=casesPlateauRepo.save(c48pRegular);
		c49pRegular=casesPlateauRepo.save(c49pRegular);
		c50pRegular=casesPlateauRepo.save(c50pRegular);
		c51pRegular=casesPlateauRepo.save(c51pRegular);
		c52pRegular=casesPlateauRepo.save(c52pRegular);
		c53pRegular=casesPlateauRepo.save(c53pRegular);
		c54pRegular=casesPlateauRepo.save(c54pRegular);
		c55pRegular=casesPlateauRepo.save(c55pRegular);
		c56pRegular=casesPlateauRepo.save(c56pRegular);
		c57pRegular=casesPlateauRepo.save(c57pRegular);
		c58pRegular=casesPlateauRepo.save(c58pRegular);
		c59pRegular=casesPlateauRepo.save(c59pRegular);
		c60pRegular=casesPlateauRepo.save(c60pRegular);
		c61pRegular=casesPlateauRepo.save(c61pRegular);
		c62pRegular=casesPlateauRepo.save(c62pRegular);
		c63pRegular=casesPlateauRepo.save(c63pRegular);

		plateau3.setCases(casesPlateauRegular);

		plateau3=plateauRepo.save(plateau3);




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
