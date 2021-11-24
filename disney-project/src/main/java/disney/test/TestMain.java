package disney.test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import disney.App.AppConfig;
import disney.classe.Admin;
import disney.classe.Boutique;
import disney.classe.Carte;
import disney.classe.Cases;
import disney.classe.CasesPlateau;
import disney.classe.Compte;
import disney.classe.Historique;
import disney.classe.Joueur;
import disney.classe.Partie;
import disney.classe.PersoObtenu;
import disney.classe.Personnage;
import disney.classe.Plateau;
import disney.classe.TypeCarte;
import disney.classe.TypeCase;
import disney.repo.IAdminRepo;
import disney.repo.IBoutiqueRepo;
import disney.repo.ICarteRepo;
import disney.repo.ICasesPlateauRepo;
import disney.repo.ICasesRepo;
import disney.repo.ICompteRepo;
import disney.repo.IHistoriqueRepo;
import disney.repo.IJoueurRepo;
import disney.repo.IPartieRepo;
import disney.repo.IPersoObtenuRepo;
import disney.repo.IPersonnageRepo;
import disney.repo.IPlateauRepo;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(AppConfig.class);
		
		IJoueurRepo joueurRepo= spring.getBean(IJoueurRepo.class);
		IAdminRepo adminRepo= spring.getBean(IAdminRepo.class);
		IPersonnageRepo persoRepo= spring.getBean(IPersonnageRepo.class);
		ICasesRepo casesRepo= spring.getBean(ICasesRepo.class);
		IBoutiqueRepo boutiqueRepo= spring.getBean(IBoutiqueRepo.class);
		ICarteRepo carteRepo= spring.getBean(ICarteRepo.class);
		ICasesPlateauRepo casesPlateauRepo= spring.getBean(ICasesPlateauRepo.class);
		IHistoriqueRepo historiqueRepo= spring.getBean(IHistoriqueRepo.class);
		IPartieRepo partieRepo= spring.getBean(IPartieRepo.class);
		IPersoObtenuRepo persoObtenuRepo= spring.getBean(IPersoObtenuRepo.class);
		IPlateauRepo plateauRepo= spring.getBean(IPlateauRepo.class);
		ICompteRepo compteRepo= spring.getBean(ICompteRepo.class);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		
//		
//		//JOUEURS
//		Joueur joueur1 = new Joueur("joueur1", "1234", "Toto", "Titi", "toto.titi@gmail.com", "TotoTropFort", "noob", 3);
//		joueur1=joueurRepo.save(joueur1);
//		Joueur joueur2 = new Joueur("joueur2", "password", "Tartanpion", "Tintin", "Tart.tintin@gmail.com", "TintinTheBest", "champion", 3);
//		joueur2=joueurRepo.save(joueur2);
//		
//		List <Joueur>listeJoueurs = new ArrayList<Joueur>();
//		listeJoueurs.add(joueur1);
//		listeJoueurs.add(joueur2);
//		
//		//ADMIN
//		Admin admin1 = new Admin("admin1", "987654321", "admin1", "admin1","admin1@gmail.com");
//		admin1=adminRepo.save(admin1);
//		Admin admin2 = new Admin("admin2", "123456789", "admin2", "admin2","admin2@gmail.com");
//		admin2=adminRepo.save(admin2);
//		
//		//Personnages
//		Personnage perso1 = new Personnage("Elsa", "Olaf", "Hans", "Glace");
//		Personnage perso2 = new Personnage("Ariel", "Eric", "Ursula", "Eau");
//		
//		List<Personnage> listeTotalePerso = new ArrayList ();
//		listeTotalePerso.add(perso1);
//		listeTotalePerso.add(perso2);
//		
//		perso1=persoRepo.save(perso1);
//		perso2=persoRepo.save(perso2);
//		
//		//Cases
//		Cases caseMechant = new Cases("Mechant",TypeCase.mechant);
//		Cases caseGentil = new Cases("Gentil",TypeCase.prince);
//		Cases casePrison = new Cases("Prison",TypeCase.prison);
//		Cases caseVide = new Cases("Vide",TypeCase.vide);
//		Cases caseDuel= new Cases("Duel",TypeCase.duel);
//		Cases caseDeplacement = new Cases("Deplacement",TypeCase.deplacement);
//		Cases caseDepart = new Cases("Depart",TypeCase.depart);
//		Cases caseArrivee = new Cases("Arrivee",TypeCase.arrivee);
//		Cases casePioche = new Cases("Pioche",TypeCase.pioche);
//		caseMechant = casesRepo.save(caseMechant);
//		caseGentil = casesRepo.save(caseGentil);
//		casePrison = casesRepo.save(casePrison);
//		caseVide = casesRepo.save(caseVide);
//		caseDuel = casesRepo.save(caseDuel);
//		caseDeplacement = casesRepo.save(caseDeplacement);
//		caseDepart = casesRepo.save(caseDepart);
//		caseArrivee = casesRepo.save(caseArrivee);
//		casePioche = casesRepo.save(casePioche);
//		
//		//BOUTIQUE
//		Boutique boutique = new Boutique(listeTotalePerso, 2, 2);
//		
//		boutique = boutiqueRepo.save(boutique);
//		
//		//Cartes
//		Carte c1 = new Carte("Carte Avance",TypeCarte.Bonus);
//		Carte c2 = new Carte("Carte Recule",TypeCarte.Malus);
//		Carte c3 = new Carte("Aller en Prison",TypeCarte.Prison);
//		
//		c1=carteRepo.save(c1);
//		c2=carteRepo.save(c2);
//		c3=carteRepo.save(c3);
//		
//		//Plateau
//		Plateau plateau1 = new Plateau ("Plateau Demo", 20);
//		plateau1=plateauRepo.save(plateau1);
//		
//		//CasesPlateau
//		List <CasesPlateau> casesPlateauDemo = new ArrayList();
//		
//		CasesPlateau c1pDemo = new CasesPlateau(plateau1, caseDepart, 0);
//		casesPlateauDemo.add(c1pDemo);
//		CasesPlateau c2pDemo = new CasesPlateau(plateau1, caseVide, 1);
//		casesPlateauDemo.add(c2pDemo);
//		CasesPlateau c3pDemo = new CasesPlateau(plateau1, caseDeplacement, 2);
//		casesPlateauDemo.add(c3pDemo);
//		CasesPlateau c4pDemo = new CasesPlateau(plateau1, caseDuel, 3);
//		casesPlateauDemo.add(c4pDemo);
//		CasesPlateau c5pDemo = new CasesPlateau(plateau1, casePioche, 4);
//		casesPlateauDemo.add(c5pDemo);
//		CasesPlateau c6pDemo = new CasesPlateau(plateau1, casePrison, 5);
//		casesPlateauDemo.add(c6pDemo);
//		CasesPlateau c7pDemo = new CasesPlateau(plateau1, caseGentil, 6);
//		casesPlateauDemo.add(c7pDemo);
//		CasesPlateau c8pDemo = new CasesPlateau(plateau1, caseMechant, 7);
//		casesPlateauDemo.add(c8pDemo);
//		CasesPlateau c9pDemo = new CasesPlateau(plateau1, caseDeplacement, 8);
//		casesPlateauDemo.add(c9pDemo);
//		CasesPlateau c10pDemo = new CasesPlateau(plateau1, caseVide, 9);
//		casesPlateauDemo.add(c10pDemo);
//		CasesPlateau c11pDemo = new CasesPlateau(plateau1, caseGentil, 10);
//		casesPlateauDemo.add(c11pDemo);
//		CasesPlateau c12pDemo = new CasesPlateau(plateau1, caseDuel, 11);
//		casesPlateauDemo.add(c12pDemo);
//		CasesPlateau c13pDemo = new CasesPlateau(plateau1, casePioche, 12);
//		casesPlateauDemo.add(c13pDemo);
//		CasesPlateau c14pDemo = new CasesPlateau(plateau1, caseMechant, 13);
//		casesPlateauDemo.add(c14pDemo);
//		CasesPlateau c15pDemo = new CasesPlateau(plateau1, caseVide, 14);
//		casesPlateauDemo.add(c15pDemo);
//		CasesPlateau c16pDemo = new CasesPlateau(plateau1, caseGentil, 15);
//		casesPlateauDemo.add(c16pDemo);
//		CasesPlateau c17pDemo = new CasesPlateau(plateau1, caseDeplacement, 16);
//		casesPlateauDemo.add(c17pDemo);
//		CasesPlateau c18pDemo = new CasesPlateau(plateau1, casePioche, 17);
//		casesPlateauDemo.add(c18pDemo);
//		CasesPlateau c19pDemo = new CasesPlateau(plateau1, caseMechant, 18);
//		casesPlateauDemo.add(c19pDemo);
//		CasesPlateau c20pDemo = new CasesPlateau(plateau1, caseArrivee, 19);
//		casesPlateauDemo.add(c20pDemo);
//		
//		c1pDemo=casesPlateauRepo.save(c1pDemo);
//		c2pDemo=casesPlateauRepo.save(c2pDemo);
//		c3pDemo=casesPlateauRepo.save(c3pDemo);
//		c4pDemo=casesPlateauRepo.save(c4pDemo);
//		c5pDemo=casesPlateauRepo.save(c5pDemo);
//		c6pDemo=casesPlateauRepo.save(c6pDemo);
//		c7pDemo=casesPlateauRepo.save(c7pDemo);
//		c8pDemo=casesPlateauRepo.save(c8pDemo);
//		c9pDemo=casesPlateauRepo.save(c9pDemo);
//		c10pDemo=casesPlateauRepo.save(c10pDemo);
//		c11pDemo=casesPlateauRepo.save(c11pDemo);
//		c12pDemo=casesPlateauRepo.save(c12pDemo);
//		c13pDemo=casesPlateauRepo.save(c13pDemo);
//		c14pDemo=casesPlateauRepo.save(c14pDemo);
//		c15pDemo=casesPlateauRepo.save(c15pDemo);
//		c16pDemo=casesPlateauRepo.save(c16pDemo);
//		c17pDemo=casesPlateauRepo.save(c17pDemo);
//		c18pDemo=casesPlateauRepo.save(c18pDemo);
//		c19pDemo=casesPlateauRepo.save(c19pDemo);
//		c20pDemo=casesPlateauRepo.save(c20pDemo);
//		c11pDemo=casesPlateauRepo.save(c11pDemo);
//		c12pDemo=casesPlateauRepo.save(c12pDemo);
//		
//		plateau1.setCases(casesPlateauDemo);
//		
//		plateau1=plateauRepo.save(plateau1);
//		
//		//Partie
//		Partie partie1 = new Partie(plateau1,listeTotalePerso);
//		partie1.setJoueursPartie(listeJoueurs);
//		partie1=partieRepo.save(partie1);
//		
//		//HISTORIQUE
//		String DateDebutPartie1 = "13/11/2021 15:49";
//		LocalDateTime localDateT1 = LocalDateTime.parse(DateDebutPartie1, formatter);
//
//		LocalDateTime heureFinPartie = LocalDateTime.now();
//		
//		Historique historique1 = new Historique();
//		historique1.setDateHeureDebutPartie(localDateT1);
//		historique1.setDateHeureFinPartie(heureFinPartie);
//		historique1=historiqueRepo.save(historique1);
//		
//		long minuteOfGameDuration = Duration.between(localDateT1, heureFinPartie).toMinutes();
//		System.out.println("duree de la partie:"+minuteOfGameDuration);
//		
//		//PersoObtenus
//		
//		PersoObtenu perso1ObtenuJoueur2 = new PersoObtenu(perso2, joueur2);
//		PersoObtenu perso2ObtenuJoueur2 = new PersoObtenu(perso1, joueur2);
//		PersoObtenu perso1ObtenuJoueur1 = new PersoObtenu (perso1, joueur1);
//		perso1ObtenuJoueur2=persoObtenuRepo.save(perso1ObtenuJoueur2);
//		perso2ObtenuJoueur2=persoObtenuRepo.save(perso2ObtenuJoueur2);
//		perso1ObtenuJoueur1=persoObtenuRepo.save(perso1ObtenuJoueur1);
		
		
		//Compte connecte:
		Compte connected = compteRepo.findByLoginAndPassword("joueur1", "1234");
		
		System.out.println("Compte connecte:"+connected);
		
		spring.close();
		
	}

}
