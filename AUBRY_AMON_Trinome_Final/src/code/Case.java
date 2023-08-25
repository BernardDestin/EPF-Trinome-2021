package code;
import java.awt.Color;

public class Case {
	private final Color couleur; //Definit la couleur d'une case
	private final int equipe; //L'equipe de la case (0 neutre, 1 equipe1 et 2 equipe2)
	
	public Case(Color couleur, int equipe) {
		//Constructeur d'une case
		this.equipe = equipe;
		this.couleur = couleur;
	}
	
	//Acces aux informations
	public int getEquipe() {return this.equipe;}
	public Color getColor() {
		return this.couleur;
	}
	@Override
	public String toString() {
		if(this.couleur.getRed()==0 && this.couleur.getBlue()==240 && this.couleur.getGreen()==240) {
			return "X"+this.equipe;
		} else {
			return "__";
		}
	}
}