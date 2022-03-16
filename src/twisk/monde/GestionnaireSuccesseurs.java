package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Représente la classe GestionnaireSuccesseurs, qui est itérable.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class GestionnaireSuccesseurs implements Iterable<Etape>{
    /**
     * Liste des successeurs de l'étape.
     */
    private ArrayList<Etape> gSuccesseur;

    /**
     * Initialise un nouveau Gestionnaire de successeurs.
     */
    public GestionnaireSuccesseurs(){
        this.gSuccesseur = new ArrayList<>();
    }

    /**
     * Ajoute une étape en successeurs de l'étape.
     * @param successeurs la ou les étapes pour ajouter au Gestionnaire de successeurs
     */
    public void ajouter(Etape... successeurs){
        this.gSuccesseur.addAll(Arrays.asList(successeurs));
    }

    /**
     * Retourne le nombre de successeurs de l'étape.
     * @return Le nombre de successeurs
     */
    public int nbEtapes(){
        return gSuccesseur.size();
    }

    /** Retourne l'itérateur du Gestionnaire de successeurs.
     * @return L'itérateur de successeurs
     */
    public Iterator<Etape> iterator(){
        return gSuccesseur.iterator();
    }

    /**
     * Retourne le successeur voulu.
     * @param index Le numéro du successeur souhaité présent dans la liste
     * @return Le successeur souhaité
     */
    public Etape getSucc(int index){
        return gSuccesseur.get(index);
    }

    /**
     * Retourne une chaîne de caractères contenant le nombre de successeurs et le nom de chaque successeur.
     * @return Taille + nom de chaque successeur
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(gSuccesseur.size()).append(" successeur");
        if(gSuccesseur.size()!=0) {
            for (int i = 0; i < gSuccesseur.size(); i++) {
                str.append(" - "+gSuccesseur.get(i).getNom() );
            }
        }
        return str.toString();
    }
}
