package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Etoile;


public interface IEtoileRepo extends JpaRepository<Etoile, Long> {

}
