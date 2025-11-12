package br.edu.ifsc.aluno.emilianohelrighel.mobile.ctrlcomputador.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Peca {
    //atributos
    private int idPeca;
    private String nmPeca;
    private String deMarca;
    private String vlPotencia;
    private String deModelo;
    private String vlCapacidade;
    private String vlPolegadas;
    private String vlDpi;
    private int idTipoPeca;
    //Construtores

    public Peca(){
        this.nmPeca = "";
        this.deMarca = "";
        this.vlPotencia = "";
        this.deModelo = "";
        this.vlCapacidade = "";
        this.vlPolegadas = "";
        this.vlDpi = "";
        this.idTipoPeca = 0;
        this.idPeca = 0;
    }

    public Peca(JSONObject jp) throws JSONException {
        this.setIdTipoPeca(jp.getInt("idTipoPeca"));
        this.setNmPeca(jp.getString("nmPeca"));
        this.setDeMarca(jp.getString("deMarca"));
        this.setVlPotencia(jp.getString("vlPotencia"));
        this.setDeModelo(jp.getString("deModelo"));
        this.setVlCapacidade(jp.getString("vlCapacidade"));
        this.setVlPolegadas(jp.getString("vlPolegadas"));
        this.setVlDpi(jp.getString("vlDpi"));
        this.setIdPeca(jp.getInt("idPeca"));
    }
    // Metodo retorna o objeto com dados no formato JSON

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("idPeca", this.idPeca);
            json.put("nmPeca", this.nmPeca);
            json.put("deMarca", this.deMarca);
            json.put("vlPotencia", this.vlPotencia);
            json.put("deModelo", this.deModelo);
            json.put("vlCapacidade", this.vlCapacidade);
            json.put("vlPolegadas", this.vlPolegadas);
            json.put("vlDpi", this.vlDpi);
            json.put("idTipoPeca", this.idTipoPeca);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getNmPeca() {
        return nmPeca;
    }

    public void setNmPeca(String nmPeca) {
        if (nmPeca.length() > 2) {
            this.nmPeca = nmPeca;
        } else {
            throw new IllegalArgumentException("Nome muito curto");
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