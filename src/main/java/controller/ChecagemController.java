/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Atendimento;
import model.Opcao;

import model.Avaliacao;
import model.dao.AtendimentoDao;
import model.dao.AvaliacaoDao;
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
public class ChecagemController implements Serializable {

    private static final long serialVersionUID = 1L;

    private AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
    private AtendimentoDao atendimentoDao = new AtendimentoDao();
    private List<Avaliacao> avaliacoesPorAtendimento;
    
    private Avaliacao avaliacao = new Avaliacao();
    
    @PostConstruct
    private void init() {
        atualizaLista();
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            avaliacao = avaliacaoDao.find(id);
        }
    }
    
    public void atualizaLista() {
        List<Atendimento> atendimentos = atendimentoDao.findAll();
        avaliacoesPorAtendimento = new ArrayList<>();
        
        for (Atendimento atendimento : atendimentos) {
            Avaliacao current = avaliacaoDao.getUltimaAvaliacaoAtendimento(atendimento);
            avaliacoesPorAtendimento.add(current);
        }
    }

    public List<Avaliacao> getAvaliacoesPorAtendimento() {
        atualizaLista();
        return avaliacoesPorAtendimento;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
}
