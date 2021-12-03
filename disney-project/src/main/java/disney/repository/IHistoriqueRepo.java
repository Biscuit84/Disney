package disney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Historique;
import disney.model.Partie;

public interface IHistoriqueRepo extends JpaRepository<Historique, Long> {

	@Query("select h from Historique h where h.joueur.id = :id order by h.id desc")
	List<Historique> findAllByJoueur(@Param("id") Long id, Pageable pageable);

	@Query("select distinct h from Historique h left join fetch h.joueur left join fetch h.personnage j")
	List<Historique> findAllWithDetail();

	@Query("select distinct h from Historique h left join fetch h.joueur j left join fetch h.personnage where h.id = :id")
	Optional<Historique> findByIdWithDetail(@Param("id") Long id);

	Historique findByPartie(Partie partie);

}
