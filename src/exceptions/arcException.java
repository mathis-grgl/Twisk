package exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * La classe arcException gérant les Exceptions liés à la création d'arcs.
 */
public class arcException extends TwiskException{
    /**
     * Instancies une nouvelle instance d'arcException.
     */
    public arcException(){
        super();
        Alert alert = new Alert(Alert.AlertType.ERROR,"L'arc ne peut pas être crée");
        alert.setTitle("Erreur dans la création de l'arc");
        alert.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.playFromStart();
        pause.setOnFinished(e -> alert.close());
    }
}
