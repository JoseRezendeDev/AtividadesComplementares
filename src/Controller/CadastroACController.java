package Controller;

import DAO.AtividadeComplementarDAO;
import Loader.AtividadesLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import Model.CategoriaAC;
import Model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CadastroACController {
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
    private ChoiceBox<Professor> cbProfessor;
    @FXML
    private ChoiceBox<CategoriaAC> cbCategoria;

    private Aluno aluno;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setLbAluno(Aluno aluno) {
        lbAluno.setText(aluno.getNome());
    }

    public void voltarAluno(ActionEvent actionEvent) {
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        atividadesLoader.loadAtividades(aluno, stage);
    }

    public void cadastrarAC(){
        AtividadeComplementar atv = new AtividadeComplementar();
        atv.setDescricao(tfDescricao.getText());
        atv.setCargaHoraria(Double.parseDouble(tfCargaHoraria.getText()));
        atv.setCodigo(atv.getDescricao() + "_" + atv.getCargaHoraria());
        AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();
        atvDAO.salvar(atv);
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        atividadesLoader.loadAtividades(aluno, stage);
    }
}
