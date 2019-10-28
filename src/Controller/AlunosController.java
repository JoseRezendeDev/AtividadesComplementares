package Controller;

import DAO.AlunoDAO;
import DAO.AtividadeComplementarDAO;
import DAO.CategoriaACDAO;
import DAO.ItemCategoriaACDAO;
import Loader.AlunosLoader;
import Loader.AtividadesLoader;
import Loader.LoginLoader;
import Loader.ValidaACLoader;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AlunosController implements Initializable {
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
    private AlunoDAO alunoDAO = new AlunoDAO();
    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();
    private CategoriaACDAO categoriaACDAO = new CategoriaACDAO();
    private ItemCategoriaACDAO itemCategoriaACDAO = new ItemCategoriaACDAO();

    //Não sei o porque dos dois parametros, mas precisa deles
    //pra sobrescrever o método da interface Initializable.
    //Esse método é chamado automaticamente para inicializar o fxml
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alunos = FXCollections.observableArrayList(alunoDAO.getAlunos());
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clProntuario.setCellValueFactory(new PropertyValueFactory<>("numeroMatricula"));
        clAnoIngresso.setCellValueFactory(new PropertyValueFactory<>("anoIngresso"));
        clSemestreIngresso.setCellValueFactory(new PropertyValueFactory<>("semestreIngresso"));
        clHorasCumpridas.setCellValueFactory(new PropertyValueFactory<>("horasCumpridas"));
        tabela.setItems(alunos);
    }

    public void exibirAluno(){
        Aluno alunoSelecionado = tabela.getSelectionModel().getSelectedItem();
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        Stage stage = (Stage) pane.getScene().getWindow();
        atividadesLoader.loadAtividades(alunoSelecionado, stage);
    }

    public void importarAlunos(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Alunos");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog( pane.getScene().getWindow());
        alunoDAO.limpar();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                Aluno aluno = new Aluno();
                aluno.setNome(dados[0]);
                aluno.setNumeroMatricula(dados[1]);
                aluno.setAnoIngresso(Integer.parseInt(dados[2]));
                aluno.setSemestreIngresso(Integer.parseInt(dados[3]));
                aluno.setTelefone(dados[4]);
                aluno.setEmail(dados[5]);
                aluno.setHorasCumpridas(0);

                alunoDAO.salvar(aluno);
            }
            initialize(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Arquivo csv deve estar na seguinte ordem
    //id, nome, carga horaria
    public void importarTabelaAtividades(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Tabela de atividades complementares");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog( pane.getScene().getWindow());
        itemCategoriaACDAO.limpar();
        categoriaACDAO.limpar();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            int categoriaDoItem = 0;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                if (dados[2].equals("0")){
                    CategoriaAC categoriaAC = new CategoriaAC();
                    categoriaAC.setId(Integer.parseInt(dados[0]));
                    categoriaAC.setNome(dados[1]);
                    categoriaDoItem = Integer.parseInt(dados[0]);
                    categoriaACDAO.salvar(categoriaAC);
                }
                else{
                    ItemCategoriaAC itemCategoriaAC = new ItemCategoriaAC();
                    itemCategoriaAC.setId(Integer.parseInt(dados[0]));
                    itemCategoriaAC.setNome(dados[1]);
                    itemCategoriaAC.setMaximoHoras(Double.parseDouble(dados[2]));
                    itemCategoriaAC.setCategoriaAC(new CategoriaAC(categoriaDoItem));
                    itemCategoriaACDAO.salvar(itemCategoriaAC);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validarAC(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        ValidaACLoader validaACLoader = new ValidaACLoader();
        validaACLoader.loadValidaAC(stage);
    }

    public void deslogar(){
        Stage stage = (Stage) pane.getScene().getWindow();
        LoginLoader loginLoader = new LoginLoader();
        loginLoader.loadLogin(stage);
    }
}
