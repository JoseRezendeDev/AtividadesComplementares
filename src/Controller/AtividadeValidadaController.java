package Controller;

import DAO.AtividadeComplementarDAO;
import Loader.ValidaACLoader;
import Model.AtividadeComplementar;
import Model.ItemCategoriaAC;
import Model.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AtividadeValidadaController{
    @FXML
    private Pane pane;
    @FXML
    private Label lbAluno;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TextField tfCargaHoraria;
    @FXML
    private TextField tfSemestre;
    @FXML
    private TextField tfAno;
    @FXML
    private ComboBox<Professor> cbProfessor;
    @FXML
    private ComboBox<ItemCategoriaAC> cbCategoria;

    private AtividadeComplementar atividadeComplementar;

    public void voltarValidaAC(){
        Stage stage = (Stage) pane.getScene().getWindow();
        ValidaACLoader loader = new ValidaACLoader();
        loader.loadValidaAC(stage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        tfDescricao.setText(atividadeComplementar.getDescricao());
        tfDescricao.setDisable(true);
        tfAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        tfAno.setDisable(true);
        tfSemestre.setText(String.valueOf(this.atividadeComplementar.getSemestreAC()));
        tfSemestre.setDisable(true);
        tfCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        tfCargaHoraria.setDisable(true);
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        lbAluno.setDisable(true);
        cbCategoria.setValue(atividadeComplementar.getItemCategoriaAC());
        cbCategoria.setDisable(true);
        cbProfessor.setValue(atividadeComplementar.getProfessor());
        cbProfessor.setDisable(true);
    }

    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }
}