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

import disney.classe.Historique;
import disney.classe.PersoObtenu;
import disney.classe.Views;
import disney.repo.IPersoObtenuRepo;


@RestController
@RequestMapping("/persoObtenu")
@CrossOrigin("*")
public class PersoObtenuRestController {

	@Autowired
	private IPersoObtenuRepo persoObtenuRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsPersoObtenu.class)
	public List<PersoObtenu> findAll() {
		List<PersoObtenu> persoObtenus = persoObtenuRepo.findAll();

		return persoObtenus;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsPersoObtenu.class)
	public PersoObtenu find(@PathVariable Long id) {
		Optional<PersoObtenu> optPersoObtenu = persoObtenuRepo.findById(id);

		if (optPersoObtenu.isPresent()) {
			return optPersoObtenu.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsPersoObtenu.class)
	public PersoObtenu create(@RequestBody PersoObtenu persoObtenu) {
		persoObtenu = persoObtenuRepo.save(persoObtenu);

		return persoObtenu;
	}
	@GetMapping("{id}/joueur")
	@JsonView(Views.ViewsHistorique.class)
	public List<PersoObtenu> findAllByJoueur(@PathVariable Long id) {
		List<PersoObtenu> persos = persoObtenuRepo.findAllByJoueur(id);

		
			return persos;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsPersoObtenu.class)
	public PersoObtenu update(@PathVariable Long id, @RequestBody PersoObtenu persoObtenu) {
		if (!persoObtenuRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		persoObtenu = persoObtenuRepo.save(persoObtenu);

		return persoObtenu;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!persoObtenuRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		persoObtenuRepo.deleteById(id);
	}
}
