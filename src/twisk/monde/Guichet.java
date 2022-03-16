package twisk.monde;

import twisk.outils.FabriqueNumero;

/**
 * Représente la classe Guichet, qui hérite de Etape.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class Guichet extends Etape{
    /**
     * Le nombre de jetons du guichet.
     */
    protected int nbjetons;
    /**
     * Le sémaphore (identifiant unique) du guichet.
     * @see FabriqueNumero
     */
    protected int sema;

    /**
     * Initialise un nouveau guichet.
     * @param nom Le nom du guichet
     */
    public Guichet(String nom){
        super(nom);
        nbjetons = 1;
        sema = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    /**
     * Initialise un nouveau guichet.
     * @param nom Le nom du guichet
     * @param nb  Le nombre de jetons du guichet
     */
    public Guichet(String nom, int nb){
        super(nom);
        this.nbjetons = nb ;
        sema = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    /**
     * Retourne le nombre de jetons du guichet.
     * @return Le nombre de jetons du guichet
     */
    public int getNbjetons() {
        return nbjetons;
    }

    public String toString(){return super.toString();}

    @Override
    public String toNonC() {
        return null;
    }

    /**
     * Génère un morceau de code du guichet pour client.c.
     * @return Une chaîne de caractère (String)
     */
    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        str.append("P(ids,")
                .append(sema)
                .append(");\n")
                .append("transfert(")
                .append(getNomBien())
                .append(",")
                .append(gestSucc.getSucc(0).getNomBien())
                .append(");\n")
                .append(gestSucc.getSucc(0).toNonC())
                .append("V(ids,")
                .append(sema)
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());

        return str.toString();
    }
}
