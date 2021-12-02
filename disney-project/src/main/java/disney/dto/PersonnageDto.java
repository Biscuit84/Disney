package disney.dto;

import com.fasterxml.jackson.annotation.JsonView;

import disney.model.Personnage;
import disney.model.Views;

/**
 * Pour un joueur, boolean isAlreadyBuyed est à true si le joueur possede deja le joueur, sinon il est à false
 */
@JsonView(Views.ViewsCommon.class)
public class PersonnageDto {

	private Personnage personnage;
	private boolean persoDejaEnPossession;

	public PersonnageDto(Personnage personnage, boolean persoDejaEnPossession) {
		super();
		this.personnage = personnage;
		this.persoDejaEnPossession = persoDejaEnPossession;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public boolean isPersoDejaEnPossession() {
		return persoDejaEnPossession;
	}

	public void setPersoDejaEnPossession(boolean persoDejaEnPossession) {
		this.persoDejaEnPossession = persoDejaEnPossession;
	}

	@Override
	public String toString() {
		return "PersonnageDto [personnage=" + personnage + ", persoDejaEnPossession=" + persoDejaEnPossession + "]";
	}



}
