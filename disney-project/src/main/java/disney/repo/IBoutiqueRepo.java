package disney.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disney.classe.Boutique;

public interface IBoutiqueRepo extends JpaRepository<Boutique,Long> {

	@Query("select distinct b from Boutique b left join fetch b.personnages p")
	List<Boutique> findAllWithPersonnages();
	
	
	
}
