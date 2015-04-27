package com.polytech4a.isi3.mvc.model;

import java.util.List;
import java.util.Random;

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

    public TortueJoueuse(Position position, double direction, String nom, List<Tortue> tortuesConnues) {
        super(position, direction, nom, tortuesConnues);
    }

    public TortueJoueuse() {
        super();
    }

    public void takeBall(TortueBalle balle){
        this.balle=balle;
    }

    public void loseBall(){
        this.balle=null;
    }

    public void avancer() {
        Random rdm = new Random(System.currentTimeMillis());
        int dist = rdm.nextInt(10);
        this.setDirection(rdm.nextDouble() * 360 - 180);
        this.avancer(dist);
    }
}
