package disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Carte;
import disney.model.Cases;
import disney.model.Historique;
import disney.model.PersoObtenu;
import disney.model.Personnage;

public interface IPersoObtenuRepo extends JpaRepository<PersoObtenu,Long> {

	@Query("select h from PersoObtenu h left join fetch h.joueur j where h.joueur.id = :id")
	List<PersoObtenu> findAllByJoueur(@Param("id") Long id);
}
