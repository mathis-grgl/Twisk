package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur qui supprime les arcs et étapes sélectionnées.
 */
public class EcouteurSupprimer implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurSupprimer.
     * @param monde Le monde où les étapes se trouvent
     */
    public EcouteurSupprimer(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent event) {
        monde.supprListeEtapesSelec();
        monde.notifierObservateurs();
    }
}
