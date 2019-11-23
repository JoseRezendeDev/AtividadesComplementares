package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAC {
    private int id;
    private String nome;
    private int numeroTabela;
    private List<ItemCategoriaAC> itensCategoria;

    public CategoriaAC(){
        itensCategoria = new ArrayList<>();
    }

    public CategoriaAC(int id){
        this.id = id;
    }

    public int getNumeroTabela() {
        return numeroTabela;
    }

    public void setNumeroTabela(int numeroTabela) {
        this.numeroTabela = numeroTabela;
    }

    public List<ItemCategoriaAC> getItensCategoria() {
        return itensCategoria;
    }

    public void setItensCategoria(List<ItemCategoriaAC> itensCategoria) {
        this.itensCategoria = itensCategoria;
    }

    public String toString(){
        return this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
