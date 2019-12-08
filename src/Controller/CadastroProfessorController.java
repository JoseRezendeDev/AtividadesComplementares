package Controller;

import DAO.ProfessorDAO;
import Loader.CadastroProfessorLoader;
import Loader.LoginLoader;
import Model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroProfessorController {
    @FXML
    private Pane pane;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfSenha;

    private ProfessorDAO professorDAO = new ProfessorDAO();

    public void cadastrar(ActionEvent actionEvent){
        String usuario = tfUsuario.getText();
        String senha = tfSenha.getText();

        if (usuario.equals("") || senha.equals("")){
            exibirMensagem("Usuário ou senha inválidos");
        } else {
            Professor professor = new Professor();
            professor.setNome(usuario);
            professor.setSenha(senha);
            professorDAO.salvar(professor);
            exibirMensagem("Professor cadastrado com SUCESSO");
            LoginLoader loginLoader = new LoginLoader();
            Stage stage = (Stage) pane.getScene().getWindow();
            loginLoader.loadLogin(stage);
        }
    }

    public void exibirMensagem(String mensagem){
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Atividade Complementar - Falha no Login");
        dialogoInfo.setHeaderText(mensagem);
        dialogoInfo.showAndWait();
    }

    public void voltar(ActionEvent actionEvent) {
        LoginLoader loginLoader = new LoginLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        loginLoader.loadLogin(stage);
    }
}
