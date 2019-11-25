package Controller;

import DAO.AtividadeComplementarDAO;
import DAO.CategoriaACDAO;
import DAO.ItemCategoriaACDAO;
import DAO.ProfessorDAO;
import Loader.AtividadesLoader;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroACController{
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
    @FXML
    private Label lbCategoria;

    private Aluno aluno;
    private ItemCategoriaACDAO itemDAO = new ItemCategoriaACDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();

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

    public void cadastrarAC() {
        String mensagemValidar = validarCadastro();

        if (mensagemValidar.equals("")){
            AtividadeComplementar atv = new AtividadeComplementar();
            atv.setDescricao(tfDescricao.getText());
            atv.setCargaHoraria(Double.parseDouble(tfCargaHoraria.getText()));
            atv.setCodigo(aluno.getNome() + "_" + aluno.getNumeroMatricula() + "_" + cbSemestre.getValue() + "_" + cbCategoria.getValue().getId());
            atv.setProfessor(cbProfessor.getValue());
            atv.setAnoAC(cbAno.getValue());
            atv.setSemestreAC(cbSemestre.getValue());
            atv.setItemCategoriaAC(cbCategoria.getValue());
            atv.setAluno(aluno);
            aluno.atualizarHorasCumpridas();
            atvDAO.salvar(atv);
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            Stage stage = (Stage) pane.getScene().getWindow();
            atividadesLoader.loadAtividades(aluno, stage);
        } else{
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Cadastro inválido");
            dialogoInfo.setHeaderText(mensagemValidar);
            dialogoInfo.showAndWait();
        }
    }

    private String validarCadastro(){
        if (tfDescricao.getText().equals(""))
            return "Descrição inválida";
        try {
            double cargaHoraria = Double.parseDouble(tfCargaHoraria.getText());
        } catch (NumberFormatException e) {
            return "Carga horária inválida";
        }
        if (cbCategoria.getValue() == null)
            return "Selecione uma categoria";
        if (cbProfessor.getValue() == null)
            return "Selecione um professor";

        if (Double.parseDouble(tfCargaHoraria.getText()) > cbCategoria.getValue().getMaximoHoras())
            return "A carga horária máxima da categoria selecionada é de " + cbCategoria.getValue().getMaximoHoras() + "horas";

        return "";
    }

    public void initialize(URL location, ResourceBundle resources) {
        List<Integer> listaAnos = new ArrayList<>();
        for (int i=2008;i<2020;i++)
            listaAnos.add(i);
        lbCategoria.setText(lbCategoria.getText() + " TABELA " + aluno.getNumeroTabelaReferente());
        cbAno.setItems(FXCollections.observableArrayList(listaAnos));
        cbSemestre.setItems(FXCollections.observableArrayList(1, 2));
        cbCategoria.setItems(FXCollections.observableArrayList(itemDAO.getItensCategoria(aluno.getNumeroTabelaReferente())));
        cbProfessor.setItems(FXCollections.observableArrayList(professorDAO.getProfessores()));
    }
}
