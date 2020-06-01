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
import model.Motivo;
import model.dao.MotivoDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class MotivoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Motivo motivo;

    private List<Motivo> motivos;
    private MotivoDao dao = new MotivoDao();

    @PostConstruct
    private void init() {
        motivo = new Motivo();
        motivos = dao.findAll();
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public List<Motivo> getMotivos() {
        if (motivos == null) {
            motivos = dao.findAll();
        }
        return motivos;
    }

    public void setMotivos(List<Motivo> motivos) {
        this.motivos = motivos;
    }

    public void salvar() {
        try {
            if (motivo.getId() == null) {
                dao.create(motivo);
            } else {
                dao.edit(motivo);
            }
            motivo = new Motivo();
            motivos = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

//    public List<Motivo> completeMotivo(String query) {
//        String queryLowerCase = query.toLowerCase();
//        List<Motivo> motivosFiltrados = getMotivos();
//        return motivosFiltrados.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
//    }

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
