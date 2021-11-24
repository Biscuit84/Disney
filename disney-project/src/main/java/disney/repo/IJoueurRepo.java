package disney.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.classe.Joueur;

public interface IJoueurRepo extends JpaRepository<Joueur,Long> {
	

	

}
