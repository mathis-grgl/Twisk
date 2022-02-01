package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{
    public Monde(){

    }

    public void aCommeEntree(Etape... Etapes){}
    public void aCommeSortie(Etape... Etapes){}
    public void ajouter(Etape... Etapes){}
    public int nbEtapes(){
        return 0;
    }
    public int nbGuichets(){
        return 0;
    }

    public Iterator<Etape> iterator(){
        return null;
    }
}
