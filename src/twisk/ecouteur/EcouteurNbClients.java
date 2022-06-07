package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.exceptions.ClientException;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Permet de modifier le nombre de clients dans la simulation.
 */
public class EcouteurNbClients implements EventHandler<ActionEvent> {
    private MondeIG mondeIG;

    /**
     * Instancie un nouveau EcouteurNbClients.
     * @param monde Le monde où se trouve les étapes.
     */
    public EcouteurNbClients(MondeIG monde) {
        this.mondeIG = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialogue = new TextInputDialog();
        dialogue.setTitle("Modifier le nombre de clients");
        dialogue.setHeaderText(null);
        dialogue.setContentText("Nouveau nombre de clients pour le monde : ");

        Optional<String> out = dialogue.showAndWait();
        out.ifPresent(nbClientsString -> {
            int nbClients = Integer.parseInt(nbClientsString);
            if(nbClients>=1 && nbClients<=99) {
                mondeIG.setNbClients(nbClients);
            } else
                try {
                    throw new ClientException();
                } catch (TwiskException ignored) {
                }
        });
    }
}
