package Controller;

import Loader.AtividadesLoader;
import Model.Aluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroACController {
    @FXML
    private Pane pane;
    @FXML
    private Label lbAluno;

    private Aluno aluno;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setLbAluno(Aluno aluno) {
        lbAluno.setText(aluno.getNome());
    }

    public void voltarAluno(ActionEvent actionEvent) {
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        atividadesLoader.loadAtividades(aluno, stage);
    }
}
