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
		Joueur ia3 = new Joueur("dingo.dingo@adresseMail.com","dingoTropFortDingo","Dingo");
		ia1 = joueurRepo.save(ia1);
		ia2 = joueurRepo.save(ia2);
		ia3 = joueurRepo.save(ia3);

		//JOUEURS
		Joueur joueur1 = new Joueur("joueur1", "1234", "Biscay", "Hugo", "hugo@gmail.com", "Princesse Saucisse", "débutant", 3);
		joueur1.setAvatar(avatar1);
		joueur1.setNbEtoiles(1000);
		joueur1=joueurRepo.save(joueur1);
		Joueur joueur2 = new Joueur("joueur2", "1234", "Champagne", "Justine", "justine@gmail.com", "Princesse Plateau", "dieu", 3);
		joueur2.setAvatar(avatar2);
		joueur2.setNbEtoiles(1000);
		joueur2=joueurRepo.save(joueur2);
		Joueur joueur3 = new Joueur("joueur3", "1234", "Cengiz", "Selin", "selin@gmail.com", "Princesse Presidente", "débutant", 3);
		joueur3.setAvatar(avatar11);
		joueur3.setNbEtoiles(1000);
		joueur3 = joueurRepo.save(joueur3);
		Joueur joueur7 = new Joueur("joueur7", "1234", "Bruno", "Anais", "anais@gmail.com", "Princesse Dors Peu", "débutant", 3);
		joueur7.setAvatar(avatar11);
		joueur7.setNbEtoiles(1000);
		joueur7 = joueurRepo.save(joueur7);

		Joueur joueur4 = new Joueur("MinnieDu44", "1234", "Minnie", "Mouse", "minnie.mouse@gmail.com", "Princesse Minnie", "expert", 15);
		joueur4.setNbEtoiles(5000);
		joueur4.setAvatar(avatar12);
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
		Personnage perso1 = new Personnage("Elsa", "Olaf", "Hans", "Glace",500);
		Personnage perso2 = new Personnage("Ariel", "Eric", "Ursula", "Eau",500);
		Personnage perso3 = new Personnage("Jasmine", "Aladdin", "Jafar", "Sable",1000);
		Personnage perso4 = new Personnage("Mulan", "Chang", "Shan-Yu", "Mushu",500);
		Personnage perso5 = new Personnage("Aurore", "Philippe", "Malefique", "Sommeil",1000);
		Personnage perso6 = new Personnage("Belle", "La Bête", "Gaston", "La Rose",5000);
		Personnage perso7 = new Personnage("Blanche-Neige", "Prince", "La méchante reine", "Chant",2500);
		Personnage perso8 = new Personnage("Cendrillon", "Prince charmant", "Mme de Trémaine", "La Bonne Fée",5000);
		Personnage perso9 = new Personnage("Raiponce", "Eugene", "Gothel", "Chevelure",500);
		Personnage perso10 = new Personnage("Tiana", "Ray", "Docteur Facilier", "Bisous",1000);
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
//		Cases caseDeplacement = new Cases("Deplacement",TypeCase.deplacement);
		Cases caseRecule = new Cases("Recule",TypeCase.deplacement);
		Cases caseAvance = new Cases("Avance",TypeCase.deplacement);		
		Cases caseDepart = new Cases("Depart",TypeCase.depart);
		Cases caseArrivee = new Cases("Arrivee",TypeCase.arrivee);
		Cases casePioche = new Cases("Pioche",TypeCase.pioche);
		caseMechant = casesRepo.save(caseMechant);
		caseGentil = casesRepo.save(caseGentil);
		casePrison = casesRepo.save(casePrison);
		caseVide = casesRepo.save(caseVide);
		caseDuel = casesRepo.save(caseDuel);
//		caseDeplacement = casesRepo.save(caseDeplacement);
		caseAvance = casesRepo.save(caseAvance);
		caseRecule = casesRepo.save(caseRecule);
		caseDepart = casesRepo.save(caseDepart);
		caseArrivee = casesRepo.save(caseArrivee);
		casePioche = casesRepo.save(casePioche);

		//BOUTIQUE
		final List<Vie> listeTotaleVie = new ArrayList<>();
		listeTotaleVie.add(new Vie(1, 500, "../../../assets/images/boutique/potion-1.png"));
		listeTotaleVie.add(new Vie(3, 1250, "../../../assets/images/boutique/potion-3.png"));
		listeTotaleVie.add(new Vie(5, 2400, "../../../assets/images/boutique/potion-5.png"));
		listeTotaleVie.add(new Vie(10, 4500, "../../../assets/images/boutique/potion-10.png"));
		vieRepo.saveAll(listeTotaleVie);

		final List<Etoile> listeTotaleEtoiles = new ArrayList<>();
		listeTotaleEtoiles.add(new Etoile(1000, 5, "../../../assets/images/boutique/etoile-1.png"));
		listeTotaleEtoiles.add(new Etoile(3000, 13, "../../../assets/images/boutique/etoile-3.png"));
		listeTotaleEtoiles.add(new Etoile(5000, 22, "../../../assets/images/boutique/etoile-5.png"));
		listeTotaleEtoiles.add(new Etoile(10000, 30, "../../../assets/images/boutique/etoile-10.png"));
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
		List <CasesPlateau> casesPlateauDemo = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pDemo = new CasesPlateau(plateau1, caseDepart, 0);
		casesPlateauDemo.add(c1pDemo);
		CasesPlateau c2pDemo = new CasesPlateau(plateau1, caseVide, 1);
		casesPlateauDemo.add(c2pDemo);
		CasesPlateau c3pDemo = new CasesPlateau(plateau1, caseAvance, 2, 1);
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
		CasesPlateau c9pDemo = new CasesPlateau(plateau1, caseRecule, 8 , -1);
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
		CasesPlateau c17pDemo = new CasesPlateau(plateau1, caseRecule, 16, -1);
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
		plateau1.setDisponible(false);

		plateau1=plateauRepo.save(plateau1);


		// plateau de test
		Plateau plateau2 = new Plateau ("Plateau Test", 10);
		plateau2.setDisponible(true);
		plateau2=plateauRepo.save(plateau2);


		//CasesPlateau
		List <CasesPlateau> casesPlateauTest = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pTest = new CasesPlateau(plateau2, caseDepart, 0);
		casesPlateauTest.add(c1pTest);
		CasesPlateau c2pTest = new CasesPlateau(plateau2, caseVide, 1);
		casesPlateauTest.add(c2pTest);
		CasesPlateau c3pTest = new CasesPlateau(plateau2, caseRecule, 2, -1);
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
		CasesPlateau c9pTest = new CasesPlateau(plateau2, caseAvance, 8, +1);
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
		Plateau plateau3 = new Plateau ("Aventure", 63);
		plateau3.setDisponible(true);
		plateau3=plateauRepo.save(plateau3);

		//CasesPlateau
		List <CasesPlateau> casesPlateauRegular = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pRegular = new CasesPlateau(plateau3, caseDepart, 0);
		casesPlateauRegular.add(c1pRegular);
		CasesPlateau c2pRegular = new CasesPlateau(plateau3, caseVide, 1);
		casesPlateauRegular.add(c2pRegular);
		CasesPlateau c3pRegular = new CasesPlateau(plateau3, caseRecule, 2, -1);
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
		CasesPlateau c9pRegular = new CasesPlateau(plateau3, caseAvance, 8, 1);
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
		CasesPlateau c17pRegular = new CasesPlateau(plateau3, caseAvance, 16, 1);
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
		CasesPlateau c27pRegular = new CasesPlateau(plateau3, caseRecule, 26, -1);
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
		CasesPlateau c37pRegular = new CasesPlateau(plateau3, caseAvance, 36, +1);
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
		CasesPlateau c47pRegular = new CasesPlateau(plateau3, caseRecule, 46, -1);
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
		CasesPlateau c57pRegular = new CasesPlateau(plateau3, caseRecule, 56, -1);
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
		
		
		//Plateau 4
		Plateau plateau4 = new Plateau ("Chemin semé d'embuches", 71);
		plateau4.setDisponible(true);
		plateau4=plateauRepo.save(plateau4);

		//CasesPlateau
		List <CasesPlateau> casesPlateauDifficile = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pDIfficile = new CasesPlateau(plateau4, caseDepart, 0);
		casesPlateauDifficile.add(c1pDIfficile);
		CasesPlateau c2pDIfficile = new CasesPlateau(plateau4, caseVide, 1);
		casesPlateauDifficile.add(c2pDIfficile);
		CasesPlateau c3pDIfficile = new CasesPlateau(plateau4, caseRecule, 2, -1);
		casesPlateauDifficile.add(c3pDIfficile);
		CasesPlateau c4pDIfficile = new CasesPlateau(plateau4, caseDuel, 3);
		casesPlateauDifficile.add(c4pDIfficile);
		CasesPlateau c5pDIfficile = new CasesPlateau(plateau4, casePioche, 4);
		casesPlateauDifficile.add(c5pDIfficile);
		CasesPlateau c6pDIfficile = new CasesPlateau(plateau4, casePrison, 5);
		casesPlateauDifficile.add(c6pDIfficile);
		CasesPlateau c7pDIfficile = new CasesPlateau(plateau4, caseGentil, 6);
		casesPlateauDifficile.add(c7pDIfficile);
		CasesPlateau c8pDIfficile = new CasesPlateau(plateau4, caseMechant, 7);
		casesPlateauDifficile.add(c8pDIfficile);
		CasesPlateau c9pDIfficile = new CasesPlateau(plateau4, caseAvance, 8, 1);
		casesPlateauDifficile.add(c9pDIfficile);
		CasesPlateau c10pDIfficile = new CasesPlateau(plateau4, caseVide, 9);
		casesPlateauDifficile.add(c10pDIfficile);
		CasesPlateau c11pDIfficile = new CasesPlateau(plateau4, caseGentil, 10);
		casesPlateauDifficile.add(c11pDIfficile);
		CasesPlateau c12pDIfficile = new CasesPlateau(plateau4, caseMechant, 11);
		casesPlateauDifficile.add(c12pDIfficile);
		CasesPlateau c13pDIfficile = new CasesPlateau(plateau4, casePioche, 12);
		casesPlateauDifficile.add(c13pDIfficile);
		CasesPlateau c14pDIfficile = new CasesPlateau(plateau4, caseMechant, 13);
		casesPlateauDifficile.add(c14pDIfficile);
		CasesPlateau c15pDIfficile = new CasesPlateau(plateau4, caseVide, 14);
		casesPlateauDifficile.add(c15pDIfficile);
		CasesPlateau c16pDIfficile = new CasesPlateau(plateau4, caseGentil, 15);
		casesPlateauDifficile.add(c16pDIfficile);
		CasesPlateau c17pDIfficile = new CasesPlateau(plateau4, caseAvance, 16, 1);
		casesPlateauDifficile.add(c17pDIfficile);
		CasesPlateau c18pDIfficile = new CasesPlateau(plateau4, caseRecule, 17, -1);
		casesPlateauDifficile.add(c18pDIfficile);
		CasesPlateau c19pDIfficile = new CasesPlateau(plateau4, caseMechant, 18);
		casesPlateauDifficile.add(c19pDIfficile);
		CasesPlateau c20pDIfficile = new CasesPlateau(plateau4, caseVide, 19);
		casesPlateauDifficile.add(c20pDIfficile);
		CasesPlateau c21pDIfficile = new CasesPlateau(plateau4, caseGentil, 20);
		casesPlateauDifficile.add(c21pDIfficile);
		CasesPlateau c22pDIfficile = new CasesPlateau(plateau4, caseDuel, 21);
		casesPlateauDifficile.add(c22pDIfficile);
		CasesPlateau c23pDIfficile = new CasesPlateau(plateau4, casePioche, 22);
		casesPlateauDifficile.add(c23pDIfficile);
		CasesPlateau c24pDIfficile = new CasesPlateau(plateau4, caseMechant, 23);
		casesPlateauDifficile.add(c24pDIfficile);
		CasesPlateau c25pDIfficile = new CasesPlateau(plateau4, caseVide, 24);
		casesPlateauDifficile.add(c25pDIfficile);
		CasesPlateau c26pDIfficile = new CasesPlateau(plateau4, caseGentil, 25);
		casesPlateauDifficile.add(c26pDIfficile);
		CasesPlateau c27pDIfficile = new CasesPlateau(plateau4, caseRecule, 26, -1);
		casesPlateauDifficile.add(c27pDIfficile);
		CasesPlateau c28pDIfficile = new CasesPlateau(plateau4, casePrison, 27);
		casesPlateauDifficile.add(c28pDIfficile);
		CasesPlateau c29pDIfficile = new CasesPlateau(plateau4, caseMechant, 28);
		casesPlateauDifficile.add(c29pDIfficile);
		CasesPlateau c30pDIfficile = new CasesPlateau(plateau4, caseRecule, 29, -1);
		casesPlateauDifficile.add(c30pDIfficile);
		CasesPlateau c31pDIfficile = new CasesPlateau(plateau4, caseMechant, 30);
		casesPlateauDifficile.add(c31pDIfficile);
		CasesPlateau c32pDIfficile = new CasesPlateau(plateau4, caseDuel, 31);
		casesPlateauDifficile.add(c32pDIfficile);
		CasesPlateau c33pDIfficile = new CasesPlateau(plateau4, casePioche, 32);
		casesPlateauDifficile.add(c33pDIfficile);
		CasesPlateau c34pDIfficile = new CasesPlateau(plateau4, caseMechant, 33);
		casesPlateauDifficile.add(c34pDIfficile);
		CasesPlateau c35pDIfficile = new CasesPlateau(plateau4, caseVide, 34);
		casesPlateauDifficile.add(c35pDIfficile);
		CasesPlateau c36pDIfficile = new CasesPlateau(plateau4, caseGentil, 35);
		casesPlateauDifficile.add(c36pDIfficile);
		CasesPlateau c37pDIfficile = new CasesPlateau(plateau4, caseAvance, 36, +1);
		casesPlateauDifficile.add(c37pDIfficile);
		CasesPlateau c38pDIfficile = new CasesPlateau(plateau4, casePrison, 37);
		casesPlateauDifficile.add(c38pDIfficile);
		CasesPlateau c39pDIfficile = new CasesPlateau(plateau4, caseMechant, 38);
		casesPlateauDifficile.add(c39pDIfficile);
		CasesPlateau c40pDIfficile = new CasesPlateau(plateau4, caseVide, 39);
		casesPlateauDifficile.add(c40pDIfficile);
		CasesPlateau c41pDIfficile = new CasesPlateau(plateau4, caseGentil, 40);
		casesPlateauDifficile.add(c41pDIfficile);
		CasesPlateau c42pDIfficile = new CasesPlateau(plateau4, caseDuel, 41);
		casesPlateauDifficile.add(c42pDIfficile);
		CasesPlateau c43pDIfficile = new CasesPlateau(plateau4, casePioche, 42);
		casesPlateauDifficile.add(c43pDIfficile);
		CasesPlateau c44pDIfficile = new CasesPlateau(plateau4, caseMechant, 43);
		casesPlateauDifficile.add(c44pDIfficile);
		CasesPlateau c45pDIfficile = new CasesPlateau(plateau4, caseVide, 44);
		casesPlateauDifficile.add(c45pDIfficile);
		CasesPlateau c46pDIfficile = new CasesPlateau(plateau4, caseGentil, 45);
		casesPlateauDifficile.add(c46pDIfficile);
		CasesPlateau c47pDIfficile = new CasesPlateau(plateau4, caseRecule, 46, -1);
		casesPlateauDifficile.add(c47pDIfficile);
		CasesPlateau c48pDIfficile = new CasesPlateau(plateau4, casePioche, 47);
		casesPlateauDifficile.add(c48pDIfficile);
		CasesPlateau c49pDIfficile = new CasesPlateau(plateau4, caseMechant, 48);
		casesPlateauDifficile.add(c49pDIfficile);
		CasesPlateau c50pDIfficile = new CasesPlateau(plateau4, caseVide, 49);
		casesPlateauDifficile.add(c50pDIfficile);
		CasesPlateau c51pDIfficile = new CasesPlateau(plateau4, caseGentil, 50);
		casesPlateauDifficile.add(c51pDIfficile);
		CasesPlateau c52pDIfficile = new CasesPlateau(plateau4, caseDuel, 51);
		casesPlateauDifficile.add(c52pDIfficile);
		CasesPlateau c53pDIfficile = new CasesPlateau(plateau4, casePioche, 52);
		casesPlateauDifficile.add(c53pDIfficile);
		CasesPlateau c54pDIfficile = new CasesPlateau(plateau4, caseMechant, 53);
		casesPlateauDifficile.add(c54pDIfficile);
		CasesPlateau c55pDIfficile = new CasesPlateau(plateau4, caseVide, 54);
		casesPlateauDifficile.add(c55pDIfficile);
		CasesPlateau c56pDIfficile = new CasesPlateau(plateau4, caseGentil, 55);
		casesPlateauDifficile.add(c56pDIfficile);
		CasesPlateau c57pDIfficile = new CasesPlateau(plateau4, caseRecule, 56, -1);
		casesPlateauDifficile.add(c57pDIfficile);
		CasesPlateau c58pDIfficile = new CasesPlateau(plateau4, casePioche, 57);
		casesPlateauDifficile.add(c58pDIfficile);
		CasesPlateau c59pDIfficile = new CasesPlateau(plateau4, caseMechant, 58);
		casesPlateauDifficile.add(c59pDIfficile);
		CasesPlateau c60pDIfficile = new CasesPlateau(plateau4, caseVide, 59);
		casesPlateauDifficile.add(c60pDIfficile);
		CasesPlateau c61pDIfficile = new CasesPlateau(plateau4, caseGentil, 60);
		casesPlateauDifficile.add(c61pDIfficile);
		CasesPlateau c62pDIfficile = new CasesPlateau(plateau4, caseDuel, 61);
		casesPlateauDifficile.add(c62pDIfficile);
		CasesPlateau c63pDIfficile = new CasesPlateau(plateau4, caseVide, 62);
		casesPlateauDifficile.add(c63pDIfficile);
        CasesPlateau c64pDIfficile = new CasesPlateau(plateau4, casePioche, 63);
		casesPlateauDifficile.add(c64pDIfficile);
		CasesPlateau c65pDIfficile = new CasesPlateau(plateau4, caseMechant, 64);
		casesPlateauDifficile.add(c65pDIfficile);
		CasesPlateau c66pDIfficile = new CasesPlateau(plateau4, caseVide, 65);
		casesPlateauDifficile.add(c66pDIfficile);
		CasesPlateau c67pDIfficile = new CasesPlateau(plateau4, caseMechant, 66);
		casesPlateauDifficile.add(c67pDIfficile);
		CasesPlateau c68pDIfficile = new CasesPlateau(plateau4, caseDuel, 67);
		casesPlateauDifficile.add(c68pDIfficile);
		CasesPlateau c69pDIfficile = new CasesPlateau(plateau4, caseArrivee, 68);
		casesPlateauDifficile.add(c69pDIfficile);
        CasesPlateau c70pDIfficile = new CasesPlateau(plateau4, caseDuel, 69);
		casesPlateauDifficile.add(c70pDIfficile);
		CasesPlateau c71pDIfficile = new CasesPlateau(plateau4, caseArrivee, 70);
		casesPlateauDifficile.add(c71pDIfficile);

		c1pDIfficile=casesPlateauRepo.save(c1pDIfficile);
		c2pDIfficile=casesPlateauRepo.save(c2pDIfficile);
		c3pDIfficile=casesPlateauRepo.save(c3pDIfficile);
		c4pDIfficile=casesPlateauRepo.save(c4pDIfficile);
		c5pDIfficile=casesPlateauRepo.save(c5pDIfficile);
		c6pDIfficile=casesPlateauRepo.save(c6pDIfficile);
		c7pDIfficile=casesPlateauRepo.save(c7pDIfficile);
		c8pDIfficile=casesPlateauRepo.save(c8pDIfficile);
		c9pDIfficile=casesPlateauRepo.save(c9pDIfficile);
		c10pDIfficile=casesPlateauRepo.save(c10pDIfficile);
		c11pDIfficile=casesPlateauRepo.save(c11pDIfficile);
		c12pDIfficile=casesPlateauRepo.save(c12pDIfficile);
		c13pDIfficile=casesPlateauRepo.save(c13pDIfficile);
		c14pDIfficile=casesPlateauRepo.save(c14pDIfficile);
		c15pDIfficile=casesPlateauRepo.save(c15pDIfficile);
		c16pDIfficile=casesPlateauRepo.save(c16pDIfficile);
		c17pDIfficile=casesPlateauRepo.save(c17pDIfficile);
		c18pDIfficile=casesPlateauRepo.save(c18pDIfficile);
		c19pDIfficile=casesPlateauRepo.save(c19pDIfficile);
		c20pDIfficile=casesPlateauRepo.save(c20pDIfficile);
		c21pDIfficile=casesPlateauRepo.save(c21pDIfficile);
		c22pDIfficile=casesPlateauRepo.save(c22pDIfficile);
		c23pDIfficile=casesPlateauRepo.save(c23pDIfficile);
		c24pDIfficile=casesPlateauRepo.save(c24pDIfficile);
		c25pDIfficile=casesPlateauRepo.save(c25pDIfficile);
		c26pDIfficile=casesPlateauRepo.save(c26pDIfficile);
		c27pDIfficile=casesPlateauRepo.save(c27pDIfficile);
		c28pDIfficile=casesPlateauRepo.save(c28pDIfficile);
		c29pDIfficile=casesPlateauRepo.save(c29pDIfficile);
		c30pDIfficile=casesPlateauRepo.save(c30pDIfficile);
		c31pDIfficile=casesPlateauRepo.save(c31pDIfficile);
		c32pDIfficile=casesPlateauRepo.save(c32pDIfficile);
		c33pDIfficile=casesPlateauRepo.save(c33pDIfficile);
		c34pDIfficile=casesPlateauRepo.save(c34pDIfficile);
		c35pDIfficile=casesPlateauRepo.save(c35pDIfficile);
		c36pDIfficile=casesPlateauRepo.save(c36pDIfficile);
		c37pDIfficile=casesPlateauRepo.save(c37pDIfficile);
		c38pDIfficile=casesPlateauRepo.save(c38pDIfficile);
		c39pDIfficile=casesPlateauRepo.save(c39pDIfficile);
		c40pDIfficile=casesPlateauRepo.save(c40pDIfficile);
		c41pDIfficile=casesPlateauRepo.save(c41pDIfficile);
		c42pDIfficile=casesPlateauRepo.save(c42pDIfficile);
		c43pDIfficile=casesPlateauRepo.save(c43pDIfficile);
		c44pDIfficile=casesPlateauRepo.save(c44pDIfficile);
		c45pDIfficile=casesPlateauRepo.save(c45pDIfficile);
		c46pDIfficile=casesPlateauRepo.save(c46pDIfficile);
		c47pDIfficile=casesPlateauRepo.save(c47pDIfficile);
		c48pDIfficile=casesPlateauRepo.save(c48pDIfficile);
		c49pDIfficile=casesPlateauRepo.save(c49pDIfficile);
		c50pDIfficile=casesPlateauRepo.save(c50pDIfficile);
		c51pDIfficile=casesPlateauRepo.save(c51pDIfficile);
		c52pDIfficile=casesPlateauRepo.save(c52pDIfficile);
		c53pDIfficile=casesPlateauRepo.save(c53pDIfficile);
		c54pDIfficile=casesPlateauRepo.save(c54pDIfficile);
		c55pDIfficile=casesPlateauRepo.save(c55pDIfficile);
		c56pDIfficile=casesPlateauRepo.save(c56pDIfficile);
		c57pDIfficile=casesPlateauRepo.save(c57pDIfficile);
		c58pDIfficile=casesPlateauRepo.save(c58pDIfficile);
		c59pDIfficile=casesPlateauRepo.save(c59pDIfficile);
		c60pDIfficile=casesPlateauRepo.save(c60pDIfficile);
		c61pDIfficile=casesPlateauRepo.save(c61pDIfficile);
		c62pDIfficile=casesPlateauRepo.save(c62pDIfficile);
        c63pDIfficile=casesPlateauRepo.save(c63pDIfficile);
        c64pDIfficile=casesPlateauRepo.save(c64pDIfficile);
		c65pDIfficile=casesPlateauRepo.save(c65pDIfficile);
		c66pDIfficile=casesPlateauRepo.save(c66pDIfficile);
		c67pDIfficile=casesPlateauRepo.save(c67pDIfficile);
		c68pDIfficile=casesPlateauRepo.save(c68pDIfficile);
        c69pDIfficile=casesPlateauRepo.save(c69pDIfficile);
		c70pDIfficile=casesPlateauRepo.save(c70pDIfficile);
        c71pDIfficile=casesPlateauRepo.save(c71pDIfficile);


		plateau4.setCases(casesPlateauDifficile);
		plateau4=plateauRepo.save(plateau4);

		

		//Plateau 5
		Plateau plateau5 = new Plateau ("Forêt paisible",71);
		plateau5.setDisponible(true);
		plateau5=plateauRepo.save(plateau5);

		//CasesPlateau
		List <CasesPlateau> casesPlateauFacile = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pFacile = new CasesPlateau(plateau5, caseDepart, 0);
		casesPlateauFacile.add(c1pFacile);
		CasesPlateau c2pFacile = new CasesPlateau(plateau5, caseVide, 1);
		casesPlateauFacile.add(c2pFacile);
		CasesPlateau c3pFacile = new CasesPlateau(plateau5, caseAvance, 2, 1);
		casesPlateauFacile.add(c3pFacile);
		CasesPlateau c4pFacile = new CasesPlateau(plateau5, caseDuel, 3);
		casesPlateauFacile.add(c4pFacile);
		CasesPlateau c5pFacile = new CasesPlateau(plateau5, casePioche, 4);
		casesPlateauFacile.add(c5pFacile);
		CasesPlateau c6pFacile = new CasesPlateau(plateau5, caseVide, 5);
		casesPlateauFacile.add(c6pFacile);
		CasesPlateau c7pFacile = new CasesPlateau(plateau5, caseMechant, 6);
		casesPlateauFacile.add(c7pFacile);
		CasesPlateau c8pFacile = new CasesPlateau(plateau5, caseGentil, 7);
		casesPlateauFacile.add(c8pFacile);
		CasesPlateau c9pFacile = new CasesPlateau(plateau5, caseRecule, 8, -1);
		casesPlateauFacile.add(c9pFacile);
		CasesPlateau c10pFacile = new CasesPlateau(plateau5, caseVide, 9);
		casesPlateauFacile.add(c10pFacile);
		CasesPlateau c11pFacile = new CasesPlateau(plateau5, caseMechant, 10);
		casesPlateauFacile.add(c11pFacile);
		CasesPlateau c12pFacile = new CasesPlateau(plateau5, caseGentil, 11);
		casesPlateauFacile.add(c12pFacile);
		CasesPlateau c13pFacile = new CasesPlateau(plateau5, casePioche, 12);
		casesPlateauFacile.add(c13pFacile);
		CasesPlateau c14pFacile = new CasesPlateau(plateau5, caseGentil, 13);
		casesPlateauFacile.add(c14pFacile);
		CasesPlateau c15pFacile = new CasesPlateau(plateau5, caseVide, 14);
		casesPlateauFacile.add(c15pFacile);
		CasesPlateau c16pFacile = new CasesPlateau(plateau5, caseMechant, 15);
		casesPlateauFacile.add(c16pFacile);
		CasesPlateau c17pFacile = new CasesPlateau(plateau5, caseRecule, 16, -1);
		casesPlateauFacile.add(c17pFacile);
		CasesPlateau c18pFacile = new CasesPlateau(plateau5, caseAvance, 17, 1);
		casesPlateauFacile.add(c18pFacile);
		CasesPlateau c19pFacile = new CasesPlateau(plateau5, caseGentil, 18);
		casesPlateauFacile.add(c19pFacile);
		CasesPlateau c20pFacile = new CasesPlateau(plateau5, caseVide, 19);
		casesPlateauFacile.add(c20pFacile);
		CasesPlateau c21pFacile = new CasesPlateau(plateau5, caseMechant, 20);
		casesPlateauFacile.add(c21pFacile);
		CasesPlateau c22pFacile = new CasesPlateau(plateau5, caseDuel, 21);
		casesPlateauFacile.add(c22pFacile);
		CasesPlateau c23pFacile = new CasesPlateau(plateau5, casePioche, 22);
		casesPlateauFacile.add(c23pFacile);
		CasesPlateau c24pFacile = new CasesPlateau(plateau5, caseGentil, 23);
		casesPlateauFacile.add(c24pFacile);
		CasesPlateau c25pFacile = new CasesPlateau(plateau5, caseVide, 24);
		casesPlateauFacile.add(c25pFacile);
		CasesPlateau c26pFacile = new CasesPlateau(plateau5, caseMechant, 25);
		casesPlateauFacile.add(c26pFacile);
		CasesPlateau c27pFacile = new CasesPlateau(plateau5, caseAvance, 26, 1);
		casesPlateauFacile.add(c27pFacile);
		CasesPlateau c28pFacile = new CasesPlateau(plateau5, casePrison, 27);
		casesPlateauFacile.add(c28pFacile);
		CasesPlateau c29pFacile = new CasesPlateau(plateau5, caseGentil, 28);
		casesPlateauFacile.add(c29pFacile);
		CasesPlateau c30pFacile = new CasesPlateau(plateau5, caseAvance, 29, 1);
		casesPlateauFacile.add(c30pFacile);
		CasesPlateau c31pFacile = new CasesPlateau(plateau5, caseGentil, 30);
		casesPlateauFacile.add(c31pFacile);
		CasesPlateau c32pFacile = new CasesPlateau(plateau5, caseDuel, 31);
		casesPlateauFacile.add(c32pFacile);
		CasesPlateau c33pFacile = new CasesPlateau(plateau5, casePioche, 32);
		casesPlateauFacile.add(c33pFacile);
		CasesPlateau c34pFacile = new CasesPlateau(plateau5, caseGentil, 33);
		casesPlateauFacile.add(c34pFacile);
		CasesPlateau c35pFacile = new CasesPlateau(plateau5, caseVide, 34);
		casesPlateauFacile.add(c35pFacile);
		CasesPlateau c36pFacile = new CasesPlateau(plateau5, caseMechant, 35);
		casesPlateauFacile.add(c36pFacile);
		CasesPlateau c37pFacile = new CasesPlateau(plateau5, caseRecule, 36, -1);
		casesPlateauFacile.add(c37pFacile);
		CasesPlateau c38pFacile = new CasesPlateau(plateau5, caseVide, 37);
		casesPlateauFacile.add(c38pFacile);
		CasesPlateau c39pFacile = new CasesPlateau(plateau5, caseGentil, 38);
		casesPlateauFacile.add(c39pFacile);
		CasesPlateau c40pFacile = new CasesPlateau(plateau5, caseVide, 39);
		casesPlateauFacile.add(c40pFacile);
		CasesPlateau c41pFacile = new CasesPlateau(plateau5, caseMechant, 40);
		casesPlateauFacile.add(c41pFacile);
		CasesPlateau c42pFacile = new CasesPlateau(plateau5, caseDuel, 41);
		casesPlateauFacile.add(c42pFacile);
		CasesPlateau c43pFacile = new CasesPlateau(plateau5, casePioche, 42);
		casesPlateauFacile.add(c43pFacile);
		CasesPlateau c44pFacile = new CasesPlateau(plateau5, caseGentil, 43);
		casesPlateauFacile.add(c44pFacile);
		CasesPlateau c45pFacile = new CasesPlateau(plateau5, caseVide, 44);
		casesPlateauFacile.add(c45pFacile);
		CasesPlateau c46pFacile = new CasesPlateau(plateau5, caseMechant, 45);
		casesPlateauFacile.add(c46pFacile);
		CasesPlateau c47pFacile = new CasesPlateau(plateau5, caseAvance, 46, 1);
		casesPlateauFacile.add(c47pFacile);
		CasesPlateau c48pFacile = new CasesPlateau(plateau5, casePioche, 47);
		casesPlateauFacile.add(c48pFacile);
		CasesPlateau c49pFacile = new CasesPlateau(plateau5, caseGentil, 48);
		casesPlateauFacile.add(c49pFacile);
		CasesPlateau c50pFacile = new CasesPlateau(plateau5, caseVide, 49);
		casesPlateauFacile.add(c50pFacile);
		CasesPlateau c51pFacile = new CasesPlateau(plateau5, caseMechant, 50);
		casesPlateauFacile.add(c51pFacile);
		CasesPlateau c52pFacile = new CasesPlateau(plateau5, caseDuel, 51);
		casesPlateauFacile.add(c52pFacile);
		CasesPlateau c53pFacile = new CasesPlateau(plateau5, casePioche, 52);
		casesPlateauFacile.add(c53pFacile);
		CasesPlateau c54pFacile = new CasesPlateau(plateau5, caseGentil, 53);
		casesPlateauFacile.add(c54pFacile);
		CasesPlateau c55pFacile = new CasesPlateau(plateau5, caseVide, 54);
		casesPlateauFacile.add(c55pFacile);
		CasesPlateau c56pFacile = new CasesPlateau(plateau5, caseMechant, 55);
		casesPlateauFacile.add(c56pFacile);
		CasesPlateau c57pFacile = new CasesPlateau(plateau5, caseAvance, 56, 1);
		casesPlateauFacile.add(c57pFacile);
		CasesPlateau c58pFacile = new CasesPlateau(plateau5, casePioche, 57);
		casesPlateauFacile.add(c58pFacile);
		CasesPlateau c59pFacile = new CasesPlateau(plateau5, caseGentil, 58);
		casesPlateauFacile.add(c59pFacile);
		CasesPlateau c60pFacile = new CasesPlateau(plateau5, caseVide, 59);
		casesPlateauFacile.add(c60pFacile);
		CasesPlateau c61pFacile = new CasesPlateau(plateau5, caseMechant, 60);
		casesPlateauFacile.add(c61pFacile);
		CasesPlateau c62pFacile = new CasesPlateau(plateau5, caseDuel, 61);
		casesPlateauFacile.add(c62pFacile);
		CasesPlateau c63pFacile = new CasesPlateau(plateau5, caseVide, 62);
		casesPlateauFacile.add(c63pFacile);
        CasesPlateau c64pFacile = new CasesPlateau(plateau5, casePioche, 63);
		casesPlateauFacile.add(c64pFacile);
		CasesPlateau c65pFacile = new CasesPlateau(plateau5, caseGentil, 64);
		casesPlateauFacile.add(c65pFacile);
		CasesPlateau c66pFacile = new CasesPlateau(plateau5, caseVide, 65);
		casesPlateauFacile.add(c66pFacile);
		CasesPlateau c67pFacile = new CasesPlateau(plateau5, caseGentil, 66);
		casesPlateauFacile.add(c67pFacile);
		CasesPlateau c68pFacile = new CasesPlateau(plateau5, caseDuel, 67);
		casesPlateauFacile.add(c68pFacile);
		CasesPlateau c69pFacile = new CasesPlateau(plateau5, caseArrivee, 68);
		casesPlateauFacile.add(c69pFacile);
        CasesPlateau c70pFacile = new CasesPlateau(plateau5, caseDuel, 69);
		casesPlateauFacile.add(c70pFacile);
		CasesPlateau c71pFacile = new CasesPlateau(plateau5, caseArrivee, 70);
		casesPlateauFacile.add(c71pFacile);

		c1pFacile=casesPlateauRepo.save(c1pFacile);
		c2pFacile=casesPlateauRepo.save(c2pFacile);
		c3pFacile=casesPlateauRepo.save(c3pFacile);
		c4pFacile=casesPlateauRepo.save(c4pFacile);
		c5pFacile=casesPlateauRepo.save(c5pFacile);
		c6pFacile=casesPlateauRepo.save(c6pFacile);
		c7pFacile=casesPlateauRepo.save(c7pFacile);
		c8pFacile=casesPlateauRepo.save(c8pFacile);
		c9pFacile=casesPlateauRepo.save(c9pFacile);
		c10pFacile=casesPlateauRepo.save(c10pFacile);
		c11pFacile=casesPlateauRepo.save(c11pFacile);
		c12pFacile=casesPlateauRepo.save(c12pFacile);
		c13pFacile=casesPlateauRepo.save(c13pFacile);
		c14pFacile=casesPlateauRepo.save(c14pFacile);
		c15pFacile=casesPlateauRepo.save(c15pFacile);
		c16pFacile=casesPlateauRepo.save(c16pFacile);
		c17pFacile=casesPlateauRepo.save(c17pFacile);
		c18pFacile=casesPlateauRepo.save(c18pFacile);
		c19pFacile=casesPlateauRepo.save(c19pFacile);
		c20pFacile=casesPlateauRepo.save(c20pFacile);
		c21pFacile=casesPlateauRepo.save(c21pFacile);
		c22pFacile=casesPlateauRepo.save(c22pFacile);
		c23pFacile=casesPlateauRepo.save(c23pFacile);
		c24pFacile=casesPlateauRepo.save(c24pFacile);
		c25pFacile=casesPlateauRepo.save(c25pFacile);
		c26pFacile=casesPlateauRepo.save(c26pFacile);
		c27pFacile=casesPlateauRepo.save(c27pFacile);
		c28pFacile=casesPlateauRepo.save(c28pFacile);
		c29pFacile=casesPlateauRepo.save(c29pFacile);
		c30pFacile=casesPlateauRepo.save(c30pFacile);
		c31pFacile=casesPlateauRepo.save(c31pFacile);
		c32pFacile=casesPlateauRepo.save(c32pFacile);
		c33pFacile=casesPlateauRepo.save(c33pFacile);
		c34pFacile=casesPlateauRepo.save(c34pFacile);
		c35pFacile=casesPlateauRepo.save(c35pFacile);
		c36pFacile=casesPlateauRepo.save(c36pFacile);
		c37pFacile=casesPlateauRepo.save(c37pFacile);
		c38pFacile=casesPlateauRepo.save(c38pFacile);
		c39pFacile=casesPlateauRepo.save(c39pFacile);
		c40pFacile=casesPlateauRepo.save(c40pFacile);
		c41pFacile=casesPlateauRepo.save(c41pFacile);
		c42pFacile=casesPlateauRepo.save(c42pFacile);
		c43pFacile=casesPlateauRepo.save(c43pFacile);
		c44pFacile=casesPlateauRepo.save(c44pFacile);
		c45pFacile=casesPlateauRepo.save(c45pFacile);
		c46pFacile=casesPlateauRepo.save(c46pFacile);
		c47pFacile=casesPlateauRepo.save(c47pFacile);
		c48pFacile=casesPlateauRepo.save(c48pFacile);
		c49pFacile=casesPlateauRepo.save(c49pFacile);
		c50pFacile=casesPlateauRepo.save(c50pFacile);
		c51pFacile=casesPlateauRepo.save(c51pFacile);
		c52pFacile=casesPlateauRepo.save(c52pFacile);
		c53pFacile=casesPlateauRepo.save(c53pFacile);
		c54pFacile=casesPlateauRepo.save(c54pFacile);
		c55pFacile=casesPlateauRepo.save(c55pFacile);
		c56pFacile=casesPlateauRepo.save(c56pFacile);
		c57pFacile=casesPlateauRepo.save(c57pFacile);
		c58pFacile=casesPlateauRepo.save(c58pFacile);
		c59pFacile=casesPlateauRepo.save(c59pFacile);
		c60pFacile=casesPlateauRepo.save(c60pFacile);
		c61pFacile=casesPlateauRepo.save(c61pFacile);
		c62pFacile=casesPlateauRepo.save(c62pFacile);
        c63pFacile=casesPlateauRepo.save(c63pFacile);
        c64pFacile=casesPlateauRepo.save(c64pFacile);
		c65pFacile=casesPlateauRepo.save(c65pFacile);
		c66pFacile=casesPlateauRepo.save(c66pFacile);
		c67pFacile=casesPlateauRepo.save(c67pFacile);
		c68pFacile=casesPlateauRepo.save(c68pFacile);
        c69pFacile=casesPlateauRepo.save(c69pFacile);
		c70pFacile=casesPlateauRepo.save(c70pFacile);
        c71pFacile=casesPlateauRepo.save(c71pFacile);


		plateau5.setCases(casesPlateauFacile);
		plateau5=plateauRepo.save(plateau5);

		

		//Plateau 6
		Plateau Plateau6 = new Plateau ("Escapade", 30);
		Plateau6.setDisponible(true);
		Plateau6=plateauRepo.save(Plateau6);

		//CasesPlateau
		List <CasesPlateau> casesPlateauRapide = new ArrayList<CasesPlateau>();
		
		CasesPlateau c1pRapide = new CasesPlateau(Plateau6, caseDepart, 0);
		casesPlateauRapide.add(c1pRapide);
		CasesPlateau c2pRapide = new CasesPlateau(Plateau6, caseVide, 1);
		casesPlateauRapide.add(c2pRapide);
		CasesPlateau c3pRapide = new CasesPlateau(Plateau6, caseAvance, 2, 1);
		casesPlateauRapide.add(c3pRapide);
		CasesPlateau c4pRapide = new CasesPlateau(Plateau6, caseDuel, 3);
		casesPlateauRapide.add(c4pRapide);
		CasesPlateau c5pRapide = new CasesPlateau(Plateau6, casePioche, 4);
		casesPlateauRapide.add(c5pRapide);
		CasesPlateau c6pRapide = new CasesPlateau(Plateau6, caseVide, 5);
		casesPlateauRapide.add(c6pRapide);
		CasesPlateau c7pRapide = new CasesPlateau(Plateau6, caseMechant, 6);
		casesPlateauRapide.add(c7pRapide);
		CasesPlateau c8pRapide = new CasesPlateau(Plateau6, caseGentil, 7);
		casesPlateauRapide.add(c8pRapide);
		CasesPlateau c9pRapide = new CasesPlateau(Plateau6, caseRecule, 8, -1);
		casesPlateauRapide.add(c9pRapide);
		CasesPlateau c10pRapide = new CasesPlateau(Plateau6, caseVide, 9);
		casesPlateauRapide.add(c10pRapide);
		CasesPlateau c11pRapide = new CasesPlateau(Plateau6, caseMechant, 10);
		casesPlateauRapide.add(c11pRapide);
		CasesPlateau c12pRapide = new CasesPlateau(Plateau6, caseGentil, 11);
		casesPlateauRapide.add(c12pRapide);
		CasesPlateau c13pRapide = new CasesPlateau(Plateau6, casePioche, 12);
		casesPlateauRapide.add(c13pRapide);
		CasesPlateau c14pRapide = new CasesPlateau(Plateau6, caseGentil, 13);
		casesPlateauRapide.add(c14pRapide);
		CasesPlateau c15pRapide = new CasesPlateau(Plateau6, caseVide, 14);
		casesPlateauRapide.add(c15pRapide);
		CasesPlateau c16pRapide = new CasesPlateau(Plateau6, caseMechant, 15);
		casesPlateauRapide.add(c16pRapide);
		CasesPlateau c17pRapide = new CasesPlateau(Plateau6, caseRecule, 16, -1);
		casesPlateauRapide.add(c17pRapide);
		CasesPlateau c18pRapide = new CasesPlateau(Plateau6, caseAvance, 17, 1);
		casesPlateauRapide.add(c18pRapide);
		CasesPlateau c19pRapide = new CasesPlateau(Plateau6, caseGentil, 18);
		casesPlateauRapide.add(c19pRapide);
		CasesPlateau c20pRapide = new CasesPlateau(Plateau6, caseVide, 19);
		casesPlateauRapide.add(c20pRapide);
		CasesPlateau c21pRapide = new CasesPlateau(Plateau6, caseMechant, 20);
		casesPlateauRapide.add(c21pRapide);
		CasesPlateau c22pRapide = new CasesPlateau(Plateau6, caseDuel, 21);
		casesPlateauRapide.add(c22pRapide);
		CasesPlateau c23pRapide = new CasesPlateau(Plateau6, casePioche, 22);
		casesPlateauRapide.add(c23pRapide);
		CasesPlateau c24pRapide = new CasesPlateau(Plateau6, caseGentil, 23);
		casesPlateauRapide.add(c24pRapide);
		CasesPlateau c25pRapide = new CasesPlateau(Plateau6, caseVide, 24);
		casesPlateauRapide.add(c25pRapide);
		CasesPlateau c26pRapide = new CasesPlateau(Plateau6, caseMechant, 25);
		casesPlateauRapide.add(c26pRapide);
		CasesPlateau c27pRapide = new CasesPlateau(Plateau6, caseAvance, 26, 1);
		casesPlateauRapide.add(c27pRapide);
		CasesPlateau c28pRapide = new CasesPlateau(Plateau6, casePrison, 27);
		casesPlateauRapide.add(c28pRapide);
		CasesPlateau c29pRapide = new CasesPlateau(Plateau6, caseGentil, 28);
		casesPlateauRapide.add(c29pRapide);
		CasesPlateau c30pRapide = new CasesPlateau(Plateau6, caseArrivee, 29);
		casesPlateauRapide.add(c30pRapide);

		c1pRapide=casesPlateauRepo.save(c1pRapide);
		c2pRapide=casesPlateauRepo.save(c2pRapide);
		c3pRapide=casesPlateauRepo.save(c3pRapide);
		c4pRapide=casesPlateauRepo.save(c4pRapide);
		c5pRapide=casesPlateauRepo.save(c5pRapide);
		c6pRapide=casesPlateauRepo.save(c6pRapide);
		c7pRapide=casesPlateauRepo.save(c7pRapide);
		c8pRapide=casesPlateauRepo.save(c8pRapide);
		c9pRapide=casesPlateauRepo.save(c9pRapide);
		c10pRapide=casesPlateauRepo.save(c10pRapide);
		c11pRapide=casesPlateauRepo.save(c11pRapide);
		c12pRapide=casesPlateauRepo.save(c12pRapide);
		c13pRapide=casesPlateauRepo.save(c13pRapide);
		c14pRapide=casesPlateauRepo.save(c14pRapide);
		c15pRapide=casesPlateauRepo.save(c15pRapide);
		c16pRapide=casesPlateauRepo.save(c16pRapide);
		c17pRapide=casesPlateauRepo.save(c17pRapide);
		c18pRapide=casesPlateauRepo.save(c18pRapide);
		c19pRapide=casesPlateauRepo.save(c19pRapide);
		c20pRapide=casesPlateauRepo.save(c20pRapide);
		c21pRapide=casesPlateauRepo.save(c21pRapide);
		c22pRapide=casesPlateauRepo.save(c22pRapide);
		c23pRapide=casesPlateauRepo.save(c23pRapide);
		c24pRapide=casesPlateauRepo.save(c24pRapide);
		c25pRapide=casesPlateauRepo.save(c25pRapide);
		c26pRapide=casesPlateauRepo.save(c26pRapide);
		c27pRapide=casesPlateauRepo.save(c27pRapide);
		c28pRapide=casesPlateauRepo.save(c28pRapide);
		c29pRapide=casesPlateauRepo.save(c29pRapide);
        c30pRapide=casesPlateauRepo.save(c30pRapide);


		Plateau6.setCases(casesPlateauRapide);
		Plateau6=plateauRepo.save(Plateau6);

		
		
		
		
		
		
		
		
		
		
		
		
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
		// joueur 2
		PersoObtenu perso1ObtenuJoueur2 = new PersoObtenu(perso2, joueur2);
		PersoObtenu perso2ObtenuJoueur2 = new PersoObtenu(perso1, joueur2);
		PersoObtenu perso3ObtenuJoueur2 = new PersoObtenu(perso3, joueur2);
		PersoObtenu perso4ObtenuJoueur2 = new PersoObtenu(perso4, joueur2);
		perso1ObtenuJoueur2=persoObtenuRepo.save(perso1ObtenuJoueur2);
		perso2ObtenuJoueur2=persoObtenuRepo.save(perso2ObtenuJoueur2);
		perso3ObtenuJoueur2=persoObtenuRepo.save(perso3ObtenuJoueur2);
		perso4ObtenuJoueur2=persoObtenuRepo.save(perso4ObtenuJoueur2);	
		
		// joueur 1
		PersoObtenu perso1ObtenuJoueur1 = new PersoObtenu (perso1, joueur1);
		PersoObtenu perso2ObtenuJoueur1 = new PersoObtenu (perso2, joueur1);
		perso1ObtenuJoueur1=persoObtenuRepo.save(perso1ObtenuJoueur1);
		perso2ObtenuJoueur1=persoObtenuRepo.save(perso2ObtenuJoueur1);
		
		// joueur 3
		PersoObtenu perso4ObtenuJoueur3 = new PersoObtenu (perso4, joueur3);
		PersoObtenu perso5ObtenuJoueur3 = new PersoObtenu (perso5, joueur3);
		perso4ObtenuJoueur3=persoObtenuRepo.save(perso4ObtenuJoueur3);
		perso5ObtenuJoueur3=persoObtenuRepo.save(perso5ObtenuJoueur3);
		
		// joueur 7
		PersoObtenu perso3ObtenuJoueur7 = new PersoObtenu (perso3, joueur7);
		PersoObtenu perso8ObtenuJoueur7 = new PersoObtenu (perso8, joueur7);
		perso3ObtenuJoueur7=persoObtenuRepo.save(perso3ObtenuJoueur7);
		perso8ObtenuJoueur7=persoObtenuRepo.save(perso8ObtenuJoueur7);
		
		// joueur 4
		PersoObtenu perso1ObtenuJoueur4 = new PersoObtenu (perso1, joueur4);
		PersoObtenu perso2ObtenuJoueur4 = new PersoObtenu (perso2, joueur4);
		perso1ObtenuJoueur4=persoObtenuRepo.save(perso1ObtenuJoueur4);
		perso2ObtenuJoueur4=persoObtenuRepo.save(perso2ObtenuJoueur4);
		
		}

	}

	//Compte connecte:
	//		Compte connected = compteRepo.findByLoginAndPassword("joueur1", "1234");
	//		
	//		System.out.println("Compte connecte:"+connected);
	//		
	//		spring.close();

