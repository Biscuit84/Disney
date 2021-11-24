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

import disney.classe.Partie;
import disney.classe.Views;
import disney.repo.IPartieRepo;


@RestController
@RequestMapping("/partie")
@CrossOrigin("*")
public class PartieRestController {

	@Autowired
	private IPartieRepo partieRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsPartie.class)
	public List<Partie> findAll() {
		List<Partie> parties = partieRepo.findAll();

		return parties;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsPartie.class)
	public Partie find(@PathVariable Long id) {
		Optional<Partie> optPartie = partieRepo.findById(id);

		if (optPartie.isPresent()) {
			return optPartie.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsPartie.class)
	public Partie create(@RequestBody Partie partie) {
		partie = partieRepo.save(partie);

		return partie;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsPartie.class)
	public Partie update(@PathVariable Long id, @RequestBody Partie partie) {
		if (!partieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		partie = partieRepo.save(partie);

		return partie;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!partieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		partieRepo.deleteById(id);
	}
}
