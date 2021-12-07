package disney.dto;

public class TourDeJeuDto {
	private int valueDice1;
	private int valueDice2;
	private int tourDeJeuEnCours;
	private int positionFutureJoueur;
	private boolean finPartie = false;
	private boolean effetAActiver = false;
	private int deplacement;
	
	public int getValueDice1() {
		return valueDice1;
	}
	public void setValueDice1(int valueDice1) {
		this.valueDice1 = valueDice1;
	}
	public int getValueDice2() {
		return valueDice2;
	}
	public void setValueDice2(int valueDice2) {
		this.valueDice2 = valueDice2;
	}
	public int getTourDeJeuEnCours() {
		return tourDeJeuEnCours;
	}
	public void setTourDeJeuEnCours(int tourDeJeuEnCours) {
		this.tourDeJeuEnCours = tourDeJeuEnCours;
	}
	public int getPositionFutureJoueur() {
		return positionFutureJoueur;
	}
	public void setPositionFutureJoueur(int positionFutureJoueur) {
		this.positionFutureJoueur = positionFutureJoueur;
	}
	public boolean isFinPartie() {
		return finPartie;
	}
	public void setFinPartie(boolean finPartie) {
		this.finPartie = finPartie;
	}
	public boolean isEffetAActiver() {
		return effetAActiver;
	}
	public void setEffetAActiver(boolean effetAActiver) {
		this.effetAActiver = effetAActiver;
	}
	public int getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	
	
	
	
	
}
