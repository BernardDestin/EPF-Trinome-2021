package code;

public class PionDemiSphereSpe extends PionDemiSphere{

	public PionDemiSphereSpe(int equipe, Plateau plateau) {
		//Constructeur de la classe
		super(equipe, plateau);
		this.imageLien="001_Pions/"+this.equipe+"_S_S";
		final int [][] tab = {{1,0,1,0,1},{0,0,0,0,0},{1,0,0,0,1},{0,0,0,0,0},{1,0,1,0,1}};
		this.tabMove = Pion.copieTab(tab);
		this.type = 6;
	}

        @Override
	public int[][] ouPeutJouer(int posX, int posY){
		//Fonction qui retourne un tableau representant les deplacements possibles autour d'un pion
		int [][] tab = super.ouPeutJouer(posX, posY).clone();
		//Verification des possibilites de deplacement
		boolean pasPossible = true;

		if(this.equipe == 1) {
			for(int x=0; x<5 && pasPossible; x++) {
				for(int y=2; y<5 && pasPossible; y++) {
					if(tab[y][x] == 1) {
						pasPossible = false;
					}
				}
			}
			//Retrait du recul s'il est possible d'avancer
			if(!pasPossible) {
				for(int x=0; x<5; x++) {
					for(int y=0; y<2; y++) {
						tab[y][x] = 0;
					}
				}
			}
		} else {
			for(int x=0; x<5 && pasPossible; x++) {
				for(int y=0; y<3 && pasPossible; y++) {
					if(tab[y][x] == 1) {
						pasPossible = false;
					}
				}
			}
			//Retrait du recul s'il est possible d'avancer
			if(!pasPossible) {
				for(int x=0; x<5; x++) {
					for(int y=3; y<5; y++) {
						tab[y][x] = 0;
					}
				}
			}
		}
		return tab;
	}
}