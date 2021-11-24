package disney.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.classe.Admin;

public interface IAdminRepo extends JpaRepository<Admin,Long> {

}
