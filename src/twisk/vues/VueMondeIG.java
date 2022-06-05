package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.ecouteur.EcouteurDropped;
import twisk.ecouteur.EcouteurOver;
import javafx.scene.layout.Pane;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.simulation.Client;

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

    /**
     *
     * @param hbox
     * @param etapeIG
     */
    public void ajouterClient(HBox hbox, EtapeIG etapeIG){
        if (monde.getSimulation() != null){
            if(monde.isSimStarted()){
                for(Client client : monde.getClients()){
                    if (monde.getCorrespondanceEtapes().get(etapeIG).equals(client.getEtape())){
                        hbox.getChildren().add(new VueClientIG(client));
                    }
                }
            }
        }
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
        for (Iterator<ArcIG> it = monde.iteratorArc(); it.hasNext(); ) {
            ArcIG a = it.next();
            this.getChildren().add(new VueArcIG(monde,a));
        }
        for(EtapeIG e : monde){
            if(e.estUneActivite())this.getChildren().add(new VueActiviteIG(monde,e));
            else this.getChildren().add(new VueGuichetIG(monde,e));
            for(PointDeControleIG point : e) this.getChildren().add(new VuePointDeControleIG(monde,point));
        }
    }
}
