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
import javax.faces.context.FacesContext;
import model.Exercicio;
import model.ItemExercicio;
import model.Treino;
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
    
    private Treino treino = new Treino();
    private ItemExercicio itemExercicio;
    
    private List<Treino> treinos;
    private TreinoService ts = new TreinoService();
    
    @PostConstruct
    private void init() {
        treino = new Treino();
        itemExercicio = new ItemExercicio();
        treinos = ts.ListarTreinos();
        
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        
        if (chave != null) {
            Long id = Long.parseLong(chave);
            treino = ts.buscarPorId(id);
        }
    }
    
    public boolean isExercicioNulo() {
        return itemExercicio.getExercicio() == null;
    }
    
    public void addItem() {
        treino.getExercicios().add(itemExercicio);
        itemExercicio.setTreino(treino);
        itemExercicio = new ItemExercicio();
    }
    
    public void removerItem() {
        treino.getExercicios().remove(itemExercicio);
        itemExercicio = new ItemExercicio();
    }
    
    public void salvar() {
        try {
            ts.salvar(treino);
            treino = new Treino();
            treinos = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (NegocioException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ItemExercicio getItemExercicio() {
        return itemExercicio;
    }

//    public List<Motivo> completeMotivo(String query) {
//        String queryLowerCase = query.toLowerCase();
//        List<Motivo> motivosFiltrados = getMotivos();
//        return motivosFiltrados.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
//    }
    public void setItemExercicio(ItemExercicio itemExercicio) {
        this.itemExercicio = itemExercicio;
    }
    
    public Treino getTreino() {
        return treino;
    }
    
    public void setTreino(Treino treino) {
        this.treino = treino;
    }
    
    public List<Treino> getTreinos() {
        if (treinos == null) {
            treinos = ts.ListarTreinos();
        }
        return treinos;
    }
    
    public void novo() {
        treino = new Treino();
    }
    
    public void remover() {
        try {
            ts.remover(treino);
            treinos = null;
            JsfUtil.addMessage(treino.getNome() + " apagado com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + treino.getNome() + ": " + e.getMessage());
        }
    }
    
}
