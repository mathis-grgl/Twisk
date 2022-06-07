package twisk.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * La classe TwiskException gérant les Exceptions principales.
 */
public class TwiskException extends Exception{
    /**
     * Instancies une nouvelle instance de TwiskException.
     */
    public TwiskException(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR,error);
        String titleError = "Erreur";
        alert.setTitle(titleError);
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.playFromStart();
        pause.setOnFinished(e -> alert.close());
    }
}
