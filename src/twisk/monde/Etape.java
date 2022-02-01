package twisk.monde;

import java.util.Iterator;

public abstract class Etape {
    private String nom;

    public Etape(String nom){
        this.nom = nom;
    }

    public void ajouterSuccesseur(Etape... e){}

    public abstract boolean estUneActivite();

    public abstract boolean estUnGuicher();

    Iterator<Etape> iterator(){
        return null;
    }
}
