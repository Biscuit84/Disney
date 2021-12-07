package disney.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import disney.model.Views;
import disney.repository.IHistoriqueRepo;


@RestController
@RequestMapping("/historique")
@CrossOrigin("*")
public class HistoriqueRestController {

	@Autowired
	private IHistoriqueRepo historiqueRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewsHistorique.class)
	public List<Historique> findAll() {
		List<Historique> historiques = historiqueRepo.findAll();

		return historiques;
	}
	
		
	@GetMapping("/allWithDetail/{id}")
	@JsonView(Views.ViewsHistoriqueDetail.class)
	public List<Historique> findAllWithJoueur() {
		List<Historique> historiques = historiqueRepo.findAllWithDetail();

		return historiques;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewsHistorique.class)
	public Historique find(@PathVariable Long id) {
		Optional<Historique> optHistorique = historiqueRepo.findById(id);

		if (optHistorique.isPresent()) {
			return optHistorique.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé");
		}
	}
	
	@GetMapping("{id}/detail")
	@JsonView(Views.ViewsHistoriqueDetail.class)
	public Historique findByIdWithDetail(@PathVariable Long id) {
		Optional<Historique> optHistorique = historiqueRepo.findByIdWithDetail(id);

		if (optHistorique.isPresent()) {
			return optHistorique.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé");
		}
	}


	@PostMapping("")
	@JsonView(Views.ViewsHistorique.class)
	public Historique create(@RequestBody Historique historique) {
		historique = historiqueRepo.save(historique);

		return historique;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewsHistorique.class)
	public Historique update(@PathVariable Long id, @RequestBody Historique historique) {
		if (!historiqueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé");
		}

		historique = historiqueRepo.save(historique);

		return historique;
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!historiqueRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé");
		}
		
		historiqueRepo.deleteById(id);
	}
	
	@GetMapping("{id}/joueur")
	@JsonView(Views.ViewsHistoriqueDetail.class)
	public List<Historique> findAllByJoueur(@PathVariable Long id) {
		List<Historique> historiques = historiqueRepo.findAllByJoueur(id, PageRequest.of(0, 5));

		
			return historiques;
		
			
	}
	
}
