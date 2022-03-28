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

    /**
     * Retourne le sémaphore (unique) de l'étape.
     * @return Le sémaphore
     */
    public int getSema() {
        return sema;
    }

    public String toString(){return super.toString();}


    /**
     * Permet d'avoir le nom de l'étape sans espace ou caractère spéciaux afin de créer des noms de variables pour les sémaphores dans client.c (#define ...).
     * @return Le nom modifié
     */
    public String getNomSema() {
        return getNomNumero()+"Sema"+getSema();
    }

    @Override
    public String delai() {
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
                .append(getNomSema())
                .append(");\n")
                .append("transfert(")
                .append(getNomNumero())
                .append(",")
                .append(gestSucc.getSucc(0).getNomNumero())
                .append(");\n")
                .append(gestSucc.getSucc(0).delai())
                .append("V(ids,")
                .append(getNomSema())
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());

        return str.toString();
    }
}
