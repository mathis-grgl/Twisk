package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;

/**
 * Représente la classe CorrespondanceEtapes (elle fait la liaison entre Etape et EtapeIG).
 */
public class CorrespondanceEtapes {
    private HashMap<EtapeIG, Etape> etapes;

    /**
     * constructeur CorrespondanceEtapes
     */
    public CorrespondanceEtapes(){
        etapes = new HashMap<>();
    }

    /**
     * ajoute un element a la HashMap
     * @param etig  EtapeIG
     * @param et  Etape
     */
    void ajouter(EtapeIG etig, Etape et){
        etapes.put(etig,et);
    }

    /**
     * renvoie l Etape correspondante a EtapeIG.
     * @param e EtapeIG
     */
    public Etape get(EtapeIG e){
        return etapes.get(e);
    }
}
