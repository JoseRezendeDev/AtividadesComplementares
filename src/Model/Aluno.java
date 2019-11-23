package Model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String numeroMatricula;
    private int anoIngresso;
    private int semestreIngresso;
    private double horasCumpridas;

    private List<AtividadeComplementar> atividades;

    public Aluno() {
        atividades = new ArrayList<>();
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
}
