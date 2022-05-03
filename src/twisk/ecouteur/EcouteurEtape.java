package twisk.Ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Représente l'écouteur permettant de sélectionner / désélectionner une étape.
 */
public class EcouteurEtape implements EventHandler<MouseEvent> {
    private EtapeIG etape;
    private MondeIG monde;


    /**
     * Instancie un nouveau EcouteurEtape.
     * @param monde Le monde où se trouve les étapes.
     * @param etape L'étape à sélectionner/désélectionner
     */
    public EcouteurEtape(MondeIG monde, EtapeIG etape){
        this.etape = etape;
        this.monde = monde;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.monde.selectDeselect(etape);
        System.out.println("L'étape selectionnée est "+etape.getNom());
        this.monde.notifierObservateurs();
    }
}
