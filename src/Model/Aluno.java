package Model;

import DAO.AlunoDAO;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String numeroMatricula;
    private int anoIngresso;
    private int semestreIngresso;
    private double horasCumpridas;
    private boolean graduando;
    public static final double TOTAL_HORAS_NECESSARIAS = 100;

    private List<AtividadeComplementar> atividades;

    public Aluno() {
        atividades = new ArrayList<>();
    }

    public boolean isGraduando() {
        return graduando;
    }

    public void setGraduando(boolean graduando) {
        this.graduando = graduando;
    }

    public double getHorasCumpridas() {
        return horasCumpridas;
    }

    public void setHorasCumpridas(double horasCumpridas) {
        this.horasCumpridas = horasCumpridas;
    }

    public List<AtividadeComplementar> getAtividades() {
        return atividades;
    }

    public void setAtividade(AtividadeComplementar atividade){
        atividades.add(atividade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public int getSemestreIngresso() {
        return semestreIngresso;
    }

    public void setSemestreIngresso(int semestreIngresso) {
        this.semestreIngresso = semestreIngresso;
    }

    public int getNumeroTabelaReferente() {
        if (this.anoIngresso <= 2017)
            return 1;
        else
            return 2;
    }

    public void atualizarHorasCumpridas(){
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.setHorasCumpridas(this.getNumeroMatricula());
    }

    //return true para concluido e false para em andamento
    public boolean getProgresso() {
        if (this.getNumeroTabelaReferente() == 1){
            if (this.getHorasCumpridas() < 74)
                return false;
            else
                return true;
        } else {
            if (this.getHorasCumpridas() < 100)
                return false;
            else
                return true;
        }
    }

    public boolean getTotalCategoriaEspecifica(AtividadeComplementar atv){
        AlunoDAO alunoDAO = new AlunoDAO();
        double total = alunoDAO.getTotalCategoriaEspecifica(this, atv);
        if (total + atv.getCargaHoraria() <= atv.getItemCategoriaAC().getMaximoHoras())
            return true;
        else
            return false;
    }
}
