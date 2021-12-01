package disney.dto;

import java.util.List;

/**
 * recupere ce qu'il y a dans le panier avec l'id
 */
public class BoutiquePersoAndLifeDto {

	private List<Long> idPersonnages;

	private List<Long> idVies;

	public List<Long> getIdPersonnages() {
		return idPersonnages;
	}

	public void setIdPersonnages(List<Long> idPersonnages) {
		this.idPersonnages = idPersonnages;
	}

	public List<Long> getIdVies() {
		return idVies;
	}

	public void setIdVies(List<Long> idVies) {
		this.idVies = idVies;
	}

	public BoutiquePersoAndLifeDto(List<Long> idPersonnages, List<Long> idVies) {
		super();
		this.idPersonnages = idPersonnages;
		this.idVies = idVies;
	}

	@Override
	public String toString() {
		return "BoutiqueFromFront [idPersonnages=" + idPersonnages + ", idVies=" + idVies + "]";
	}

}
