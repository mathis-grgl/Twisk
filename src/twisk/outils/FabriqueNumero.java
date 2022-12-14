package twisk.outils;

/**
 * Représente le singleton FabriqueNumero.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class FabriqueNumero {
    /**
     * Le numéro unique de chaque étape.
     */
    private int cptEtape;
    /**
     *  Le numéro unique de chaque guichet (sémaphore).
     */
    private int cptSemaphore;
    /**
     * L'instance de FabriqueNumero.
     */
    private static FabriqueNumero instance = null;
    private int noSim;

    /**
     * Initialise un nouveau FabriqueNumero (privé).
     */
    private FabriqueNumero(){
        cptEtape = 0;
        cptSemaphore = 1;
        noSim = 0;
    }

    /**
     * Retourne l'instance de FabriqueNumero.
     * @return L'instance FabriqueNumero
     */
    public static FabriqueNumero getInstance(){
        if (instance == null) instance = new FabriqueNumero();
        return instance;
    }

    /**
     * Retourne le numéro unique de l'étape.
     * @return Le numéro de l'étape
     * @see twisk.monde.Etape
     */
    public int getNumeroEtape(){
        int cmp = cptEtape;
        cptEtape+=1;
        return cmp;
    }

    /**
     * Retourne le numéro unique du guichet.
     * @return Le sémaphore du guichet
     * @see twisk.monde.Guichet
     */
    public int getNumeroSemaphore(){
        int cmp = cptSemaphore;
        cptSemaphore+=1;
        return cmp;
    }

    /**
     * Retourne le numéro de la simulation actuelle.
     * @return le n° de la sim en string
     */
    public String getNoSim() {
        return String.valueOf(noSim);
    }

    /**
     * Incrémente et retourne le numéro de la simulation.
     * @return le nouveau n° de la simulation en string
     */
    public String getNouveauNoSim() {
        noSim += 1;
        return String.valueOf(noSim);
    }

    /**
     * Réinitialise les variables à l'état défaut.
     */
    public void reset(){
        cptEtape = 0;
        cptSemaphore = 1;
    }

    /**
     * Réinitialise le numéro de la simulation à l'état défaut.
     */
    public void resetNoSim(){
        noSim = 0;
    }
}
