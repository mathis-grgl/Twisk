package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;

import java.util.ArrayList;


/**
 * Représente la Vue d'un GuichetIG (sous classe de VueEtapeIG).
 */
public class VueGuichetIG extends VueEtapeIG {
    private MondeIG monde;
    private EtapeIG etape;
    private HBox hbox;
    private ArrayList<Label> listeJetons;

    /**
     * Instancie une nouvelle VueActiviteIG.
     * @param monde Le monde de l'activité
     * @param etape L'activité à afficher
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape){
        super(monde,etape);
        this.monde = monde;
        this.etape = etape;

        hbox = new HBox();
        hbox.setStyle("-fx-background-color: #F7BEC0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        hbox.relocate(etape.getPosX(), etape.getPosY());
        listeJetons = new ArrayList<>();

        for(int i = 0;i<((GuichetIG) etape).getNbJetons();i++) {
            Label cases = new Label("");
            cases.setStyle("-fx-border-color: #0059FF;-fx-background-color: #F7BEC0 ;");
            cases.setPrefSize(15,15);
            listeJetons.add(cases);
            hbox.getChildren().add(cases);
        }
        hbox.setPrefSize(15* listeJetons.size(),15);
        hbox.setMaxSize(15* listeJetons.size(),15);
        hbox.setMinSize(15* listeJetons.size(),15);

        this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: #0059FF; -fx-background-color: #E9EAE0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
        this.setPadding(new Insets(0,5,0,5));
        this.setPrefSize(TailleComposant.getInstance().getLargeurGUI(),TailleComposant.getInstance().getHauteurGUI());

        if(this.monde.getListeEtapesSelec() !=null) {
            if (this.monde.getListeEtapesSelec().contains(this.etape)) {
                this.setStyle("-fx-border-radius: 7 7 7 7;-fx-border-color: RED; -fx-background-color: #fffdd0 ;-fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 7 7 7 7 ;");
            }
        }

        this.getChildren().add(hbox);
    }

    @Override
    public void reagir() {

    }
}
