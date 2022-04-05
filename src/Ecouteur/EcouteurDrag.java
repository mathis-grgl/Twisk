package Ecouteur;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import mondeIG.EtapeIG;
import mondeIG.MondeIG;
import vues.VueEtapeIG;

import java.util.HashMap;


/**
 * Représente l'écouteur permettant de commencer le dragnDrop d'une activité.
 */
public class EcouteurDrag implements EventHandler<MouseEvent> {
    private EtapeIG etape;
    private VueEtapeIG vueEtape;

    /**
     * Instancie un nouveau EcouteurDrag.
     * @param vueEtape La vue de l'étape
     * @param etape    L'étape en question
     */
    public EcouteurDrag(VueEtapeIG vueEtape,EtapeIG etape) {
        this.etape = etape;
        this.vueEtape = vueEtape;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard db = vueEtape.startDragAndDrop(TransferMode.MOVE);
        vueEtape.setId(etape.getIdentifiant());

        ClipboardContent cc = new ClipboardContent();
        cc.putString(etape.getIdentifiant());

        db.setDragView(vueEtape.snapshot(new SnapshotParameters(),null));
        db.setContent(cc);

        mouseEvent.consume();
    }
}
