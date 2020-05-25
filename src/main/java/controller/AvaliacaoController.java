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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public String validarPressao() {
        if (pressaoArterial >= 50 && pressaoArterial <= 100) {
            return "frequenciaCardiaca?faces-redirect=true";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaCardiaca() {
        if (frequenciaCardiaca >= 60 && frequenciaCardiaca <= 100) {
            return "frequenciaRespiratoria?faces-redirect=true";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaRespiratoria() {
        if (FrequenciaRespiratoria >= 12 && FrequenciaRespiratoria <= 20) {
            if (suporteVentilacao) {
                return "psv-ps?faces-redirect=true";
            } else {
                return "hemoglobina?faces-redirect=true";
            }
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPsv() {
        if (psv <= 10) {
            return "peep?faces-redirect=true";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPeep() {
        if (peep <= 8) {
            return "fio2";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFio2() {
        return (fio2 <= 60) ? "hemoglobina?faces-redirect=true" : "reprovado?faces-redirect=true";
    }

    public String validarHemoglobina() {
        return (hemoglobina <= 7) ? "lactato?faces-redirect=true" : "reprovado?faces-redirect=true";
    }

    public String validarLactato() {
        return (lactato >= 2) ? "plaqueta?faces-redirect=true" : "reprovado?faces-redirect=true";
    }

    public String validarPlaquetas() {
        if(plaquetas <= 35) {
            return "glasgow?faces-redirect=true";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarGlasgow() {
        return (glasgow <= 9) ? "rass?faces-redirect=true" : "reprovado?faces-redirect=true";
    }

    public String validarRass() {
        return (rass >= -2 && rass <= 2) ? "aprovado?faces-redirect=true" : "reprovado?faces-redirect=true";
    }
}
