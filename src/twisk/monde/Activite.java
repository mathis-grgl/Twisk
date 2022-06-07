package twisk.monde;

/**
 * Représente la classe Activite, qui hérite de Etape.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class Activite extends Etape{
    /**
     * Le temps qu'on passe dans l'activité.
     */
    protected int temps;
    /**
     * L'écart-temps qu'il peut y avoir dans l'activité.
     */
    protected int ecartTemps;

    /**
     * Booléen permettant d'écrire correctement le code dans Client.c
     * @see #toC()
     * @see #delai()
     */
    private boolean dejaAppele;

    /**
     * Initialise une nouvelle activité.
     * @param nom Le nom de l'activité
     */
    public Activite(String nom){
        super(nom);
        temps = 4;
        ecartTemps = 2;
        dejaAppele = false;
    }

    /**
     * Initialise une nouvelle activité.
     * @param nom Le nom de l'activité
     * @param t   Le temps de l'activité
     * @param e   L'écart-temps de l'activité
     */
    public Activite(String nom, int t, int e){
        super(nom);
        this.temps = t;
        this.ecartTemps = e;
        dejaAppele = false;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    @Override
    public String delai() {
        StringBuilder str = new StringBuilder();
        str.append("delai(")
                .append(temps)
                .append(",")
                .append(ecartTemps)
                .append(");\n");
        dejaAppele = true;
        return str.toString();
    }

    /**
     * Génère un morceau de code de l'activité pour client.c.
     * @return Une chaîne de caractère (String)
     */
    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        if(!dejaAppele) {
            str.append(delai());
            dejaAppele = false;
        }
        if(nbSuccesseur() == 1) {
            str.append("transfert(")
                    .append(getNomNumero())
                    .append(",")
                    .append(gestSucc.getSucc(0).getNomNumero())
                    .append(");\n");
            str.append(gestSucc.getSucc(0).toC());
        } else {
            str.append("int nb = (int) ( (rand() / (float) RAND_MAX ) * ")
                    .append(nbSuccesseur())
                    .append(") ;\n")
                    .append("switch(nb){\n");
            for (int i = 0; i < nbSuccesseur(); i++) {
                str.append("case "+ i + " :\n")
                        .append("transfert(")
                        .append(getNomNumero())
                        .append(",")
                        .append(gestSucc.getSucc(i).getNomNumero())
                        .append(");\n")
                        .append(gestSucc.getSucc(i).toC())
                        .append("break;\n");
            }
            str.append("}\n");

        }
        return str.toString();
    }
}
