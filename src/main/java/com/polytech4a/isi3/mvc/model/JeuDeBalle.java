package com.polytech4a.isi3.mvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Adrien CHAUSSENDE on 27/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class JeuDeBalle implements Runnable {

    private final static int wait = 100;

    private final static int keep = 10;

    private int currentkeep = 0;

    private ArrayList<TortueJoueuse> tortuesJoueuses = new ArrayList<>();

    private TortueBalle balle;

    private boolean canPass;

    public JeuDeBalle(ArrayList<TortueJoueuse> tortuesJoueuses, TortueBalle balle) {
        this.tortuesJoueuses = tortuesJoueuses;
        this.balle = balle;
        this.canPass = true;
    }

    public ArrayList<TortueJoueuse> getTortuesJoueuses() {
        return tortuesJoueuses;
    }

    public TortueBalle getBalle() {
        return balle;
    }

    public void etapeJeu() {
        Random rdm = new Random(System.currentTimeMillis());
        canPass = tortuesJoueuses.parallelStream().filter(t -> t.getBalle() != null).findAny().get().passBall();
        new Thread(() -> waitPass()).run();
        tortuesJoueuses.parallelStream().forEach(t -> {
            int dist = rdm.nextInt(20);
            if (dist % 2 ==0) t.setDirection(rdm.nextDouble() * 360 - 180);
            t.avancer(dist);
        });
    }

    public static JeuDeBalle jeuDeBallFactory(int nbJoueurs) {
        ArrayList<TortueJoueuse> tortuesJoueuses = new ArrayList<>();
        Random rdm = new Random(System.currentTimeMillis());
        for (int i = 0; i < nbJoueurs; i++) {
            List<Tortue> tortuesConnues = new ArrayList<>();
            tortuesConnues.addAll(tortuesJoueuses);
            TortueJoueuse tortue = new TortueJoueuse(new Position(rdm.nextInt(500), rdm.nextInt(400)), 0, "Joueur " + i, tortuesConnues);
            tortuesJoueuses.parallelStream().forEach(t -> t.getTortuesConnues().add(tortue));
            tortuesJoueuses.add(tortue);
        }
        TortueBalle tortueBalle = new TortueBalle();
        tortuesJoueuses.get(rdm.nextInt(tortuesJoueuses.size())).takeBall(tortueBalle);
        return new JeuDeBalle(tortuesJoueuses, tortueBalle);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(wait);
                this.etapeJeu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public void waitPass() {
        try {
            Thread.sleep(keep);
            this.canPass = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
