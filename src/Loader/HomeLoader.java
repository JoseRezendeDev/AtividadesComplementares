package Loader;

import Controller.AlunoController;
import Controller.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeLoader {
    public void loadHome(Stage stage) {
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/home.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            HomeController ctrl = loader.getController();
            ctrl.initialize(null, null);

            stage.setScene(scene);
            stage.setTitle("Alunos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
