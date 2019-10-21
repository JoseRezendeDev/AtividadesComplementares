package Controller;

import Loader.AlunosLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Pane pane;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Button btEntrar;


    public void logar(ActionEvent actionEvent) {
        String usuario = tfUsuario.getText();
        String senha = tfSenha.getText();

        if (usuario.equals("leticia") || usuario.equals("jose")){
            AlunosLoader alunosLoader = new AlunosLoader();
            Stage stage = (Stage) pane.getScene().getWindow();
            alunosLoader.loadAlunos(stage);
        }
    }
}
