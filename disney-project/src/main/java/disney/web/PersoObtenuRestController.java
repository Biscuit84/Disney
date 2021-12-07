package disney.web;

import java.security.Principal;
import java.util.ArrayList;
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

import disney.model.Historique;
import disney.model.Joueur;
import disney.model.PersoObtenu;
import disney.model.Personnage;
import disney.model.Views;
import disney.repository.IJoueurRepo;
import disney.repository.IPersoObtenuRepo;


@RestController
@RequestMapping("/persoObtenu")
@CrossOrigin("*")
public class PersoObtenuRestController {

	@Autowired
	private IPersoObtenuRepo persoObtenuRepo;

	@Autowired
	private IJoueurRepo iJoueurRepo;

	@GetMapping("/detail/{id}")
	@JsonView(Views.ViewsPersoObtenuDetailJoueur.class)
	public List<Personnage> findAllPersoObtenuByIdJoueur(@PathVariable Long id) {
		List<PersoObtenu> persosObtenu = persoObtenuRepo.findAllByJoueur(id);
		List<Personnage> listPerso = new ArrayList<Personnage>();
		for(PersoObtenu po : persosObtenu) {
			listPerso.add(po.getPerso());
		}
		return listPerso;
	}

	@GetMapping("")
	@JsonView(Views.ViewsPersoObtenuDetailJoueur.class)
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personnage obtenu non trouvé");
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personnage obtenu non trouvé");
		}

		persoObtenu = persoObtenuRepo.save(persoObtenu);

		return persoObtenu;
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!persoObtenuRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personnage obtenu non trouvé");
		}

		persoObtenuRepo.deleteById(id);
	}
}
