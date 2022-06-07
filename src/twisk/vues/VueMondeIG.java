package twisk.vues;

import javafx.application.Platform;
import twisk.ecouteur.EcouteurDropped;
import twisk.ecouteur.EcouteurOver;
import javafx.scene.layout.Pane;
import twisk.monde.Etape;
import twisk.mondeIG.*;
import twisk.simulation.Client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Représente la vue du MondeIG (sous classe d'un Pane), qui est aussi un observateur. Gère l'affichage des VueArcIG, VuePointDeControleIG et VueActiviteIG.
 */
public class VueMondeIG extends Pane implements Observateur {
    private MondeIG monde;

    /**
     * Instancie une nouvelle VueMondeIG.
     * @param monde Le monde en question
     */
    public VueMondeIG(MondeIG monde){
        this.monde = monde;
        this.monde.ajouterObservateur(this);
        for(EtapeIG e : monde){
            this.getChildren().add(new VueActiviteIG(monde,e));
            for(PointDeControleIG point : e) this.getChildren().add(new VuePointDeControleIG(monde,point));
        }

        this.setOnDragOver(new EcouteurOver(this,this.monde));
        this.setOnDragDropped(new EcouteurDropped(this,this.monde));
    }

    @Override
    public void reagir() {
        Pane panneau = this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                panneau.getChildren().clear();
                for (Iterator<ArcIG> it = monde.iteratorArc(); it.hasNext(); ) {
                    ArcIG a = it.next();
                    panneau.getChildren().add(new VueArcIG(monde, a));
                }
                for (EtapeIG e : monde) {
                    if (e.estUneActivite()) panneau.getChildren().add(new VueActiviteIG(monde, e));
                    else panneau.getChildren().add(new VueGuichetIG(monde, e));
                    for (PointDeControleIG point : e) panneau.getChildren().add(new VuePointDeControleIG(monde, point));
                }
                if (monde.getSimulation() != null) {
                    CorrespondanceEtapes correspondanceEtapes = monde.getCorrespondanceEtapes();
                    ArrayList<Client> listClients = monde.getClients();
                    for (Client clientActuel : listClients) {
                        if(correspondanceEtapes.getEtapeIG(clientActuel.getEtape())!=null) {
                            panneau.getChildren().add(new VueClientIG(monde, correspondanceEtapes.getEtapeIG(clientActuel.getEtape()) ,clientActuel.getRang()));
                        }
                    }
                }
            }
        };
        if(Platform.isFxApplicationThread())
            command.run();
        else
            Platform.runLater(command);
    }
}
