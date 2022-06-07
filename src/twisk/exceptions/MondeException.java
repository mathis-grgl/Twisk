package twisk.exceptions;

/**
 * La classe MondeException gérant les Exceptions liés à la validité du monde à simuler.
 */
public class MondeException extends TwiskException{

    /**
     * Instancie un nouveau MondeException avec en phrase le nom de l'erreur.
     * @param exceptionNom le nom de l'erreur
     */
    public MondeException(String exceptionNom){
        super(exceptionNom);
    }
}
