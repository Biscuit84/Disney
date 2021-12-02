package disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disney.model.Plateau;

public interface IPlateauRepo extends JpaRepository<Plateau,Long> {

	@Query("select distinct p from Plateau p left join fetch p.cases")
	List<Plateau> findAllWithDetail();
}
