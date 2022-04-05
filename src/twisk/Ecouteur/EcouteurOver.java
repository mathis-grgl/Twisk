package twisk.Ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMondeIG;

/**
 * Représente l'écouteur qui permet de déplacer l'étape sur le monde.
 */
public class EcouteurOver implements EventHandler<DragEvent> {
    private MondeIG monde;
    private VueMondeIG vueMonde;

    /**
     * Instancie un nouveau EcouteurOver.
     * @param vueMonde La vue du monde
     * @param monde    Le monde en question
     */
    public EcouteurOver(VueMondeIG vueMonde,MondeIG monde) {
    this.monde = monde;
    this.vueMonde = vueMonde;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasString())
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        dragEvent.consume();
    }
}
