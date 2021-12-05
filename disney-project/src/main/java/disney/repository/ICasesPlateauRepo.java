package disney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Boutique;
import disney.model.CasesPlateau;
import disney.model.Partie;
import disney.model.Plateau;


public interface ICasesPlateauRepo extends JpaRepository<CasesPlateau,Long> {
	
	List<CasesPlateau> findAllByPlateau(Plateau plateau);

	@Query("select distinct cp from CasesPlateau cp left join fetch cp.plateau p where p.id=:id")
	List<CasesPlateau> findAllByIdPlateau(Long id);
	
	
	/*
	 * 	@Query("select distinct p from Partie p left join fetch p.personnages pe where p.id = :id")
	Optional<Partie> findByIdWithDetailPersos(@Param("id") Long id);
	 * 	@Query("select distinct b from Boutique b left join fetch b.personnages p")
	List<Boutique> findAllWithPersonnages();
	 */
}
