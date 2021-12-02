package disney.model;

import java.util.List;
import java.util.Set;

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
	@JsonView(Views.ViewsPartieDetail.class)
	private Plateau plateau;
		
	@OneToMany(mappedBy = "partie")
	@JsonView(Views.ViewsPartieDetailPersos.class)
	private List<Personnage> personnages;
	
	@OneToMany(mappedBy = "partie")
	@JsonView(Views.ViewsPartieDetailJoueurs.class)
	private List<Joueur> joueurs;
	
	// représente le nombre de tours dans la partie
	//si partie en cours alors le chiffre bouge
	//si partie terminée alors nb de tour total avant la fin de partie
    private int nbTourDeJeu;
		
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

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public int getNbTourDeJeu() {
		return nbTourDeJeu;
	}

	public void setNbTourDeJeu(int nbTourDeJeu) {
		this.nbTourDeJeu = nbTourDeJeu;
	}

	@Override
	public String toString() {
		return "Partie [id=" + id + ", plateau=" + plateau +  "]";
	}



	
}
