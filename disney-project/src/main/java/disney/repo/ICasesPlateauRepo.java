package disney.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import disney.classe.CasesPlateau;


public interface ICasesPlateauRepo extends JpaRepository<CasesPlateau,Long> {

	
}
