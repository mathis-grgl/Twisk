package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;
import twisk.vues.VueMondeIG;

/**
 * Représente l'écouteur du moment où on drop l'activité.
 */
public class EcouteurDropped implements EventHandler<DragEvent> {
    private MondeIG monde;
    private VueMondeIG vueMonde;

    /**
     * Instancie un nouveau EcouteurDropped.
     * @param vueMonde La vue du monde
     * @param monde    Le monde correspondant
     */
    public EcouteurDropped(VueMondeIG vueMonde,MondeIG monde) {
        this.monde = monde;
        this.vueMonde = vueMonde;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasString()) {
            monde.dragnDrop(db.getString(),(int) dragEvent.getX() - TailleComposant.getInstance().getLargeurAC() /2,(int) dragEvent.getY() - TailleComposant.getInstance().getHauteurAC() /2);
            dragEvent.setDropCompleted(true);
            this.monde.notifierObservateurs();
        }
        dragEvent.consume();
    }
}
