/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Atendimento;
import model.Paciente;
import model.Triagem;
import model.dao.TriagemDao;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class TriagemController implements Serializable {

    private static final long serialVersionUID = 1L;


    private Triagem triagem = new Triagem();
    private TriagemDao dao = new TriagemDao();

    private Atendimento atendimento = new Atendimento();
    private Integer pressaoArterial; //50-100
    private Integer frequenciaCardiaca; //60-100

    private Integer FrequenciaRespiratoria; //12-20
    private boolean suporteVentilacao;
    private Integer psv;  //<10
    private Integer peep; //<8
    private Integer fio2; // <0.6

    private Integer hemoglobina; //< 7
    private Integer lactato; // > 2
    private Integer plaquetas; //< 35

    private Integer glasgow; // glasgow < 9
    private Integer rass; // -2 ---- 2

    private String resultado;

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    

    public Integer getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(Integer pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public Integer getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(Integer frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public Integer getFrequenciaRespiratoria() {
        return FrequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(Integer FrequenciaRespiratoria) {
        this.FrequenciaRespiratoria = FrequenciaRespiratoria;
    }

    public boolean isSuporteVentilacao() {
        return suporteVentilacao;
    }

    public void setSuporteVentilacao(boolean suporteVentilacao) {
        this.suporteVentilacao = suporteVentilacao;
    }

    public Integer getPsv() {
        return psv;
    }

    public void setPsv(Integer psv) {
        this.psv = psv;
    }

    public Integer getPeep() {
        return peep;
    }

    public void setPeep(Integer peep) {
        this.peep = peep;
    }

    public Integer getFio2() {
        return fio2;
    }

    public void setFio2(Integer fio2) {
        this.fio2 = fio2;
    }

    public Integer getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(Integer hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Integer getLactato() {
        return lactato;
    }

    public void setLactato(Integer lactato) {
        this.lactato = lactato;
    }

    public Integer getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(Integer plaquetas) {
        this.plaquetas = plaquetas;
    }

    public Integer getGlasgow() {
        return glasgow;
    }

    public void setGlasgow(Integer glasgow) {
        this.glasgow = glasgow;
    }

    public Integer getRass() {
        return rass;
    }

    public void setRass(Integer rass) {
        this.rass = rass;
    }

    public String getResultado() {
        return resultado;
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    
}
