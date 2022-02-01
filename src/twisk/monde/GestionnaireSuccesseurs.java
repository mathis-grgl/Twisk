package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>{
    private ArrayList<Etape> etapes;
    
    public GestionnaireSuccesseurs(){
        this.etapes = new ArrayList<>();
    }

    public void ajouter(Etape... etapes){
        this.etapes.addAll(Arrays.asList(etapes));
    }

    public int nbEtapes(){
        return etapes.size();
    }

    public Iterator<Etape> iterator(){
        return null;
    }
}
