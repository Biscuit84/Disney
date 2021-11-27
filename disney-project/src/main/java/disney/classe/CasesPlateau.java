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
public class CasesPlateau {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCasePlateau;
	@ManyToOne
	@JsonView(Views.CasesPlateauDetail.class)
	private Plateau plateau;
	@OneToOne
	@JsonView(Views.CasesPlateauDetail.class)
	private Cases uneCase;
	
	@OneToMany(mappedBy = "position")
	@JsonView(Views.CasesPlateauDetail.class)
	private List<Personnage> joueurs;
	
	private int ordreCase;	
	
	@Version
	private int version;
	
	public CasesPlateau(Plateau plateau, Cases uneCase, int ordreCase) {
		this.plateau = plateau;
		this.uneCase = uneCase;
		this.ordreCase = ordreCase;
	}
	public CasesPlateau() {
		super();
	}

	
	public Long getIdCasePlateau() {
		return idCasePlateau;
	}
	public void setIdCasePlateau(Long idCasePlateau) {
		this.idCasePlateau =  idCasePlateau;
	}
	public Plateau getPlateau() {
		return plateau;
	}
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	public Cases getUneCase() {
		return uneCase;
	}
	public void setUneCase(Cases uneCase) {
		this.uneCase = uneCase;
	}
	public int getOrdreCase() {
		return ordreCase;
	}
	public void setOrdreCase(int ordreCase) {
		this.ordreCase = ordreCase;
	}
	
	@Override
	public String toString() {
		return "CasesPlateau [idCasePlateau=" + idCasePlateau + ", plateau=" + plateau + ", uneCase=" + uneCase
				+ ", ordreCase=" + ordreCase + "]";
	}

	
	
	
	
	
}
