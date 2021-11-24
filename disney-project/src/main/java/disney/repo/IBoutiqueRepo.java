package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Admin;
import disney.classe.Boutique;

public interface IBoutiqueRepo extends JpaRepository<Boutique,Long> {

}
