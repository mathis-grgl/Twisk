package twisk.exceptions;

/**
 * La classe ClientException gérant les Exceptions liés à la saisie du nombre de clients.
 */
public class ClientException extends TwiskException{
    /**
     * Instancies une nouvelle instance de delaiException.
     */
    public ClientException(){
        super("Saisi des entiers pour les clients.");
    }
}
