package disney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Boutique;

public interface IBoutiqueRepo extends JpaRepository<Boutique,Long> {

	@Query("select distinct b from Boutique b left join fetch b.personnages p")
	List<Boutique> findAllWithPersonnages();
	
	@Query("select distinct b from Boutique b left join fetch b.personnages p where b.id = :id")
	Optional<Boutique> findByIdWithPersonnages(@Param("id") Long id);
	
//	@Query("select distinct b from Boutique b left join fetch b.listEtoiles p where b.id = :id")
//	Optional<Boutique> findByIdWithEtoiles(@Param("id") Long id);
}
