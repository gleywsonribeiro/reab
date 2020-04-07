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
import javax.faces.bean.ViewScoped;
import model.Pesquisa;
import model.dao.PesquisaDao;
import util.jsf.JsfUtil;

/**
 *
 * @author Gleywson
 */
@ManagedBean(name = "plController")
@ViewScoped
public class PesquisaListController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pesquisa pesquisa = new Pesquisa();
    private List<Pesquisa> pesquisas;
    private List<Pesquisa> pesquisasAtivas;
    
    private PesquisaDao dao = new PesquisaDao();
    
    private void init() {
        pesquisasAtivas = dao.findAllActive();
    }

    public List<Pesquisa> getPesquisas() {
        if (pesquisas == null) {
            pesquisas = dao.findAll();
        }
        return pesquisas;
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<Pesquisa> getPesquisasAtivas() {
        pesquisasAtivas = dao.findAllActive();
        return pesquisasAtivas;
    }
    
    

    public void remover() {
        try {
            dao.remove(pesquisa);
            JsfUtil.addMessage("Pesquisa removida com sucesso!");
            pesquisas = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Existem avaliações para esta pesquisa.");
        }
    }
    
   

}
