package disney.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import disney.classe.Partie;

public interface IPartieRepo extends JpaRepository<Partie,Long> {
	
	@Query("select distinct p from Partie p left join fetch p.plateau pl left join fetch p.personnage pe left join fetch p.joueurPartie pj where p.id = :id")
	Optional<Partie> findByIdAllDetail(@Param("id") Long id);
	
}
