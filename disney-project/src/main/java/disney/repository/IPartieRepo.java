package disney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Partie;

public interface IPartieRepo extends JpaRepository<Partie,Long> {
	
	@Query("select distinct p from Partie p left join fetch p.plateau pl left join fetch p.personnages pe where p.id = :id")
	Optional<Partie> findByIdWithDetailPlateauAndPersos(@Param("id") Long id);
	
	@Query("select distinct p from Partie p left join fetch p.personnages pe where p.id = :id")
	Optional<Partie> findByIdWithDetailPersos(@Param("id") Long id);
	
	@Query("select distinct p from Partie p left join fetch p.joueurs pj where p.id = :id")
	Optional<Partie> findByIdWithDetailJoueurs(@Param("id") Long id);
	
}
