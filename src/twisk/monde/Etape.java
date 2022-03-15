package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    protected String nom;
    protected int num;
    protected GestionnaireSuccesseurs gestSucc;

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

    public Iterator<Etape> iterator(){
        return new GestionnaireEtapes().iterator();
    }

    public String getNomBien() {
        String copieNom = nom;
        copieNom = copieNom.replaceAll("(\\s)|(\\W)","");
        return copieNom;
    }

    public String getNom() {
        return nom;
    }

    public int nbSuccesseur(){
        return gestSucc.nbEtapes();
    }

    public String toString(){return nom +" : "+ gestSucc.toString();}

    public abstract String toC();
}
