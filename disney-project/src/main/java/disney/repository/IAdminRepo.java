package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Admin;

public interface IAdminRepo extends JpaRepository<Admin,Long> {

}
