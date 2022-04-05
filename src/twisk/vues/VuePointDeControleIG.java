package twisk.vues;

import twisk.Ecouteur.EcouteurPointDeControle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Représente la Vue d'un PointDeControleIG (sous classe de Cicle).
 */
public class VuePointDeControleIG extends Circle {
    private MondeIG monde;
    private PointDeControleIG point;

    /**
     * Instancie une nouvelle VuePointDeControleIG.
     * @param monde Le monde correspondant
     * @param point Le point à afficher
     */
    public VuePointDeControleIG(MondeIG monde, PointDeControleIG point){
        this.monde = monde;
        this.point = point;

        this.setCenterX(this.point.getPosX());
        this.setCenterY(this.point.getPosY());

        this.setRadius(4);
        this.setStyle("-fx-fill: #EF7C8E ;");

        this.setOnMouseClicked(new EcouteurPointDeControle(monde,point));
    }
}
