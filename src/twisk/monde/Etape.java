package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    private String nom;
    ArrayList<Etape> lEtape;

    public Etape(String nom){
        this.nom = nom;
        lEtape = new ArrayList<>();
    }

    public void ajouterSuccesseur(Etape... e){
        lEtape.addAll(Arrays.asList(e));
    }

    public abstract boolean estUneActivite();

    public abstract boolean estUnGuicher();

    Iterator<Etape> iterator(){
        return lEtape.iterator();
    }

    public int nbSuccesseur(){
        return ;
    }
}
