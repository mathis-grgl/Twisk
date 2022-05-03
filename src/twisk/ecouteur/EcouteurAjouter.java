package twisk.Ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant d'ajouter une activité.
 */
public class EcouteurAjouter implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurAjouter.
     * @param monde Le monde où se trouve l'activité
     */
    public EcouteurAjouter(MondeIG monde) {
        this.monde = monde;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter("Activite");
        this.monde.notifierObservateurs();
    }
}
