package com.polytech4a.isi3.mvc.vue;

import com.polytech4a.isi3.mvc.model.Tortue;

import java.awt.*;

/**
 * Created by Antoine CARON on 27/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public abstract class ITortue {

    private Tortue tortue;

    public Tortue getTortue() {
        return tortue;
    }

    public ITortue(Tortue tortue) {
        this.tortue = tortue;
    }

    public abstract void draw(Graphics graphics);
}
