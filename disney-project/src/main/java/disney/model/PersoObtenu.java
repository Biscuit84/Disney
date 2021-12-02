package disney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonView(Views.ViewsCommon.class)
public class PersoObtenu {

	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JsonView(Views.ViewsPersoObtenuDetailPerso.class)
	private Personnage perso;
	
	@ManyToOne
	@JsonView(Views.ViewsPersoObtenuDetailJoueur.class)
	private Joueur joueur;
	
	@Version
	private int version;
	
	public PersoObtenu() {
		// TODO Auto-generated constructor stub
	}

	public PersoObtenu(Personnage perso, Joueur joueur) {
		
		this.perso = perso;
		this.joueur = joueur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Personnage getPerso() {
		return perso;
	}

	public void setPerso(Personnage perso) {
		this.perso = perso;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "PersoObtenu [id=" + id + ", perso=" + perso + ", joueur=" + joueur + "]";
	}
	
	
	
}
