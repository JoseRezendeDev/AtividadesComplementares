package Loader;

import Controller.CadastroACController;
import Model.Aluno;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroACLoader {
    public void loadCadastroAC(Aluno aluno, Stage stage){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/cadastroAC.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            CadastroACController ctrl = loader.getController();
            ctrl.setAluno(aluno);
            ctrl.setLbAluno(aluno);

            //Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro de atividade complementar");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
