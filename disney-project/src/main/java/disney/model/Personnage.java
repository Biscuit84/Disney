package disney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonView(Views.ViewsCommon.class)
public class Personnage  {
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String prince;
	
	private String mechant;
	
	private String pouvoir;
//	@ManyToOne
	private int position;

	private int prixAchatPerso;
	
	private String avatar;
	
	@ManyToOne
	@JoinColumn(name="id_partie")
	@JsonView(Views.ViewsPersonnageDetail.class)
	private Partie partie;
	
	public Personnage(String nom, String prince, String mechant, String pouvoir) {
		this.nom = nom;
		this.prince = prince;
		this.mechant = mechant;
		this.pouvoir = pouvoir;
	}
	
	public Personnage(String nom, String prince, String mechant, String pouvoir, int prixAchatPerso) {
		this.nom = nom;
		this.prince = prince;
		this.mechant = mechant;
		this.pouvoir = pouvoir;
		this.prixAchatPerso=prixAchatPerso;
	}
	
	public Personnage() {
		
	}

	


	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPrixAchatPerso() {
		return prixAchatPerso;
	}

	public void setPrixAchatPerso(int prixAchatPerso) {
		this.prixAchatPerso = prixAchatPerso;
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


	public String getPrince() {
		return prince;
	}


	public void setPrince(String prince) {
		this.prince = prince;
	}


	public String getMechant() {
		return mechant;
	}


	public void setMechant(String mechant) {
		this.mechant = mechant;
	}


	public String getPouvoir() {
		return pouvoir;
	}


	public void setPouvoir(String pouvoir) {
		this.pouvoir = pouvoir;
	}


	@Override
	public String toString() {
		return "Personnage [id=" + id + ", nom=" + nom + ", prince=" + prince + ", mechant=" + mechant + ", pouvoir="
				+ pouvoir + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		return id == other.id;
	}

	
	
	
	
	

}
