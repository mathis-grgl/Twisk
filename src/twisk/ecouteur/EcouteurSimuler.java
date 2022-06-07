package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.exceptions.MondeException;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant d'ajouter une activité.
 */
public class EcouteurSimuler implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurAjouter.
     * @param monde Le monde où se trouve l'activité
     */
    public EcouteurSimuler(MondeIG monde) {
        this.monde = monde;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        if(!monde.getSimuEstLancee()) {
            monde.simuler();
        } else {
            monde.stopSimulation();
        }
        monde.notifierObservateurs();
    }
}