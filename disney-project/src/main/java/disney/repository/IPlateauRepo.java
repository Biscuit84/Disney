package disney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Plateau;

public interface IPlateauRepo extends JpaRepository<Plateau,Long> {

	@Query("select distinct p from Plateau p left join fetch p.cases")
	List<Plateau> findAllWithDetail();

	@Query("select distinct p from Plateau p left join fetch p.cases c where p.id = :id")
	Optional<Plateau> findByIdWithDetail(@Param("id") Long id);
	
	//@Query("select p from Plateau p where p.partie.id = :id ")
	//Optional<Plateau> findByPartieId();
	
	@Query("select distinct p from Plateau p where p.disponible = TRUE")
	List<Plateau> findAllPlateauDispo();
	
	
	
}
