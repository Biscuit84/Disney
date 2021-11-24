package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Joueur;

public interface IJoueurRepo extends JpaRepository<Joueur,Long> {

}
