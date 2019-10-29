package Controller;

import DAO.AtividadeComplementarDAO;
import Loader.CadastroACLoader;
import Loader.AlunosLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AtividadesController {
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
    @FXML
    private Label lbAluno;


    private Aluno aluno;
    private ObservableList<AtividadeComplementar> listaAC;
    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void preencherTabelaACdoAluno() {
        if (aluno != null) {
            listaAC = FXCollections.observableArrayList(atvDAO.getAtividades(aluno.getNumeroMatricula()));
            clDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            clCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
            clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            clSemestre.setCellValueFactory(new PropertyValueFactory<>("semestreAC"));
            clAno.setCellValueFactory(new PropertyValueFactory<>("anoAC"));
            clProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
            clCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria_ac"));
            tabela.setItems(listaAC);
        }
    }

    public void setLbTitulo() {
        if (this.aluno != null)
            lbAluno.setText(aluno.getNome());
    }

    public void voltarAluno(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    public void cadastrarAC(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        CadastroACLoader cadastroACLoader = new CadastroACLoader();
        cadastroACLoader.loadCadastroAC(aluno, stage);
    }
}
