package twisk.Ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant de définir une étape comme sortie.
 */
public class EcouteurSortie implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurSortie.
     * @param monde Le monde où se trouve les étapes
     */
    public EcouteurSortie(MondeIG monde) {
    this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.aCommeSortie();
        monde.resetListeSelec();
        monde.notifierObservateurs();
    }
}
