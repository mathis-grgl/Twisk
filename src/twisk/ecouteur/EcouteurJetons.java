package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.exceptions.TwiskException;
import twisk.exceptions.JetonsException;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Représente l'écouteur permettant de modifier le nombre de jetons des guichets.
 */
public class EcouteurJetons implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurJetons.
     * @param monde Le monde où se trouve les étapes.
     */
    public EcouteurJetons(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialogue = new TextInputDialog();
        dialogue.setTitle("Modifier le nombre de jetons");
        dialogue.setHeaderText(null);
        dialogue.setContentText("Nouveau nombre de jetons pour "+this.monde.getListeEtapesSelec().get(0).getNom());

        Optional<String> out = dialogue.showAndWait();
        out.ifPresent(nbJetonsString -> {
            int nbJetons = Integer.parseInt(nbJetonsString);
            if(nbJetons>=1 && nbJetons<=99)
                this.monde.modifNbJetons(nbJetons);
            else
                try {
                    throw new JetonsException();
                } catch (TwiskException ignored) {}
        });

        this.monde.resetListeSelec();
        this.monde.notifierObservateurs();
    }
}
