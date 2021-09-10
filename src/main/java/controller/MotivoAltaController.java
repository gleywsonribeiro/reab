/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Motivo;
import model.MotivoAlta;
import model.dao.MotivoAltaDao;
import model.dao.MotivoDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class MotivoAltaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private MotivoAlta motivo;

    private List<MotivoAlta> motivos;
    private MotivoAltaDao dao = new MotivoAltaDao();

    @PostConstruct
    private void init() {
        motivo = new MotivoAlta();
        motivos = dao.findAll();
    }

    public MotivoAlta getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAlta motivo) {
        this.motivo = motivo;
    }

    public List<MotivoAlta> getMotivos() {
        if (motivos == null) {
            motivos = dao.findAll();
        }
        return motivos;
    }


    public void salvar() {
        try {
            if (motivo.getId() == null) {
                dao.create(motivo);
            } else {
                dao.edit(motivo);
            }
            motivo = new MotivoAlta();
            motivos = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }


    public void remover() {
        try {
            dao.remove(motivo);
            motivos = null;
            JsfUtil.addMessage(motivo.getDescricao() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + motivo.getDescricao()+ ": " + e.getMessage());
        }
    }

}
