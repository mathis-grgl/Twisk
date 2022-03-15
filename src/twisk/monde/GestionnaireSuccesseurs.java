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

    public Etape getSucc(int index){
        return gSuccesseur.get(index);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(gSuccesseur.size() + " successeur");
        if(gSuccesseur.size()!=0) {
            for (int i = 0; i < gSuccesseur.size(); i++) {
                str.append(" - "+gSuccesseur.get(i).nom );
            }
        }
        return str.toString();
    }
}
