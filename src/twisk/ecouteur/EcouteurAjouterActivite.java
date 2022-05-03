package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant d'ajouter une activité.
 */
public class EcouteurAjouterActivite implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurAjouterActivite.
     * @param monde Le monde où se trouve l'activité
     */
    public EcouteurAjouterActivite(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter("Activite");
        this.monde.notifierObservateurs();
    }
}
