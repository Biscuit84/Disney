package disney.classe;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;


@Entity
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
	
	
	
	@OneToMany
	private List <Partie> parties;
	


	public Historique(Long id, LocalDateTime dateHeureDebutPartie, LocalDateTime dateHeureFinPartie, int positionArrivee,
			int nbEtoilesGagnees, List<Partie> parties) {
		super();
		this.id = id;
		this.dateHeureDebutPartie = dateHeureDebutPartie;
		this.dateHeureFinPartie = dateHeureFinPartie;
		this.positionArrivee = positionArrivee;
		this.nbEtoilesGagnees = nbEtoilesGagnees;
		this.parties = parties;
	}

	


	public Historique(LocalDateTime dateHeureDebutPartie, LocalDateTime dateHeureFinPartie, int positionArrivee, int nbEtoilesGagnees,
			List<Partie> parties) {
		super();
		this.dateHeureDebutPartie = dateHeureDebutPartie;
		this.dateHeureFinPartie = dateHeureFinPartie;
		this.positionArrivee = positionArrivee;
		this.nbEtoilesGagnees = nbEtoilesGagnees;
		this.parties = parties;
	}




	public Historique() {
		
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





	


	@Override
	public String toString() {
		return "Historique [ positionArrivee="+ positionArrivee + ", nbEtoilesGagnees=" + nbEtoilesGagnees +  "]";
	}





	public List<Partie> getParties() {
		return parties;
	}





	public void setParties(List<Partie> parties) {
		this.parties = parties;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public Long getId() {
		return id;
	}
	
	

	
	
	

	
	
	
	
	
	
	
	

}
