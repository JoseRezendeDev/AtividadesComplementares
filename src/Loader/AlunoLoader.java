package Loader;

import Controller.AlunoController;
import Model.Aluno;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AlunoLoader {
    public void loadAluno(Aluno aluno, Stage stage){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/aluno.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            AlunoController ctrl = loader.getController();
            ctrl.setAluno(aluno);
            ctrl.setLbTitulo();
            ctrl.preencherTabelaACdoAluno();

            //Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Aluno");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
