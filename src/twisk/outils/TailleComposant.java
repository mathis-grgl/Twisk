package twisk.outils;

/**
 * Représente le singleton TailleComposant.
 */
public class TailleComposant {
    private int largeurAC,largeurGUI,hauteurGUI,hauteurAC;
    private static TailleComposant instance;

    private TailleComposant(){
        hauteurAC = 80;
        largeurAC = (int) (hauteurAC*2.5);
        hauteurGUI = 50;
        largeurGUI = 200;
    }

    /**
     * Retourne l'instance de TailleComposant.
     * @return L'instance TailleComposant
     */
    public static TailleComposant getInstance(){
        if (instance == null) instance = new TailleComposant();
        return instance;}

    /**
     * Retourne la largeur d'un composant activité.
     * @return La largeur
     */
    public int getLargeurAC(){
        return largeurAC;
    }

    /**
     * Retourne la hauteur d'un composant activité.
     * @return La hauteur
     */
    public int getHauteurAC(){
        return hauteurAC;
    }

    /**
     * Retourne la largeur d'un composant guichet.
     * @return La largeur
     */
    public int getLargeurGUI() {
        return largeurGUI;
    }

    /**
     * Retourne la hauteur d'un composant guichet.
     * @return La hauteur
     */
    public int getHauteurGUI() {
        return hauteurGUI;
    }
}
