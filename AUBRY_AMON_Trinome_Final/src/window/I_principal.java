//Package
package window;
//Imports
import code.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import java.io.File;

public class I_principal extends javax.swing.JFrame {
	/* Cette fenetre est la fenetre principale du jeu
	 * Elle contient le plateau et permet de jouer une partie
	 * Elle est accessible par le menu et s'ouvre lors du lancement ou
	 * de la reprise d'une partie
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;

	public I_principal(String J1, String J2, Plateau plateau) {
		//Creation du plateau
		this.plateau = plateau;

		//Initialisation des compsoants de la fenetre
		initComponents(J2, J1);

		//Affichage des pions
		this.updateI();

		//Initialisation de la bande son
		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void updateI() {
		//Fonction qui met a jour l'emplacement des pions sur l'interface graphique
		for (int x=0; x<11; x++) {
			for (int y=0; y<11; y++) {
				if(this.plateau.getTableauPion()[y][x] == null) {
					this.jTabButtonCase[x][y].setIcon(null);
				} else {
					this.jTabButtonCase[x][y].setIcon(new ImageIcon(this.plateau.getTableauPion()[y][x].getLien()+".png"));
				}
			}
		}
	}

	public void zoneMove(int[][] tabMove, int x, int y) {
		//Fonction qui surligne les cases ou le pion peut se deplacer
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (tabMove[i][j]==1) {
					this.jTabButtonCase[x+j-2][y+i-2].setBackground(new Color(253,108,158));
				}
			}
		}
	}
	public void suprZoneMove() {
		//Fonction qui redonne aux cases leur couleur initiale
		for (int i=0; i<11; i++) {
			for (int j=0; j<11; j++) {
				this.jTabButtonCase[i][j].setBackground(this.plateau.getTableauCase()[j][i].getColor());
			}
		}
	}

	private SequentialGroup groupButtonH(int a, javax.swing.GroupLayout layout) {
		//Fonction permettant de gerer l'alignement des cases sur le layout
		SequentialGroup group = layout.createSequentialGroup();
		group.addGap(40);
		for (int i=0; i<11; i++) {
			group.addComponent(jTabButtonCase[i][a], 60,60,60);
		}
		return group;
	}

	private SequentialGroup groupButtonV(int a, javax.swing.GroupLayout layout) {
		//Fonction permettant de gerer l'alignement des cases sur le layout
		SequentialGroup group = layout.createSequentialGroup();
		group.addGap(40);
		for (int i=0; i<11; i++) {
			group.addComponent(jTabButtonCase[a][10-i], 60,60,60);
		}
		return group;
	}

	private void initComponents(String J2, String J1) {
		//Fonction qui initialise les composants de la Frame
		//Parametrage de la fenetre
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(1000, 850));
		setMinimumSize(new java.awt.Dimension(1000, 200));
		setPreferredSize(new java.awt.Dimension(1000, 850));
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Hecelyne.jpg"));//Change l'icone de la fenetre
		getContentPane().setBackground(new Color(71,14,116));
		
		//Parametrage des items de l'interface
		jP1 = new javax.swing.JLabel();
		jP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jP1.setText(J1);
		jP1.setForeground(new Color(255,0,96));
		jP1.setFont(new Font("Centaur", Font.BOLD,25));
		jP1.setMaximumSize(new java.awt.Dimension(715, 30));
		jP1.setMinimumSize(new java.awt.Dimension(715, 30));
		jP1.setPreferredSize(new java.awt.Dimension(715, 30));

		jP2 = new javax.swing.JLabel();
		jP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jP2.setText(J2);
		jP2.setForeground(new Color(255,0,96));
		jP2.setFont(new Font("Centaur", Font.BOLD,25));
		jP2.setMaximumSize(new java.awt.Dimension(715, 30));
		jP2.setMinimumSize(new java.awt.Dimension(715, 30));
		jP2.setPreferredSize(new java.awt.Dimension(715, 30));

		jMenu = new javax.swing.JButton();
		jMenu.setText("Menu");
		jMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuActionPerformed(evt);
			}
		});

		jMusic = new javax.swing.JButton();
		jMusic.setText("Music");
		jMusic.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMusicActionPerformed(evt);
			}
		});

		jSave = new javax.swing.JButton();
		jSave.setText("Save");
		jSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jSaveActionPerformed(evt);
			}
		});

		jLeave = new javax.swing.JButton();
		jLeave.setText("Quit");
		jLeave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jQuitActionPerformed(evt);
			}
		});

		jRules = new javax.swing.JButton();
		jRules.setText("Rules");
		jRules.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRulesActionPerformed(evt);
			}
		});


		for (int x=0; x<11; x++) {
			for (int y=0; y<11; y++) {
				this.jTabButtonCase[x][y] = new javax.swing.JButton();
				this.jTabButtonCase[x][y].setActionCommand(""+(x+1)+":"+(y+1));
				this.jTabButtonCase[x][y].setBackground(this.plateau.getTableauCase()[y][x].getColor());

				if(this.plateau.getTableauPion()[y][x] != null) {
					this.jTabButtonCase[x][y].setIcon(new ImageIcon(this.plateau.getTableauPion()[y][x].getLien()+".png"));
				}

				this.jTabButtonCase[x][y].addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCaseActionPerformed(evt);
					}
				});
			}
		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
						.addComponent(jP1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
				//========Plateau
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(10, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(9, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(8, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(7, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(6, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(5, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(4, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(3, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(2, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(1, layout))
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupButtonH(0, layout))
				//========
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
						.addComponent(jP2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(20, 20, 20)
						.addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(34, 34, 34)
						.addComponent(jMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(34, 34, 34)
						.addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
						.addComponent(jRules, javax.swing.GroupLayout.DEFAULT_SIZE, 125, 125)
						.addComponent(jLeave, javax.swing.GroupLayout.DEFAULT_SIZE, 125, 125))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
				//========Plateau
				.addGroup(groupButtonV(0, layout))
				.addGroup(groupButtonV(1, layout))
				.addGroup(groupButtonV(2, layout))
				.addGroup(groupButtonV(3, layout))
				.addGroup(groupButtonV(4, layout))
				.addGroup(groupButtonV(5, layout))
				.addGroup(groupButtonV(6, layout))
				.addGroup(groupButtonV(7, layout))
				.addGroup(groupButtonV(8, layout))
				.addGroup(groupButtonV(9, layout))
				.addGroup(groupButtonV(10, layout))
				//========
				.addGroup(layout.createSequentialGroup()
						.addGap(60*11+30)
						.addComponent(jP2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jRules, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);// </editor-fold>   

		Container c = getContentPane();
		JScrollPane scroll = new JScrollPane(c, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(scroll);
		pack();
	}                     

	private void jMenuActionPerformed(java.awt.event.ActionEvent evt) {                                      
		// TODO add your handling code here:
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_Menu().setVisible(true);}});
		this.dispose();
	}                                     

	private void jMusicActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.clip.isActive()) {
			this.clip.close();
		} else {
			try {
				this.clip.close();
				this.clip.open(AudioSystem.getAudioInputStream(new File("Musique/song.wav")));
				this.clip.start();
			} catch (Exception exc){
				exc.printStackTrace(System.out);
			}
		}
	}                                      

	private void jSaveActionPerformed(java.awt.event.ActionEvent evt) {                                      
		JFrame frame = new JFrame();
		String bigList[] = new String[5];
		for (int i = 0; i < bigList.length; i++) {
			bigList[i] = Integer.toString(i+1);
		}
		String n = (String) JOptionPane.showInputDialog(frame, "Choisissez l'emplacement de la sauvegarde", "Choisir", JOptionPane.QUESTION_MESSAGE, null, bigList, "0");
		if(!n.equals("null")) {
			if(!n.equals("0")) {
				this.plateau.Save(n);
			}
		}
	}                                     

	private void jQuitActionPerformed(java.awt.event.ActionEvent evt) {System.exit(0);}                                        

	private void jRulesActionPerformed(java.awt.event.ActionEvent evt) {                                       
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_Regles().setVisible(true);}});
	}

	private void jCaseActionPerformed(java.awt.event.ActionEvent evt) {                                       
		this.plateau.event(((AbstractButton) evt.getSource()).getActionCommand(),this);
	}    



	// Variables declaration - do not modify    
	private javax.swing.JButton[][] jTabButtonCase = new javax.swing.JButton[11][11];
	private javax.swing.JButton jLeave;
	private javax.swing.JButton jMenu;
	private javax.swing.JButton jMusic;
	private javax.swing.JLabel jP1;
	private javax.swing.JLabel jP2;
	private javax.swing.JButton jSave;
	private javax.swing.JButton jRules;
	// End of variables declaration  

	//Variables perosnnelles
	private Clip clip = null;
}
