package com.polytech4a.isi3.mvc.vue;
// package logo;

import com.polytech4a.isi3.mvc.controller.Controleur;
import com.polytech4a.isi3.mvc.model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


/**
 * **********************************************************************
 * <p>
 * Un petit Logo minimal qui devra etre ameliore par la suite
 * <p>
 * J. Ferber - 1999-2001
 * <p>
 * Cours de DESS TNI - Montpellier II
 *
 * @version 2.0
 *          <p>
 *          <p>
 *          ************************************************************************
 */


public class SimpleLogo extends JFrame implements Observer {
    public static final Dimension HGAP = new Dimension(5, 1);
    private FeuilleDessin feuille;
    private Tortue courante;
    private JTextField inputValue;
    private Controleur controleur;
    private JTextField tortueName;

    public void setCourante(Tortue courante) {
        this.courante = courante;
    }

    public FeuilleDessin getFeuille() {
        return feuille;
    }

    public void quitter() {
        System.exit(0);
    }

    public SimpleLogo(Controleur controleur) {
        super("un logo tout simple");
        this.controleur = controleur;
        this.courante = controleur.getCurrentTortue();
        logoInit();
        feuille.addMouseListener(controleur);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel, "North");

        addButton(toolBar, "Effacer", "Nouveau dessin", "/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45", 5);
        tortueName = new JTextField("", 5);
        tortueName.setToolTipText("Nom de la nouvelle Tortue");
        toolBar.add(inputValue);
        addButton(toolBar, "Avancer", "Avancer 50", null);
        addButton(toolBar, "Gauche", "Gauche 45", null);
        addButton(toolBar, "Droite", "Droite 45", null);
        addButton(toolBar, "Ajouter", "ajouter", null);
        addButton(toolBar, "Jeu de balle", "JeuDeBalle", null);
        toolBar.add(tortueName);

        final HashMap<String, Color> map = new HashMap<String, Color>();
        map.put("noir", Color.black);
        map.put("red", Color.red);
        map.put("bleu", Color.blue);
        map.put("cyan", Color.cyan);
        map.put("gris", Color.darkGray);
        map.put("yellow", Color.yellow);
        map.put("magenta", Color.magenta);

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox<Object> colorList = new JComboBox<Object>(map.keySet().toArray());
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int n = cb.getSelectedIndex();
                String color = (String) cb.getItemAt(n);
                Color c = map.get(color);
                courante.setColor(c);
            }
        });


        // Menus
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);    // on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes = new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Ajouter Tortue", "Ajouter Tortue", -1);
        addMenuItem(menuCommandes, "Jeu de balle", "JeuDeBalle", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        feuille = new FeuilleDessin(); //500, 400);
        feuille.setBackground(Color.white);
        feuille.setSize(new Dimension(600, 400));
        feuille.setPreferredSize(new Dimension(600, 400));

        getContentPane().add(feuille, "Center");

        feuille.addTortue(new VueTortue(this.courante));

        pack();
        setVisible(true);
    }

    public String getInputValue() {
        return inputValue.getText();
    }

    public String getTortueName() {
        return tortueName.getText();
    }


    /**
     * les procedures Logo qui combine plusieurs commandes..
     * <p>
     * public void proc1() {
     * courante.carre();
     * }
     * <p>
     * public void proc2() {
     * courante.poly(60, 8);
     * }
     * <p>
     * public void proc3() {
     * courante.spiral(50, 40, 6);
     * }
     */

    // efface tout et reinitialise la feuille
    public void effacer() {
        feuille.reset();
        feuille.repaint();
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(controleur);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(controleur);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }

    public void update(Observable o, Object arg) {
        this.getFeuille().repaint();
    }
}
