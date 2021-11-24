package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Carte;
import disney.classe.Cases;
import disney.classe.Plateau;

public interface IPlateauRepo extends JpaRepository<Plateau,Long> {

}
