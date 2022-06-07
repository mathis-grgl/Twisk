package twisk.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;

/**
 * Permet de modifier la loi de probabilité utilisée pour la simulation.
 */
public class EcouteurLois implements EventHandler<ActionEvent> {

    private MondeIG mondeIG;

    /**
     * Instancie un nouveau EcouteurLois.
     * @param monde Le monde où se trouve les étapes.
     */
    public EcouteurLois(MondeIG monde){
        this.mondeIG = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setTitle("Loi d'arivée");
        Button ok = new Button("OK");
        ComboBox<String> box = new ComboBox<>();
        box.getItems().addAll("Uniforme","Gaussienne","Exponentielle");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(box, ok);
        ok.setOnAction(f-> {
            mondeIG.setLoi(box.getSelectionModel().getSelectedItem());
            stage.close();
        });
        Scene scene = new Scene(vBox,250,100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
