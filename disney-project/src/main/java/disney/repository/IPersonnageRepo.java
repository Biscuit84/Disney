package disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Partie;
import disney.model.Personnage;

public interface IPersonnageRepo extends JpaRepository<Personnage,Long> {
	List<Personnage> getByPartie(Partie partie);
	
}
