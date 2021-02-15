/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.io.Serializable;
import java.util.List;
import model.Hospital;
import model.Setor;
import model.dao.SetorDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author Gleywson
 */
public class SetorService implements Serializable {

    SetorDao dao = new SetorDao();

    public void salvar(Setor setor) {
        try {
            if (setor.getId() == null) {
                dao.create(setor);
            } else {
                dao.edit(setor);
            }
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }
    
    public void remover(Setor setor) {
        try {
            dao.remove(setor);
            JsfUtil.addMessage(setor.getNome() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + setor.getNome() + ": " + e.getMessage());
        }
    }
    
    public List<Setor> getSetoresPorHospital(Hospital hospital) {
        return dao.getSetoresPorHospital(hospital);
    }
    
    public List<Setor> getSetores() {
        return dao.findAll();
    }
    
    public Setor buscarPorId(Long id) {
        return dao.find(id);
    }
}
