package Controller;

import DAO.AtividadeComplementarDAO;
import Loader.AtividadesLoader;
import Loader.ValidaACLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import Model.ItemCategoriaAC;
import Model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtividadeSelecionadaController {
    @FXML
    private Pane pane;
    @FXML
    private Label lbAluno;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbDescricao;
    @FXML
    private Label lbCargaHoraria;
    @FXML
    private Label lbSemestre;
    @FXML
    private Label lbAno;
    @FXML
    private Label lbProfessor;
    @FXML
    private Label lbCategoria;

    private AtividadeComplementar atividadeComplementar;
    private Aluno aluno;
    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();

    public void voltarAtividades(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        atividadesLoader.loadAtividades(aluno, stage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        lbDescricao.setText(atividadeComplementar.getDescricao());
        lbSemestre.setText(String.valueOf(atividadeComplementar.getSemestreAC()));
        lbAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        lbCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        lbCategoria.setText(atividadeComplementar.getItemCategoriaAC().getNome());
        lbProfessor.setText(atividadeComplementar.getProfessor().getNome());
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        lbCodigo.setText(atividadeComplementar.getCodigo());
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }

    public void excluirAtividade(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atividade Complementar - Excluir Atividade");
        alert.setHeaderText("Atenção, você está prestes a excluir essa atividade permanentemente!");
        alert.setContentText("Você deseja excluir essa atividade complementar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            atvDAO.excluirAtividade(this.atividadeComplementar.getCodigo());
            Stage stage = (Stage) pane.getScene().getWindow();
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            atividadesLoader.loadAtividades(aluno, stage);
        } else {
            Stage stage = (Stage) pane.getScene().getWindow();
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            atividadesLoader.loadAtividades(aluno, stage);
        }

    }
}
