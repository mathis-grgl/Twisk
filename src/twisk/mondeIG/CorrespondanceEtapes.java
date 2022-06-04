package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Représente la classe CorrespondanceEtapes (Liaison entre Etape et EtapeIG).
 */
public class CorrespondanceEtapes {
    private HashMap<EtapeIG, Etape> hashEtapes;

    /**
     * Instancie une nouvelle instance de CorrespondanceEtapes.
     */
    public CorrespondanceEtapes(){
        hashEtapes = new HashMap<>();
    }

    /**
     * Ajoute l'étapeIG et son étape correspondante à la HashMap.
     * @param etapeIG L'étapeIG en question
     * @param etape  L'étape correspondante
     */
    public void ajouter(EtapeIG etapeIG, Etape etape){
        hashEtapes.put(etapeIG,etape);
    }

    /**
     * Renvoie l'étape correspondante à l'étapeIG.
     * @param etape L'étapeIG
     */
    public Etape get(EtapeIG etape){
        return hashEtapes.get(etape);
    }

    /**
     * ajoute les successeur de etapeIG a Etape
     * @param etapeIG EtapeIG
     */
    public void correspondanceSucc(EtapeIG etapeIG){
        for (EtapeIG etapeIGSucc : etapeIG.getListSuccesseurs()){
            Etape etape = get(etapeIG);
            Etape etapeSucc = get(etapeIGSucc);
            etape.ajouterSuccesseur(etapeSucc);
        }
    }

}
