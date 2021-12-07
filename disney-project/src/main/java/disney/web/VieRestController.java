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

import disney.model.Vie;
import disney.model.Views;
import disney.repository.IVieRepo;


@RestController
@RequestMapping("/vie")
@CrossOrigin("*")
public class VieRestController {

	@Autowired
	private IVieRepo vieRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsVie.class)
	public List<Vie> findAll() {
		List<Vie> vies = vieRepo.findAll();

		return vies;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsVie.class)
	public Vie find(@PathVariable Long id) {
		Optional<Vie> optVie = vieRepo.findById(id);

		if (optVie.isPresent()) {
			return optVie.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vie non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsVie.class)
	public Vie create(@RequestBody Vie vie) {
		vie = vieRepo.save(vie);

		return vie;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsVie.class)
	public Vie update(@PathVariable Long id, @RequestBody Vie vie) {
		if (!vieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vie non trouvé");
		}

		vie = vieRepo.save(vie);

		return vie;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!vieRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vie non trouvé");
		}
		
		vieRepo.deleteById(id);
	}
}
