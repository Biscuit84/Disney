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

import disney.model.Admin;
import disney.model.Views;
import disney.repository.IAdminRepo;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRestController {

	@Autowired
	private IAdminRepo adminRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsAdmin.class)
	public List<Admin> findAll() {
		List<Admin> admins = adminRepo.findAll();

		return admins;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsAdmin.class)
	public Admin find(@PathVariable Long id) {
		Optional<Admin> optAdmin = adminRepo.findById(id);

		if (optAdmin.isPresent()) {
			return optAdmin.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsAdmin.class)
	public Admin create(@RequestBody Admin admin) {
		admin = adminRepo.save(admin);

		return admin;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsAdmin.class)
	public Admin update(@PathVariable Long id, @RequestBody Admin admin) {
		if (!adminRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		admin = adminRepo.save(admin);

		return admin;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!adminRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		adminRepo.deleteById(id);
	}
}
