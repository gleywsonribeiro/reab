/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Hospital;
import model.Paciente;
import model.Perfil;
import model.Usuario;
import model.dao.HospitalDao;
import model.dao.PacienteDao;
import model.dao.UsuarioDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AvaliacaoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Paciente paciente;
    private int pressaoArterial; //50-100
    private int frequenciaCardiaca; //60-100

    private int FrequenciaRespiratoria; //12-20
    private boolean suporteVentilacao;
    private int psv;  //<10
    private int peep; //<8
    private int fio2; // <0.6

    private int hemoglobina; //< 7
    private int lactato; // > 2
    private int plaquetas; //< 35

    private int glasgow; // glasgow < 9
    private int rass; // -2 ---- 2

    private String resultado;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(int pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public int getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(int frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public int getFrequenciaRespiratoria() {
        return FrequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(int FrequenciaRespiratoria) {
        this.FrequenciaRespiratoria = FrequenciaRespiratoria;
    }

    public boolean isSuporteVentilacao() {
        return suporteVentilacao;
    }

    public void setSuporteVentilacao(boolean suporteVentilacao) {
        this.suporteVentilacao = suporteVentilacao;
    }

    public int getPsv() {
        return psv;
    }

    public void setPsv(int psv) {
        this.psv = psv;
    }

    public int getPeep() {
        return peep;
    }

    public void setPeep(int peep) {
        this.peep = peep;
    }

    public int getFio2() {
        return fio2;
    }

    public void setFio2(int fio2) {
        this.fio2 = fio2;
    }

    public int getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(int hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public int getLactato() {
        return lactato;
    }

    public void setLactato(int lactato) {
        this.lactato = lactato;
    }

    public int getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(int plaquetas) {
        this.plaquetas = plaquetas;
    }

    public int getGlasgow() {
        return glasgow;
    }

    public void setGlasgow(int glasgow) {
        this.glasgow = glasgow;
    }

    public int getRass() {
        return rass;
    }

    public void setRass(int rass) {
        this.rass = rass;
    }

    public void salvar() {
        boolean liberado = true;

        if (pressaoArterial < 50 || pressaoArterial > 100) {
            liberado = false;
        }

        if (frequenciaCardiaca < 60 || frequenciaCardiaca > 100) {
            liberado = false;
        }

        if (FrequenciaRespiratoria < 12 || FrequenciaRespiratoria > 20) {
            liberado = false;
        }

        if (isSuporteVentilacao()) {
            if (psv > 10 || peep > 8 || fio2 > 60) {
                liberado = false;
            }
        }

        if (glasgow > 9 || rass < -2 || rass > 2) {
            liberado = false;
        }

        if (liberado) {
            resultado = "Paciente liberado para mobilização";
        } else {
            resultado = "Paciente liberado para mobilização";
        }

    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
