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
        super("Saisi des entiers pour les jetons.");
    }
}
