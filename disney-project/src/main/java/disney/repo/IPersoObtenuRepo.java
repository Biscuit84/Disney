package disney.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.classe.Carte;
import disney.classe.Cases;
import disney.classe.Historique;
import disney.classe.PersoObtenu;
import disney.classe.Personnage;

public interface IPersoObtenuRepo extends JpaRepository<PersoObtenu,Long> {

	@Query("select h from PersoObtenu h where h.joueur.id = :id")
	List<PersoObtenu> findAllByJoueur(@Param("id") Long id);
}
