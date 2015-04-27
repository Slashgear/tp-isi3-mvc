package com.polytech4a.isi3.mvc.controller;


import com.polytech4a.isi3.mvc.model.Tortue;
import com.polytech4a.isi3.mvc.model.TortueAmelioree;
import com.polytech4a.isi3.mvc.vue.FeuilleDessin;
import com.polytech4a.isi3.mvc.vue.SimpleLogo;
import com.polytech4a.isi3.mvc.vue.VueTortue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Antoine CARON on 22/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Controleur implements ActionListener, MouseListener {
    /**
     * Vue.
     */
    private SimpleLogo simpleLogo;

    /**
     * Tortue.
     */
    private TortueAmelioree currentTortue;

    private ArrayList<TortueAmelioree> tortues = new ArrayList<TortueAmelioree>();

    public SimpleLogo getSimpleLogo() {
        return simpleLogo;
    }

    public Tortue getCurrentTortue() {
        return currentTortue;
    }

    public Controleur() {
        currentTortue = new TortueAmelioree();
        tortues.add(currentTortue);
        simpleLogo = new SimpleLogo(this);
        currentTortue.addObserver(simpleLogo);
    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                currentTortue.avancer(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }

        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                currentTortue.droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(simpleLogo.getInputValue());
                currentTortue.gauche(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + simpleLogo.getInputValue());
            }
        } else if (c.equals("Effacer")) {
            currentTortue.setPosition(
                    simpleLogo.getFeuille().getSize().width / 2,
                    simpleLogo.getFeuille().getSize().height / 2);
            simpleLogo.effacer();
        } else if (c.equals("Quitter")) {
            simpleLogo.quitter();
        } else if (c.equals("Ajouter")){
            TortueAmelioree t=new TortueAmelioree();
            tortues.add(t);
            t.setNom(t.getNom()+tortues.indexOf(t));
            t.addObserver(simpleLogo);
            simpleLogo.getTortues().add(t);
            simpleLogo.setCourante(t);
            simpleLogo.getFeuille().addTortue(new VueTortue(t));
            currentTortue=t;
            t.notifyObservers();
        }
        simpleLogo.getFeuille().repaint();
    }

    public void mouseClicked(MouseEvent e) {
        try {
            TortueAmelioree cTortue = tortues
                    .parallelStream().filter(t -> {
                        Polygon polygon = VueTortue.getPolygon(t);
                        return (polygon.contains(e.getX(), e.getY()));
                    }).findAny().get();
            currentTortue=cTortue;
            simpleLogo.setCourante(cTortue);
            System.out.println("Changement de tortue courante");
        } catch (NoSuchElementException ex) {
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
