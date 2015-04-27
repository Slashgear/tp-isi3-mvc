package com.polytech4a.isi3.mvc.model;

import org.apache.commons.math3.util.FastMath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 27/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Classe modèle pour une tortue améliorée.
 */
public class TortueAmelioree extends Tortue {

    /**
     * Nom de la tortue améliorée
     */
    private String nom;

    /**
     * Liste de tortues connues par la tortue améliorée.
     */
    private List<Tortue> tortuesConnues;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Tortue> getTortuesConnues() {
        return tortuesConnues;
    }

    public void setTortuesConnues(List<Tortue> tortuesConnues) {
        this.tortuesConnues = tortuesConnues;
    }

    /**
     * Constructeur.
     *
     * @param position       position de la tortue.
     * @param direction      direction dans laquelle est orientée la tortue (en degré).
     * @param nom            nom de la tortue.
     * @param tortuesConnues liste des tortues connues par la tortues créée.
     */
    public TortueAmelioree(Position position, double direction, String nom, List<Tortue> tortuesConnues) {
        super(position, direction);
        this.nom = nom;
        this.tortuesConnues = tortuesConnues;
    }

    /**
     * Constructeur.
     *
     * @param nom nom de la tortue.
     */
    public TortueAmelioree(String nom) {
        this.nom = nom;
    }

    /**
     * Constructeur vide.
     */
    public TortueAmelioree() {
        super();
        this.nom = "Thortue";
        this.tortuesConnues = new ArrayList<>();
    }

    /**
     * Ajout d'une tortue à la liste des tortues connues.
     *
     * @param t nouvelle tortue connue.
     */
    public void ajouterTortueConnue(Tortue t) {
        tortuesConnues.add(t);
    }

    /**
     * Suppression d'une tortue à la liste des tortues connues.
     *
     * @param t tortue à retirer de la liste des tortues connues.
     */
    public void supprimerTortueConnue(Tortue t) {
        tortuesConnues.remove(t);
    }

    /**
     * Calcul de la distance euclidienne entre la tortue passée en paramètre et la tortue courante.
     *
     * @param t tortue
     * @return Distance euclidienne entre les deux tortues sous forme de double.
     */
    public double distanceEuclidienne(Tortue t) {
        return FastMath.sqrt(FastMath.pow(t.getPosition().getX() - this.getPosition().getX(), 2) + FastMath.pow(t.getPosition().getY() - this.getPosition().getY(), 2));
    }

    /**
     * Permet de savoir si la tortue courante est proche de la tortue passée en paramètre.
     *
     * @param t Tortue
     * @return True si la tortue est proche de la tortue passée en paramètre. False sinon.
     */
    public boolean estProche(Tortue t) {
        return this.distanceEuclidienne(t) <= 50;
    }

    /**
     * Permet de savoir si la tortue courante est proche d'une des tortues connues (<= 15 px).
     *
     * @return True si la tortue est proche d'au moins une tortue connue. False sinon.
     */
    public boolean estProcheDeTortuesConnue() {
        for (Tortue t : this.tortuesConnues) {
            if (this.estProche(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        tortuesConnues.parallelStream().filter(t -> this.estProche(t)).forEach(t -> {
            StringBuffer message = new StringBuffer();
            message.append("SALUT ");
            if (t instanceof TortueAmelioree) {
                message.append(((TortueAmelioree) t).getNom());
            } else {
                message.append("tortue non amelioree");
            }
            message.append(" !!");
            System.out.println(message.toString());
            int x = (int) FastMath.round(t.getPosition().getX() + 10 * FastMath.cos(FastMath.toRadians(t.getDirection())));
            int y = (int) FastMath.round(t.getPosition().getY() + 10 * FastMath.sin(FastMath.toRadians(t.getDirection())));
            t.setPosition(x, y);
        });
    }
}
