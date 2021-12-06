package disney.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import disney.model.Avatar;
import disney.model.Views;
import disney.repository.IAvatarRepo;
import disney.repository.IAvatarRepo;

@RestController
@RequestMapping("/avatar")
@CrossOrigin("*")
public class AvatarRestController {

	@Autowired
	private IAvatarRepo avatarRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsAvatar.class)
	public List<Avatar> findAll() {
		List<Avatar> avatars = avatarRepo.findAll();

		return avatars;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsAvatar.class)
	public Avatar find(@PathVariable Long id) {
		Optional<Avatar> optAvatar = avatarRepo.findById(id);

		if (optAvatar.isPresent()) {
			return optAvatar.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsAvatar.class)
	public Avatar create(@RequestBody Avatar avatar) {
		avatar = avatarRepo.save(avatar);

		return avatar;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsAvatar.class)
	public Avatar update(@PathVariable Long id, @RequestBody Avatar avatar) {
		if (!avatarRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar non trouvé");
		}

		avatar = avatarRepo.save(avatar);

		return avatar;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!avatarRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar non trouvé");
		}
		
		avatarRepo.deleteById(id);
	}
	
}
