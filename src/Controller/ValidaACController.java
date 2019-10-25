package Controller;

import Loader.AlunosLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ValidaACController {
    @FXML
    private Pane pane;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Button btVerifica;
    @FXML
    private Label lbMensagem;

    public void voltarHome(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    public void verificaAC(ActionEvent actionEvent) {
        lbMensagem.setText("O código não é válido.");
    }
}
