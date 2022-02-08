package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape>{
    private ArrayList<Etape> gEtapes;

    public GestionnaireEtapes(){this.gEtapes = new ArrayList<>();}

    public void ajouter(Etape... Etapes){gEtapes.addAll(Arrays.asList(Etapes));}

    public int nbEtapes(){
        return gEtapes.size();
    }

    public Iterator<Etape> iterator(){
        return gEtapes.iterator();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(gEtapes.size() + " etapes");
        if(gEtapes.size()!=0) {
            for (int i = 0; i < gEtapes.size(); i++) {
                str.append(" - "+gEtapes.get(i).getNom() );
            }
        }
        return str.toString();
    }
}
