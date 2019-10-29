package Loader;

import Controller.AtividadeValidadaController;
import Model.AtividadeComplementar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AtividadeValidadaLoader {
    public void loadAtividadeValidada(Stage stage, AtividadeComplementar atv){
        FXMLLoader loader =  new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../View/atividadeValidada.fxml").openStream());
            Scene scene = new Scene(graph, 1100, 800);

            System.out.println(atv);
            AtividadeValidadaController ctrl = loader.getController();
            ctrl.setAtividade(atv);

            //Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro de atividade complementar");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
}
