package mondeIG;

/**
 * Représente la classe ArcIG.
 */
public class ArcIG {
    private int posXp1,posYp1,posXp2,posYp2;
    private PointDeControleIG p1,p2;

    /**
     * Instancie un nouvel ArcIG.
     * @param p1 Le premier point de contrôle
     * @param p2 Le deuxième point de contrôle
     */
    public ArcIG(PointDeControleIG p1,PointDeControleIG p2){
        this.posXp1 = p1.getPosX();
        this.posXp2 = p2.getPosX();
        this.posYp1 = p1.getPosY();
        this.posYp2 = p2.getPosY();
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Retourne le premier point de contrôle.
     * @return Le 1er point de contrôle
     */
    public PointDeControleIG getP1() {
        return p1;
    }

    /**
     * Retourne le deuxième point de contrôle.
     * @return Le 2ᵉ point de contrôle
     */
    public PointDeControleIG getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "ArcIG{" +
                "posXp1=" + posXp1 +
                ", posYp1=" + posYp1 +
                ", posXp2=" + posXp2 +
                ", posYp2=" + posYp2 +
                '}';
    }
}

