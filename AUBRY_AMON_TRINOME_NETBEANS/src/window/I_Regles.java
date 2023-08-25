package window;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class I_Regles extends javax.swing.JFrame {
	/* Cette fenetre affiche les regles, 
	 * permet d'acceder aux traductions des regles 
	 * et permet de retourner a la fenetre precedente
	 */
	private static final long serialVersionUID = 1L;
	public I_Regles() {
		//Constructeur de la classe
		initComponents();
	}

	private void initComponents() {
		//Fonction qui initialise les composants de la Frame
		//Creation des elements
		jQuit = new javax.swing.JButton();
		jrules = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextRules = new javax.swing.JTextArea();

		//Parametrage de la fenetre
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(600, 600));
		setMinimumSize(new java.awt.Dimension(600, 600));
		setPreferredSize(new java.awt.Dimension(600, 600));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Hecelyne.jpg"));//Change l'icone de la fenetre
		getContentPane().setBackground(new Color(122,57,248));

		//Parametrage des items de l'interface
		jQuit.setText("Quit");
		jQuit.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jQuitActionPerformed(evt);
			}
		});

		jrules.setText("Rules");
		jrules.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jrulesActionPerformed(evt);
			}
		});

		jTextRules.setColumns(20);
		jTextRules.setRows(5);
		jTextRules.setEditable(false);
		jTextRules.setForeground(new Color(255,255,255));
		jTextRules.setSelectionColor(new Color(230,0,126));
		jTextRules.setBackground(new Color(122,57,248));
		this.changeTxtRule();
		jScrollPane1.setViewportView(jTextRules);

		//Creation du layout (cree par l'assistant GUI Netbeans puis modifie a la main)
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(jQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
										.addComponent(jrules, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jrules))
								.addGroup(layout.createSequentialGroup()
										.addGap(26, 26, 26)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jQuit))
				);
		pack();
	}

	private void jQuitActionPerformed(java.awt.event.ActionEvent evt) {this.dispose();}

	private void jrulesActionPerformed(java.awt.event.ActionEvent evt) {this.changeTxtRule();}

	private void changeTxtRule() {
		//Fonction qui permet de changer la version des regles a partir de differents fichiers textes
		String txt = "";
		try {
			FileReader file = new FileReader("Rulestexts/rule_"+this.actualRule+".txt", StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(file);
			String str;
			while ((str = br.readLine()) != null) {
				txt += str + "\n";
			}
			this.actualRule=(this.actualRule+1)%7;
			file.close();
		} catch (Exception e) {
			txt = "erreur d'importation";
		}
		jTextRules.setText(txt);
	}

	// Variables declaration - do not modify//
	private javax.swing.JButton jQuit;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextRules;
	private javax.swing.JButton jrules;
	// End of variables declaration
	private int actualRule = 0; 
}