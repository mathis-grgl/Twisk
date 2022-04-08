package twisk.vues;

import twisk.Ecouteur.EcouteurDrag;
import twisk.Ecouteur.EcouteurEtape;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;


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

        this.setAlignment(Pos.CENTER);

        if(this.etape.estUneEntree()){
            ImageView entreeIMG = new ImageView(new Image("images/E.png"));
            entreeIMG.setFitHeight(15);
            entreeIMG.setPreserveRatio(true);
            titre.setGraphic(entreeIMG);
        }

        if(this.etape.estUneSortie()){
            ImageView sortieIMG = new ImageView(new Image("images/S.png"));
            sortieIMG.setFitHeight(15);
            sortieIMG.setPreserveRatio(true);
            titre.setGraphic(sortieIMG);
        }

        this.setOnMouseClicked(new EcouteurEtape(this.monde,this.etape));

        this.setOnDragDetected(new EcouteurDrag(this,this.etape));

        this.getChildren().add(titre);
    }
}
