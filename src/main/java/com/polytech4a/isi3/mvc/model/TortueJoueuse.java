package com.polytech4a.isi3.mvc.model;

import java.util.List;

/**
 * Created by Antoine CARON on 27/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class TortueJoueuse extends TortueAmelioree {

    private TortueBalle balle;

    public TortueBalle getBalle() {
        return balle;
    }


    public void setBalle(TortueBalle balle) {
        this.balle = balle;
    }

    public TortueJoueuse(Position position, double direction, String nom, List<Tortue> tortuesConnues) {
        super(position, direction, nom, tortuesConnues);
    }

    public TortueJoueuse() {
        super();
    }

    public void takeBall(TortueBalle balle) {
        this.balle = balle;
        balle.setPosition(this.getPosition());
    }

    public boolean passBall() {
        for (Tortue t : getTortuesConnues()) {
            if (estProche(t) && t instanceof TortueJoueuse) {
                ((TortueJoueuse) t).setBalle(balle);
                balle.setPosition(t.getPosition());
                loseBall();
                return false;
            }
        }
        return true;
    }

    public void loseBall() {
        this.balle = null;
    }
}
