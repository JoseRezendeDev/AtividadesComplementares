package Model;

public class AtividadeComplementar {
    private int codigo;
    private double cargaHoraria;
    private String descricao;
    private int anoAC;
    private int semestreAC;
    private CategoriaAC categoriaAC;
    private Aluno aluno;
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public CategoriaAC getCategoriaAC() {
        return categoriaAC;
    }

    public void setCategoriaAC(CategoriaAC categoriaAC) {
        this.categoriaAC = categoriaAC;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
