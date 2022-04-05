package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;


/**
 * Représente la Vue d'une ActiviteIG (sous classe de VueEtapeIG).
 */
public class VueActiviteIG extends VueEtapeIG {

    /**
     * Instancie une nouvelle VueActiviteIG.
     * @param monde Le monde de l'activité
     * @param etape L'activité à afficher
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape){
        super(monde,etape);
        HBox boxClient = new HBox();
        boxClient.setStyle("-fx-border-color: #0059FF; -fx-background-color: #D8A7B1 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        boxClient.relocate(etape.getPosX(),etape.getPosY());
        boxClient.setPrefSize(TailleComposant.getInstance().getLargeur(), TailleComposant.getInstance().getHauteur()-40);
        this.getChildren().add(boxClient);
        this.setPadding(new Insets(0,5,0,5));
    }

    @Override
    public void reagir() {

    }
}
