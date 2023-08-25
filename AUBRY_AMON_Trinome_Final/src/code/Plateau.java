package code;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import window.I_principal;
import window.I_FinalFrame;

public class Plateau {
	private Pion[][] tableauPion; //[y][x] Matrice qui permet de situer les pions sur le plateau
	private final Case[][] tableauCase; //[y][x] Matrice qui permet de situer les cases sur le Plateau
	private int round; //Compteur du nombre de tours de la partie
	private boolean j1DoitJouer; //Indique quel joueur doit jouer : true = Joueur1, false = Joueur2
	private int gagnant;//definit le gagnant: 0:Pas encore de gagnant, 1:J1 et 2:J2
	private final Scanner sc;//scanner associe au plateau
	
	private String pseudoJ1;//Pseudonyme du joueur 1
	private String pseudoJ2;//Pseudonyme du joueur 2
	
	//variables Interface
	private boolean selecCase;//Boolean permettant de savoir si une case du plateau de la fenetre est deja selectionnee
	private int selecX;//Integer permettant de savoir la position de la case selectionnee en X
	private int selecY;//Integer permettant de savoir la position de la case selectionnee en Y
	private int [][] tab2;//Tableau utilise lors de la gestion des deplacements des pions dans la partie graphique du projet 
	
	public Plateau(boolean spe, String pseudoJ1, String pseudoJ2) {
		//Initialisation des variables numeriques
		this.round = 0;
		this.j1DoitJouer = true;
		//Initialisation des tableaux
		if(spe) {
			this.tableauPion = this.tableauPionSpeInit();
		} else {
			this.tableauPion = this.tableauPionInit();
		}
		this.tableauCase = this.tableauCaseInit();
		//Initialisation du scanner
		this.sc = new Scanner(System.in);
		
		//Initialisation des Pseudos
		this.pseudoJ1 = pseudoJ1;
		this.pseudoJ2 = pseudoJ2;
		
		//Initialisation variables Interface
		this.selecCase = false;
		this.selecX = 0;
		this.selecY = 0;
		this.tab2 = new int[5][5];
	}

	//Procedures d'une partie avec interface graphique
	public void event(String txt, I_principal fen) {
		String[] tab = txt.split(":");
		int x = Integer.valueOf(tab[0])-1;
		int y = Integer.valueOf(tab[1])-1;
		//Verifie si une case a deja ete selectionnee - selectionne le pion s'il fait partie de l'equipe du joueur
		if(!this.selecCase) {
			if(this.tableauPion[y][x]!=null) {
				if(this.tableauPion[y][x].getEquipe()==(this.j1DoitJouer?1:2)){
					this.selecX = x;
					this.selecY = y;
					this.selecCase = true;
					this.tab2 = this.tableauPion[this.selecY][this.selecX].ouPeutJouer(this.selecX, this.selecY);
					fen.zoneMove(this.tab2, this.selecX, this.selecY);
					return;
				} else {return;}
			} else {return;}
		} else {// Une case est donc selectionnee
			//change de pion selectionne si le deuxieme pion clique appartient au joueur
			if(this.tableauPion[y][x]!=null) {
				if(this.tableauPion[y][x].getEquipe()==(this.j1DoitJouer?1:2)){
					fen.suprZoneMove();
					this.selecX = x;
					this.selecY = y;
					this.tab2 = this.tableauPion[this.selecY][this.selecX].ouPeutJouer(this.selecX, this.selecY);
					fen.zoneMove(this.tab2, this.selecX, this.selecY);
					return;
				}
			}
			if(Math.abs(x-this.selecX) <= 2  && Math.abs(y-this.selecY) <= 2) {
				//System.out.println(""+x+" "+y+" "+this.selecX+" "+this.selecY);
				if(this.valeurTab(this.tab2, y-this.selecY+2, x-this.selecX+2) == 1) {
					fen.suprZoneMove();
					this.DeplacementInterface(this.selecX, this.selecY, x, y);
					fen.updateI(); //Update de la fenetre de jeu
					if(!this.continuer()) {//Verification des conditions de fin de partie
						String gagnantPartie = "";
						if (this.gagnant == 1) {
							gagnantPartie=this.pseudoJ1;
						} else {
							gagnantPartie=this.pseudoJ2;
						}
						//Lancement de la fenetre d'annonce du gagnant
						java.awt.EventQueue.invokeLater(new Runnable() {
						    String gagnant;                         
						    public void run() {
						    	new I_FinalFrame(gagnant).setVisible(true);
						    }
						    public Runnable init(String gagnant) {
						        this.gagnant=gagnant;
						        return(this);
						    }
						}.init(gagnantPartie));
						fen.dispose();
					}
					if(!this.j1DoitJouer) {
						this.round++;
					}
					this.j1DoitJouer = !this.j1DoitJouer;
					this.selecCase = false;
					this.Save("0");
				}
			}
		}
	}

	public void DeplacementInterface(int x1, int y1, int x2, int y2){
		//Fonction qui permet de deplacer un Pion sur le tableau des Pions et sur l'Interface
		if (this.tableauPion[y1][x1].getType() == 4) {
			if (y2-y1 == 2 && x2-x1 == 2){
				this.tableauPion[y1+1][x1+1] = null;
			} else if (y2-y1 == 2 && x2-x1 == -2){
				this.tableauPion[y1+1][x1-1] = null;
			} else if (y2-y1 == -2 && x2-x1 == 2){
				this.tableauPion[y1-1][x1+1] = null;
			} else if (y2-y1 == -2 && x2-x1 == -2){
				this.tableauPion[y1-1][x1-1] = null;
			}
		}
		this.tableauPion[y2][x2] = this.tableauPion[y1][x1];
		this.tableauPion[y1][x1] = null;
	}


	//====================================================================================================
	//Fonctions Principales
	public boolean continuer() {
		//Fonction qui permet de verifier si la partie doit continuer
		//Retourne Vrai s'il n'y a pas encore de gagant
		//Verification du nombre de pions restants par type et equipe
		int[][] tab = {{0, 0, 0},{0, 0, 0}};
		for(int x = 0; x <11; x++) {
			for(int y = 0; y <11; y++) {
				if(this.tableauPion[y][x] != null) {
					if (this.tableauPion[y][x].getType()-1 < 3) {
						tab[this.tableauPion[y][x].getEquipe()-1][this.tableauPion[y][x].getType()-1]++;
					} else {
						tab[this.tableauPion[y][x].getEquipe()-1][this.tableauPion[y][x].getType()-4]++;
					}

				}
			}
		}
		for(int i = 0; i <2; i++) {
			for(int j = 0; j <3; j++) {
				if(tab[i][j] == 0) {
					return false;
				}
			}
		}
		//Verification des cases rouges des deux equipes
		int[][] tab2 = {{0, 0, 0},{0, 0, 0}};
		//Equipe 1
		for(int x=4; x < 7; x++) {
			if(this.tableauPion[0][x] == null) {
				break;
			} else {
				if(this.tableauPion[0][x].getEquipe()==2 && this.tableauPion[0][x].getType()-1 < 3) {
					tab2[1][this.tableauPion[0][x].getType()-1]++;
				} else if (this.tableauPion[0][x].getEquipe()==2 && this.tableauPion[0][x].getType()-4 < 3){
					tab2[1][this.tableauPion[0][x].getType()-4]++;
				}
			}
		}
		//Equipe 2
		for(int x=4; x < 7; x++) {
			if(this.tableauPion[10][x] == null) {
				break;
			} else {
				if(this.tableauPion[10][x].getEquipe()==1 && this.tableauPion[10][x].getType()-1 < 3) {
					tab2[0][this.tableauPion[10][x].getType()-1]++;
				} else if (this.tableauPion[10][x].getEquipe()==1 && this.tableauPion[10][x].getType()-4 < 3){
					tab2[0][this.tableauPion[10][x].getType()-4]++;
				}
			}
		}
		//System.out.println(""+tab2[0][0]+tab2[0][1]+tab2[0][2]+" "+tab2[1][0]+tab2[1][1]+tab2[1][2]);
		if(tab2[1][0] == 1 && tab2[1][1] == 1 && tab2[1][2] == 1) {
			this.gagnant = 1;
			return false;
		} else if(tab2[0][0] == 1 && tab2[0][1] == 1 && tab2[0][2] == 1) {
			this.gagnant = 2;
			return false;
		}
		return true;
	}

	public void Deplacement(int x1, int y1, int x2, int y2){
		//Fonction qui permet de deplacer un Pion sur le tableau des Pions
		if (this.tableauPion[y1][x1].getType() == 4) {
			if (y2-y1 == 2 && x2-x1 == 2){
				this.tableauPion[y1+1][x1+1] = null;
			} else if (y2-y1 == 2 && x2-x1 == -2){
				this.tableauPion[y1+1][x1-1] = null;
			} else if (y2-y1 == -2 && x2-x1 == 2){
				this.tableauPion[y1-1][x1+1] = null;
			} else if (y2-y1 == -2 && x2-x1 == -2){
				this.tableauPion[y1-1][x1-1] = null;
			}
		}
		this.tableauPion[y2][x2] = this.tableauPion[y1][x1];
		this.tableauPion[y1][x1] = null;
	}

	//Procedures d'une partie sans interface graphique
	public void PvPsansGraphismes() {
		//Fonction qui permet de jouer ou de reprendre une partie
		this.affichageMatricielDesPions();
		while(this.continuer()) {
			this.Save("0");
			if(this.j1DoitJouer) {
				this.round++;
				this.joue(1);
				this.j1DoitJouer = false;
			} else {
				this.joue(2);
				this.j1DoitJouer = true;
			}
			this.verifDesJokers();
			this.affichageMatricielDesPions();
		}
		System.out.println("Partie finie, le joueur "+this.gagnant+" est le gagnant en seulement "+this.round+" rounds.");
	}

	public void joue(int joueur) {
		//Fonction qui permet a un joueur de jouer
		//1: Choix du pion a deplacer
		System.out.println("Round "+this.round+". C'est au tour du Joueur "+joueur+".");
		System.out.println("Saisissez les coordonnees du pion que vous souhaitez deplacer (x puis y, 1:1 en haut a gauche):");
		System.out.println("rentrez -1 -1 pour sauvegarder");
		int x1 = this.sc.nextInt()-1;
		int y1 = this.sc.nextInt()-1;
		int[][] tab;
		while(x1 < 0 || y1 < 0 || x1 > 10 || y1 > 10 || this.tableauPion[y1][x1] == null || this.tableauPion[y1][x1].getEquipe() != joueur) {
			if (x1 == -2 && y1 == -2) {
				this.Save("1");
			}
			System.out.println("Saisissez des coordonnees valide d'un pion que vous que vous souhaitez deplacer (x puis y, 1:1 en bas a gauche):");
			System.out.println("rentrez -1 -1 pour sauvegarder");
			x1 = this.sc.nextInt()-1; y1 = this.sc.nextInt()-1;
		}
		//2: Affichage des possibilites et selection de la case cible
		boolean next = true;
		int x2;
		int y2;
		while(next) {
			tab = this.tableauPion[y1][x1].ouPeutJouer(x1, y1);
			System.out.println("Voici les deplacements possibles autour du pion (retenez qu'il ne peux pas sortir du plateau)");
			System.out.println("0: Deplacement impossible, 1: Deplacement possible");
			this.afficher(tab);
			System.out.println("Saisissez les coordonnees cibles (x puis y):");
			x2 = this.sc.nextInt()-1; y2 = this.sc.nextInt()-1;

			while (!(0<=x2 && x2<=11 && 0<=y2 && y2<=11)) {
				if (0<=y2-y1+2 && y2-y1<3 && 0<=x2-x1+2 && x2-x1<3) {
					if (this.valeurTab(tab, y2-y1+2, x2-x1+2) == 1 && Math.abs(x2-x1) <= 2  && Math.abs(y2-y1) <= 2) {
						break;
					}
				}
				if(x2 < 0 && y2 < 0 && x2 > 10 && y2 > 10) {
					if (this.tableauPion[y2][x2] != null) {
						if (this.tableauPion[y2][x2].getEquipe() == (this.j1DoitJouer? 1:2)) {
							break;
						}
					}
				}
				System.out.println("Saisissez des coordonnees cibles valides (x puis y):");
				x2 = this.sc.nextInt()-1; y2 = this.sc.nextInt()-1;
			}

			if(this.valeurTab(tab, y2-y1+2, x2-x1+2) == 1 && Math.abs(x2-x1) <= 2  && Math.abs(y2-y1) <= 2) {
				this.Deplacement(x1, y1, x2, y2);
				next = false;
			} else if(this.tableauPion[y2][x2] != null) {
				if(this.tableauPion[y2][x2].getEquipe() == (this.j1DoitJouer? 1:2)) {
					System.out.println("Changement de Pion");
					x1 = x2; y1 = y2;
				}
			}
		}
	}

	private int valeurTab(int[][] tab, int y, int x) {
		//Fonction qui permet de savoir si un pion a le droit de se deplacer quelque part
		if (y < 0 || x <0 || y>4 || x>4) {
			return 0;
		} else {
			//this.afficher(tab);
			//System.out.println("abc"+x+" "+y);
			//System.out.println("abc"+tab[y][x]);
			return tab[y][x];
		}
	}

	public void afficher(int[][] tab) {
		//Fonction qui affiche les deplacements possibles pour un pion
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab.length; j++) {
				System.out.print(tab[tab.length-1-i][j]+" ");
			}
			System.out.println();
		}
	}

	private void affichageMatricielDesPions() {
		//Fonction qui affiche une representation du plateau
		for(int i=0; i<11; i++) {
			System.out.print((11-i < 10 ? " " : "")+(11-i)+" ");
			for(int j=0; j<11; j++) {
				if( this.tableauPion[10-i][j] != null) {
					System.out.print(" "+this.tableauPion[10-i][j].toString()+this.tableauPion[10-i][j].getEquipe()+" ");
				} else {
					System.out.print(" "+this.tableauCase[10-i][j].toString()+" ");
				}
			}
			System.out.println();
		}
		System.out.println("    1   2   3   4   5   6   7   8   9   10  11");
	}

	private void verifDesJokers() {
		//Fonction qui verifie l'etat des jokers des pions du plateau
		for(int x=0; x<5; x++) {
			for(int y=0; y<5; y++) {
				if (this.tableauPion[y][x] != null) {
					if (this.tableauPion[y][x].getType() == 5) {
						this.tableauPion[y][x].verifJoker(x,y);
					}
				}
			}
		}
	}

	//Aides au constructeur
	public final Pion[][] tableauPionInit() {
		//Fonction qui retourne un tableau des pions initialise

		Pion[][] pions = new Pion[11][11];
		////Placement des pions verts
		//Premiere ligne
		pions[0][0] = new PionPyramide(1, this);   pions[0][1] = new PionPyramide(1, this);   pions[0][2] = new PionCube(1, this);
		pions[0][4] = new PionDemiSphere(1, this); pions[0][5] = new PionDemiSphere(1, this); pions[0][6] = new PionDemiSphere(1, this);
		pions[0][8] = new PionCube(1, this);       pions[0][9] = new PionPyramide(1, this);   pions[0][10] = new PionPyramide(1, this);
		//Deuxieme ligne
		pions[1][0] = new PionPyramide(1, this);   pions[1][1] = new PionCube(1, this);
		pions[1][9] = new PionCube(1, this);       pions[1][10] = new PionPyramide(1, this);
		//Troisieme ligne
		pions[2][0] = new PionCube(1, this);
		pions[2][10] = new PionCube(1, this);

		////Placement des pions rouges
		//Premiere ligne
		pions[10][0] = new PionPyramide(2, this);   pions[10][1] = new PionPyramide(2, this);   pions[10][2] = new PionCube(2, this);
		pions[10][4] = new PionDemiSphere(2, this); pions[10][5] = new PionDemiSphere(2, this); pions[10][6] = new PionDemiSphere(2, this);
		pions[10][8] = new PionCube(2, this);       pions[10][9] = new PionPyramide(2, this);   pions[10][10] = new PionPyramide(2, this);
		//Deuxieme ligne
		pions[9][0] = new PionPyramide(2, this);    pions[9][1] = new PionCube(2, this);
		pions[9][9] = new PionCube(2, this);        pions[9][10] = new PionPyramide(2, this);
		//Troisieme ligne
		pions[8][0] = new PionCube(2, this);
		pions[8][10] = new PionCube(2, this);

		return pions;
	}

	public final Pion[][] tableauPionSpeInit() {
		//Fonction qui retourne un tableau des pions initialise avec des pions speciaux

		Pion[][] pions = new Pion[11][11];
		////Placement des pions verts
		//Premiere ligne
		pions[0][0] = new PionPyramideSpe(1, this);   pions[0][1] = new PionPyramide(1, this);      pions[0][2] = new PionCube(1, this);
		pions[0][4] = new PionDemiSphere(1, this);    pions[0][5] = new PionDemiSphereSpe(1, this); pions[0][6] = new PionDemiSphere(1, this);
		pions[0][8] = new PionCube(1, this);          pions[0][9] = new PionPyramide(1, this);      pions[0][10] = new PionPyramideSpe(1, this);
		//Deuxieme ligne
		pions[1][0] = new PionPyramide(1, this);      pions[1][1] = new PionCubeSpe(1, this);
		pions[1][9] = new PionCubeSpe(1, this);       pions[1][10] = new PionPyramide(1, this);
		//Troisieme ligne
		pions[2][0] = new PionCube(1, this);
		pions[2][10] = new PionCube(1, this);

		////Placement des pions rouges
		//Premiere ligne
		pions[10][0] = new PionPyramideSpe(2, this);  pions[10][1] = new PionPyramide(2, this);     pions[10][2] = new PionCube(2, this);
		pions[10][4] = new PionDemiSphere(2, this);   pions[10][5] = new PionDemiSphereSpe(2, this);pions[10][6] = new PionDemiSphere(2, this);
		pions[10][8] = new PionCube(2, this);         pions[10][9] = new PionPyramide(2, this);     pions[10][10] = new PionPyramideSpe(2, this);
		//Deuxieme ligne
		pions[9][0] = new PionPyramide(2, this);      pions[9][1] = new PionCubeSpe(2, this);
		pions[9][9] = new PionCubeSpe(2, this);       pions[9][10] = new PionPyramide(2, this);
		//Troisieme ligne
		pions[8][0] = new PionCube(2, this);
		pions[8][10] = new PionCube(2, this);

		return pions;
	}

	public final Case[][] tableauCaseInit() {
		//Fonction qui retourne un tableau des cases initialise

		Case[][] cases = new Case[11][11];
		//Coloration des cases noires et blanches
		for (int x=0; x<11; x++) {
			//Coloration des colonnes paires
			for (int y=0; y<11 && x % 2 == 0; y++) {
				if (y % 2 == 0) {
					cases[y][x] = new Case(new Color(0,0,0), 0);
				} else {
					cases[y][x] = new Case(new Color(240,240,240), 0);
				}
			}
			//Coloration des colones impaires
			for (int y=0; y<11 && x % 2 == 1; y++) {
				if (y % 2 == 0) {
					cases[y][x] = new Case(new Color(240,240,240), 0);
				} else {
					cases[y][x] = new Case(new Color(0,0,0), 0);
				}
			}
		}
		//Coloration des cases rouges
		for (int y=4; y<7; y++) {
			cases[0][y] = new Case(new Color(240,0,0), 1);
			cases[10][y] = new Case(new Color(240,0,0), 2);
		}
		return cases;
	}

	//Sauvegarde
	public void Save(String nb) {
		//Fonction de sauvegarde manuelle
		try{
			FileWriter fich = new FileWriter("Saves/Save_Nb_"+nb+".txt", StandardCharsets.UTF_8);
			fich.write(this.toString());
			fich.close();
			if (Integer.valueOf(nb) == 0) {
				System.out.println("Sauvegarde Automatique effectuee");
			} else {
				System.out.println("Sauvegarde effectuee");
			}
		} catch (IOException ex) {
			if (Integer.valueOf(nb) == 0) {
				System.out.println("Erreur de sauvegarde Automatique ");
			} else {
				System.out.println("Erreur de sauvegarde");
			}
			
		}
	}
	
	//Chargement
	public void Load(String nb) {
		//Fonction qui permet de charger une partie
		try{
			FileReader fich = new FileReader("Saves/Save_Nb_"+nb+".txt", StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fich);
			String str;
			
			String[] a = br.readLine().split(":");
			this.pseudoJ1 = a[0];
			this.pseudoJ2 = a[1];
			
			String[] b = br.readLine().split(":");
			this.round = Integer.valueOf(b[0]);
			this.j1DoitJouer = Boolean.valueOf(b[1]);

			String[] c;
			this.tableauPion = new Pion[11][11];
			for (int y = 0; y < 11; y++) {
				for (int x = 0; x < 11; x++) {
					if ((str = br.readLine()) != null) {
						c = str.split(":");
						if (!c[0].isEmpty()) {
							switch(Integer.valueOf(c[0])){
							case 0:
								this.tableauPion[y][x] = new Pion(Integer.valueOf(c[1]), this);
								break;
							case 1:
								this.tableauPion[y][x] = new PionPyramide(Integer.valueOf(c[1]), this);
								break;
							case 2:
								this.tableauPion[y][x] = new PionCube(Integer.valueOf(c[1]), this);
								break;
							case 3:
								this.tableauPion[y][x] = new PionDemiSphere(Integer.valueOf(c[1]), this);
								break;
							case 4:
								this.tableauPion[y][x] = new PionPyramideSpe(Integer.valueOf(c[1]), this);
								break;
							case 5:
								this.tableauPion[y][x] = new PionCubeSpe(Integer.valueOf(c[1]), this);
								break;
							case 6:
								this.tableauPion[y][x] = new PionDemiSphereSpe(Integer.valueOf(c[1]), this);
								break;
							default:
								break;
							}
						}
					}
				}
			}
			fich.close();
			if(this.j1DoitJouer) {
				this.round--;
			}
		} catch (IOException ex) {
			System.out.println("Loading Error");
		}
	}

	//Acces aux informations
	public Pion[][] getTableauPion() {return this.tableauPion;}
	public Case[][] getTableauCase() {return this.tableauCase;}
	public boolean J1DoitJouer() {return this.j1DoitJouer;}
	public String pseudoJ1() {return this.pseudoJ1;}
	public String pseudoJ2() {return this.pseudoJ2;}
	@Override
	public String toString() {
		//Fonction qui convertit un plateau en String pret a etre sauvegarde
		String txt = this.pseudoJ1+":"+this.pseudoJ2+"\n";
		txt += ""+this.round+":"+this.j1DoitJouer+"\n";
		for (int y = 0; y < 11; y++) {
			for (int x = 0; x < 11; x++) {
				if (this.tableauPion[y][x] != null) {
					txt += this.tableauPion[y][x].toSave();
				}
				txt += "\n";
			}
		}
		return txt;
	}
}