package twisk.exceptions;

public class ClientException extends TwiskException{
    /**
     * Instancies une nouvelle instance de delaiException.
     */
    public ClientException(){
        super("Saisi des entiers pour les clients.");
    }
}
