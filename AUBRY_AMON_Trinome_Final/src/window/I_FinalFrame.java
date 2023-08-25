package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class I_FinalFrame extends javax.swing.JFrame {
    /* Fenetre de fin de partie
	 * Cette fenetre permet d'afficher le pseudo du joueur gagnant
	 * Elle demande aussi au joueur s'il veut rejouer ou quitter le jeu
	 */
	private static final long serialVersionUID = 1L;
	public I_FinalFrame(String gagnant) {
		
        initComponents(gagnant);
    }                 
    private void initComponents(String gagnant) {
    	//Fonction qui initialise les composants de la Frame
    	//Creation des elements
        jFrame = new javax.swing.JFrame();
        jMenu = new javax.swing.JButton();
        jQuitter = new javax.swing.JButton();
        jLabelGagnant = new javax.swing.JLabel();
        
        //Parametrage de la fenetre
        javax.swing.GroupLayout jFrameLayout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(jFrameLayout);
        setMaximumSize(new java.awt.Dimension(800, 400));
		setMinimumSize(new java.awt.Dimension(800, 400));
		setPreferredSize(new java.awt.Dimension(800, 400));
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Hecelyne.jpg"));//Change l'icone de la fenetre
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(122,57,248));
        
        //Parametrage des items de l'interface
        jMenu.setFont(new Font("Arial", Font.PLAIN, 40));
        jMenu.setText("Menu Principal");
        jMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActionPerformed(evt);
            }
        });
        
        jQuitter.setFont(new Font("Arial", Font.PLAIN, 40));
        jQuitter.setText("Quitter");
        jQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuitterActionPerformed(evt);
            }
        });
        
        jLabelGagnant.setFont(new Font("Arial", Font.PLAIN, 60));
        jLabelGagnant.setText(gagnant+" à gagné!");
        jLabelGagnant.setForeground(Color.white);
        jLabelGagnant.setBorder(new javax.swing.border.MatteBorder(null));
        
        //Creation du layout (cree par l'assistant GUI Netbeans puis modifiÃ© Ã  la main)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGagnant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jQuitter)))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelGagnant, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMenu)
                    .addComponent(jQuitter))
                .addGap(38, 38, 38))
        );
        pack();
    }

    private void jQuitterActionPerformed(java.awt.event.ActionEvent evt) {System.exit(0);}                                        

    private void jMenuActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	java.awt.EventQueue.invokeLater(new Runnable() {public void run() {new I_Menu().setVisible(true);}});
		this.dispose();
    }                                        
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jMenu;
    private javax.swing.JButton jQuitter;
    private javax.swing.JFrame jFrame;
    private javax.swing.JLabel jLabelGagnant;
    // End of variables declaration
}
