/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Paciente;
import model.Setor;
import model.dao.PacienteDao;
import model.service.PacienteService;
import model.service.SetorService;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class LeitoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    SetorService setorService = new SetorService();
    
    private List<Setor> setores;
   
    @PostConstruct
    private void init() {
 //       setores = setorService.getSetores();
//        pacientes = service.listarTodos();
//        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//
//        if (chave != null) {
//            Long id = Long.parseLong(chave);
//            paciente = service.buscarPorId(id);
//        }

    }
    
    

   
    public void salvar() {
       
    }

    public List<Setor> getSetores() {
        if(setores == null) {
            setores = setorService.getSetores();
        }
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    

}
