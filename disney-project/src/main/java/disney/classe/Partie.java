package disney.classe;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;




@Entity
@JsonView(Views.ViewsCommon.class)
public class Partie {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;

	@OneToOne
	private Plateau plateau;
		
	@ManyToOne
	private Personnage personnage;
	
	@ManyToOne
	private Joueur joueurPartie;
	
    
		
	public Partie() {
		
	}
	
	public Partie(Plateau plateau, Personnage personnage) {
		super();
		this.plateau = plateau;
		this.personnage = personnage;
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



	public Personnage getPersonnage() {
		return personnage;
	}




	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}




	public Joueur getJoueurPartie() {
		return joueurPartie;
	}




	public void setJoueurPartie(Joueur joueurPartie) {
		this.joueurPartie = joueurPartie;
	}

	



	@Override
	public String toString() {
		return "Partie [id=" + id + ", plateau=" + plateau +  "]";
	}




	
	
	
	
	
	
	
	
	
	
	
}
