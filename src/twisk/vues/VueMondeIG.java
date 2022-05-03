package twisk.vues;

import twisk.ecouteur.EcouteurDropped;
import twisk.ecouteur.EcouteurOver;
import javafx.scene.layout.Pane;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

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
