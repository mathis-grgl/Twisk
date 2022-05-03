package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant d'ajouter un guichet.
 */
public class EcouteurAjouterGuichet implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurAjouterGuichet.
     * @param monde Le monde où se trouve l'activité
     */
    public EcouteurAjouterGuichet(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter("Guichet");
        this.monde.notifierObservateurs();
    }
}
