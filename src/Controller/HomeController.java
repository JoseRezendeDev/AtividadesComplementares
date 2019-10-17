package Controller;

import Loader.AlunoLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import Model.CategoriaAC;
import Model.Professor;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private TableView<Aluno> tabela;
    @FXML
    private TableColumn<Aluno, String> clNome;
    @FXML
    private TableColumn<Aluno, String> clProntuario;
    @FXML
    private TableColumn<Aluno, Integer> clAnoIngresso;
    @FXML
    private TableColumn<Aluno, Integer> clSemestreIngresso;
    @FXML
    private TableColumn<Aluno, Double> clHorasCumpridas;

    private ObservableList<Aluno> alunos;

    //Não sei o porque dos dois parametros, mas precisa deles
    //pra sobrescrever o método da interface Initializable.
    //Esse método é chamado automaticamente para inicializar o fxml
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunos = FXCollections.observableArrayList();
        Aluno alunoTeste = new Aluno();
        alunoTeste.setNome("Jose");
        alunoTeste.setNumeroMatricula("3001491");
        alunoTeste.setAnoIngresso(2018);
        alunoTeste.setSemestreIngresso(1);
        alunoTeste.setHorasCumpridas(50);
        alunos.add(alunoTeste);
        CategoriaAC categoriaTeste = new CategoriaAC();
        categoriaTeste.setId(1);
        categoriaTeste.setNome("Palestra");
        categoriaTeste.setMaximoHoras(20);
        Professor profTeste = new Professor();
        profTeste.setId(1);
        profTeste.setNome("Lucas Ruas");
        AtividadeComplementar atvTeste = new AtividadeComplementar();
        atvTeste.setAluno(alunoTeste);
        atvTeste.setAnoAC(2019);
        atvTeste.setSemestreAC(2);
        atvTeste.setCargaHoraria(3.5);
        atvTeste.setCategoriaAC(categoriaTeste);
        atvTeste.setProfessor(profTeste);
        atvTeste.setCodigo(atvTeste.getAluno().getNome() + "_"
                + atvTeste.getSemestreAC() + "_" + atvTeste.getAnoAC());
        atvTeste.setDescricao("Palestra sobre Design Patterns");
        alunoTeste.setAtividade(atvTeste);
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clProntuario.setCellValueFactory(new PropertyValueFactory<>("numeroMatricula"));
        clAnoIngresso.setCellValueFactory(new PropertyValueFactory<>("anoIngresso"));
        clSemestreIngresso.setCellValueFactory(new PropertyValueFactory<>("semestreIngresso"));
        clHorasCumpridas.setCellValueFactory(new PropertyValueFactory<>("horasCumpridas"));
        tabela.setItems(alunos);
    }

    public void exibirAluno(){
        Aluno alunoSelecionado = tabela.getSelectionModel().getSelectedItem();
        AlunoLoader alunoLoader = new AlunoLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        alunoLoader.loadAluno(alunoSelecionado, stage);
    }
}
