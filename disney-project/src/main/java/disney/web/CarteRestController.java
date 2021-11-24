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

import disney.classe.Carte;
import disney.classe.Views;
import disney.repo.ICarteRepo;


@RestController
@RequestMapping("/carte")
@CrossOrigin("*")
public class CarteRestController {

	@Autowired
	private ICarteRepo carteRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsCarte.class)
	public List<Carte> findAll() {
		List<Carte> cartes = carteRepo.findAll();

		return cartes;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsCarte.class)
	public Carte find(@PathVariable Long id) {
		Optional<Carte> optCarte = carteRepo.findById(id);

		if (optCarte.isPresent()) {
			return optCarte.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsCarte.class)
	public Carte create(@RequestBody Carte carte) {
		carte = carteRepo.save(carte);

		return carte;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsCarte.class)
	public Carte update(@PathVariable Long id, @RequestBody Carte carte) {
		if (!carteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		carte = carteRepo.save(carte);

		return carte;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!carteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		carteRepo.deleteById(id);
	}
}
