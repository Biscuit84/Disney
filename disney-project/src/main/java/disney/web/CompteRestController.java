package disney.web;

import java.time.LocalDate;
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

import disney.model.Avatar;
import disney.model.Compte;
import disney.model.Joueur;
import disney.model.Views;
import disney.repository.IAvatarRepo;
import disney.repository.ICompteRepo;
import disney.repository.IJoueurRepo;
import disney.dto.ConnexionDTO;


@RestController
@RequestMapping("/compte")
@CrossOrigin("*")
public class CompteRestController {

	@Autowired
	private ICompteRepo compteRepo;
	
	@Autowired
	private IJoueurRepo joueurRepo;
	
	@Autowired
	private IAvatarRepo avatarRepo;
	
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsCompte.class)
	public Compte create(@RequestBody Compte compte) {
		//on charge mickey par defaut
		Avatar avatarParDefaut = avatarRepo.findById((long) 11).get();
		compte.setAvatar(avatarParDefaut);
		compte = compteRepo.save(compte);

		return compte;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsCompte.class)
	public Compte update(@PathVariable Long id, @RequestBody Compte compte) {
		if (!compteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
		}

		compte = compteRepo.save(compte);

		return compte;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!compteRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
		}
		
		compteRepo.deleteById(id);
	}
	@PostMapping("/connexion")
	@JsonView(Views.ViewsCompte.class)
	public Compte connexion(@RequestBody ConnexionDTO connexion) {
		Optional<Compte> optCompte = compteRepo.findByMailAndPassword(connexion.getMail(), connexion.getPassword());

		if (optCompte.isPresent()) {
			Compte compte = optCompte.get();
			LocalDate ld = LocalDate.now();
			if(compte.getDateLastConnexion() != null) {
				if(compte.getDateLastConnexion().isBefore(ld)) {
					Joueur j = (Joueur) compte;
					if(j.getLife() < 3 ) {
						j.setLife(3);
						joueurRepo.save(j);
					}
				}
			}
			compte.setDateLastConnexion(ld);
			compte = compteRepo.save(compte);
			return compte;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte non trouvé");
		}
	}
}
