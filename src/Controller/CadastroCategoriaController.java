package Controller;

import DAO.CategoriaACDAO;
import DAO.ItemCategoriaACDAO;
import Loader.AlunosLoader;
import Model.CategoriaAC;
import Model.ItemCategoriaAC;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class CadastroCategoriaController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfMaximoHoras;
    @FXML
    private RadioButton rbCategoria;
    @FXML
    private RadioButton rbItemCategoria;
    @FXML
    private ComboBox<CategoriaAC> cbCategoria;
    @FXML
    private ComboBox<Integer> cbNumeroTabela;
    @FXML
    private ToggleGroup categoriaOuItem;

    private CategoriaACDAO categoriaACDAO = new CategoriaACDAO();
    private ItemCategoriaACDAO itemCategoriaACDAO = new ItemCategoriaACDAO();

    public void selecionarRbCategoria(){
        cbCategoria.setValue(null);
        tfMaximoHoras.setText("");
        cbCategoria.setDisable(true);
        tfMaximoHoras.setDisable(true);
    }

    public void selecionarRbItemCategoria(){
        cbCategoria.setDisable(false);
        tfMaximoHoras.setDisable(false);
    }

    public void voltarAluno(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AlunosLoader alunosLoader = new AlunosLoader();
        alunosLoader.loadAlunos(stage);
    }

    public void cadastrarCategoria(){
        String valorRb = (String) categoriaOuItem.getSelectedToggle().getUserData();
        String mensagemValidar = validarCadastro(valorRb);

        if (mensagemValidar.equals("")) {
            if (valorRb.equals("Categoria")) {
                CategoriaAC categoriaAC = new CategoriaAC();
                categoriaAC.setNome(tfNome.getText());
                categoriaAC.setNumeroTabela(cbNumeroTabela.getValue());
                categoriaACDAO.salvar(categoriaAC);
            } else if (valorRb.equals("Item de categoria")) {
                ItemCategoriaAC itemCategoriaAC = new ItemCategoriaAC();
                itemCategoriaAC.setNome(tfNome.getText());
                itemCategoriaAC.setMaximoHoras(Double.parseDouble(tfMaximoHoras.getText()));
                itemCategoriaAC.setNumeroTabela(cbNumeroTabela.getValue());
                itemCategoriaAC.setCategoriaAC(cbCategoria.getValue());
                itemCategoriaACDAO.salvar(itemCategoriaAC);
            }
            voltarAluno();
        }
        else{
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Cadastro inválido");
            dialogoInfo.setHeaderText(mensagemValidar);
            dialogoInfo.showAndWait();
        }
    }

    private String validarCadastro(String radioButtonValue) {
        if (radioButtonValue.equals("Categoria")) {
            if (tfNome.getText().equals(""))
                return "Nome inválido";
        }
        else {
            if (tfNome.getText().equals(""))
                return "Nome inválido";
            try{
                double maximoHoras = Double.parseDouble(tfMaximoHoras.getText());
            } catch (NumberFormatException e){
                return "Máximo de horas inválido";
            }
        }
        return "";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbCategoria.setItems(FXCollections.observableArrayList(categoriaACDAO.getCategorias()));
        cbNumeroTabela.setItems(FXCollections.observableArrayList(1,2));
        tfMaximoHoras.setDisable(true);
        cbCategoria.setDisable(true);
        rbCategoria.setUserData("Categoria");
        rbItemCategoria.setUserData("Item de categoria");
    }
}
