package disney.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonView(Views.ViewsCommon.class)
public class Historique  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	private LocalDateTime dateHeureDebutPartie;
	
	private LocalDateTime dateHeureFinPartie;
	
	private int positionArrivee;
	
	private int nbEtoilesGagnees;
	
	private boolean victoire; // victoire = true ça veut dire qu'on a gagné hehe
	
	@OneToOne
	private Partie partie;
	@ManyToOne
	@JsonView(Views.ViewsHistoriqueDetail.class)
	private Joueur joueur;
	
	//perso pour une partie en cours d'execution 
	@ManyToOne
	@JsonView(Views.ViewsHistoriqueDetail.class)
	private Personnage personnage;

	public Historique(Long id, LocalDateTime dateHeureDebutPartie, LocalDateTime dateHeureFinPartie, int positionArrivee,
			int nbEtoilesGagnees, Partie partie, Joueur joueur) {
		super();
		this.id = id;
		this.dateHeureDebutPartie = dateHeureDebutPartie;
		this.dateHeureFinPartie = dateHeureFinPartie;
		this.positionArrivee = positionArrivee;
		this.nbEtoilesGagnees = nbEtoilesGagnees;
		this.partie = partie;
		this.joueur = joueur;
	}

	


	public Historique(LocalDateTime dateHeureDebutPartie, LocalDateTime dateHeureFinPartie, int positionArrivee, int nbEtoilesGagnees,
			Partie partie) {
		super();
		this.dateHeureDebutPartie = dateHeureDebutPartie;
		this.dateHeureFinPartie = dateHeureFinPartie;
		this.positionArrivee = positionArrivee;
		this.nbEtoilesGagnees = nbEtoilesGagnees;
		this.partie = partie;
	}




	public Historique() {
		
	}





	public Joueur getJoueur() {
		return joueur;
	}




	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}




	public Personnage getPersonnage() {
		return personnage;
	}




	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}




	public LocalDateTime getDateHeureDebutPartie() {
		return dateHeureDebutPartie;
	}
	public void setDateHeureDebutPartie(LocalDateTime dateHeureDebutPartie) {
		this.dateHeureDebutPartie = dateHeureDebutPartie;
	}
	public LocalDateTime getDateHeureFinPartie() {
		return dateHeureFinPartie;
	}
	public void setDateHeureFinPartie(LocalDateTime dateHeureFinPartie) {
		this.dateHeureFinPartie = dateHeureFinPartie;
	}
	
	public int getPositionArrivee() {
		return positionArrivee;
	}
	public void setPositionArrivee(int positionArrivee) {
		this.positionArrivee = positionArrivee;
	}
	public int getNbEtoilesGagnees() {
		return nbEtoilesGagnees;
	}
	public void setNbEtoilesGagnees(int nbEtoilesGagnees) {
		this.nbEtoilesGagnees = nbEtoilesGagnees;
	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}
	

	public boolean isVictoire() {
		return victoire;
	}

	public void setVictoire(boolean victoire) {
		this.victoire = victoire;
	}




	@Override
	public String toString() {
		return "Historique [id=" + id + ", version=" + version + ", dateHeureDebutPartie=" + dateHeureDebutPartie
				+ ", dateHeureFinPartie=" + dateHeureFinPartie + ", positionArrivee=" + positionArrivee
				+ ", nbEtoilesGagnees=" + nbEtoilesGagnees + ", victoire=" + victoire + ", partie=" + partie
				+ ", joueur=" + joueur + ", personnage=" + personnage + ", getJoueur()=" + getJoueur()
				+ ", getPersonnage()=" + getPersonnage() + ", getDateHeureDebutPartie()=" + getDateHeureDebutPartie()
				+ ", getDateHeureFinPartie()=" + getDateHeureFinPartie() + ", getPositionArrivee()="
				+ getPositionArrivee() + ", getNbEtoilesGagnees()=" + getNbEtoilesGagnees() + ", getVersion()="
				+ getVersion() + ", getPartie()=" + getPartie() + ", getId()=" + getId() + ", isVictoire()="
				+ isVictoire() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	
	

}
