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

import java.net.URL;
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
                String nome = tfNome.getText();
                CategoriaAC categoriaAC = new CategoriaAC();
                categoriaAC.setNome(nome);
                categoriaACDAO.salvar(categoriaAC);
            } else if (valorRb.equals("Item de categoria")) {
                String nome = tfNome.getText();
                double maximoHoras = Double.parseDouble(tfMaximoHoras.getText());
                CategoriaAC categoriaAC = cbCategoria.getValue();
                ItemCategoriaAC itemCategoriaAC = new ItemCategoriaAC();
                itemCategoriaAC.setNome(nome);
                itemCategoriaAC.setMaximoHoras(maximoHoras);
                itemCategoriaAC.setCategoriaAC(categoriaAC);
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
        tfMaximoHoras.setDisable(true);
        cbCategoria.setDisable(true);
        rbCategoria.setUserData("Categoria");
        rbItemCategoria.setUserData("Item de categoria");
    }
}
