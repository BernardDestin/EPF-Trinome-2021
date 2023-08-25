package code;
public class PionDemiSphere extends Pion{

	public PionDemiSphere(int equipe, Plateau plateau) {
		//Constructeur de la classe
		super(equipe, plateau);
		this.imageLien="001_Pions/"+this.equipe+"_S_N";
		final int [][] tab = {{1,0,1,0,1},{0,0,0,0,0},{1,0,0,0,1},{0,0,0,0,0},{1,0,1,0,1}};
		this.tabMove = Pion.copieTab(tab);
		this.type = 3;
	}

        @Override
	public int[][] ouPeutJouer(int posX, int posY){
		//Fonction qui retourne un tableau representant les deplacements possibles autour d'un pion
		int [][] tab = super.ouPeutJouer(posX, posY).clone();

		//Ajout de l'impossibilite de capturer un Pion (la demiSphere ne peut pas capturer)
		for(int x=0; x<5; x++) {
			for(int y=0; y<5; y++) {
				if(0 <= posY+y-2 && posY+y-2 < 11 &&  0 <= posX+x-2 && posX+x-2 < 11) {
					if(this.plateau.getTableauPion()[posY+y-2][posX+x-2] != null) {
						if(this.plateau.getTableauPion()[posY+y-2][posX+x-2].getEquipe() != this.equipe) {
							tab[y][x] = 0;
						}
					}
				}
			}
		}
		//Ajout de l'impossibilite de sauter par dessus un Pion
		//Haut
		if(0 <= posY+1 && posY+1 < 11) {
			if(this.plateau.getTableauPion()[posY+1][posX] != null) {
				tab[4][2] = 0;
			}
		}
		//Droit
		if(0 <= posX+1 && posX+1 < 11) {
			if(this.plateau.getTableauPion()[posY][posX+1] != null) {
				tab[2][4] = 0;
			}
		}
		//Gauche
		if(0 <= posX-1 && posX-1 < 11) {
			if(this.plateau.getTableauPion()[posY][posX-1] != null) {
				tab[2][0] = 0;
			}
		}
		//Bas
		if(0 <= posY-1 && posY-1 < 11) {
			if(this.plateau.getTableauPion()[posY-1][posX] != null) {
				tab[0][2] = 0;
			}
		}
		//Haut-Droit
		if(0 <= posY+1 && posY+1 < 11 &&  0 <= posX+1 && posX+1 < 11) {
			if(this.plateau.getTableauPion()[posY+1][posX+1] != null) {
				tab[4][4] = 0;
			}
		}
		//Haut-Gauche
		if(0 <= posY+1 && posY+1 < 11 &&  0 <= posX-1 && posX-1 < 11) {
			if(this.plateau.getTableauPion()[posY+1][posX-1] != null) {
				tab[0][4] = 0;
			}
		}
		//Bas-Droite
		if(0 <= posY-1 && posY-1 < 11 &&  0 <= posX+1 && posX+1 < 11) {
			if(this.plateau.getTableauPion()[posY-1][posX+1] != null) {
				tab[4][0] = 0;
			}
		}
		//Bas-Gauche
		if(0 <= posY-1 && posY-1 < 11 &&  0 <= posX-1 && posX-1 < 11) {
			if(this.plateau.getTableauPion()[posY-1][posX-1] != null) {
				tab[0][0] = 0;
			}
		}
		return tab;
	}
}