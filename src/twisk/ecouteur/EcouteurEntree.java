package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant de définir une étape comme entrée.
 */
public class EcouteurEntree implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurEntree.
     * @param monde Le monde où se trouve les étapes
     */
    public EcouteurEntree(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.aCommeEntree();
        monde.resetListeSelec();
        monde.notifierObservateurs();
    }
}
