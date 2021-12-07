package disney.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_Compte")
@JsonView(Views.ViewsCommon.class)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "role")
@JsonSubTypes({
	@Type(value=Joueur.class, name="joueur"),
	@Type(value=Admin.class, name="admin")
})
public abstract class Compte  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(unique = true)
	protected String login;
	
	@Version
	protected int version;
	
	protected String password;
	
	protected String nom;
	
	protected String prenom;
	@Column(unique = true)
	protected String mail;
	
	@ManyToOne
	protected Avatar avatar;
	
	private boolean enable;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dateLastConnexion;
	
	

	
		
	public Compte(String login, String password, String nom, String prenom, String mail) {
	
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}

	



	public Compte() {
		
	}



	public Compte(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}

	


	public int getVersion() {
		return version;
	}





	public void setVersion(int version) {
		this.version = version;
	}





	public Avatar getAvatar() {
		return avatar;
	}





	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	


	

//security
//
//	public boolean isEnable() {
//		return enable;
//	}
//
//
//
//
//
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}
//
//
//	public List<String> getStringRoles(Compte c) {
//		List<String> stringRoles = new ArrayList<>();
//
//		if (c instanceof Admin) {
//			stringRoles.add("ROLE_Admin");
//		}
//		if (c instanceof Joueur) {
//			stringRoles.add("ROLE_Joueur");
//		}
//
//		return stringRoles;
//	}



	public boolean isEnable() {
		return enable;
	}





	public void setEnable(boolean enable) {
		this.enable = enable;
	}





	public LocalDate getDateLastConnexion() {
		return dateLastConnexion;
	}





	public void setDateLastConnexion(LocalDate dateLastConnexion) {
		this.dateLastConnexion = dateLastConnexion;
	}





	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", mail=" + mail + "]";
	}
	
	
	
	
	
}
