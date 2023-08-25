package code;

public class PionCubeSpe extends PionCube{
	private boolean sousJoker;

	public PionCubeSpe(int equipe, Plateau plateau) {
		//Constructeur de la classe
		super(equipe, plateau);
		this.imageLien="001_Pions/"+this.equipe+"_C_S";
		final int [][] tab = {{0,0,0,0,0},{0,0,1,0,0},{0,1,8,1,0},{0,0,1,0,0},{0,0,0,0,0}};
		this.tabMove = Pion.copieTab(tab);
		this.sousJoker =  true;
		this.type = 5;
	}

        @Override
	public void verifJoker(int x, int y) {
		//Fonction qui permet de verifier l'etat du Joker d'un PionCube Special et de le modifier si besoin
		if (this.sousJoker) {
			if (!this.checkConditionsJoker(x, y)) {
				this.sousJoker = !this.sousJoker;
			}
		} else {
			if (!((x==0 && 3<y && y<7 && this.equipe == 1) || (x==10 && 3<y && y<7 && this.equipe == 2))) {
				if (this.checkConditionsJoker(x, y)) {
					this.sousJoker = true;
				}
			}
		}
	}

	private boolean checkConditionsJoker(int x, int y) {
		//Verification des quatres diagonales
		int compt1 = 0;
		int compt2 = 0;
		for (int i=0; x+i<11 && y+i <11; i++) {
			if(this.plateau.getTableauPion()[y+i][x+i] != null) {
				if(this.plateau.getTableauPion()[y+i][x+i].getEquipe() == this.equipe) {
					compt1++;
				}
			}
		}
		for (int i=0; x+i<11 && 0 <= y-i; i++) {
			if(this.plateau.getTableauPion()[y+i][x+i] != null) {
				if(this.plateau.getTableauPion()[y-i][x+i].getEquipe() == this.equipe) {
					compt2++;
				}
			}
		}
		for (int i=0; 0 <= x-i && y+i <11; i++) {
			if(this.plateau.getTableauPion()[y+i][x+i] != null) {
				if(this.plateau.getTableauPion()[y+i][x-i].getEquipe() == this.equipe) {
					compt2++;
				}
			}
		}
		for (int i=0; 0 <= x-i && 0 <= y-i; i++) {
			if(this.plateau.getTableauPion()[y+i][x+i] != null) {
				if(this.plateau.getTableauPion()[y-i][x-i].getEquipe() == this.equipe) {
					compt1++;
				}
			}
		}
            return compt1 > 2 || compt2 > 2;
	}

        @Override
	public int[][] ouPeutJouer(int posX, int posY){
		int [][] tab = super.ouPeutJouer(posX, posY).clone();

		if(!this.sousJoker && (this.plateau.getTableauCase()[posY][posX].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1))) {
			int[][] tabVide = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
			return tabVide;
		}
		return tab;
	}
}