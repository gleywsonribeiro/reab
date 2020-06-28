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
import model.Atendimento;
import model.Paciente;

import model.Triagem;
import model.dao.AtendimentoDao;

import model.dao.TriagemDao;
import model.service.AtendimentoService;
import model.service.TriagemService;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@SessionScoped
public class TriagemController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Triagem triagem = new Triagem();

    private List<Triagem> triagens;
    private final TriagemService service = new TriagemService(new TriagemDao());
    private AtendimentoService as = new AtendimentoService(new AtendimentoDao());

    public void salvar() {
//        try {
//            Atendimento temp = as.buscarPorId(triagem.getAtendimento().getId());
//            temp.setLiberadoMobilizacao(triagem.isLiberadoMobilizacao());
//            
//            service.salvar(triagem);
//            as.salvar(temp);
//            triagem = new Triagem();
//        } catch (NegocioException e) {
//            JsfUtil.addErrorMessage(e.getMessage());
//        }
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    public List<Triagem> getTriagens() {
        return triagens;
    }
    
    

}
