package Loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginLoader loader = new LoginLoader();

        //Abrir a tela maximizada
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        loader.loadLogin(primaryStage);

        //Código original da main pra iniciar o JavaFX, coloquei
        //nas classes Loader
        /*Parent root = FXMLLoader.load(getClass().getResource("../View/alunos.fxml"));
        primaryStage.setTitle("Atividades Complementares");
        primaryStage.setScene(new Scene(root, 100, 100));
        primaryStage.show();
         */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
