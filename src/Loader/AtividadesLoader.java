package Loader;

import Controller.AtividadesController;
import Model.Aluno;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AtividadesLoader {
    public void loadAtividades(Aluno aluno, Stage stage){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/atividades.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            AtividadesController ctrl = loader.getController();
            ctrl.setAluno(aluno);
            ctrl.setLbTitulo();
            ctrl.preencherTabelaACdoAluno();

            //Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Atividades Complementares - " + aluno.getNome());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
