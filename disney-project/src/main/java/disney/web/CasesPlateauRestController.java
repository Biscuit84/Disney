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

import disney.model.CasesPlateau;
import disney.model.Views;
import disney.repository.ICasesPlateauRepo;


@RestController
@RequestMapping("/casesplateau")
@CrossOrigin("*")
public class CasesPlateauRestController {

	@Autowired
	private ICasesPlateauRepo casesplateauRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsCasesPlateau.class)
	public List<CasesPlateau> findAll() {
		List<CasesPlateau> casesplateaus = casesplateauRepo.findAll();

		return casesplateaus;
	}
	
	@GetMapping("/detail")
	@JsonView(Views.CasesPlateauDetail.class)
	public List<CasesPlateau> findAllWithPerso() {
		List<CasesPlateau> casesplateaus = casesplateauRepo.findAll();

		return casesplateaus;
	}


	@GetMapping("/{id}")
	@JsonView(Views.ViewsCasesPlateau.class)
	public CasesPlateau find(@PathVariable Long id) {
		Optional<CasesPlateau> optCasesPlateau = casesplateauRepo.findById(id);

		if (optCasesPlateau.isPresent()) {
			return optCasesPlateau.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cases Plateau non trouvé");
		}
	}
	
	@GetMapping("/{id}/detail")
	@JsonView(Views.CasesPlateauDetail.class)
	public CasesPlateau findWithDetail(@PathVariable Long id) {
		Optional<CasesPlateau> optCasesPlateau = casesplateauRepo.findById(id);

		if (optCasesPlateau.isPresent()) {
			return optCasesPlateau.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case Plateau non trouvée");
		}
	}
	
	
	@GetMapping("/plateau/{id}")
	@JsonView(Views.CasesPlateauDetail.class)
	public List<CasesPlateau> findByPlateau(@PathVariable Long id) {
		List<CasesPlateau> casesPlateauX = casesplateauRepo.findAllByIdPlateau(id);

       return casesPlateauX;
	}
	
	


	@PostMapping("")
	@JsonView(Views.ViewsCasesPlateau.class)
	public CasesPlateau create(@RequestBody CasesPlateau casesplateau) {
		casesplateau = casesplateauRepo.save(casesplateau);

		return casesplateau;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsCasesPlateau.class)
	public CasesPlateau update(@PathVariable Long id, @RequestBody CasesPlateau casesplateau) {
		if (!casesplateauRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cases Plateau non trouvé");
		}

		casesplateau = casesplateauRepo.save(casesplateau);

		return casesplateau;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!casesplateauRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cases Plateau non trouvé");
		}
		
		casesplateauRepo.deleteById(id);
	}
}
