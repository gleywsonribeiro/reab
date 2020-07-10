/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Exercicio;
import model.Motivo;
import model.Paciente;
import model.dao.ExercicioDao;
import model.dao.MotivoDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class ExercicioController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Exercicio exercicio;

    private List<Exercicio> exercicios;
    private ExercicioDao dao = new ExercicioDao();

    @PostConstruct
    private void init() {
        exercicio = new Exercicio();
        exercicios = dao.findAll();
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public List<Exercicio> getExercicios() {
        if(exercicios == null) {
            exercicios = dao.findAll();
        }
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
    
     public List<Exercicio> completeTreino(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Exercicio> exerciciosFiltrados = dao.findAll();
        return exerciciosFiltrados.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    
    public void salvar() {
        try {
            if (exercicio.getId() == null) {
                dao.create(exercicio);
            } else {
                dao.edit(exercicio);
            }
            exercicio = new Exercicio();
            exercicios = null;
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
            dao.remove(exercicio);
            exercicios = null;
            JsfUtil.addMessage(exercicio.getNome() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + exercicio.getNome()+ ": " + e.getMessage());
        }
    }

}
