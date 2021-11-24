package disney.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disney.classe.Joueur;

public interface IJoueurRepo extends JpaRepository<Joueur,Long> {
	
	@Query("select distinct j from Joueur j left join fetch j.historiques h where j.id :id")
	Optional<Joueur> findByIdWithHistorique(Long id);
	
	@Query("select distinct j from Joueur j left join fetch j.persos h where j.id :id")
	Optional<Joueur> findByIdWithPersos(Long id);
	

}
