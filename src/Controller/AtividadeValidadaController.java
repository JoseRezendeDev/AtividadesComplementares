package Controller;

import Loader.AlunosLoader;
import Loader.ValidaACLoader;
import Model.AtividadeComplementar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AtividadeValidadaController{
    @FXML
    private Pane pane;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbAluno;
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

    public void voltarValidaAC(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        lbDescricao.setText(atividadeComplementar.getDescricao());
        lbSemestre.setText(String.valueOf(atividadeComplementar.getSemestreAC()));
        lbAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        lbCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        lbCategoria.setText(atividadeComplementar.getItemCategoriaAC().getNome());
        lbProfessor.setText(atividadeComplementar.getProfessor().getNome());
        lbCodigo.setText(atividadeComplementar.getCodigo());
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
    }

    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }
}
