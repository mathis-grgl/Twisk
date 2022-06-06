package twisk.exceptions;

/**
 * La classe jetonsException gérant les Exceptions de jetons sur GuichetIG.
 */
public class JetonsException extends TwiskException {
    /**
     * Instancies une nouvelle instance de jetonsException.
     */
    public JetonsException(){
        super("Saisi des entiers pour les jetons.");
    }
}
