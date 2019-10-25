package Loader;

import Controller.AlunosController;
import Controller.ValidaACController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ValidaACLoader {
    public void loadValidaAC(Stage stage) {
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/validaAC.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            stage.setScene(scene);
            stage.setTitle("Validação de Atividade Complementar");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
