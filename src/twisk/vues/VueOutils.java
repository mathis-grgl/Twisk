package twisk.vues;

import javafx.geometry.Pos;
import twisk.ecouteur.EcouteurAjouterActivite;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twisk.ecouteur.EcouteurAjouterGuichet;
import twisk.exceptions.MondeException;
import twisk.mondeIG.MondeIG;

/**
 * Représente la classe VueOutils (sous classe de TilePane). Permet d'ajouter des activités.
 */
public class VueOutils extends TilePane implements Observateur {
    private MondeIG monde;

    /**
     * Instancie une nouvelle VueOutils.
     * @param monde Le monde où ajouter les activités
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde = monde;

        Button ajouterActivite = new Button("Ajouter activité");
        Button ajouterGuichet = new Button("Ajouter guichet");
        Button simuler = new Button("simuler");

        //Bouton activite
        ajouterActivite.setPrefSize(150,30);
        ajouterActivite.setOnAction(new EcouteurAjouterActivite(this.monde));
        ajouterActivite.setTooltip(new Tooltip("Bouton qui permet d'ajouter une activité"));
        ajouterActivite.setAlignment(Pos.BOTTOM_LEFT);

        //Bouton Guichet
        ajouterGuichet.setPrefSize(150,30);
        ajouterGuichet.setOnAction(new EcouteurAjouterGuichet(this.monde));
        ajouterGuichet.setTooltip(new Tooltip("Bouton qui permet d'ajouter un guichet"));
        ajouterGuichet.setAlignment(Pos.BOTTOM_RIGHT);

        //Bouton Simulation
        simuler.setPrefSize(150,30);
        simuler.setTooltip(new Tooltip("Boutton qui permet de simuler"));
        simuler.setOnAction(actionEvent -> {
            try{
                monde.simuler();
            } catch (MondeException e) {
                e.printStackTrace();
            }

        });

        this.getChildren().addAll(ajouterActivite,ajouterGuichet,simuler);
    }

    @Override
    public void reagir() {

    }
}
