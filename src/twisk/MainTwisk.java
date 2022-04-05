package twisk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {

    @Override
    public void start(Stage stage) {
        MondeIG monde = new MondeIG();
        BorderPane root = new BorderPane();
        root.setCenter(new VueMondeIG(monde));
        root.setBottom(new VueOutils(monde));
        root.setTop(new VueMenu(monde));
        stage.setScene(new Scene(root,1000,700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
