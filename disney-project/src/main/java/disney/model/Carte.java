package disney.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonView(Views.ViewsCommon.class)
public class Carte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String nom;
	
	@Enumerated(EnumType.STRING)
	private TypeCarte typeCarte;
	
	public Carte(String nom, TypeCarte typeCarte) {
		
		this.nom = nom;
		this.typeCarte=typeCarte;
	}

	public Carte() {
	
	}
	
	

	public TypeCarte getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(TypeCarte typeCarte) {
		this.typeCarte = typeCarte;
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
		return "Carte [id=" + id + ", nom=" + nom + "]";
	}
	
	

	
	
	
}
