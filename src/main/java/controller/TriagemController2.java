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
        if (triagem.isPressaoArterial()) {
            return "frequenciaCardiaca?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaCardiaca() {
        if (triagem.isFrequenciaCardiaca()) {
            return "frequenciaRespiratoria?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFrequenciaRespiratoria() {
        if (triagem.isFrequenciaRespiratoria()) {
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
        if (triagem.isPsv()) {
            return "peep?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPeep() {
        if (triagem.isPeep()) {
            return "fio2";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarFio2() {
        if (triagem.isFio2()) {
            return "hemoglobina?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarHemoglobina() {
        if (triagem.isHemoglobina()) {
            return "lactato?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarLactato() {
        if (triagem.isLactato()) {
            return "plaqueta?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarPlaquetas() {
        if (triagem.isPlaquetas()) {
            return "glasgow?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarGlasgow() {
        if (triagem.isGlasgow()) {
            return "rass?faces-redirect=true";
        } else {
            salvar();
            return "reprovado?faces-redirect=true";
        }
    }

    public String validarRass() {
        String destino;
        if (triagem.isRass()) {
            triagem.setLiberadoMobilizacao(true);
            destino = "aprovado?faces-redirect=true";
        } else {
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
