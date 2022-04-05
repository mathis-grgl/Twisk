package Ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import mondeIG.MondeIG;

import java.util.Optional;

/**
 * Représente l'écouteur qui permet de renommer une étape.
 */
public class EcouteurRenommer implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurRenommer.
     * @param monde Le monde où se trouve l'étape
     */
    public EcouteurRenommer(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialogue = new TextInputDialog();
        dialogue.setTitle("Renommer");
        dialogue.setHeaderText(null);
        dialogue.setContentText("Nouveau nom pour l'étape "+this.monde.getListeEtapesSelec().get(0).getNom());
        Optional<String> out = dialogue.showAndWait();

        out.ifPresent(nom -> {
            System.out.println("Nouveau nom pour l'étape "+this.monde.getListeEtapesSelec().get(0).getNom()+" est "+nom);
            this.monde.getListeEtapesSelec().get(0).setNom(nom);
        });
        this.monde.resetListeSelec();
        this.monde.notifierObservateurs();
    }
}
