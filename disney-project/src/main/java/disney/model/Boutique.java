package disney.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonView(Views.ViewsCommon.class)
public class Boutique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;

	@OneToMany
	@JsonView(Views.ViewsBoutiqueDetail.class)
	private List<Personnage> personnages = new ArrayList<Personnage>();
	
	@OneToMany
	private List<Vie> listVies = new ArrayList<>();

	@OneToMany
	private List<Etoile> listEtoiles = new ArrayList<>();
	


	public Boutique(List<Personnage> personnages, List<Vie>listVies, List<Etoile>listEtoiles) {
		super();
		this.personnages = personnages;
		this.listVies = listVies;
		this.listEtoiles = listEtoiles;
	}

	public Boutique(List<Personnage> personnages, List<Vie>listVies, List<Etoile>listEtoiles, Long id, int version) {
		super();
		this.personnages = personnages;
		this.listVies = listVies;
		this.listEtoiles = listEtoiles;
		this.id = id;
		this.version = version;
	}

	public Boutique() {
		super();
	}

	public List<Personnage> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(List<Personnage> personnages) {
		this.personnages = personnages;
	}



	public List<Vie> getListVies() {
		return listVies;
	}

	public void setListVies(List<Vie> listVies) {
		this.listVies = listVies;
	}

	public List<Etoile> getListEtoiles() {
		return listEtoiles;
	}

	public void setListEtoiles(List<Etoile> listEtoiles) {
		this.listEtoiles = listEtoiles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Boutique [id=" + id + ", version=" + version + ", personnages=" + personnages + ", listVies=" + listVies
				+ ", listEtoiles=" + listEtoiles + "]";
	}


	
	
	
	
	
	
	
	
	
}
