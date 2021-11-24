package disney.classe;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_Compte")
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
	
	
	
	

	
		
	public Compte(String login, String password, String nom, String prenom, String mail) {
	
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
	}

	



	public Compte() {
		
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



	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", mail=" + mail + "]";
	}
	
	
	
	
	
}
