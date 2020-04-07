/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Entrevista;
import model.Pergunta;
import model.Pesquisa;
import model.Resposta;
import model.dao.EntrevistaDao;
import model.dao.PesquisaDao;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class EntrevistaController {

    private Pesquisa pesquisa;
    private List<Entrevista> entrevistas = new ArrayList<Entrevista>();
    
    private Entrevista entrevista;
  
    private PesquisaDao pesquisaDao = new PesquisaDao();
    

    private EntrevistaDao entrevistaDao = new EntrevistaDao();
        
    private List<Resposta> respostas;

    @PostConstruct
    public void init() {
        String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_pesquisa");
        
        pesquisa = pesquisaDao.find(Long.parseLong(codigo));
        entrevista = new Entrevista();
        entrevista.setPesquisa(pesquisa);
        
        respostas = new ArrayList<Resposta>();
        
        for (Pergunta p : entrevista.getPesquisa().getPerguntas()) {
            Resposta resposta = new Resposta();
            resposta.setEntrevista(entrevista);
            resposta.setPergunta(p);
            entrevista.getRespostas().add(resposta);          
        }
        
    }
    
    public void atualizaEntrevistasPorPesquisa() {
        entrevistas = entrevistaDao.getEntrevistasPorPesquisa(pesquisa);
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public String salvar() {
        entrevista.setDataHora(new Date());
       if(entrevista.getId() == null) {
           entrevistaDao.create(entrevista);
       } else {
           entrevistaDao.edit(entrevista);
       }
        JsfUtil.addMessage("salvo com sucesso!");
        return "concluido?faces-redirect=true";
        
    }
    
    public void remover() {
        try {
            entrevistaDao.remove(entrevista);
            atualizaEntrevistasPorPesquisa();
            JsfUtil.addMessage("Excluído com sucesso!");
        } catch (Exception e) {
            JsfUtil.addMessage("Não foi possível excluir entrevista! \n" + e.getMessage());
        }
    }

    public List<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    
    
    
}
