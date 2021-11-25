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

import disney.classe.Boutique;
import disney.classe.Views;
import disney.repo.IBoutiqueRepo;


@RestController
@RequestMapping("/boutique")
@CrossOrigin("*")
public class BoutiqueRestController {

	@Autowired
	private IBoutiqueRepo boutiqueRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsBoutique.class)
	public List<Boutique> findAll() {
		List<Boutique> boutiques = boutiqueRepo.findAll();

		return boutiques;
	}
	
	@GetMapping("/detail")
	@JsonView(Views.ViewsBoutiqueDetail.class)
	public List<Boutique> findAllWithPersonnages() {
		List<Boutique> boutiques = boutiqueRepo.findAllWithPersonnages();

		return boutiques;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsBoutique.class)
	public Boutique find(@PathVariable Long id) {
		Optional<Boutique> optBoutique = boutiqueRepo.findById(id);

		if (optBoutique.isPresent()) {
			return optBoutique.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}
	}
	
	@GetMapping("{id}/detail")
	@JsonView(Views.ViewsBoutiqueDetail.class)
	public Boutique findWithDetail(@PathVariable Long id) {
		Optional<Boutique> optBoutique = boutiqueRepo.findByIdWithPersonnages(id);

		if (optBoutique.isPresent()) {
			return optBoutique.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsBoutique.class)
	public Boutique create(@RequestBody Boutique boutique) {
		boutique = boutiqueRepo.save(boutique);

		return boutique;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsBoutique.class)
	public Boutique update(@PathVariable Long id, @RequestBody Boutique boutique) {
		if (!boutiqueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}

		boutique = boutiqueRepo.save(boutique);

		return boutique;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!boutiqueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boutique non trouvée");
		}
		
		boutiqueRepo.deleteById(id);
	}
}
