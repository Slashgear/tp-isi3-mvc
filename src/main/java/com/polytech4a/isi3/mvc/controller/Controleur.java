package com.polytech4a.isi3.mvc.controller;


import com.polytech4a.isi3.mvc.model.Tortue;
import com.polytech4a.isi3.mvc.vue.SimpleLogo;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Antoine CARON on 22/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Controleur implements ActionListener {
    /**
     * Vue.
     */
    private SimpleLogo simpleLogo;

    /**
     * Tortue.
     */
    private Tortue tortue;

    public SimpleLogo getSimpleLogo() {
        return simpleLogo;
    }

    public Tortue getTortue() {
        return tortue;
    }

    public Controleur() {
        tortue = new Tortue();
        simpleLogo = new SimpleLogo(this);
        tortue.addObserver(simpleLogo);
    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                tortue.avancer(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }

        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                tortue.droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                tortue.gauche(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }
        } else if (c.equals("Effacer")) {
            tortue.setPosition(
                    simpleLogo.getFeuille().getSize().width / 2,
                    simpleLogo.getFeuille().getSize().height / 2);
            simpleLogo.effacer();
        } else if (c.equals("Quitter"))
            simpleLogo.quitter();

        simpleLogo.getFeuille().repaint();
    }
}
