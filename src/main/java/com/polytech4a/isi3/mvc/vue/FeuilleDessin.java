package com.polytech4a.isi3.mvc.vue;// package logo;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel {
    private ArrayList<ITortue> tortues; // la liste des tortues enregistrees

    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche

    public void setTortues(ArrayList<ITortue> tortues) {
        this.tortues = tortues;
    }

    public FeuilleDessin() {
        tortues = new ArrayList<ITortue>();
    }

    public ArrayList<ITortue> getTortues() {
        return tortues;
    }

    public void addTortue(ITortue o) {
        tortues.add(o);
    }

    public void reset() {
        for (Iterator it = tortues.iterator(); it.hasNext(); ) {
            ITortue t = (ITortue) it.next();
            t.getTortue().reset();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (Iterator it = tortues.iterator(); it.hasNext(); ) {
            ITortue t = (ITortue) it.next();
            t.draw(g);
        }
    }

}
