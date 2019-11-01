package Controller;

import Loader.AtividadesLoader;
import Loader.ValidaACLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import Model.ItemCategoriaAC;
import Model.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AtividadeSelecionadaController {
    @FXML
    private Pane pane;
    @FXML
    private Label lbAluno;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TextField tfCargaHoraria;
    @FXML
    private ComboBox<Integer> cbSemestre;
    @FXML
    private ComboBox<Integer> cbAno;
    @FXML
    private ComboBox<Professor> cbProfessor;
    @FXML
    private ComboBox<ItemCategoriaAC> cbCategoria;

    private AtividadeComplementar atividadeComplementar;
    private Aluno aluno;

    public void voltarAtividades(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        atividadesLoader.loadAtividades(aluno, stage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        tfDescricao.setText(atividadeComplementar.getDescricao());
        tfDescricao.setDisable(true);
        cbSemestre.setValue(atividadeComplementar.getSemestreAC());
        cbSemestre.setDisable(true);
        cbAno.setValue(atividadeComplementar.getAnoAC());
        cbAno.setDisable(true);
        tfCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        tfCargaHoraria.setDisable(true);
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        lbAluno.setDisable(true);
        cbCategoria.setValue(atividadeComplementar.getItemCategoriaAC());
        cbCategoria.setDisable(true);
        cbProfessor.setValue(atividadeComplementar.getProfessor());
        cbProfessor.setDisable(true);
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }
}
