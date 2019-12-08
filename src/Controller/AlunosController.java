package Controller;

import DAO.AlunoDAO;
import DAO.AtividadeComplementarDAO;
import DAO.CategoriaACDAO;
import DAO.ItemCategoriaACDAO;
import Loader.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.CORBA.INTERNAL;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfProntuario;
    @FXML
    private ComboBox<Integer> cbAnoIngresso;
    @FXML
    private ComboBox<Integer> cbSemestreIngresso;
    @FXML
    private ToggleGroup situacaoAlunos;
    @FXML
    private ToggleGroup progressao;
    @FXML
    private ToggleButton rbAtivos;
    @FXML
    private ToggleButton rbTodos;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Label lbMensagem;
    @FXML
    private Button btVerAtividade;


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
        alunoDAO.setHorasCumpridasAll();
        alunos = FXCollections.observableArrayList(alunoDAO.getAlunosAtivos());
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clProntuario.setCellValueFactory(new PropertyValueFactory<>("numeroMatricula"));
        clAnoIngresso.setCellValueFactory(new PropertyValueFactory<>("anoIngresso"));
        clSemestreIngresso.setCellValueFactory(new PropertyValueFactory<>("semestreIngresso"));
        clHorasCumpridas.setCellValueFactory(new PropertyValueFactory<>("horasCumpridas"));
        clHorasCumpridas.setCellFactory(column -> {
            return new TableCell<Aluno, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(String.valueOf(item));
                        if (item > Aluno.TOTAL_HORAS_NECESSARIAS) {
                            setStyle("-fx-background-color: #b8ff8f");
                        } else {
                            setStyle("");
                        }
                    }
                }
            };
        });
        tabela.setItems(alunos);
        List<Integer> listaAnos = new ArrayList<>();
        for (int i=2008;i<2020;i++)
            listaAnos.add(i);
        cbAnoIngresso.setItems(FXCollections.observableArrayList(listaAnos));
        cbSemestreIngresso.setItems(FXCollections.observableArrayList(1,2));
        btVerAtividade.setVisible(false);
    }

    public void exibirAtividades() {
        Aluno alunoSelecionado = tabela.getSelectionModel().getSelectedItem();
        if (alunoSelecionado != null) {
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            Stage stage = (Stage) pane.getScene().getWindow();
            atividadesLoader.loadAtividades(alunoSelecionado, stage);
        }
    }

    //Arquivo csv deve estar na seguinte ordem
    //***;prontuario;nome;ano de ingresso;***;***;semestre de ingresso
    //*** == qualquer coisa
    public void importarAlunos(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Alunos");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog( pane.getScene().getWindow());
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            Map<String, Aluno> mapaAlunos = new HashMap<>();
            List<Aluno> listaAlunos = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                Aluno aluno = new Aluno();
                aluno.setNumeroMatricula(dados[1]);
                aluno.setNome(dados[2]);
                aluno.setAnoIngresso(Integer.parseInt(dados[3]));
                aluno.setSemestreIngresso(Integer.parseInt(dados[6]));
                aluno.setHorasCumpridas(0);
                aluno.setGraduando(true);
                mapaAlunos.put(aluno.getNumeroMatricula(), aluno);
                listaAlunos.add(aluno);
            }
            removerAlunosFormados(mapaAlunos);
            cadastrarAlunosBanco(listaAlunos);
            exibirMensagem("Importar alunos", "Alunos importados COM SUCESSO!");
        } catch (Exception e) {
            exibirAlunos();
            exibirMensagem("Importar alunos", "Falha ao importar alunos");
        }

        initialize(null, null);
    }

    private void exibirAlunos() {
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    private void cadastrarAlunosBanco(List<Aluno> lista){
        Map<String, Aluno> mapaAlunosBanco = alunoDAO.getAlunosAsMap();
        for (Aluno aluno : lista){
            if (!mapaAlunosBanco.containsKey(aluno.getNumeroMatricula()))
                alunoDAO.salvar(aluno);
            else
                alunoDAO.reativar(aluno.getNumeroMatricula());
        }
    }

    private void removerAlunosFormados(Map<String, Aluno> mapa){
        List<Aluno> listaAlunosBanco = alunoDAO.getAlunosAsList();
        for (Aluno aluno : listaAlunosBanco){
            if (!mapa.containsKey(aluno.getNumeroMatricula())) {
                alunoDAO.remover(aluno.getNumeroMatricula());
            }
        }
    }

    public void exportarDados(ActionEvent actionEvent) {
        List<String> status = new ArrayList<>();
        status.add("Todos");
        status.add("Em Andamento");
        status.add("Concluídos");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Todos", status);
        dialog.setTitle("Atividade Complementar - Exportar Dados");
        dialog.setHeaderText("Exportar Dados do Sistema");
        dialog.setContentText("Quais alunos deseja exportar?");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int opcao = result.get().equals("Em Andamento") ? 1 : result.get().equals("Concluídos") ? 2 : 3;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Atividade Complementar - Exportar Dados");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showSaveDialog(pane.getScene().getWindow());

            if(file == null)
                return;

            try(PrintWriter out = new PrintWriter(new FileOutputStream(new File(file.getAbsolutePath())))){
                List<ItemCategoriaAC> listaItemCategoria = itemCategoriaACDAO.getTodosItensCategoria();
                String cabecalho = "Prontuário;Total Horas";
                for (ItemCategoriaAC item : listaItemCategoria) {
                    cabecalho = cabecalho + ";" + item.getNome();
                }
                cabecalho = cabecalho + "\n";
                out.append(cabecalho);
                for (Aluno aluno : alunos) {
                    if ((opcao == 1 && !aluno.getProgresso()) || (opcao == 2 && aluno.getProgresso()) || opcao == 3) {
                        String infoAluno = aluno.getNumeroMatricula() + ";" + aluno.getHorasCumpridas();
                        for (ItemCategoriaAC item : listaItemCategoria) {
                            if (item.getNumeroTabela() == aluno.getNumeroTabelaReferente()){
                                Double horas = atvDAO.getSomaHorasPorItem(aluno.getNumeroMatricula(),item.getId());
                                infoAluno = infoAluno + ";" + (horas == 0 ? " " : horas);
                            }
                            else {
                                infoAluno = infoAluno + ";" + " ";
                            }
                        }
                        infoAluno = infoAluno + "\n";
                        out.append(infoAluno);
                    }

                }
                exibirMensagem("Atividade Complementar - Exportar Dados", "Dados Exportados com Sucesso!");
            } catch (FileNotFoundException e) {
                exibirMensagem("Atividade Complementar - Exportar Dados", "Falha ao Exportar Dados");
            }
        }


    }

    /*
    Categoria:
    numeroTabela;id;nome
    ItemCategoria:
    numeroTabela;idParcial1;idParcial2;nome;cargaHoraria
    */
    public void importarTabelaAtividades(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Tabela de atividades complementares");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog( pane.getScene().getWindow());
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            itemCategoriaACDAO.limpar();
            categoriaACDAO.limpar();
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                if (dados.length == 3){
                    CategoriaAC categoriaAC = new CategoriaAC();
                    categoriaAC.setId(Integer.parseInt(dados[0] + dados[1]));
                    categoriaAC.setNumeroTabela(Integer.parseInt(dados[0]));
                    categoriaAC.setNome(dados[2]);
                    categoriaACDAO.salvar(categoriaAC);
                }
                else{
                    ItemCategoriaAC itemCategoriaAC = new ItemCategoriaAC();
                    itemCategoriaAC.setId(Integer.parseInt(dados[0] + dados[1] + dados[2]));
                    itemCategoriaAC.setNome(dados[3]);
                    itemCategoriaAC.setNumeroTabela(Integer.parseInt(dados[0]));
                    itemCategoriaAC.setMaximoHoras(Double.parseDouble(dados[4]));
                    itemCategoriaAC.setCategoriaAC(categoriaACDAO.getCategoria(Integer.parseInt(dados[0] + dados[1])));
                    itemCategoriaACDAO.salvar(itemCategoriaAC);
                }
            }
            exibirMensagem("Importar tabela de atividades", "Tabela importada COM SUCESSO!");
        } catch (Exception e) {
            exibirAlunos();
            exibirMensagem("Importar tabela de atividades", "Falha ao importar tabela");
        }
    }

    public void validarAC(ActionEvent actionEvent) {
        if (atvDAO.getAtividade(tfCodigo.getText()) != null) {
            lbMensagem.setText("A atividade é válida.");
            btVerAtividade.setVisible(true);
        } else {
            lbMensagem.setText("O código não é válido.");
            btVerAtividade.setVisible(false);
        }
    }

    public void deslogar(){
        Stage stage = (Stage) pane.getScene().getWindow();
        LoginLoader loginLoader = new LoginLoader();
        loginLoader.loadLogin(stage);
    }

    public void cadastrarNovaCategoria(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        CadastroCategoriaLoader cadastroCategoriaLoader = new CadastroCategoriaLoader();
        cadastroCategoriaLoader.loadCadastroCategoria(stage);
    }

    public void filtrar(){
        alunos.clear();
        String rbSituacao = getRadioButtonString(situacaoAlunos);
        String rbProgressao = getRadioButtonString(progressao);

        if (rbSituacao.equalsIgnoreCase("ativos"))
            alunos = FXCollections.observableArrayList(alunoDAO.getAlunosAtivos());
        else if (rbSituacao.equalsIgnoreCase("inativos"))
            alunos = FXCollections.observableArrayList(alunoDAO.getAlunosInativos());
        else
            alunos = FXCollections.observableArrayList(alunoDAO.getAlunosAsList());

        if (rbProgressao.equalsIgnoreCase("em andamento")) {
            alunos = FXCollections.observableArrayList(alunoDAO.getAlunosEmAndamento(alunos));
        }
        else if (rbProgressao.equalsIgnoreCase("concluído")) {
            alunos = FXCollections.observableArrayList(alunoDAO.getAlunosConcluidos(alunos));
        }

        filtrarNome();
        filtrarProntuario();
        filtrarAnoIngresso();
        filtrarSemestreIngresso();
        tabela.setItems(FXCollections.observableArrayList(alunos));
    }

    private String getRadioButtonString(ToggleGroup radioButton){
        return ((RadioButton) radioButton.getSelectedToggle()).getText();
    }

    private void filtrarNome() {
        String nome = tfNome.getText();
        Iterator<Aluno> it = alunos.iterator();
        while (it.hasNext()){
            Aluno aluno = it.next();
            if (!aluno.getNome().toUpperCase().contains(nome.toUpperCase()))
                it.remove();
        }
    }

    private void filtrarProntuario(){
        String prontuario = tfProntuario.getText();
        Iterator<Aluno> it = alunos.iterator();
        while (it.hasNext()){
            Aluno aluno = it.next();
            if (!aluno.getNumeroMatricula().toUpperCase().contains(prontuario.toUpperCase()))
                it.remove();
        }
    }

    private void filtrarAnoIngresso(){
        if (cbAnoIngresso.getValue() != null) {
            int anoIngresso = cbAnoIngresso.getValue();
            Iterator<Aluno> it = alunos.iterator();
            while (it.hasNext()) {
                Aluno aluno = it.next();
                if (aluno.getAnoIngresso() != anoIngresso)
                    it.remove();
            }
        }
    }

    private void filtrarSemestreIngresso() {
        if (cbSemestreIngresso.getValue() != null) {
            int semestreIngresso = cbSemestreIngresso.getValue();
            Iterator<Aluno> it = alunos.iterator();
            while (it.hasNext()) {
                Aluno aluno = it.next();
                if (aluno.getSemestreIngresso() != semestreIngresso)
                    it.remove();
            }
        }
    }

    public void limparFiltros(){
        tfNome.setText("");
        tfProntuario.setText("");
        cbAnoIngresso.getSelectionModel().clearSelection();
        cbSemestreIngresso.getSelectionModel().clearSelection();
        rbAtivos.setSelected(true);
        rbTodos.setSelected(true);
        filtrar();
    }

    public void exibirMensagem(String tema, String mensagem){
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle(tema);
        dialogoInfo.setHeaderText(mensagem);
        dialogoInfo.showAndWait();
    }

    public void verAtividade(ActionEvent actionEvent) {
        if (atvDAO.getAtividade(tfCodigo.getText()) != null) {
            Stage stage = (Stage) pane.getScene().getWindow();
            AtividadeValidadaLoader atividadeValidadaLoader = new AtividadeValidadaLoader();
            atividadeValidadaLoader.loadAtividadeValidada(stage, atvDAO.getAtividade(tfCodigo.getText()));
        }
    }
}
