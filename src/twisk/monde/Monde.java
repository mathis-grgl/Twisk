package twisk.monde;

import java.util.Iterator;

/**
 * Représente la classe Monde, qui est itérable.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class Monde implements Iterable<Etape>{
    /**
     * Gestionnaire des étapes du monde.
     */
    private GestionnaireEtapes gEtapes;
    /**
     * Sas d'entrée du monde.
     */
    private SasEntree sasE;
    /**
     * Sas de sortie du monde.
     */
    private SasSortie sasS;

    /**
     * Initialise un nouveau monde.
     */
    public Monde(String loi){
        sasE = new SasEntree(loi);
        sasS = new SasSortie();
        gEtapes = new GestionnaireEtapes();
        gEtapes.ajouter(sasE,sasS);
    }

    /**
     * Ajoute l'étape en successeur du sas d'entrée.
     * @param Etapes la ou les étapes à ajouter en successeur du sas d'entrée
     */
    public void aCommeEntree(Etape... Etapes){sasE.ajouterSuccesseur(Etapes);}

    /**
     * Ajoute le sas de sortie en successeur de l'étape.
     * @param Etapes la ou les étapes qui auront comme successeur le sas de sortie
     */
    public void aCommeSortie(Etape... Etapes){for(Etape e : Etapes) e.ajouterSuccesseur(sasS);}

    /**
     * Ajoute une étape au Gestionnaire d'étapes.
     * @param Etapes la ou les étapes pour ajouter au Gestionnaire d'étapes
     * @see GestionnaireEtapes
     */
    public void ajouter(Etape... Etapes){gEtapes.ajouter(Etapes);}

    /**
     * Retourne le nombre d'étapes du Gestionnaire d'étapes.
     * @return Le nombre d'étapes
     * @see GestionnaireEtapes
     */
    public int nbEtapes(){
        return gEtapes.nbEtapes();
    }

    /**
     * Retourne le nombre de guichets du Gestionnaire d'étapes.
     * @return Le nombre de guichets
     * @see GestionnaireEtapes
     */
    public int nbGuichets(){
        int cmp =0;
        Iterator<Etape> g = gEtapes.iterator();
        while(g.hasNext()){
            if(g.next().estUnGuichet()) cmp++;
        }
        return cmp;
    }

    /** Retourne l'itérateur du Gestionnaire d'étapes.
     * @return L'itérateur d'étapes
     */
    public Iterator<Etape> iterator(){
        return gEtapes.iterator();
    }

    /**
     * Retourne une chaîne de caractères contenant le nombre d'étapes, le nom de chaque étape et le nombre de guichets.
     * @return nb étapes + nom de chaque étape + nb guichets
     */
    public String toString(){return gEtapes.toString()+"\n nombre de guichets "+nbGuichets();}

    /**
     * Génère tout le code pour client.c.
     * @return Une chaîne de caractère (String)
     */
    public String toC(){
        StringBuilder str = new StringBuilder();

        str.append("#include \"def.h\" \n" +
                "#include \"lois.h\n \n");

        for(Etape e : gEtapes){
            str.append("#define ").append(e.getNomNumero()).append(" ").append(e.num).append("\n");
        }
        for(Etape e : gEtapes){
            if(e.estUnGuichet()){
                Guichet g  = (Guichet) e;
                str.append("#define ").append(g.getNomSema()).append(" ").append(g.getSema()).append("\n");
            }
        }

        str.append("void simulation(int ids){\n");
        str.append(sasE.toC());


        str.append("}\n");
        return str.toString();
    }
}
