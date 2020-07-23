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
import model.Score;
import model.dao.MotivoDao;
import model.dao.ResultadoDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class ResultadoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Score resultado;

    private List<Score> resultados;
    private ResultadoDao dao = new ResultadoDao();

    @PostConstruct
    private void init() {
        resultado = new Score();
        resultados = dao.findAll();
    }

    public Score getResultado() {
        return resultado;
    }

    public void setResultado(Score resultado) {
        this.resultado = resultado;
    }

   

    public List<Score> getResultados() {
        if (resultados == null) {
            resultados = dao.findAll();
        }
        return resultados;
    }

    

    public void salvar() {
        try {
            if (resultado.getId() == null) {
                dao.create(resultado);
            } else {
                dao.edit(resultado);
            }
            resultado = new Score();
            resultados = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }


    public void remover() {
        try {
            dao.remove(resultado);
            resultados = null;
            JsfUtil.addMessage(resultado.getDescricao() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + resultado.getDescricao()+ ": " + e.getMessage());
        }
    }

}
