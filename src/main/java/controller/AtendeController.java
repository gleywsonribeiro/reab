/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Atendimento;
import model.Leito;
import model.Ocupacao;
import model.Paciente;
import model.Usuario;
import model.service.AtendimentoService;
import model.service.LeitoService;
import model.service.PacienteService;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AtendeController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Atendimento atendimento = new Atendimento();
    private AtendimentoService atendimentoService = new AtendimentoService();
   

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimento.setPaciente(new PacienteService().buscarPorId(id));
        }

    }

   

    public void darAlta() {
//        try {
//            atendimento.setDataAlta(new Date());
//            Leito leito = atendimento.getLeito();
//            service.salvar(atendimento);
//            leito.setOcupacao(Ocupacao.VAGO);
//            leitoService.salvar(leito);
//            JsfUtil.addMessage("Alta realizada com sucesso!");
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage("Erro ao realizar alta");
//        }

    }

    public String salvar() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
            Usuario usuario = (Usuario) httpSession.getAttribute("currentUser");
            atendimento.setAtendente(usuario);
            
            Leito leito = atendimento.getLeito();
            leito.setOcupacao(Ocupacao.OCUPADO);
            
            atendimentoService.salvar(atendimento);
            new LeitoService().salvar(leito);


            JsfUtil.addMessage("Salvo com sucesso!");
            return "/atendimento/unidades?faces-redirect=true";
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
            return "";
        } catch (NegocioException negocio) {
            JsfUtil.addErrorMessage(negocio.getMessage());
            return "";
        } 
        
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
    
    

}
