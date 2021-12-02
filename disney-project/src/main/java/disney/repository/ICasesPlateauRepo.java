package disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.CasesPlateau;
import disney.model.Plateau;


public interface ICasesPlateauRepo extends JpaRepository<CasesPlateau,Long> {
	
	List<CasesPlateau> findAllByPlateau(Plateau plateau);
	
}
