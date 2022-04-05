package Ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mondeIG.ArcIG;
import mondeIG.MondeIG;


/**
 * Représente l'écouteur permettant de sélectionner un arc.
 */
public class EcouteurArc implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private ArcIG arc;

    /**
     * Instancie un nouveau EcouteurArc.
     * @param monde Le monde correspondant
     * @param arc   L'arc en question
     */
    public EcouteurArc(MondeIG monde, ArcIG arc) {
        this.arc = arc;
        this.monde = monde;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.monde.selectDeselect(arc);
        System.out.println("L'arc selectionnée est au position x :"+arc.getP1().getPosX()+" "+arc.getP2().getPosX()+", et y : "+arc.getP1().getPosY()+ " "+arc.getP2().getPosY());
        this.monde.notifierObservateurs();
    }
}