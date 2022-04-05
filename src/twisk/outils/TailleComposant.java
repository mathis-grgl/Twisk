package twisk.outils;

/**
 * Représente le singleton TailleComposant.
 */
public class TailleComposant {
    private int largeur,hauteur;
    private static TailleComposant instance;

    private TailleComposant(){
        hauteur = 80;
        largeur = (int) (hauteur*2.5);
    }

    /**
     * Retourne l'instance de TailleComposant.
     * @return L'instance TailleComposant
     */
    public static TailleComposant getInstance(){
        if (instance == null) instance = new TailleComposant();
        return instance;}

    /**
     * Retourne la largeur d'un composant.
     * @return La largeur
     */
    public int getLargeur(){
        return largeur;
    }

    /**
     * Retourne la hauteur d'un composant.
     * @return La hauteur
     */
    public int getHauteur(){
        return hauteur;
    }
}
