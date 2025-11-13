package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model;

public class TipoPeca {
    private int idTipoPeca;
    private String nmTipoPeca;
    //metodo toString() para o adapter do spinner
    @Override
    public String toString() {return this.nmTipoPeca;}

    public int getIdTipoPeca() {
        return idTipoPeca;
    }

    public void setIdTipoPeca(int idTipoPeca) {
        this.idTipoPeca = idTipoPeca;
    }

    public String getNmTipoPeca() {
        return nmTipoPeca;
    }

    public void setNmTipoPeca(String nmTipoPeca) {
        this.nmTipoPeca = nmTipoPeca;
    }
}

