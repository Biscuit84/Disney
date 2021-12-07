package disney;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import disney.model.Personnage;
import disney.repository.IPersonnageRepo;
import disney.web.PersoObtenuRestController;

@SpringBootTest
public class PersoObtenuRestControllerTest {
	
	@Autowired
	private PersoObtenuRestController controller;
	@Autowired
	private IPersonnageRepo persoRepo;
	
	@Test
	void testPersoObtenu() {
		Long id = (long) 2 ;
		//je prépare ma liste de lp attendu :
		List<Personnage> lpPredefini = new ArrayList<Personnage>();
		Personnage p1 = persoRepo.findById((long) 1).get();
		Personnage p2 = persoRepo.findById((long) 2).get();
		lpPredefini.add(p2);
		lpPredefini.add(p1);
		
		List<Personnage> lp = controller.findAllPersoObtenuByIdJoueur(id);
		//vérifier que lp correspond à ce que j'attend
		
		assertTrue(lpPredefini.equals(lp));
		System.out.println("################## ici : "+lp);
	}

}
