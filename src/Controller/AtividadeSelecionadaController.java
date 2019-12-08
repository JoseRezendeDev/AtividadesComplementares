package Controller;

import DAO.AtividadeComplementarDAO;
import DAO.ItemCategoriaACDAO;
import DAO.ProfessorDAO;
import Loader.AtividadesLoader;
import Loader.ValidaACLoader;
import Model.Aluno;
import Model.AtividadeComplementar;
import Model.ItemCategoriaAC;
import Model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class AtividadeSelecionadaController {
    @FXML
    private Pane pane;
    @FXML
    private Label lbAluno;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbDescricao;
    @FXML
    private Label lbCargaHoraria;
    @FXML
    private Label lbSemestre;
    @FXML
    private Label lbAno;
    @FXML
    private Label lbProfessor;
    @FXML
    private Label lbCategoria;

    private AtividadeComplementar atividadeComplementar;
    private Aluno aluno;
    private AtividadeComplementarDAO atvDAO = new AtividadeComplementarDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private ItemCategoriaACDAO itemDAO = new ItemCategoriaACDAO();

    public void voltarAtividades(){
        Stage stage = (Stage) pane.getScene().getWindow();
        AtividadesLoader atividadesLoader = new AtividadesLoader();
        atividadesLoader.loadAtividades(aluno, stage);
    }

    public void initialize(URL location, ResourceBundle resources) {
        lbDescricao.setText(atividadeComplementar.getDescricao());
        lbSemestre.setText(String.valueOf(atividadeComplementar.getSemestreAC()));
        lbAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        lbCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
        lbCategoria.setText(atividadeComplementar.getItemCategoriaAC().getNome());
        lbProfessor.setText(atividadeComplementar.getProfessor().getNome());
        lbAluno.setText(atividadeComplementar.getAluno().getNome());
        lbCodigo.setText(atividadeComplementar.getCodigo());
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

    public void setAtividade(AtividadeComplementar atv) {
        this.atividadeComplementar = atv;
    }

    public void excluirAtividade(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atividade Complementar - Excluir Atividade");
        alert.setHeaderText("Atenção, você está prestes a excluir essa atividade permanentemente!");
        alert.setContentText("Você deseja excluir essa atividade complementar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            atvDAO.excluirAtividade(this.atividadeComplementar.getCodigo());
            Stage stage = (Stage) pane.getScene().getWindow();
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            atividadesLoader.loadAtividades(aluno, stage);
        } else {
            Stage stage = (Stage) pane.getScene().getWindow();
            AtividadesLoader atividadesLoader = new AtividadesLoader();
            atividadesLoader.loadAtividades(aluno, stage);
        }

    }

    public void editarDescricao(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog(this.atividadeComplementar.getDescricao());
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Descrição");
        dialog.setContentText("Entre com uma nova descrição:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.atividadeComplementar.setDescricao(result.get());
            atvDAO.editarAtividade(this.atividadeComplementar);
            lbDescricao.setText(atividadeComplementar.getDescricao());
        }
    }

    public void editarCargaHoraria(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(this.atividadeComplementar.getCargaHoraria()));
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Carga Horária");
        dialog.setContentText("Entre com uma nova carga horária:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (atividadeComplementar.getItemCategoriaAC().getMaximoHoras() >= Double.parseDouble(result.get())) {
                this.atividadeComplementar.setCargaHoraria(Double.parseDouble(result.get()));
                atvDAO.editarAtividade(this.atividadeComplementar);
                lbCargaHoraria.setText(String.valueOf(atividadeComplementar.getCargaHoraria()));
            }
            else {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Erro ao Editar");
                dialogoInfo.setHeaderText("A categoria não permite essa carga horária.");
                dialogoInfo.showAndWait();
            }
        }
    }

    public void editarAno(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(this.atividadeComplementar.getAnoAC()));
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Ano");
        dialog.setContentText("Entre com um novo ano:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.atividadeComplementar.setAnoAC(Integer.parseInt(result.get()));
            atvDAO.editarAtividade(this.atividadeComplementar);
            lbAno.setText(String.valueOf(atividadeComplementar.getAnoAC()));
        }
    }

    public void editarCategoria(ActionEvent actionEvent) {
        List<String> categorias = new ArrayList<>();
        for (ItemCategoriaAC cat : itemDAO.getItensCategoria(atividadeComplementar.getAluno().getNumeroTabelaReferente())) {
            categorias.add(cat.getNome());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", categorias);
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Categoria");
        dialog.setContentText("Escolha uma nova categoria:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            for (ItemCategoriaAC cat : itemDAO.getItensCategoria(atividadeComplementar.getAluno().getNumeroTabelaReferente())) {
                if (cat.getNome().equals(result.get())) {
                    if (atividadeComplementar.getCargaHoraria() <= cat.getMaximoHoras()) {
                        this.atividadeComplementar.setItemCategoriaAC(cat);
                        atvDAO.editarAtividade(this.atividadeComplementar);
                        lbCategoria.setText(atividadeComplementar.getItemCategoriaAC().getNome());
                    }
                    else {
                        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                        dialogoInfo.setTitle("Erro ao Editar");
                        dialogoInfo.setHeaderText("Essa categoria não permite essa carga horária.");
                        dialogoInfo.showAndWait();
                    }
                }
            }
        }
    }

    public void editarSemestre(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(this.atividadeComplementar.getSemestreAC()));
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Semestre");
        dialog.setContentText("Entre com um novo semestre:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.atividadeComplementar.setSemestreAC(Integer.parseInt(result.get()));
            atvDAO.editarAtividade(this.atividadeComplementar);
            lbAno.setText(String.valueOf(atividadeComplementar.getSemestreAC()));
        }
    }

    public void editarProfessor(ActionEvent actionEvent) {
        List<String> professores = new ArrayList<>();
        for (Professor professor : professorDAO.getProfessores()) {
            professores.add(professor.getNome());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", professores);
        dialog.setTitle("Atividade Complementar - Editar Atividade");
        dialog.setHeaderText("Edição de Atividade Complementar - Professor");
        dialog.setContentText("Escolha um novo professor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            for (Professor professor : professorDAO.getProfessores()) {
               if (professor.getNome().equals(result.get())){
                   this.atividadeComplementar.setProfessor(professor);
                   atvDAO.editarAtividade(this.atividadeComplementar);
                   lbProfessor.setText(String.valueOf(atividadeComplementar.getProfessor()));
               }
            }
        }

    }
}
