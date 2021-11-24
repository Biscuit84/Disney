package disney.classe;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;




@Entity
public class Partie {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	
	@OneToOne
	private Plateau plateau;
		
	@OneToMany
	private List<Personnage> personnages;
	
	@OneToMany
	private List<Joueur> joueursPartie;
	
		
	public Partie() {
		
	}
	
	public Partie(Plateau plateau, List<Personnage> personnages) {
		super();
		this.plateau = plateau;
		this.personnages = personnages;
	}


	public Partie(Plateau plateau) {
		this.plateau = plateau;
		
		
	}


	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Plateau getPlateau() {
		return plateau;
	}




	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}



	public List<Personnage> getPersonnages() {
		return personnages;
	}




	public void setPersonnages(List<Personnage> personnages) {
		this.personnages = personnages;
	}




	public List<Joueur> getJoueursPartie() {
		return joueursPartie;
	}




	public void setJoueursPartie(List<Joueur> joueursPartie) {
		this.joueursPartie = joueursPartie;
	}




	@Override
	public String toString() {
		return "Partie [id=" + id + ", plateau=" + plateau +  "]";
	}




	
	
	
	
	
	
	
	
	
	
	
}
