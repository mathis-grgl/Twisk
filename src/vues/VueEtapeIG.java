package vues;

import Ecouteur.EcouteurDrag;
import Ecouteur.EcouteurEtape;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import mondeIG.EtapeIG;
import mondeIG.MondeIG;
import outils.TailleComposant;


/**
 * Représente la vue d'une EtapeIG (sous classe d'une VBox).
 */
public abstract class VueEtapeIG extends VBox implements Observateur{
    private MondeIG monde;
    private EtapeIG etape;
    private Label titre;

    /**
     * Instancie une nouvelle VueEtapeIG.
     * @param monde Le monde de l'étape
     * @param etape L'étape à afficher
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape){
        this.monde = monde;
        this.etape = etape;

        titre = new Label(this.etape.getNom());

        this.relocate(etape.getPosX(),etape.getPosY());

        this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: #0059FF; -fx-background-color: #FAE8E0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(TailleComposant.getInstance().getLargeur(),TailleComposant.getInstance().getHauteur());

        if(this.etape.isEntree()){
            ImageView entreeIMG = new ImageView(new Image("E.png"));
            entreeIMG.setFitHeight(15);
            entreeIMG.setPreserveRatio(true);
            titre.setGraphic(entreeIMG);
        }

        if(this.etape.isSortie()){
            ImageView sortieIMG = new ImageView(new Image("S.png"));
            sortieIMG.setFitHeight(15);
            sortieIMG.setPreserveRatio(true);
            titre.setGraphic(sortieIMG);
        }

        this.setOnMouseClicked(new EcouteurEtape(this.monde,this.etape));

        this.setOnDragDetected(new EcouteurDrag(this,this.etape));

        this.getChildren().add(titre);

        if(this.monde.getListeEtapesSelec() !=null) {
            if (this.monde.getListeEtapesSelec().contains(this.etape)) {
                this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: RED; -fx-background-color: #fffdd0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
            }
        }
    }
}
