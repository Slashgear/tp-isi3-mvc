package com.polytech4a.isi3.mvc.vue;

import com.polytech4a.isi3.mvc.model.Tortue;

import java.awt.*;

/**
 * Created by Antoine CARON on 27/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class VueBalle extends ITortue {

    public VueBalle(Tortue tortue) {
        super(tortue);
    }

    @Override
    public void draw(Graphics graphics) {
        Point p = new Point(getTortue().getPosition().getX(), getTortue().getPosition().getY());
        graphics.setColor(Color.red);
        graphics.drawOval(p.x, p.y, 10, 10);
    }
}
