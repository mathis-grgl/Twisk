package twisk.Ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
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
            monde.dragnDrop(db.getString(),(int) dragEvent.getSceneX(),(int) dragEvent.getSceneY());
            dragEvent.setDropCompleted(true);
            this.monde.notifierObservateurs();
        }
        dragEvent.consume();
    }
}
