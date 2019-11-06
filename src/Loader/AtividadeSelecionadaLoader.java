package Loader;

import Controller.AtividadeSelecionadaController;
import Controller.AtividadeValidadaController;
import Model.Aluno;
import Model.AtividadeComplementar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AtividadeSelecionadaLoader {
    public void loadAtividadeSelecionada(AtividadeComplementar atividadeComplementar, Aluno aluno, Stage stage){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/atividadeSelecionada.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);
            AtividadeSelecionadaController ctrl = loader.getController();
            ctrl.setAtividade(atividadeComplementar);
            ctrl.setAluno(aluno);
            ctrl.initialize(null, null);

            stage.setScene(scene);
            stage.setTitle("Atividade Complementar - " + atividadeComplementar.getCodigo() + " - " + aluno.getNome());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
