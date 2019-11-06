package Model;

public class AtividadeComplementar {
    private String codigo;
    private double cargaHoraria;
    private String descricao;
    private int anoAC;
    private int semestreAC;
    private ItemCategoriaAC itemCategoriaAC;
    private Aluno aluno;
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAnoAC() {
        return anoAC;
    }

    public void setAnoAC(int anoAC) {
        this.anoAC = anoAC;
    }

    public int getSemestreAC() {
        return semestreAC;
    }

    public void setSemestreAC(int semestreAC) {
        this.semestreAC = semestreAC;
    }

    public ItemCategoriaAC getItemCategoriaAC() {
        return itemCategoriaAC;
    }

    public void setItemCategoriaAC(ItemCategoriaAC itemCategoriaAC) {
        this.itemCategoriaAC = itemCategoriaAC;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return aluno.getNumeroMatricula() + ";" + aluno.getNome() + ";" + codigo + ";" + descricao + ";" + cargaHoraria + ";" + semestreAC + ";" + anoAC + ";" + itemCategoriaAC.getNome() + "\n";
    }
}
