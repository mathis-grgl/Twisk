package vues;

import Ecouteur.EcouteurAjouter;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import mondeIG.MondeIG;

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
        Button ajouterB = new Button("Ajouter");
        ajouterB.setPrefSize(80,30);
        ajouterB.setOnAction(new EcouteurAjouter(this.monde));
        ajouterB.setTooltip(new Tooltip("Bouton qui permet d'ajouter une activité"));
        this.getChildren().add(ajouterB);
    }

    @Override
    public void reagir() {

    }
}
