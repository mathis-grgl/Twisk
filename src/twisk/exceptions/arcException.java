package twisk.exceptions;

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
        super("l'arc");
    }
}
