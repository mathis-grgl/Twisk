package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    private String nom;
    private GestionnaireSuccesseurs gestSucc;

    public Etape(String nom){
        this.nom = nom;
        this.gestSucc = new GestionnaireSuccesseurs();
    }

    public void ajouterSuccesseur(Etape... e){
        gestSucc.ajouter(e);
    }

    public abstract boolean estUneActivite();

    public abstract boolean estUnGuicher();

    public Iterator<Etape> iterator(){
        return null;
    }

    public int nbSuccesseur(){
        return 0;
    }
}
