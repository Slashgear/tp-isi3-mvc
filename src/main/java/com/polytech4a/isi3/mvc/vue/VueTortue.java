package com.polytech4a.isi3.mvc.vue;

import com.polytech4a.isi3.mvc.model.Tortue;
import org.apache.commons.math3.util.FastMath;

import java.awt.*;

/**
 * Created by Antoine CARON on 27/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class VueTortue extends ITortue {

    protected static final int rp = 10, rb = 5;

    public VueTortue(Tortue tortue) {
        super(tortue);
    }

    public void draw( Graphics graphics) {
        graphics.setColor(getTortue().getColor());
        graphics.fillPolygon(getPolygon(getTortue()));
    }
    public static Polygon getPolygon(Tortue tortue) {
        Point p = new Point(tortue.getPosition().getX(), tortue.getPosition().getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = FastMath.toRadians(-tortue.getDirection());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float) rb / (float) rp);
        //Rayon de la fleche
        double r = Math.sqrt(rp * rp + rb * rb);
        //Sens de la fleche

        //Pointe
        Point p2 = new Point((int) Math.round(p.x + r * Math.cos(theta)),
                (int) Math.round(p.y - r * Math.sin(theta)));
        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta + alpha)),
                (int) Math.round(p2.y + r * Math.sin(theta + alpha)));

        //Base2
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(theta - alpha)),
                (int) Math.round(p2.y + r * Math.sin(theta - alpha)));

        arrow.addPoint(p2.x, p2.y);
        return arrow;
    }
}
