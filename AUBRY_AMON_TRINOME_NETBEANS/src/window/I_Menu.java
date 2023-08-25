//Package
package window;

//Imports
import code.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class I_Menu extends javax.swing.JFrame {
	/* Cette fenetre permet de saisir les pseudos des joueurs,
	 * de commencer une partie classique ou variante,
	 * de lancer une partie ou d'acceder aux regles. 
	 */
	private static final long serialVersionUID = 1L;
	public I_Menu() {
		//Constructeur de l'interface du Menu
		initComponents();
	}

	private void initComponents() {
		//Fonction qui initialise les composants de la Frame
		//Creation des elements
		normal_variant = new javax.swing.ButtonGroup();
		jTitle = new javax.swing.JLabel();
		jClassic = new javax.swing.JRadioButton();
		jVariant = new javax.swing.JRadioButton();
		jP1txt = new javax.swing.JTextField();
		jP2txt = new javax.swing.JTextField();
		jPlay = new javax.swing.JButton();
		jResume = new javax.swing.JButton();
		jLoadText = new javax.swing.JLabel();
		jLoad = new javax.swing.JButton();
		jQuit = new javax.swing.JButton();
		jRules = new javax.swing.JButton();
		jChoice = new java.awt.Choice();
		jNewGame = new javax.swing.JLabel();

		//Parametrage de la fenetre
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(700, 600));
		setMinimumSize(new java.awt.Dimension(700, 600));
		setPreferredSize(new java.awt.Dimension(700, 600));
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Hecelyne.jpg"));//Change l'icone de la fenetre
		getContentPane().setBackground(new Color(122,57,248));
		
		//Parametrage des items de l'interface
		jTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jTitle.setText("TRINÔME");
		jTitle.setForeground(Color.white);
		jTitle.setFont(new Font("Centaur", Font.BOLD,50));
		
		normal_variant.add(jClassic);
		jClassic.setText("classic");
		jClassic.setSelected(true);
		jClassic.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		jClassic.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

		normal_variant.add(jVariant);
		jVariant.setText("variant");
		jVariant.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

		jP1txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jP1txt.setText("Player 1");

		jP2txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jP2txt.setText("Player 2");
		
		jNewGame.setText("Play a new game :");
		jNewGame.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		jNewGame.setFont(new Font("Courier", Font.BOLD,20));
		jNewGame.setForeground(Color.white);
		
		jPlay.setText("Play");
		jPlay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jPlayActionPerformed(evt);
			}
		});

		jResume.setText("Resume");
		jResume.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jLoadActionPerformed(evt,"0");
			}
		});

		jLoadText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLoadText.setText("Load a save");
		jLoadText.setForeground(Color.white);
		jLoadText.setFont(new Font("Courier", Font.BOLD,17));

		this.loadListSave();

		jLoad.setText("Load");
		jLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jLoadActionPerformed(evt,"a");
			}
		});

		jQuit.setText("Quit");
		jQuit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jQuitActionPerformed(evt);
			}
		});
		
		jRules.setText("Rules");
		jRules.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRulesActionPerformed(evt);
			}
		});
		
		//Creation du layout (cree par l'assistant GUI Netbeans puis modifie a la main)
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE-40)
						.addComponent(jRules)
						.addGap(10, 10, 10)
						.addComponent(jQuit)
						.addGap(10, 10, 10))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
						.addGroup(layout.createSequentialGroup()
								.addGap(200, 200, 200)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
										.addComponent(jClassic, javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(jP1txt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(100, 100, 100)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
										.addComponent(jP2txt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jVariant, javax.swing.GroupLayout.Alignment.LEADING)))
						.addGroup(layout.createSequentialGroup()
								.addGap(200, 200, 200)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
										.addComponent(jTitle)
										.addComponent(jPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jResume, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jNewGame)
										.addComponent(jLoadText, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLoad)
										)))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(50, 50, 50)
						.addComponent(jTitle)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGap(30, 30, 30)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jP1txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jP2txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(40, 40, 40)
						.addComponent(jNewGame)
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jVariant)
								.addComponent(jClassic))
						.addGap(30, 30, 30)
						.addComponent(jPlay)
						.addGap(30, 30, 30)
						.addComponent(jResume)
						.addGap(30, 30, 30)
						.addComponent(jLoadText)
						.addGap(20, 20, 20)
						.addComponent(jChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)
						.addComponent(jLoad)
						.addGap(5, 5, 5)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jQuit)
								.addComponent(jRules))
						.addContainerGap(20, Short.MAX_VALUE))
				);
		pack();
	}

	private void jPlayActionPerformed(java.awt.event.ActionEvent evt) {
		//Action du bouton Play:
		//Lancement de l'interface du plateau avec le mode de jeu selectionne et les pseudos des joueurs
		Plateau plateau = new Plateau(!jClassic.isSelected(),jP2txt.getText(),jP1txt.getText());
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_principal(jP2txt.getText(),jP1txt.getText(), plateau).setVisible(true);}});
		this.dispose();
	}  

	private void jLoadActionPerformed(ActionEvent evt, String nb) {
		//Action du bouton Load:
		//Chargement d'une sauvegarde et lancement de l'interface du plateau
		if(nb.equals("a")) {
			nb = String.valueOf(this.jChoice.getSelectedIndex()+1);
		}

		Plateau plateau = new Plateau(false,"J1","J2");
		plateau.Load(nb);
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_principal(plateau.pseudoJ1(),plateau.pseudoJ2(), plateau).setVisible(true);}});
		this.dispose();
	}

	private void loadListSave() {
		//Remplissage de la liste des sauvegardes
		for (int i=1; i<6; i++) {
			this.jChoice.add("Emplacement n°"+i);
		}
	}
	private void jRulesActionPerformed(java.awt.event.ActionEvent evt) {                                       
		java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_Regles().setVisible(true);}});
	}
	
	private void jQuitActionPerformed(java.awt.event.ActionEvent evt) {System.exit(0);} //Action du bouton quitter  

	// Variables declaration
	private javax.swing.JLabel jTitle;
	private javax.swing.JLabel jNewGame;
	private javax.swing.JLabel jLoadText;

	private javax.swing.JTextField jP1txt;
	private javax.swing.JTextField jP2txt;

	private java.awt.Choice jChoice;
	private javax.swing.ButtonGroup normal_variant;

	private javax.swing.JRadioButton jClassic;
	private javax.swing.JRadioButton jVariant;

	private javax.swing.JButton jPlay;
	private javax.swing.JButton jResume;
	private javax.swing.JButton jRules;
	private javax.swing.JButton jLoad;
	private javax.swing.JButton jQuit;
	// End of variables declaration
}
