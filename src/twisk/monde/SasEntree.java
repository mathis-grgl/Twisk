package twisk.monde;

/**
 * Représente la classe SasEntree, qui hérite de Activite.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class SasEntree extends Activite {
    private String loi ;

    /**
     * Initialise un nouveau sas d'entrée.
     */
    public SasEntree(String loi) {
        super("entree");
        this.loi = loi ;
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
                .append("); \n");
        if (this.loi.equals("Uniforme")) {
            str.append("delai((int) delaiUniforme(5,1),1); \n");
        } else if (this.loi.equals("Gaussienne")){
            str.append("delai((int) delaiGauss(5,1),1); \n");
        }else {// Exponentielle
            str.append("delai((int) delaiExponentiel(1.0/5.0),1); \n");
        }

        str.append("transfert(")
                .append(getNomNumero())
                .append(",")
                .append(gestSucc.getSucc(0).getNomNumero())
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());
        return str.toString();
    }
}