package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Carte;
import disney.classe.Cases;
import disney.classe.PersoObtenu;
import disney.classe.Personnage;

public interface IPersoObtenuRepo extends JpaRepository<PersoObtenu,Long> {

}
