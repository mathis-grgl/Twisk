package twisk.exceptions;

/**
 * La classe delaiException gérant les Exceptions de délais sur les activités.
 */
public class DelaiException extends TwiskException{
    /**
     * Instancies une nouvelle instance de delaiException.
     */
    public DelaiException(){
        super("Saisi des entiers pour les délais.");
    }
}
