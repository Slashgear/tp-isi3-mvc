package com.polytech4a.isi3.mvc.model;

import org.apache.commons.math3.util.FastMath;

import java.util.Observable;

/**
 * Created by Adrien CHAUSSENDE on 22/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Classe modèle pour la Tortue.
 */
public class Tortue extends Observable{

    /**
     * Position de la tortue.
     */
    private Position position;

    /**
     * Angle en degré pour la direction de la tortue.
     */
    private double direction;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        this.setChanged();
        this.notifyObservers();
    }

    public void setPosition(int x,int y){
        this.position.setX(x);
        this.position.setY(y);
        this.setChanged();
        this.notifyObservers();
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Constructeur.
     *
     * @param position  position de la tortue.
     * @param direction angle en degrés pour la direction de la tortue.
     */
    public Tortue(Position position, double direction) {
        this.position = position;
        this.direction = direction;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Constructeur vide. Initialise une position et un angle par défaut : (0;0) et -90°
     */
    public Tortue() {
        this.position=new Position(500 / 2, 400 / 2);
        this.direction=-90;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Méthode permettant de faire avancer la tortue d'une distance dist dans la direction dans laquelle elle est orientée.
     *
     * @param dist distance sur laquelle avancer.
     */
    public void avancer(int dist) {
        int x=(int) FastMath.round(position.getX() + dist * FastMath.cos(FastMath.toRadians(direction)));
        int y=(int) FastMath.round(position.getY() + dist * FastMath.sin(FastMath.toRadians(direction)));
        position.setX(x);
        position.setY(y);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Méthode permettant de tourner la tortue vers sa droite (dans le sens des aiguilles d'une montre) d'un certain montant.
     *
     * @param ang Montant de l'angle en degré.
     */
    public void droite(int ang) {
        direction = (direction + ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Méthode permettant de tourner la tortue vers sa droite (dans le sens inverse des aiguilles d'une montre) d'un certain montant.
     *
     * @param ang Montant de l'angle en degré.
     */
    public void gauche(int ang) {
        direction = (direction - ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Réinitialisation aux valeurs par défaut de la tortue.
     */
    public void reset() {
        position.setX(500/2);
        position.setY(400/2);
        direction = -90;
        this.setChanged();
        this.notifyObservers();
    }
}
