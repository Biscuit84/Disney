package disney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Historique;
import disney.model.Joueur;

public interface IJoueurRepo extends JpaRepository<Joueur,Long> {
	
	@Query("select distinct j from Joueur j left join fetch j.historiques h where j.id = :id")
	Optional<Joueur> findByIdWithHistorique(@Param("id") Long id);
	
	@Query("select distinct j from Joueur j left join fetch j.persos h where j.id = :id")
	Optional<Joueur> findByIdWithPersos(@Param("id")Long id);

}
