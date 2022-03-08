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

    public String toString(){return gEtapes.toString()+"\n nombre de guichets "+nbGuichets();}

    public String toC(){
        StringBuilder str = new StringBuilder();
        str.append("#include \"def.h\"\n").append("#define ").append(sasE.nom).append(" 0\n").append("#define ").append(sasS.nom).append(" 1\n");
        int compteur=0;
        for(Etape e : gEtapes){
            if(compteur!=0 && compteur !=nbEtapes()-1) str.append("#define ").append(e.nom).append(" ").append(compteur+1).append("\n");
            compteur++;
        }
        for (int i = 1; i < nbGuichets()+1; i++) str.append("#define num_sema").append(i).append(" ").append(i).append("\n");
        str.append("void simulation(int ids){\n");
        str.append(sasE.toC());
        str.append("}\n");
        return str.toString();
    }
}
