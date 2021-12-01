package disney.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import disney.model.Etoile;
import disney.model.Vie;
import disney.model.Views;

@JsonView(Views.ViewsCommon.class)
public class BoutiqueDto {

	private List<PersonnageDto> personnages;

	private List<Vie> listVies;

	private List<Etoile> listEtoiles;

	public BoutiqueDto(List<PersonnageDto> personnages, List<Vie> listVies, List<Etoile> listEtoiles) {
		super();
		this.personnages = personnages;
		this.listVies = listVies;
		this.listEtoiles = listEtoiles;
	}

	public List<PersonnageDto> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(List<PersonnageDto> personnages) {
		this.personnages = personnages;
	}

	public List<Vie> getListVies() {
		return listVies;
	}

	public void setListVies(List<Vie> listVies) {
		this.listVies = listVies;
	}

	public List<Etoile> getListEtoiles() {
		return listEtoiles;
	}

	public void setListEtoiles(List<Etoile> listEtoiles) {
		this.listEtoiles = listEtoiles;
	}

	@Override
	public String toString() {
		return "BoutiqueToFront [personnages=" + personnages + ", listVies=" + listVies + ", listEtoiles=" + listEtoiles
				+ "]";
	}

}
