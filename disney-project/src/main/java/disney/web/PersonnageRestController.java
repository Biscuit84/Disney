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

import disney.model.Personnage;
import disney.model.Views;
import disney.repository.IPersonnageRepo;


@RestController
@RequestMapping("/personnage")
@CrossOrigin("*")
public class PersonnageRestController {

	@Autowired
	private IPersonnageRepo personnageRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsPersonnage.class)
	public List<Personnage> findAll() {
		List<Personnage> personnages = personnageRepo.findAll();

		return personnages;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsPersonnage.class)
	public Personnage find(@PathVariable Long id) {
		Optional<Personnage> optPersonnage = personnageRepo.findById(id);

		if (optPersonnage.isPresent()) {
			return optPersonnage.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "personnage non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsPersonnage.class)
	public Personnage create(@RequestBody Personnage personnage) {
		personnage = personnageRepo.save(personnage);

		return personnage;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsPersonnage.class)
	public Personnage update(@PathVariable Long id, @RequestBody Personnage personnage) {
		if (!personnageRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "personnage non trouvé");
		}

		personnage = personnageRepo.save(personnage);

		return personnage;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!personnageRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "personnage non trouvé");
		}
		
		personnageRepo.deleteById(id);
	}
}
