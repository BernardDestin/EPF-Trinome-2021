package code;

public class PionPyramideSpe extends PionPyramide{

	public PionPyramideSpe(int equipe, Plateau plateau) {
		//Constructeur de la classe
		super(equipe, plateau);
		this.imageLien="001_Pions/"+this.equipe+"_T_S";
		final int [][] tab = {{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0},{0,1,0,1,0},{0,0,0,0,0}};
		this.tabMove = Pion.copieTab(tab);
		this.type = 4;
	}

	@Override
	public int[][] ouPeutJouer(int posX, int posY){
		//Fonction qui retourne un tableau representant les deplacements possibles autour d'un pion
		int [][] tab = super.ouPeutJouer(posX, posY).clone();
		
		//Verification des possibilites de sauter par dessus un Pion
		if(0<=posX-2 && posX-2<11 && 0<=posY-2 && posY-2<11) {
			if(this.plateau.getTableauPion()[posY-1][posX-1] != null) {
				if(this.plateau.getTableauPion()[posY-1][posX-1].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1)) {
					tab[0][0] = 1;
				}
			}
		}
		if(0<=posX+2 && posX+2<11 && 0<=posY-2 && posY-2<11) {
			if(this.plateau.getTableauPion()[posY-1][posX+1] != null) {
				if(this.plateau.getTableauPion()[posY-1][posX+1].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1)) {
					tab[0][4] = 1;
				}
			}
		}
		if(0<=posX-2 && posX-1<12 && 0<=posY+2 && posY+2<11) {
			if(this.plateau.getTableauPion()[posY+1][posX-1] != null) {
				if(this.plateau.getTableauPion()[posY+1][posX-1].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1)) {
					tab[4][0] = 1;
				}
			}
		}
		if(0<=posX+2 && posX+2<11 && 0<=posY+2 && posY+2<11) {
			if(this.plateau.getTableauPion()[posY+1][posX+1] != null) {
				if(this.plateau.getTableauPion()[posY+1][posX+1].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1)) {
					tab[4][4] = 1;
				}
			}
		}
		return tab;
	}
}