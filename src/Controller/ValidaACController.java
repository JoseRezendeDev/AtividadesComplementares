package Controller;

import DAO.AtividadeComplementarDAO;
import Loader.AlunosLoader;
import Loader.AtividadeValidadaLoader;
import Model.AtividadeComplementar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ValidaACController {
    @FXML
    private Pane pane;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Label lbMensagem;

    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();

    public void voltarHome(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    public void verificaAC(ActionEvent actionEvent) {
        if (atvDAO.getAtividade(tfCodigo.getText()) != null) {
            exibirAtividadeValidada(atvDAO.getAtividade(tfCodigo.getText()));
        } else
            lbMensagem.setText("O código não é válido.");
    }

    private void exibirAtividadeValidada(AtividadeComplementar atv){
        Stage stage = (Stage) pane.getScene().getWindow();
        AtividadeValidadaLoader loader = new AtividadeValidadaLoader();
        loader.loadAtividadeValidada(stage, atv);
    }
}
