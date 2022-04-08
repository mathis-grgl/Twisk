package twisk.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * La classe jetonsException gérant les Exceptions de jetons sur GuichetIG.
 */
public class jetonsException extends TwiskException {
    /**
     * Instancies une nouvelle instance de jetonsException.
     */
    public jetonsException(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Le nombre de jetons est faux");
        alert.setTitle("Erreur dans le nombre de jetons");
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.playFromStart();
        pause.setOnFinished(e -> alert.close());
    }
}
