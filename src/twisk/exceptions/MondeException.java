package twisk.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public class MondeException extends TwiskException{


    public MondeException(){
        super();
        Alert alert = new Alert(Alert.AlertType.ERROR,"Le monde est faux");
        alert.setTitle("Erreur dans le monde");
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.playFromStart();
        pause.setOnFinished(e -> alert.close());
    }
}
