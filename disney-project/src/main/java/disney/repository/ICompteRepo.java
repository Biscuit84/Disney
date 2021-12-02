package disney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import disney.model.Compte;
import disney.model.Joueur;


public interface ICompteRepo extends JpaRepository<Compte,Long> {

	@Query("select distinct u from Compte u  where u.mail = :mail and u.password = :password")
	Optional<Compte> findByMailAndPassword(@Param("mail") String mail, @Param("password") String password);
	
	
//	Compte findByLoginAndPassword(String login, String password);
//
//	Optional<Compte> findByLogin(String login);


	//	
	//	@Query("select distinct j from Joueur j left join fetch j.persos h where j.id :id")
	//	Optional<Joueur> findByIdWithPersos(@Param("id")Long id);
	//	
}
