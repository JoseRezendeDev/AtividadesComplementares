package Loader;

import Controller.CadastroACController;
import Model.Aluno;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroCategoriaLoader {
    public void loadCadastroCategoria(Stage stage){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/cadastroCategoria.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            //Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro de Categoria");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
