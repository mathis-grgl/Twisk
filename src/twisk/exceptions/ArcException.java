package twisk.exceptions;

/**
 * La classe arcException gérant les Exceptions liés à la création d'arcs.
 */
public class ArcException extends TwiskException{
    /**
     * Instancies une nouvelle instance d'arcException.
     */
    public ArcException(){
        super("Problème lié à la création de l'arc.");
    }
}
