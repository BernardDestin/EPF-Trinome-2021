package code;

public class Pion {
	protected String imageLien;//Lien vers le fichier de l'image du pion
	protected int equipe;//A qui appartient le pion
	protected int type;//Assigne un type au pion: 1 pour pyramide, 2 pour cube, 3 pour demisphere, ect...
	protected Plateau plateau;
	protected int [][] tabMove;

	public Pion(int equipe, Plateau plateau) {
		//Constructeur de la classe
		this.imageLien="image d'erreur";
		this.equipe = equipe;
		this.type=0;
		final int [][] tab = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		this.tabMove = Pion.copieTab(tab);
		this.plateau = plateau;
	}

	public static int[][] copieTab(int[][] tab) {
		//Fonction qui permet de copier un tableau a deux dimensions
		int length = tab.length;
		int[][] tab2 = new int[length][tab[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(tab[i], 0, tab2[i], 0, tab[i].length);
		}
		return tab2;
	}

	public int[][] ouPeutJouer(int posX, int posY){
		//Fonction qui retourne un tableau representant les deplacements possibles autour d'un pion
		//Redefinie dans les sous-classes
		int [][] tab = Pion.copieTab(this.tabMove);

		////Retrait des deplacements impossibles:
		//En cas de sortie de plateau
		for(int x=0; x<5; x++) {
			for(int y=0; y<5; y++) {
				if(0 > posY+y-2 || posY+y-2 > 10 ||  0 > posX+x-2 || posX+x-2 > 10) {
					tab[y][x] = 0;
				}
			}
		}
		//En cas de presence d'un pion sur une case rouge de son equipe
		for(int x=0; x<5; x++) {
			for(int y=0; y<5; y++) {
				if(0 <= posY+y-2 && posY+y-2 < 11 &&  0 <= posX+x-2 && posX+x-2 < 11) {
					if(this.plateau.getTableauPion()[posY+y-2][posX+x-2] != null && this.plateau.getTableauCase()[posY+y-2][posX+x-2].getEquipe() == (this.plateau.J1DoitJouer()?1:2)) {
						tab[y][x] = 0;
					}
				}
			}
		}
		//En cas de presence d'un pion allie
		for(int x=0; x<5; x++) {
			for(int y=0; y<5; y++) {
				if(0 <= posY+y-2 && posY+y-2 < 11 &&  0 <= posX+x-2 && posX+x-2 < 11) {
					if(this.plateau.getTableauPion()[posY+y-2][posX+x-2] != null) {
						if(this.plateau.getTableauPion()[posY+y-2][posX+x-2].getEquipe() == this.equipe) {
							tab[y][x] = 0;
						}
					}
				}
			}
		}
		if(this.plateau.getTableauPion()[posY][posX].getType() != 5 && (this.plateau.getTableauCase()[posY][posX].getEquipe() == (this.plateau.J1DoitJouer()? 2 : 1))) {
			int[][] tabVide = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
			return tabVide;
		}
		return tab;
	}

	//Acces aux informations
	public int getEquipe() {return this.equipe;}
	public int getType() {return this.type;}
	public String getLien() {return this.imageLien;}
	public String toSave() {
		//Renvoie les donnees de sauvegarde du pion en txt
		String txt = "";
		txt += this.type;
		txt += ":";
		txt += this.equipe;
		return txt;
	}
	@Override
	public String toString() {
            //Permet d'afficher une representation du Pion lors d'une partie
            switch (this.type) {
                case 1:
                    return "P";
                case 2:
                    return "C";
                case 3:
                    return "D";
                case 4:
                    return "T";
                case 5:
                    return "R";
                default:
                    return "S";
            }
	}

	//Overrides
	public void verifJoker(int x, int y) {}
}