package code;
public class PionPyramide extends Pion{
	
	public PionPyramide(int equipe, Plateau plateau) {
		//Constructeur de la classe
		super(equipe, plateau);
		this.imageLien="001_Pions/"+this.equipe+"_T_N";
		this.type = 1;
		final int [][] tab = {{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0}};
		this.tabMove = Pion.copieTab(tab);
	}
	
	@Override
	public int[][] ouPeutJouer(int posX, int posY){
		//Fonction qui retourne un tableau representant les deplacements possibles autour d'un pion
		//Presente ici en cas de modification future
		int [][] tab = super.ouPeutJouer(posX, posY).clone();
		return tab;
	}
}
