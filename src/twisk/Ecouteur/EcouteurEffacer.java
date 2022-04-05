package twisk.Ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;


/**
 * Représente l'écouteur qui annule la sélection des étapes et arcs.
 */
public class EcouteurEffacer implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurEffacer.
     * @param monde Le monde où se trouve les étapes et arcs.
     */
    public EcouteurEffacer(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.resetListeSelec();
        monde.notifierObservateurs();
    }
}
