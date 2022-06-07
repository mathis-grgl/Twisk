package twisk.mondeIG;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Représente la classe CorrespondanceEtapes (Liaison entre Etape et EtapeIG).
 */
public class CorrespondanceEtapes {
    private HashMap<EtapeIG, Etape> hashEtapesIGtoEtapes;
    private HashMap<Etape,EtapeIG> hashEtapestoEtapesIG;

    /**
     * Instancie une nouvelle instance de CorrespondanceEtapes.
     */
    public CorrespondanceEtapes(){
        hashEtapesIGtoEtapes = new HashMap<>();
        hashEtapestoEtapesIG = new HashMap<>();
    }

    /**
     * Ajoute l'étapeIG et son étape correspondante à la HashMap.
     * @param etapeIG L'étapeIG en question
     * @param etape  L'étape correspondante
     */
    public void ajouter(EtapeIG etapeIG, Etape etape){
        hashEtapesIGtoEtapes.put(etapeIG,etape);
        hashEtapestoEtapesIG.put(etape,etapeIG);
    }

    /**
     * Renvoie l'étape correspondante à l'étapeIG.
     * @param etape L'étapeIG
     */
    public Etape get(EtapeIG etape){
        return hashEtapesIGtoEtapes.get(etape);
    }

    public EtapeIG getEtapeIG(Etape etape){
        return hashEtapestoEtapesIG.get(etape);
    }

    /**
     * Fait la correspondance des successeurs entre  l'étapeIG et l'étape classique.
     * @param etapeIG EtapeIG correspondante
     */
    public void correspondanceSucc(EtapeIG etapeIG){
        for (EtapeIG etapeIGSucc : etapeIG.getListSuccesseurs()){
            Etape etape = get(etapeIG);
            Etape etapeSucc = get(etapeIGSucc);
            etape.ajouterSuccesseur(etapeSucc);
        }
    }

}
