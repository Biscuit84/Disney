package disney.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.classe.Carte;
import disney.classe.Cases;
import disney.classe.Historique;
import disney.classe.Joueur;

public interface IHistoriqueRepo extends JpaRepository<Historique,Long> {

	@Query("select h from Historique h where h.joueur.id = :id")
	List<Historique> findAllByJoueur(@Param("id") Long id);
}
