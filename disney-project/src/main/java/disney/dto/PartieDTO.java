package disney.dto;

import disney.model.Personnage;
import disney.model.Plateau;

public class PartieDTO {

	private Long id;
	private int version;
	private Personnage monPersonnage;
	private Plateau plateau;
	private int nbTourDeJeu;
	private Long idPlateau;
	private Long idPerso;
	private Long idJoueur;

	
	public Long getIdPlateau() {
		return idPlateau;
	}

	public void setIdPlateau(Long idPlateau) {
		this.idPlateau = idPlateau;
	}

	public Long getIdPerso() {
		return idPerso;
	}

	public void setIdPerso(Long idPerso) {
		this.idPerso = idPerso;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
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

	public Personnage getMonPersonnage() {
		return monPersonnage;
	}

	public void setMonPersonnage(Personnage monPersonnage) {
		this.monPersonnage = monPersonnage;
	}

	public int getNbTourDeJeu() {
		return nbTourDeJeu;
	}

	public void setNbTourDeJeu(int nbTourDeJeu) {
		this.nbTourDeJeu = nbTourDeJeu;
	}

	
	

	
	
	public Long getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(Long idJoueur) {
		this.idJoueur = idJoueur;
	}

	

	
	public PartieDTO(Long id, int version, Personnage monPersonnage, Plateau plateau, int nbTourDeJeu, Long idPlateau,
			Long idPerso, Long idJoueur) {
		this.id = id;
		this.version = version;
		this.monPersonnage = monPersonnage;
		this.plateau = plateau;
		this.nbTourDeJeu = nbTourDeJeu;
		this.idPlateau = idPlateau;
		this.idPerso = idPerso;
		this.idJoueur = idJoueur;
	}

	public PartieDTO() {
	}

}
