/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Atendimento;
import model.Opcao;

import model.Avaliacao;
import model.Score;
import model.service.AtendimentoService;
import model.service.AvaliacaoService;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AvaliacaoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Avaliacao avaliacao = new Avaliacao();

    
    private final AvaliacaoService service = new AvaliacaoService();
    
    @PostConstruct
    private void init() {
        
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            avaliacao = service.buscarPorId(id);
        }

    }

    public String salvar() {
        try {
            //Atendimento temp = as.buscarPorId(avaliacao.getAtendimento().getId());
            //boolean liberado = validar();
            //emp.setLiberadoMobilizacao(liberado);
            //avaliacao.setLiberadoMobilizacao(liberado);

            service.salvar(avaliacao);
            

            return "sucesso?faces-redirect=true";

        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
            return "";
        }
    }



    public Score[] getScores() {
        return Score.values();
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

}
