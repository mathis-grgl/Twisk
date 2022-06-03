package twisk.outils;

/**
 * Représente le singleton FabriqueIdentifiant.
 */
public class FabriqueIdentifiant {
    private int noEtape;
    private int noActivite;
    private int noSema;
    private static FabriqueIdentifiant instance;

    private FabriqueIdentifiant() {
        noEtape = 0;
        noSema = 0;
        noActivite = 0;
    }

    /**
     * Retourne l'instance de FabriqueIdentifiant.
     *
     * @return L'instance FabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance() {
        if (instance == null) instance = new FabriqueIdentifiant();
        return instance;
    }

    /**
     * Retourne un identifiant unique pour une étape.
     *
     * @return L'identifiant unique
     */
    public String getIdentifiantEtape() {
        int cmp = noEtape;
        noEtape += 1;
        return String.valueOf(cmp);
    }

    public String getSemaphore() {
        int cmp = noSema;
        noSema += 1;
        return String.valueOf(cmp);
    }

    public String getIdentifiantActivite() {
        int cmp = noActivite;
        noActivite += 1;
        return String.valueOf(cmp);
    }




}
