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

import disney.classe.Compte;
import disney.classe.Views;
import disney.repo.ICompteRepo;


@RestController
@RequestMapping("/compte")
@CrossOrigin("*")
public class CompteRestController {

	@Autowired
	private ICompteRepo compteRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsCompte.class)
	public List<Compte> findAll() {
		List<Compte> comptes = compteRepo.findAll();

		return comptes;
	}

	@GetMapping("{id}")
	@JsonView(Views.ViewsCompte.class)
	public Compte find(@PathVariable Long id) {
		Optional<Compte> optCompte = compteRepo.findById(id);

		if (optCompte.isPresent()) {
			return optCompte.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsCompte.class)
	public Compte create(@RequestBody Compte compte) {
		compte = compteRepo.save(compte);

		return compte;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsCompte.class)
	public Compte update(@PathVariable Long id, @RequestBody Compte compte) {
		if (!compteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}

		compte = compteRepo.save(compte);

		return compte;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!compteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation non trouvé");
		}
		
		compteRepo.deleteById(id);
	}
}
