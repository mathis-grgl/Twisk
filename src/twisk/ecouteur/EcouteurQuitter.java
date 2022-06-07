package twisk.ecouteur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.outils.ThreadsManager;

/**
 * Représente l'écouteur qui permet de fermer la fenêtre.
 */
public class EcouteurQuitter implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        ThreadsManager.getInstance().detruireTout();
        Platform.exit();
    }
}
