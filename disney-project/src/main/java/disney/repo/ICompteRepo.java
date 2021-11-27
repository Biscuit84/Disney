package disney.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.classe.Compte;
import disney.classe.Joueur;

public interface ICompteRepo extends JpaRepository<Compte,Long> {

	Compte findByLoginAndPassword(String login, String password);
	

//	
//	@Query("select distinct j from Joueur j left join fetch j.persos h where j.id :id")
//	Optional<Joueur> findByIdWithPersos(@Param("id")Long id);
//	
}
