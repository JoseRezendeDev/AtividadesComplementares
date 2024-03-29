package Controller;

import DAO.ProfessorDAO;
import Loader.AlunosLoader;
import Loader.CadastroProfessorLoader;
import Model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private ProfessorDAO professorDAO = new ProfessorDAO();

    public void logar(ActionEvent actionEvent) {
        String usuario = tfUsuario.getText();
        String senha = tfSenha.getText();

        if (professorDAO.autentica(usuario, senha)){
            AlunosLoader alunosLoader = new AlunosLoader();
            Stage stage = (Stage) pane.getScene().getWindow();
            alunosLoader.loadAlunos(stage);
        }
        else
            exibirMensagem("Usuário ou senha inválidos");
    }

    public void cadastrar(ActionEvent actionEvent){
        CadastroProfessorLoader cadastroProfessorLoader = new CadastroProfessorLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        cadastroProfessorLoader.loadCadastroProfessor(stage);
    }

    public void exibirMensagem(String mensagem){
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Falha no login");
        dialogoInfo.setHeaderText(mensagem);
        dialogoInfo.showAndWait();
    }
}
