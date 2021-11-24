package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disney.classe.Joueur;

public interface IJoueurRepo extends JpaRepository<Joueur,Long> {
	
	@Query("select distinct j from joueur j left join fetch j.historiques h where j.id :id")
	Joueur findByIdWithHistorique(Long id);
	
	
	
	

}
