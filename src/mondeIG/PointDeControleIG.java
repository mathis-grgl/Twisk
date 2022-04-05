package mondeIG;

/**
 * Représente la classe PointDeControleIG.
 */
public class PointDeControleIG {
    private int posX,posY;
    private String id;
    private EtapeIG etape;

    /**
     * Instancie un nouveau PointDeControleIG.
     * @param posX  La position en x
     * @param posY  La position en y
     * @param id    L'identifiant du point
     * @param etape L'étape du point
     */
    public PointDeControleIG(int posX, int posY, String id, EtapeIG etape){
        this.posX = posX;
        this.posY = posY;
        this.id = id;
        this.etape = etape;
    }

    /**
     * Retourne la position en y du pdcIG.
     * @return La position en y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Retourne la position en x du pdcIG.
     * @return La position en x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Modifie la position en x et en y du pdcIG.
     * @param posX La nouvelle position x
     * @param posY La nouvelle position y
     */
    public void setPos(int posX,int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Retourne l'étape du pdcIG.
     * @return L'étape correspondante
     */
    public EtapeIG getEtape() {
        return etape;
    }

    /**
     * Retourne l'identifiant du pdcIG.
     * @return L'identifiant correspondant
     */
    public String getId() {
        return id;
    }
}
