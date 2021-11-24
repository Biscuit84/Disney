package disney.classe;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
public  class Cases {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nom;
	private int parameter;
	@Version
	private int version;
	
	@Enumerated(EnumType.STRING)
	private TypeCase typeCase;
	
	public Cases() {
	}
		
	public Cases(String nom) {
		
	
		this.nom = nom;
	}
	


	public Cases( String nom, TypeCase typeCase) {
		super();
		this.nom = nom;
		this.typeCase = typeCase;
	}

	public Cases(String nom, int parameter, int version) {
		super();
		this.nom = nom;
		this.parameter = parameter;
		this.version = version;
	}

	public int getParameter() {
		return parameter;
	}

	public void setParameter(int parameter) {
		this.parameter = parameter;
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




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Cases [nom=" + nom + ", parameter=" + parameter + ", version=" + version + "]";
	}





	
	
}
