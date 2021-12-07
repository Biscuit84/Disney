package disney;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonView;

import disney.dto.BoutiqueDto;
import disney.model.Boutique;
import disney.model.Etoile;
import disney.model.Personnage;
import disney.model.Vie;
import disney.model.Views;
import disney.repository.IBoutiqueRepo;
import disney.repository.IEtoileRepo;
import disney.repository.IPersonnageRepo;
import disney.repository.IVieRepo;
import disney.web.BoutiqueRestController;

@SpringBootTest
class BoutiqueReposotirySpringTests {

	@Autowired
	private IBoutiqueRepo boutiqueRepo;
	
	@Autowired
	private IVieRepo vieRepo;
	
	@Autowired
	private IEtoileRepo etoileRepo;
	
	@Autowired
	private IPersonnageRepo personnageRepo;
	
	@Autowired
	private BoutiqueRestController boutiqueRestController;
	
	@Test
	public void testFindByIdWithPersonnages() {
		List<Personnage> lp = personnageRepo.findAll();
		
		Boutique boutiqueEnBDD = boutiqueRepo.findByIdWithPersonnages((long) 1).get();
		
		assertEquals(lp, boutiqueEnBDD.getPersonnages());
	}
	
//	@Test
//	@JsonView(Views.ViewsBoutique.class)
//	public void testChoixBoutiqueJoueur() {
//		BoutiqueDto boutiqueDto = boutiqueRestController.choixBoutique((long) 4);
//
//		assertEquals(4, boutiqueDto.getListVies().size());
//		assertEquals(4, boutiqueDto.getListEtoiles().size());
////		assertTrue(boutiqueDto.getPersonnages().get(0).isPersoDejaEnPossession());
//	}
	
	@Test
	public void testFindAllBoutique() {
		List<Boutique> boutique = boutiqueRepo.findAll();
		
		List<Boutique> boutiqueController = boutiqueRestController.findAll();
		
		assertEquals(boutique.get(0).getId(), boutiqueController.get(0).getId());
	}
	
	@Test
	public void testFindAllWithPersonnages() {
		List<Boutique> boutique = boutiqueRepo.findAllWithPersonnages();
		
		List<Boutique> boutiqueController = boutiqueRestController.findAllWithPersonnages();
		
		assertEquals(boutique.get(0).getPersonnages().get(0).getId(), boutiqueController.get(0).getPersonnages().get(0).getId());
	}


	//findAllBoutique
	@Test
	public void findAllBoutique() {
		System.out.println("testFindAll Début ###################");

		//vies
		List<Vie> listeVie1 = new ArrayList<>();
		List<Vie> listeVie2 = new ArrayList<>();
		listeVie1.add(new Vie(1, 100, "../../../assets/images/boutique/potion-1.png"));
		listeVie1.add(new Vie(3, 275, "../../../assets/images/boutique/potion-3.png"));
		listeVie2.add(new Vie(5, 400, "../../../assets/images/boutique/potion-5.png"));
		listeVie2.add(new Vie(10, 750, "../../../assets/images/boutique/potion-10.png"));
		
		//etoiles
		List<Etoile> listeEtoiles1 = new ArrayList<>();
		List<Etoile> listeEtoiles2 = new ArrayList<>();
		listeEtoiles1.add(new Etoile(100, 5, "../../../assets/images/boutique/etoile-1.png"));
		listeEtoiles1.add(new Etoile(300, 13, "../../../assets/images/boutique/etoile-3.png"));
		listeEtoiles2.add(new Etoile(500, 22, "../../../assets/images/boutique/etoile-5.png"));
		listeEtoiles2.add(new Etoile(1000, 40, "../../../assets/images/boutique/etoile-10.png"));
		
		
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
		List<Personnage> listePerso1 = new ArrayList<> ();
		listePerso1.add(perso1);
		listePerso1.add(perso2);
		listePerso1.add(perso3);
		listePerso1.add(perso4);
		listePerso1.add(perso5);
		List<Personnage> listePerso2 = new ArrayList<> ();
		listePerso2.add(perso6);
		listePerso2.add(perso7);
		listePerso2.add(perso8);
		listePerso2.add(perso9);
		listePerso2.add(perso10);
		

		List<Boutique> listBoutiqueController = boutiqueRestController.findAll();
		
		int sizeStart = listBoutiqueController.size();

		Boutique boutique1 = new Boutique(listePerso1, listeVie1, listeEtoiles1);

		Boutique boutique2 = new Boutique(listePerso2, listeVie2, listeEtoiles2);

		listBoutiqueController = boutiqueRestController.findAll();
		int sizeEnd = listBoutiqueController.size();

		assertEquals(2, sizeEnd - sizeStart);
		assertEquals(listePerso1, listBoutiqueController.get(0).getPersonnages());
		assertEquals(listeEtoiles1, boutique1.getListEtoiles());
		assertEquals(listeVie1, boutique1.getListVies());
		assertEquals(listePerso2, boutique2.getPersonnages());
		assertEquals(listeEtoiles2, boutique2.getListEtoiles());
		assertEquals(listeVie2, boutique2.getListVies());

		System.out.println("testFindAll Fin ###################");
	}
	
	
	
	//findAllBoutiqueWitPersonnages
	@Test
	public void findAllBoutiqueWitPersonnages() {
		System.out.println("testFindAll Début ###################");

		//vies
		List<Vie> listeVie1 = new ArrayList<>();
		List<Vie> listeVie2 = new ArrayList<>();
		listeVie1.add(new Vie(1, 100, "../../../assets/images/boutique/potion-1.png"));
		listeVie1.add(new Vie(3, 275, "../../../assets/images/boutique/potion-3.png"));
		listeVie2.add(new Vie(5, 400, "../../../assets/images/boutique/potion-5.png"));
		listeVie2.add(new Vie(10, 750, "../../../assets/images/boutique/potion-10.png"));
		listeVie1= vieRepo.saveAll(listeVie1);
		listeVie2= vieRepo.saveAll(listeVie2);
		
		//etoiles
		List<Etoile> listeEtoiles1 = new ArrayList<>();
		List<Etoile> listeEtoiles2 = new ArrayList<>();
		listeEtoiles1.add(new Etoile(100, 5, "../../../assets/images/boutique/etoile-1.png"));
		listeEtoiles1.add(new Etoile(300, 13, "../../../assets/images/boutique/etoile-3.png"));
		listeEtoiles2.add(new Etoile(500, 22, "../../../assets/images/boutique/etoile-5.png"));
		listeEtoiles2.add(new Etoile(1000, 40, "../../../assets/images/boutique/etoile-10.png"));
		listeEtoiles1= etoileRepo.saveAll(listeEtoiles1);
		listeEtoiles2= etoileRepo.saveAll(listeEtoiles2);
		
		
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
		List<Personnage> listePerso1 = new ArrayList<> ();
		listePerso1.add(perso1);
		listePerso1.add(perso2);
		listePerso1.add(perso3);
		listePerso1.add(perso4);
		listePerso1.add(perso5);
		List<Personnage> listePerso2 = new ArrayList<> ();
		listePerso2.add(perso6);
		listePerso2.add(perso7);
		listePerso2.add(perso8);
		listePerso2.add(perso9);
		listePerso2.add(perso10);
		
		listePerso1= personnageRepo.saveAll(listePerso1);
		listePerso2= personnageRepo.saveAll(listePerso2);

		int sizeStart = boutiqueRepo.findAll().size();

		Boutique boutique1 = new Boutique(listePerso1, listeVie1, listeEtoiles1);

		boutique1 = boutiqueRepo.save(boutique1);

		Boutique boutique2 = new Boutique(listePerso2, listeVie2, listeEtoiles2);

		boutique2 = boutiqueRepo.save(boutique2);

		int sizeEnd = boutiqueRepo.findAll().size();

		assertEquals(2, sizeEnd - sizeStart);
		
		assertEquals(listePerso1, boutique1.getPersonnages());
		assertEquals(listeEtoiles1, boutique1.getListEtoiles());
		assertEquals(listeVie1, boutique1.getListVies());
		assertEquals(listePerso2, boutique2.getPersonnages());
		assertEquals(listeEtoiles2, boutique2.getListEtoiles());
		assertEquals(listeVie2, boutique2.getListVies());

		System.out.println("testFindAll Fin ###################");
	}
	
	



}
