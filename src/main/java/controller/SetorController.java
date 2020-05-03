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
import model.Hospital;
import model.Setor;
import model.dao.SetorDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class SetorController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Setor setor;

    private List<Setor> setores;
    
    private SetorDao dao = new SetorDao();
  

    @PostConstruct
    private void init() {
        setor = new Setor();
        setores = dao.findAll();
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Setor> getSetores() {
        if (setores == null) {
            setores = dao.findAll();
        }
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }


    public void salvar() {
        try {
            if (setor.getId() == null) {
                dao.create(setor);
            } else {
                dao.edit(setor);
            }
            setores = null;
            setor = new Setor();
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

    public void remover() {
        try {
            dao.remove(setor);
            setores = null;
            JsfUtil.addMessage(setor.getNome() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + setor.getNome() + ": " + e.getMessage());
        }
    }

}
