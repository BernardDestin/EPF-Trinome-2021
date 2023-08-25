package code;
import java.util.Scanner;
import window.I_Menu;

public class AUBRY_AMON_Trinome {

	public static void main(String[] args) {
		//JoueurVSJoueurVIAConsole(args); //Partie en mode console
		JoueurVSJoueurVIAInterface(args); //Partie via l'Interface
	}
	
	public static void JoueurVSJoueurVIAInterface(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_Menu().setVisible(true);}});
	}
	
	public static void JoueurVSJoueurVIAConsole(String[] args) {
		//Main de verification des fonctions
		Scanner sc = new Scanner(System.in);
		Plateau plateau;
		int choix = -1;
		while(choix != 0) {
			System.out.println("Bienvenue. Choisissez une option");
			System.out.println("1. Jouer une partie");
			System.out.println("2. Jouer une partie speciale");
			System.out.println("3. Charger une partie");
			System.out.println("4. Reprendre la partie en cours");
			System.out.println("5. Regles");
			System.out.println("0. Quitter");

			choix = sc.nextInt();
			switch (choix) {
			case 0:
				System.out.println("Au revoir");
				break;
			case 1:
				plateau = new Plateau(false,"J1","J2");
				plateau.PvPsansGraphismes();
				choix = 0;
				break;
			case 2:
				plateau = new Plateau(true,"J1","J2");
				plateau.PvPsansGraphismes();
				choix = 0;
				break;
			case 3:
				plateau = new Plateau(false,"J1","J2");
				plateau.Load("001");
				plateau.PvPsansGraphismes();
				choix = 0;
				break;
			case 4:
				plateau = new Plateau(false,"J1","J2");
				plateau.Load("000");
				plateau.PvPsansGraphismes();
				choix = 0;
				break;
			case 5: 
				System.out.println("Preparation : \r\n"
						+ "PREPARATION\r\n"
						+ "Placez le plateau de jeu au centre de la table.\r\n"
						+ "Disposez les pions comme sur la figure 1.\r\n"
						+ "Le joueur rouge commence, puis les joueurs jouent e tour de rele.");
				System.out.println("Tour de jeu : \r\n"
						+ "A son tour de jeu, un joueur doit deplacer l'un de ses pions.\r\n"
						+ "- Les pyramide se deplacent en diagonal d'une case.\r\n"
						+ "- Les Cubes se deplacent verticalement ou horizontalement d'une case.\r\n"
						+ "- Les spheres se deplacent verticalement, horizontalement ou en diagonale de deux cases.\r\n"
						+ "Capture:\r\n"
						+ "La capture se fait par remplacement, c'est e dire par un pion se deplaeant dans une case occupee par un pion adverse. "
						+ "Attention, seul les pyramides et les cubes peuvent effectuer des captures. "
						+ "Les demi-spheres ne peuvent pas effectuer de capture, par contre elles peuvent etre capturees. Un pion capture est definitivement elimine du plateau de jeu.");
				System.out.println("Fin de partie :\r\n"
						+ "1ere possibilite: Le premier joueur qui parvient e placer 3 differentes pieces (une demi-sphere + un cube, + une pyramide l'emporte) sur les 3 cases rouges de l'adversaire remporte la partie.\r\n"
						+ "Ci-contre le joueur vert a remporter la partie.\r\n"
						+ "2eme possibilite: Si un joueur parvient e capturer tous les pions d'une sorte de son adversaire (ex: les 6 cubes) il remporte la partie immediatement.\r\n"
						+ "\r\n"
						+ "Variante (3eme possibilite):\r\n"
						+ "Se sont les trois demi-spheres qui doivent occuper les cases de depart des trois demi-spheres adverses.");
				break;
			default:
				System.out.println("Merci de rentrer un choix valide");
			}
		}
		sc.close();
	}
}