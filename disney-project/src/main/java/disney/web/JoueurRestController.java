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

import disney.model.Joueur;
import disney.model.Views;
import disney.repository.ICompteRepo;
import disney.repository.IJoueurRepo;


@RestController
@RequestMapping("/joueur")
@CrossOrigin("*")
public class JoueurRestController {

	@Autowired
	private IJoueurRepo joueurRepo;
	@Autowired
	private ICompteRepo compteRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsJoueur.class)
	public List<Joueur> findAll() {
		List<Joueur> joueurs = joueurRepo.findAll();

		return joueurs;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewsJoueur.class)
	public Joueur find(@PathVariable Long id) {
		Optional<Joueur> optJoueur = joueurRepo.findById(id);

		if (optJoueur.isPresent()) {
			return optJoueur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé");
		}
	}

	@GetMapping("/{id}/historiques")
	@JsonView(Views.ViewsJoueurHistoriques.class)
	public Joueur findWithHistoriques(@PathVariable Long id) {
		Optional<Joueur> optJoueur = joueurRepo.findByIdWithHistorique(id);

		if (optJoueur.isPresent()) {
			return optJoueur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé");
		}
	}
	
	@GetMapping("/{id}/persos-obtenus")
	@JsonView(Views.ViewsJoueurAndPersos.class)
	public Joueur findWithPersos(@PathVariable Long id) {
		Optional<Joueur> optJoueur = joueurRepo.findByIdWithPersos(id);

		if (optJoueur.isPresent()) {
			return optJoueur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé");
		}
	}
	

	@PostMapping("")
	@JsonView(Views.ViewsJoueur.class)
	public Joueur create(@RequestBody Joueur joueur) {
		joueur = joueurRepo.save(joueur);

		return joueur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsJoueur.class)
	public Joueur update(@PathVariable Long id, @RequestBody Joueur joueur) {
		if (!joueurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé");
		}

		joueur = joueurRepo.save(joueur);

		return joueur;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!joueurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur non trouvé");
		}
		
		joueurRepo.deleteById(id);
	}
}
