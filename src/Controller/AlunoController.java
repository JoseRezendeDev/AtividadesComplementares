package Controller;

import Loader.HomeLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AlunoController {
    @FXML
    private Pane pane;
    @FXML
    private TableView<AtividadeComplementar> tabela;
    @FXML
    private TableColumn<AtividadeComplementar, String> clDescricao;
    @FXML
    private TableColumn<AtividadeComplementar, Double> clCargaHoraria;
    @FXML
    private TableColumn<AtividadeComplementar, String> clCodigo;
    @FXML
    private TableColumn<AtividadeComplementar, Integer> clSemestre;
    @FXML
    private TableColumn<AtividadeComplementar, Integer> clAno;
    @FXML
    private TableColumn<AtividadeComplementar, String> clCategoria;
    @FXML
    private TableColumn<AtividadeComplementar, String> clProfessor;
    @FXML
    private Label lbTitulo;


    private Aluno aluno;
    private ObservableList<AtividadeComplementar> listaAC;

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void preencherTabelaACdoAluno() {
        if (aluno != null) {
            listaAC = FXCollections.observableArrayList(aluno.getAtividades());
            clDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            clCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
            clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            clSemestre.setCellValueFactory(new PropertyValueFactory<>("semestreAC"));
            clAno.setCellValueFactory(new PropertyValueFactory<>("anoAC"));
            clCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaAC"));
            clProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
            tabela.setItems(listaAC);
        }
    }

    public void setLbTitulo() {
        if (this.aluno != null)
            lbTitulo.setText("Atividades Complementares - " + aluno.getNome());
    }

    public void voltarHome(){
        Stage stage = (Stage) pane.getScene().getWindow();
        HomeLoader homeLoader = new HomeLoader();
        homeLoader.loadHome(stage);
    }
}
