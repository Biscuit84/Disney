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

import disney.model.Etoile;
import disney.model.Views;
import disney.repository.IEtoileRepo;

@RestController
@RequestMapping("/etoile")
@CrossOrigin("*")
public class EtoileRestController {

	@Autowired
	private IEtoileRepo etoileRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsEtoile.class)
	public List<Etoile> findAll() {
		List<Etoile> etoiles = etoileRepo.findAll();

		return etoiles;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsEtoile.class)
	public Etoile find(@PathVariable Long id) {
		Optional<Etoile> optEtoile = etoileRepo.findById(id);

		if (optEtoile.isPresent()) {
			return optEtoile.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etoile non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsEtoile.class)
	public Etoile create(@RequestBody Etoile etoile) {
		etoile = etoileRepo.save(etoile);

		return etoile;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsEtoile.class)
	public Etoile update(@PathVariable Long id, @RequestBody Etoile etoile) {
		if (!etoileRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etoile non trouvé");
		}

		etoile = etoileRepo.save(etoile);

		return etoile;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!etoileRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etoile non trouvé");
		}
		
		etoileRepo.deleteById(id);
	}
}
