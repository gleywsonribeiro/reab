/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    private Paciente paciente = new Paciente();
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
        if (plaquetas <= 35) {
            return "glasgow?faces-redirect=true";
        } else {
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarGlasgow() {
        return (glasgow <= 9) ? "rass?faces-redirect=true" : "reprovado?faces-redirect=true";
    }

    public String validarRass() {
        String destino;
        if (rass >= -2 && rass <= 2) {
            triagem.setLiberadoMobilizacao(true);
            destino = "aprovado?faces-redirect=true";
        } else {
            triagem.setLiberadoMobilizacao(false);
            destino = "reprovado?faces-redirect=true";
        }
        
        triagem.setPaciente(paciente);
        
        try {
            dao.create(triagem);
            return destino;
        } catch (Exception e) {
            return null;
        }
    }
}
