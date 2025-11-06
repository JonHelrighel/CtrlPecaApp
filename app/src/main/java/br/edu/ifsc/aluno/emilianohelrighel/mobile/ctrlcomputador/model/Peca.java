package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model;

public class Peca {
    //atributos
    private String nmPeca;
    private String deMarca;
    private String vlPotencia;
    private String deModelo;
    private String vlCapacidade;
    private String vlPolegadas;
    private String vlDpi;
    private int idTipoPeca;


    //metodos

    public String getNmPeca() {
        return nmPeca;
    }

    public void setNmPeca(String nmPeca) throws Exception {
        if (nmPeca.length() > 2) {
            this.nmPeca = nmPeca;
        } else {
            throw new Exception("Nome muito curto");
        }

    }

    public String getDeMarca() {
        return deMarca;
    }

    public void setDeMarca(String deMarca) {
        this.deMarca = deMarca;
    }

    public String getVlPotencia() {
        return vlPotencia;
    }

    public void setVlPotencia(String vlPotencia) {
        this.vlPotencia = vlPotencia;
    }

    public String getDeModelo() {
        return deModelo;
    }

    public void setDeModelo(String deModelo) {
        this.deModelo = deModelo;
    }

    public String getVlCapacidade() {
        return vlCapacidade;
    }

    public void setVlCapacidade(String vlCapacidade) {
        this.vlCapacidade = vlCapacidade;
    }

    public String getVlPolegadas() {
        return vlPolegadas;
    }

    public void setVlPolegadas(String vlPolegadas) {
        this.vlPolegadas = vlPolegadas;
    }

    public String getVlDpi() {
        return vlDpi;
    }

    public void setVlDpi(String vlDpi) {
        this.vlDpi = vlDpi;
    }

    public int getIdTipoPeca() {
        return idTipoPeca;
    }

    public void setIdTipoPeca(int idTipoPeca) {
        this.idTipoPeca = idTipoPeca;
    }
}
