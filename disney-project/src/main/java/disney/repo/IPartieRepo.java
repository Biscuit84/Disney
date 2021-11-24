package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Partie;

public interface IPartieRepo extends JpaRepository<Partie,Long> {

}
