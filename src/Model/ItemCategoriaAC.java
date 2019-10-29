package Model;

public class ItemCategoriaAC {
    private int id;
    private String nome;
    private double maximoHoras;
    private CategoriaAC categoriaAC;

    public CategoriaAC getCategoriaAC() {
        return categoriaAC;
    }

    public String toString(){
        return this.nome;
    }

    public void setCategoriaAC(CategoriaAC categoriaAC) {
        this.categoriaAC = categoriaAC;
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

    public double getMaximoHoras() {
        return maximoHoras;
    }

    public void setMaximoHoras(double maximoHoras) {
        this.maximoHoras = maximoHoras;
    }
}
