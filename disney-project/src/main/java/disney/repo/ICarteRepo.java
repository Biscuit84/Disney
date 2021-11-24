package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Carte;

public interface ICarteRepo extends JpaRepository<Carte,Integer> {

}
