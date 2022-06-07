package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposant;

public class VueClientIG extends Circle implements Observateur{
    private MondeIG mondeIG;
    private EtapeIG etapeIG;

    public VueClientIG(MondeIG mondeIG, EtapeIG etapeIG, int rang){
        this.mondeIG = mondeIG;
        this.etapeIG = etapeIG;
        this.mondeIG.ajouterObservateur(this);

        this.setRadius(4.00);
        this.setFill(Color.BLUE);

        if(etapeIG.estUneActivite() || etapeIG.estUneActiviteRestreinte()) {
            this.setCenterX(this.etapeIG.getPosX()+ TailleComposant.getInstance().getLargeurAC()-(rang*10)-5);
            this.setCenterY(this.etapeIG.getPosY()+TailleComposant.getInstance().getHauteurAC()-20);
        }
        if(etapeIG.estUnGuichet()){
            this.setCenterX(this.etapeIG.getPosX()+TailleComposant.getInstance().getLargeurGUI()-rang*16);
            this.setCenterY(this.etapeIG.getPosY()+TailleComposant.getInstance().getHauteurGUI()-16);
        }
    }

    @Override
    public void reagir() {

    }
}
