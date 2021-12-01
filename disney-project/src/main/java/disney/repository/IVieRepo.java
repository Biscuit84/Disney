package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Vie;

public interface IVieRepo extends JpaRepository<Vie, Long> {

}
