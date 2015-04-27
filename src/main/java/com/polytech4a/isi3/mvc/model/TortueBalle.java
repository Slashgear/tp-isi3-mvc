package com.polytech4a.isi3.mvc.model;

/**
 * Created by Adrien CHAUSSENDE on 27/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Classe modèle pour une tortue-balle.
 */
public class TortueBalle extends Tortue {

    /**
     * Possesseur de la Tortue Balle.
     */
    private Tortue possesseur;

    /**
     * Constructeur.
     *
     * @param possesseur possesseur de la tortue balle.
     */
    public TortueBalle(Tortue possesseur) {
        super(possesseur.getPosition(), possesseur.getDirection());
        this.possesseur = possesseur;
    }

    public void setPossesseur(Tortue possesseur) {
        this.possesseur = possesseur;
    }
}
