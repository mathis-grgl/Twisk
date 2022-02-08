package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>{
    private ArrayList<Etape> gSuccesseur;
    
    public GestionnaireSuccesseurs(){
        this.gSuccesseur = new ArrayList<>();
    }

    public void ajouter(Etape... successeurs){
        this.gSuccesseur.addAll(Arrays.asList(successeurs));
    }

    public int nbEtapes(){
        return gSuccesseur.size();
    }

    public Iterator<Etape> iterator(){
        return gSuccesseur.iterator();
    }

    public String toString(){return gSuccesseur.size() + " successeur " + gSuccesseur.toString();}
}
