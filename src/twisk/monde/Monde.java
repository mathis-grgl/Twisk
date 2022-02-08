package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{
    private GestionnaireEtapes gEtapes;
    private Etape sasE, sasS;
    public Monde(){
        sasE = new SasEntree();
        sasS = new SasSortie();
        gEtapes = new GestionnaireEtapes();
    }

    public void aCommeEntree(Etape... Etapes){gEtapes.ajouter(Etapes);}
    public void aCommeSortie(Etape... Etapes){gEtapes.ajouter(Etapes);}
    public void ajouter(Etape... Etapes){gEtapes.ajouter(Etapes);}
    public int nbEtapes(){
        return gEtapes.nbEtapes();
    }
    public int nbGuichets(){
        int cmp =0;
        Iterator<Etape> g = gEtapes.iterator();
        while(g.hasNext()){
            if(g.next().estUnGuichet()) cmp++;
        }
        return cmp;
    }

    public Iterator<Etape> iterator(){
        return gEtapes.iterator();
    }

    //public String toString(){}
}
