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

import disney.model.Plateau;
import disney.model.Views;
import disney.repository.IPlateauRepo;


@RestController
@RequestMapping("/plateau")
@CrossOrigin("*")
public class PlateauRestController {

	@Autowired
	private IPlateauRepo plateauRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsPlateau.class)
	public List<Plateau> findAll() {
		List<Plateau> plateaus = plateauRepo.findAll();

		return plateaus;
	}
	
	@GetMapping("/detail")
	@JsonView(Views.ViewsPlateauDetail.class)
	public List<Plateau> findAllWithDetail() {
		List<Plateau> plateaus = plateauRepo.findAll();

		return plateaus;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsPlateau.class)
	public Plateau find(@PathVariable Long id) {
		Optional<Plateau> optPlateau = plateauRepo.findById(id);

		if (optPlateau.isPresent()) {
			return optPlateau.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsPlateau.class)
	public Plateau create(@RequestBody Plateau plateau) {
		plateau = plateauRepo.save(plateau);

		return plateau;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsPlateau.class)
	public Plateau update(@PathVariable Long id, @RequestBody Plateau plateau) {
		if (!plateauRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		plateau = plateauRepo.save(plateau);

		return plateau;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!plateauRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		plateauRepo.deleteById(id);
	}
}
