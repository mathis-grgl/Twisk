package exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * La classe delaiException gérant les Exceptions de délais sur les activités.
 */
public class delaiException extends TwiskException{
    /**
     * Instancies une nouvelle instance de delaiException.
     */
    public delaiException(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Le délai ou l'écart-temps est faux");
        alert.setTitle("Erreur dans les délais");
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.playFromStart();
        pause.setOnFinished(e -> alert.close());
    }
}
