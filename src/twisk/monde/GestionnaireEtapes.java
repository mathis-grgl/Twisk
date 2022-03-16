package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Représente la classe GestionnaireEtapes, qui est itérable.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class GestionnaireEtapes implements Iterable<Etape>{
    /**
     * Liste des étapes.
     */
    private ArrayList<Etape> gEtapes;

    /**
     * Initialise un nouveau Gestionnaire d'étapes.
     */
    public GestionnaireEtapes(){this.gEtapes = new ArrayList<>();}

    /**
     * Ajoute une étape au Gestionnaire d'étapes.
     * @param Etapes la ou les étapes pour ajouter au Gestionnaire d'étapes
     */
    public void ajouter(Etape... Etapes){gEtapes.addAll(Arrays.asList(Etapes));}

    /**
     * Retourne le nombre d'étapes du Gestionnaire d'étapes.
     * @return Le nombre d'étapes
     */
    public int nbEtapes(){
        return gEtapes.size();
    }

    /** Retourne l'itérateur du Gestionnaire d'étapes.
     * @return L'itérateur d'étapes
     */
    public Iterator<Etape> iterator(){
        return gEtapes.iterator();
    }

    /**
     * Retourne une chaîne de caractères contenant le nombre d'étapes et le nom de chaque étape.
     * @return Taille + nom de chaque étape
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(gEtapes.size() + " etapes");
        if(gEtapes.size()!=0) {
            for (int i = 0; i < gEtapes.size(); i++) {
                str.append(" - "+gEtapes.get(i).nom );
            }
        }
        return str.toString();
    }
}
