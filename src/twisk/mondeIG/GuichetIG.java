package twisk.mondeIG;

public class GuichetIG extends EtapeIG{
    private int nbJetons;

    /**
     * Instancie un nouveau GuichetIG.
     * @param nom  Le nom
     * @param idf  L'identifiant
     * @param larg La largeur
     * @param haut La hauteur
     */
    public GuichetIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        nbJetons = 2;
    }

    /**
     * Retourne le nombre de jetons d'un GuichetIG.
     * @return le nombre de jetons
     */
    public int getNbJetons() {
        return nbJetons;
    }

    /**
     * Modifie le nombre de jetons d'un GuichetIG en fonction de l'entier en paramètre.
     */
    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public boolean estUneActiviteRestreinte() {
        return false;
    }

    /**
     * Retourne le nom modifié avec le nombre de jetons du guichet.
     * @return Le nom modifié
     */
    @Override
    public String getNom(){
        return super.getNom()+" : "+nbJetons+" jetons";
    }
}
