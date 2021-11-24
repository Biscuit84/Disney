package disney.classe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Boutique {
	
	

	@OneToMany
	private List<Personnage> personnages = new ArrayList<Personnage>();
	
	private int vies; 
	
	private int nbEtoile;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;

	public Boutique(List<Personnage> personnages, int vies, int nbEtoile) {
		super();
		this.personnages = personnages;
		this.vies = vies;
		this.nbEtoile = nbEtoile;
	}

	public Boutique(List<Personnage> personnages, int vies, int nbEtoile, Long id, int version) {
		super();
		this.personnages = personnages;
		this.vies = vies;
		this.nbEtoile = nbEtoile;
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

	public int getVies() {
		return vies;
	}

	public void setVies(int vies) {
		this.vies = vies;
	}

	public int getNbEtoile() {
		return nbEtoile;
	}

	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile = nbEtoile;
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
		return "Boutique [personnages=" + personnages + ", vies=" + vies + ", nbEtoile=" + nbEtoile + ", id=" + id
				+ ", version=" + version + "]";
	}
	
	
	
	
	
	
	
	
	
}
