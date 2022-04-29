package twisk.mondeIG;

/**
 * Représente la classe ActiviteIG (sous classe d'EtapeIG).
 */
public class ActiviteIG extends EtapeIG {
    /**
     * Représente les délais pour les activités.
     */
    private int temps,ecartTemps;
    private  boolean activiteRestreinte;

    /**
     * Instancie une nouvelle ActiviteIG.
     * @param nom  Le nom
     * @param idf  L'identifiant
     * @param larg La largeur
     * @param haut La hauteur
     */
    public ActiviteIG(String nom, String idf, int larg, int haut){
        super(nom,idf,larg,haut);
        temps = 4;
        ecartTemps = 2;
        activiteRestreinte = false;
    }

    /**
     * Change le temps de l'activité en fonction du temps en paramètre.
     * @param temps Le nouveau temps
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Change l'écart-temps en fonction du temps en paramètre de l'activité.
     * @param ecartTemps Le nouvel écart-temps
     */
    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * Retourne le nom modifié avec le temps et l'écart-temps de l'activité.
     * @return Le nom modifié
     */
    @Override
    public String getNom(){
        return super.getNom()+" : "+temps + " +- "+ecartTemps + " temps";
    }

    /**
     * Retourne le temps de l'activité.
     * @return Le temps
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Retourne l'écart-temps de l'activité.
     * @return L'écart-temps
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * retourne si c est une activite restrainte ou pas
     * @return boolean
     */
    @Override
    public boolean estUneActiviteRestreinte(){
        return activiteRestreinte;
    }


    /**
     * setter sur activiterRestrainte.
     */
    public void setEstUnActiviteRestreinte(){
        activiteRestreinte = !activiteRestreinte;
    }
}
