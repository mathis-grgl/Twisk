package twisk.exceptions;

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
        super("délai");
    }
}
