/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Entrevista;
import model.Pesquisa;
import model.dao.EntrevistaDao;
import util.jsf.JsfUtil;

/**
 *
 * @author Gleywson
 */
@ManagedBean
@ViewScoped
public class TabelaEntrevistaController {

    private Pesquisa pesquisa;
    private Entrevista entrevista;
    private List<Entrevista> entrevistas = new ArrayList<Entrevista>();
    
    
    private final EntrevistaDao entrevistaDao = new EntrevistaDao();


    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public void atualizaEntrevistasPorPesquisa() {
        entrevistas = entrevistaDao.getEntrevistasPorPesquisa(pesquisa);
    }
    
    public void remover() {
        try {
            entrevistaDao.remove(entrevista);
            atualizaEntrevistasPorPesquisa();
            JsfUtil.addMessage("Excluído com sucesso!");
        } catch (Exception e) {
            JsfUtil.addMessage("Não foi possível excluir avaliação! \n" + e.getMessage());
        }
    }

    public List<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    
    
}