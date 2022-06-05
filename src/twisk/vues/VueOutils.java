package twisk.vues;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import twisk.ecouteur.EcouteurAjouterActivite;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twisk.ecouteur.EcouteurAjouterGuichet;
import twisk.ecouteur.EcouteurSimuler;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;

/**
 * Représente la classe VueOutils (sous classe de TilePane). Permet d'ajouter des activités.
 */
public class VueOutils extends TilePane implements Observateur {
    private MondeIG monde;
    private Button simuler;

    /**
     * Instancie une nouvelle VueOutils.
     * @param monde Le monde où ajouter les activités
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde = monde;
        this.monde.notifierObservateurs();

        Button ajouterActivite = new Button("Ajouter activité");
        Button ajouterGuichet = new Button("Ajouter guichet");
        simuler = new Button();

        //Bouton Activite
        ajouterActivite.setPrefSize(150,30);
        ajouterActivite.setOnAction(new EcouteurAjouterActivite(this.monde));
        ajouterActivite.setTooltip(new Tooltip("Bouton qui permet d'ajouter une activité"));
        ajouterActivite.setAlignment(Pos.BOTTOM_LEFT);

        //Bouton Guichet
        ajouterGuichet.setPrefSize(150,30);
        ajouterGuichet.setOnAction(new EcouteurAjouterGuichet(this.monde));
        ajouterGuichet.setTooltip(new Tooltip("Bouton qui permet d'ajouter un guichet"));

        //Bouton Simulation
        simuler.setPrefSize(25,25);
        simuler.setTooltip(new Tooltip("Bouton qui permet de simuler"));
        simuler.setOnAction(new EcouteurSimuler(this.monde));

        //Image Activite
        ImageView activiteIMG = new ImageView(new Image("/twisk/ressources/images/activite.png"));
        activiteIMG.setFitHeight(25);
        activiteIMG.setPreserveRatio(true);
        ajouterActivite.setGraphic(activiteIMG);

        //Image Guichet
        ImageView guichetIMG = new ImageView(new Image("/twisk/ressources/images/guichet.png"));
        guichetIMG.setFitHeight(25);
        guichetIMG.setPreserveRatio(true);
        ajouterGuichet.setGraphic(guichetIMG);

        //Image Simulation
        if(!monde.getEstLancee()){
            ImageView playIMG = new ImageView(new Image("/twisk/ressources/images/play.png"));
            playIMG.setFitHeight(25);
            playIMG.setPreserveRatio(true);
            simuler.setGraphic(playIMG);
        }

        //Ajout des boutons à la fenêtre
        this.getChildren().addAll(ajouterActivite,ajouterGuichet,simuler);
    }

    @Override
    public void reagir() {
        //Image Simulation
        if(!monde.getEstLancee()){
            ImageView playIMG = new ImageView(new Image("/twisk/ressources/images/play.png"));
            playIMG.setFitHeight(25);
            playIMG.setPreserveRatio(true);
            simuler.setGraphic(playIMG);
        } else {
            ImageView pauseIMG = new ImageView(new Image("/twisk/ressources/images/pause.png"));
            pauseIMG.setFitHeight(25);
            pauseIMG.setPreserveRatio(true);
            simuler.setGraphic(pauseIMG);
        }
    }
}
