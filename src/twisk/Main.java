package twisk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World"); //Met un titre à la fenêtre
        primaryStage.setScene(new Scene(root, 300, 275)); //Définit la scène de la fenêtre avec root et les dimensions
        primaryStage.show(); // Affiche et actualise la fenêtre
    }


    public static void main(String[] args) {
        launch(args);//Lance l'interface
    }
}
