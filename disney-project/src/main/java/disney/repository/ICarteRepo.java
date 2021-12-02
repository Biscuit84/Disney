package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Carte;

public interface ICarteRepo extends JpaRepository<Carte,Long> {

}
