package twisk.vues;

import twisk.Ecouteur.EcouteurArc;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

/**
 * Représente la vue d'un ArcIG (sous classe d'un Pane).
 */
public class VueArcIG extends Pane {
    private MondeIG monde;
    private ArcIG arc;
    private Line ligne;
    private Polyline fleche;

    /**
     * Instancie une nouvelle VueArcIG.
     * @param monde Le monde de l'arc
     * @param arc   L'arc à afficher
     */
    public VueArcIG(MondeIG monde, ArcIG arc){
        this.monde = monde;
        this.arc = arc;

        ligne = new Line(this.arc.getP1().getPosX(), this.arc.getP1().getPosY(), this.arc.getP2().getPosX(), this.arc.getP2().getPosY());

        double angle = Math.atan2((this.arc.getP2().getPosY()-this.arc.getP1().getPosY()),(this.arc.getP2().getPosX()-this.arc.getP1().getPosX())) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        fleche = new Polyline(this.arc.getP2().getPosX(),this.arc.getP2().getPosY(),
                (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 8 + this.arc.getP2().getPosX() ,(- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 8+this.arc.getP2().getPosY() ,
                ( 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 8 + this.arc.getP2().getPosX() ,( 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 8+this.arc.getP2().getPosY() ,
                this.arc.getP2().getPosX(),this.arc.getP2().getPosY());


        fleche.setStrokeWidth(4.0);
        fleche.setStroke(Paint.valueOf("#EF7C8E"));
        ligne.setStroke(Paint.valueOf("#EF7C8E"));
        ligne.setOpacity(8.0);

        ligne.setOnMouseClicked(new EcouteurArc(this.monde,this.arc));
        fleche.setOnMouseClicked(new EcouteurArc(this.monde,this.arc));

        this.getChildren().add(ligne);
        this.getChildren().add(fleche);

        if(this.monde.getListeArcsSelec() !=null) {
            if (this.monde.getListeArcsSelec().contains(this.arc)) {
                this.ligne.setStroke(Color.RED);
            }
        }
    }
}
