package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.simulation.Client;

public class VueClientIG extends Circle implements Observateur{
    /**
     * cons Vue client
     * @param client Client
     */
    public VueClientIG(Client client){
        this.setRadius(4d);
        this.setFill(Color.BLUE);
    }

    @Override
    public void reagir() {

    }
}
