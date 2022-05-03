package twisk.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.EtapeIG;
import twisk.vues.VueEtapeIG;


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
        db.setDragViewOffsetX(vueEtape.getScaleX()+ etape.getLargeur() /2);
        db.setDragViewOffsetY(vueEtape.getScaleY()+ etape.getHauteur() /2);
        db.setContent(cc);

        mouseEvent.consume();
    }
}
