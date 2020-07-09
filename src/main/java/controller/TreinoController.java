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
import model.Exercicio;
import model.Motivo;
import model.Treino;
import model.dao.ExercicioDao;
import model.dao.MotivoDao;
import model.service.TreinoService;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class TreinoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Treino treino;

    private List<Treino> treinos;
    private TreinoService ts = new TreinoService();

    @PostConstruct
    private void init() {
        treino = new Treino();
        treinos = ts.ListarTreinos();
    }

    
    public void salvar() {
        try {
            ts.salvar(treino);
            treino = new Treino();
            treinos = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (NegocioException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

//    public List<Motivo> completeMotivo(String query) {
//        String queryLowerCase = query.toLowerCase();
//        List<Motivo> motivosFiltrados = getMotivos();
//        return motivosFiltrados.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
//    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    

}
