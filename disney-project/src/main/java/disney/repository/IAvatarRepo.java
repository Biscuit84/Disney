package disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import disney.model.Avatar;

public interface IAvatarRepo extends JpaRepository<Avatar,Long> {

}
