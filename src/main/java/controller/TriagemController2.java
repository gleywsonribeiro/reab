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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import model.Paciente;

import model.Triagem;

import model.dao.TriagemDao;

/**
 *
 * @author gleyw
 */
@ManagedBean
@SessionScoped
public class TriagemController2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Triagem triagem = new Triagem();
    private Paciente paciente;

    private List<Triagem> triagens;
    private TriagemDao dao;

    @PostConstruct
    private void init() {
        triagem = new Triagem();
    }

//    public void salvar() {
//        try {
//            if (hospital.getId() == null) {
//                dao.create(hospital);
//            } else {
//                dao.edit(hospital);
//            }
//            hospitais = null;
//        } catch (DBException e) {
//            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
//        }
//    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    
    public List<Triagem> getTriagens() {
        return triagens;
    }

   
    public String resultado() {
        try {
            triagem.setPaciente(paciente);
            String resultado = triagem.validarRass();
            dao.create(triagem);
            
            return resultado;
        } catch (Exception e) {
            return "web/404";
        }
    }
    

}
