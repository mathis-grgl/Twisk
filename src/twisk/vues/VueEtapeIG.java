package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.ecouteur.EcouteurDrag;
import twisk.ecouteur.EcouteurEtape;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;


/**
 * Représente la vue d'une EtapeIG (sous classe d'une VBox).
 */
public abstract class VueEtapeIG extends VBox implements Observateur{
    private MondeIG monde;
    private EtapeIG etape;
    private HBox titreAvecImages;

    /**
     * Instancie une nouvelle VueEtapeIG.
     * @param monde Le monde de l'étape
     * @param etape L'étape à afficher
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape){
        this.monde = monde;
        this.etape = etape;

        titreAvecImages = new HBox();
        titreAvecImages.setAlignment(Pos.CENTER);

        Label titre = new Label(this.etape.getNom());

        ImageView entreeIMG = new ImageView(new Image("images/E.png"));
        entreeIMG.setFitHeight(15);
        entreeIMG.setPreserveRatio(true);

        ImageView sortieIMG = new ImageView(new Image("images/S.png"));
        sortieIMG.setFitHeight(15);
        sortieIMG.setPreserveRatio(true);


        if(this.etape.estUneEntree()) titreAvecImages.getChildren().add(entreeIMG);

        titreAvecImages.getChildren().add(titre);

        if(this.etape.estUneSortie()) titreAvecImages.getChildren().add(sortieIMG);

        this.setOnMouseClicked(new EcouteurEtape(this.monde,this.etape));

        this.setOnDragDetected(new EcouteurDrag(this,this.etape));

        this.relocate(etape.getPosX(),etape.getPosY());

        this.setAlignment(Pos.CENTER);

        this.getChildren().add(titreAvecImages);
    }
}
