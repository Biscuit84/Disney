package disney.classe;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Joueur extends Compte{

	private String pseudo;

	private String level="noob";

	private int life=3;

	private int nbEtoiles=100;


	@OneToOne
	private Historique historique;


	//	@OneToMany (mappedBy = "j")
	//	private List<Partie> listePartie;

	@OneToMany(mappedBy = "joueur")
	List<PersoObtenu> persos;



	public Joueur(String login, String password, String nom, String prenom, String mail, String pseudo,
			String level, int life) {
		super(login, password, nom, prenom, mail);
		this.pseudo = pseudo;
		this.level = level;
		this.life = life;
	}


	public Joueur(String login, String password, String nom, String prenom, String mail, String pseudo) {
		super(login, password, nom, prenom, mail);
		this.pseudo = pseudo;
		this.level="noob";
		this.life=3;
		this.nbEtoiles=100;
		
	}


	public Joueur(String pseudo) {
		super();
		this.pseudo = pseudo;
	}





	public Joueur() {
		super();

	}
	
	public int getNbEtoiles() {
		return nbEtoiles;
	}

	public void setNbEtoiles(int nbEtoiles) {
		this.nbEtoiles = nbEtoiles;
	}

	public List<PersoObtenu> getPersos() {
		return persos;
	}



	public void setPersos(List<PersoObtenu> persos) {
		this.persos = persos;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
	
	@Override
	public String toString() {
		return "Joueur [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", mail=" + mail + ", pseudo=" + pseudo + ", level=" + level + ", life=" + life
				+ ", historique=" + historique + "]";
	}















}
