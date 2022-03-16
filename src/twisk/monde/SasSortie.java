package twisk.monde;

/**
 * Représente la classe SasSortie, qui hérite de Activite.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class SasSortie extends Activite{
    /**
     * Initialise un nouveau sas de sortie.
     */
    public SasSortie() {
        super("sortie");
    }

    public String toString(){return super.toString();}

    /**
     * Génère un morceau de code (vide) du sas de sortie pour client.c.
     * @return Une chaîne de caractère (String)
     */
    @Override
    public String toC() {
        return "";
    }
}
