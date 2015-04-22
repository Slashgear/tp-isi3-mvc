package com.polytech4a.isi3.mvc.model;

import org.apache.commons.math3.util.FastMath;

/**
 * Created by Adrien CHAUSSENDE on 22/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class Tortue {

    private Position position;

    private double direction;

    public void avancer(int dist) {
        position.setX((int) FastMath.round(position.getX() + dist * FastMath.cos(FastMath.toRadians(direction))));
        position.setY((int) FastMath.round(position.getY() + dist * FastMath.sin(FastMath.toRadians(direction))));
    }

    public void droite(int ang) {
        direction = (direction + ang) % 360;
    }

    public void gauche(int ang) {
        direction = (direction - ang) % 360;
    }

}
