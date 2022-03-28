package twisk.monde;

/**
 * Représente la classe SasEntree, qui hérite de Activite.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class SasEntree extends Activite {
    /**
     * Initialise un nouveau sas d'entrée.
     */
    public SasEntree() {
        super("entree");
    }

    public String toString(){return super.toString();}

    /**
     * Génère un morceau de code du sas d'entrée pour client.c.
     * @return Une chaîne de caractère (String)
     */
    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        str.append("entrer(")
                .append(getNomNumero())
                .append(");\ndelai(")
                .append(temps)
                .append(",")
                .append(ecartTemps)
                .append(");\n")
                .append("transfert(")
                .append(getNomNumero())
                .append(",")
                .append(gestSucc.getSucc(0).getNomNumero())
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());
        return str.toString();
    }
}