package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Personnage;

public interface IPersonnageRepo extends JpaRepository<Personnage,Long> {

}
