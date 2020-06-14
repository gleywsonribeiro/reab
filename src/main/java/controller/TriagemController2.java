/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.Atendimento;
import model.Paciente;

import model.Triagem;
import model.dao.AtendimentoDao;

import model.dao.TriagemDao;
import model.service.AtendimentoService;
import model.service.TriagemService;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@SessionScoped
public class TriagemController2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Triagem triagem = new Triagem();

    private List<Triagem> triagens;
    private final TriagemService service = new TriagemService(new TriagemDao());
    private AtendimentoService as = new AtendimentoService(new AtendimentoDao());

    @PostConstruct
    private void init() {
        triagem = new Triagem();
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

  

    public List<Triagem> getTriagens() {
        return triagens;
    }

    //--
    public String validarPressao() {
        if (triagem.getPressaoArterial() >= 50 && triagem.getPressaoArterial() <= 100) {
            return "frequenciaCardiaca?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaCardiaca() {
        if (triagem.getFrequenciaCardiaca() >= 60 && triagem.getFrequenciaCardiaca() <= 100) {
            return "frequenciaRespiratoria?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaRespiratoria() {
        if (triagem.getFrequenciaRespiratoria() >= 12 && triagem.getFrequenciaRespiratoria() <= 20) {
            if (triagem.isSuporteVentilacao()) {
                return "psv-ps?faces-redirect=true";
            } else {
                return "hemoglobina?faces-redirect=true";
            }
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPsv() {
        if (triagem.getPsv() <= 10) {
            return "peep?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPeep() {
        if (triagem.getPeep() <= 8) {
            return "fio2";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFio2() {
        if (triagem.getFio2() <= 60) {
            return "hemoglobina?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarHemoglobina() {
        if (triagem.getHemoglobina() <= 7) {
            return "lactato?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarLactato() {
        if (triagem.getLactato() >= 2) {
            return "plaqueta?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPlaquetas() {
        if (triagem.getPlaquetas() <= 35) {
            return "glasgow?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarGlasgow() {
        if (triagem.getGlasgow() <= 9) {
            return "rass?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarRass() {
        String destino;
        if (triagem.getRass() >= -2 && triagem.getRass() <= 2) {
            triagem.setLiberadoMobilizacao(true);
            destino = "aprovado?faces-redirect=true";
        } else {
            triagem.setLiberadoMobilizacao(false);
            destino = "reprovado?faces-redirect=true";
        }
        salvar();
        return destino;
    }


    public void salvar() {
        try {
            Atendimento temp = as.buscarPorId(triagem.getAtendimento().getId());
            temp.setLiberadoMobilizacao(triagem.isLiberadoMobilizacao());
            
            service.salvar(triagem);
            as.salvar(temp);
            triagem = new Triagem();
        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }
    }

}
