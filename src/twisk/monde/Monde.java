package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{
    private GestionnaireEtapes gEtapes;
    private Etape sasE, sasS;

    public Monde(){
        sasE = new SasEntree();
        sasS = new SasSortie();
        gEtapes = new GestionnaireEtapes();
        gEtapes.ajouter(sasE,sasS);
    }

    public void aCommeEntree(Etape... Etapes){sasE.ajouterSuccesseur(Etapes);}

    public void aCommeSortie(Etape... Etapes){for(Etape e : Etapes) e.ajouterSuccesseur(sasS);}

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

    public String toString(){return gEtapes.toString()+"\n nombre de guichets "+nbGuichets();}

    public String toC(){
        StringBuilder str = new StringBuilder();

        str.append("#include \"def.h\"\n");

        for(Etape e : gEtapes){
            str.append("#define ").append(e.nom).append(" ").append(e.num).append("\n");
        }

        str.append("void simulation(int ids){\n");
        str.append(sasE.toC());


        str.append("}\n");
        return str.toString();
    }
}
