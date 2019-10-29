package Controller;

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

public class AtividadeValidadaController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        tfDescricao.setText(atividadeComplementar.getDescricao());
        tfAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        tfSemestre.setText(String.valueOf(atividadeComplementar.getSemestreAC()));
        tfCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        cbCategoria.setValue(atividadeComplementar.getItemCategoriaAC());
        cbProfessor.setValue(atividadeComplementar.getProfessor());
    */
    }


    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }
}
