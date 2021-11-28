package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Carte;
import disney.model.Cases;

public interface ICasesRepo extends JpaRepository<Cases,Long> {

}
