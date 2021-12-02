package disney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import disney.model.Admin;
import disney.model.Compte;
import disney.repository.IAdminRepo;


@SpringBootTest
public class AllRepoSpringTest {
	
	@Autowired
	private IAdminRepo adminRepo;

	@Test
	public void createAndFindById() {
		System.out.println("testCreate Début ###################");

		Admin html = new Admin("toto","titi");

		html = adminRepo.save(html);

		Admin htmlFind = adminRepo.findById(html.getId()).get();

		assertEquals("toto", htmlFind.getNom());

		assertEquals( "titi", htmlFind.getPrenom());

		System.out.println("testCreate Fin ###################");
	}

	

}
