package twisk.outils;

/**
 * Représente le singleton FabriqueIdentifiant.
 */
public class FabriqueIdentifiant {
    private int noEtape;
    private static FabriqueIdentifiant instance;

    private FabriqueIdentifiant(){}

    /**
     * Retourne l'instance de FabriqueIdentifiant.
     * @return L'instance FabriqueIdentifiant
     */
    public static FabriqueIdentifiant getInstance(){
        if (instance == null) instance = new FabriqueIdentifiant();
        return instance;}

    /**
     * Retourne un identifiant unique pour une étape.
     * @return L'identifiant unique
     */
    public String getIdentifiantEtape(){
        int cmp = noEtape;
        noEtape+=1;
        return String.valueOf(cmp);}
}
