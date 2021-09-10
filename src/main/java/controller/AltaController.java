/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import service.AtendimentoService;
import service.LeitoService;
import service.Sessao;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AltaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private AtendimentoService service = new AtendimentoService();
    private Atendimento atendimento = new Atendimento();

    //    private Leito leito = new Leito();
    private LeitoService leitoService = new LeitoService();

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimento = service.buscarPorId(id);
            atendimento.setDataAlta(new Date());
        }

    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public void darAlta() {
        try {
            Leito leito = atendimento.getLeito();
            service.salvar(atendimento);
            leito.setOcupacao(Ocupacao.VAGO);
            leitoService.salvar(leito);
            JsfUtil.addMessage("Alta realizada com sucesso!");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro ao realizar alta: " + e.getMessage());
        }

    }

    public String voltarUnidade() {
        String id = atendimento.getLeito().getSetor().getId().toString();
        return "painel?id=" + id + "faces-redirect=true";
    }

}
