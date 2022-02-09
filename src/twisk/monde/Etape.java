package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    private String nom;
    private int num;
    private GestionnaireSuccesseurs gestSucc;

    public Etape(String nom){
        this.nom = nom;
        this.gestSucc = new GestionnaireSuccesseurs();
        this.num = FabriqueNumero.getInstance().getNumeroEtape();
    }

    public void ajouterSuccesseur(Etape... e){
        gestSucc.ajouter(e);
    }

    public abstract boolean estUneActivite();

    public abstract boolean estUnGuichet();

    public int getNum(){
        return num;
    }

    public Iterator<Etape> iterator(){
        return new GestionnaireEtapes().iterator();
    }

    public int nbSuccesseur(){
        return gestSucc.nbEtapes();
    }

    public String getNom() {return nom;}

    public String toString(){return nom +" : "+ gestSucc.toString();}
}
