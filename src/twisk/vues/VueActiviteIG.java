package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;


/**
 * Représente la Vue d'une ActiviteIG (sous classe de VueEtapeIG).
 */
public class VueActiviteIG extends VueEtapeIG {
    private MondeIG monde;
    private EtapeIG etape;

    /**
     * Instancie une nouvelle VueActiviteIG.
     * @param monde Le monde de l'activité
     * @param etape L'activité à afficher
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape){
        super(monde,etape);
        this.monde = monde;
        this.etape = etape;

        HBox boxClient = new HBox();

        boxClient.setStyle("-fx-border-color: #0059FF; -fx-background-color: #D8A7B1 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        boxClient.relocate(etape.getPosX(),etape.getPosY());
        boxClient.setPrefSize(TailleComposant.getInstance().getLargeurAC(), TailleComposant.getInstance().getHauteurAC()-40);

        this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: #0059FF; -fx-background-color: #FAE8E0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
        this.setPadding(new Insets(0,5,0,5));
        this.setPrefSize(TailleComposant.getInstance().getLargeurAC(),TailleComposant.getInstance().getHauteurAC());

        if(this.monde.getListeEtapesSelec() !=null) {
            if (this.monde.getListeEtapesSelec().contains(this.etape)) {
                this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: RED; -fx-background-color: #fffdd0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
            }
        }

        this.getChildren().add(boxClient);
    }

    @Override
    public void reagir() {

    }
}
