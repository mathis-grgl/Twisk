package twisk.monde;

/**
 * Représente la classe ActiviteRestreinte, qui hérite de Activite.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class ActiviteRestreinte extends Activite{
    /**
     * Initialise une nouvelle activité restreinte.
     * @param nom Le nom de l'activité restreinte
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Initialise une nouvelle activité restreinte.
     * @param nom Le nom de l'activité restreinte
     * @param t   Le temps de l'activité restreinte
     * @param e   L'écart-temps de l'activité restreinte
     */
    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    public String toString(){return super.toString();}
}
