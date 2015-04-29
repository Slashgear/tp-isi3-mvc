package com.polytech4a.isi3.mvc.controller;


import com.polytech4a.isi3.mvc.model.JeuDeBalle;
import com.polytech4a.isi3.mvc.model.Tortue;
import com.polytech4a.isi3.mvc.model.TortueAmelioree;
import com.polytech4a.isi3.mvc.model.TortueJoueuse;
import com.polytech4a.isi3.mvc.vue.SimpleLogo;
import com.polytech4a.isi3.mvc.vue.VueBalle;
import com.polytech4a.isi3.mvc.vue.VueTortue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Antoine CARON on 22/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Controleur extends Thread implements ActionListener, MouseListener {
    /**
     * Vue.
     */
    private SimpleLogo simpleLogo;

    /**
     * Tortue.
     */
    private TortueAmelioree currentTortue;

    private ArrayList<TortueAmelioree> tortues = new ArrayList<TortueAmelioree>();

    private Thread jeuThread;

    private boolean gameRunning;

    private JeuDeBalle jeu;

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
        gameRunning = false;
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
        } else if (c.equals("Ajouter")) {
            if (!simpleLogo.getTortueName().isEmpty()) {
                TortueAmelioree t;
                if (gameRunning) {
                    TortueJoueuse joueur = new TortueJoueuse();
                    joueur.setNom("Joueur " + jeu.getTortuesJoueuses().size());
                    jeu.getTortuesJoueuses().add(joueur);
                    t = joueur;
                } else {
                    t = new TortueAmelioree();
                }
                t.getTortuesConnues().addAll(tortues);
                tortues.parallelStream().forEach(ct -> ct.getTortuesConnues().add(t));
                tortues.add(t);
                t.setNom(simpleLogo.getTortueName());
                t.addObserver(simpleLogo);
                simpleLogo.getTortues().add(t);
                simpleLogo.setCourante(t);
                simpleLogo.getFeuille().addTortue(new VueTortue(t));
                currentTortue = t;
                t.notifyObservers();
            } else {
                System.out.println("Nom de la tortue Vide");
            }
        } else if (c.equals("Jeu de balle")) {
            jeu = JeuDeBalle.jeuDeBallFactory(4);
            tortues.clear();
            tortues.addAll(jeu.getTortuesJoueuses());
            tortues.parallelStream().forEach(t -> t.addObserver(simpleLogo));
            simpleLogo.getTortues().clear();
            simpleLogo.getTortues().addAll(tortues);
            simpleLogo.setCourante(jeu.getTortuesJoueuses().parallelStream().filter(t -> t.getBalle() != null).findAny().get());
            currentTortue = jeu.getTortuesJoueuses().parallelStream().filter(t -> t.getBalle() != null).findAny().get();
            simpleLogo.getFeuille().getTortues().clear();
            for (Tortue t : tortues) {
                simpleLogo.getFeuille().addTortue(new VueTortue(t));
            }
            simpleLogo.getFeuille().addTortue(new VueBalle(jeu.getBalle()));
            gameRunning = true;
            jeuThread = new Thread(jeu);
            jeuThread.start();

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
            currentTortue = cTortue;
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
