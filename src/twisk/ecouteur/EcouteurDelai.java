package twisk.ecouteur;

import twisk.exceptions.TwiskException;
import twisk.exceptions.DelaiException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Représente l'écouteur permettant de changer les délais d'une activité.
 */
public class EcouteurDelai implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * Instancie un nouveau EcouteurDelai.
     * @param monde Le monde où se trouve l'activité à modifier
     */
    public EcouteurDelai(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TextInputDialog dialogue = new TextInputDialog();
        dialogue.setTitle("Modifier le temps");
        dialogue.setHeaderText(null);
        dialogue.setContentText("Nouveau temps pour "+this.monde.getListeEtapesSelec().get(0).getNom());

        Optional<String> out = dialogue.showAndWait();
        out.ifPresent(nom -> {
            int temps = Integer.parseInt(nom);
            if(temps>=0 && temps<=99 && temps>=((ActiviteIG) monde.getListeEtapesSelec().get(0)).getEcartTemps())
                this.monde.modifTemps(temps);
            else
                try {
                    throw new DelaiException();
                } catch (TwiskException ignored) {}
        });

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier l'écart-temps pour "+this.monde.getListeEtapesSelec().get(0).getNom());
        dialog.setHeaderText(null);
        dialog.setContentText("Nouveau écart-temps pour "+this.monde.getListeEtapesSelec().get(0).getNom());

        Optional<String> ou = dialog.showAndWait();
        ou.ifPresent(nom -> {
            ActiviteIG ac = (ActiviteIG) this.monde.getListeEtapesSelec().get(0);
            int temps = ac.getTemps();
            int ecartemps = Integer.parseInt(nom);
            if(ecartemps>=0 && ecartemps<=temps)
                this.monde.modifEcartTemps(ecartemps);
            else
                try {
                    throw new DelaiException();
                } catch (TwiskException ignored) {}
        });

        this.monde.resetListeSelec();
        this.monde.notifierObservateurs();
    }
}
