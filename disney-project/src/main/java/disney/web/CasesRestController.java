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

import disney.model.Cases;
import disney.model.Views;
import disney.repository.ICasesRepo;


@RestController
@RequestMapping("/cases")
@CrossOrigin("*")
public class CasesRestController {

	@Autowired
	private ICasesRepo casesRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsCases.class)
	public List<Cases> findAll() {
		List<Cases> casess = casesRepo.findAll();

		return casess;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsCases.class)
	public Cases find(@PathVariable Long id) {
		Optional<Cases> optCases = casesRepo.findById(id);

		if (optCases.isPresent()) {
			return optCases.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsCases.class)
	public Cases create(@RequestBody Cases cases) {
		cases = casesRepo.save(cases);

		return cases;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsCases.class)
	public Cases update(@PathVariable Long id, @RequestBody Cases cases) {
		if (!casesRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		cases = casesRepo.save(cases);

		return cases;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!casesRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		casesRepo.deleteById(id);
	}
}
