package Loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroProfessorLoader {
    public void loadCadastroProfessor(Stage stage) {
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/cadastroProfessor.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            stage.setScene(scene);
            stage.setTitle("Atividade Complementar - Cadastro de Professor");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
