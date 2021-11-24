package disney.classe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Plateau   {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column(columnDefinition = "VARCHAR(50)")
	private String nom;
	@Column(name="nombre_de_cases")
	private int nbCases;
	
	@OneToMany(mappedBy = "plateau")
	private List<CasesPlateau> cases= new ArrayList<CasesPlateau>();
	
	
	public Plateau(String nom, int nbCases) {
		this.nom = nom;
		this.nbCases = nbCases;
	}
	
	


	public Plateau(String nom, int nbCases, List<CasesPlateau> cases) {
		super();
		this.nom = nom;
		this.nbCases = nbCases;
		this.cases = cases;
	}




	public Plateau() {
		
	}

	

	public List<CasesPlateau> getCases() {
		return cases;
	}




	public void setCases(List<CasesPlateau> cases) {
		this.cases = cases;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getNbCases() {
		return nbCases;
	}


	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}


	@Override
	public String toString() {
		return "Plateau [id=" + id + ", nom=" + nom + ", nbCases=" + nbCases + "]";
	}
	
	
}
