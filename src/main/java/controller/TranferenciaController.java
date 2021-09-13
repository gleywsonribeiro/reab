/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import service.AtendimentoService;
import service.LeitoService;
import service.PacienteService;
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
import java.util.List;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class TranferenciaController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Atendimento atendimento = new Atendimento();
    private AtendimentoService atendimentoService = new AtendimentoService();

    private List<Leito> leitosVagos = new ArrayList<>();
    private LeitoService leitoService = new LeitoService();

    private Long idLeitoDestino;

   

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimento = atendimentoService.buscarPorId(id);

            leitosVagos = leitoService.getLeitosVagos(atendimento.getLeito().getSetor());
        }

    }

    public String voltarPaciente() {
        String id = atendimento.getPaciente().getId().toString();
        return "cadastro?id=" + id + "faces-redirect=true";
    }



    public void transferir() {
        try {
            Leito leito = atendimento.getLeito();
            leito.setOcupacao(Ocupacao.VAGO);
            leitoService.salvar(leito);

            Leito destino = leitoService.getLeito(idLeitoDestino);

            atendimento.setLeito(destino);
            atendimentoService.salvar(atendimento);

            destino.setOcupacao(Ocupacao.OCUPADO);
            leitoService.salvar(destino);

            leitosVagos = leitoService.getLeitosVagos(atendimento.getLeito().getSetor());
            JsfUtil.addMessage("Transferência salva com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao transferir: " + e.getMessage());
        } catch (NegocioException negocio) {
            JsfUtil.addErrorMessage(negocio.getMessage());
        }
        
    }

    public String voltarUnidade() {
        String id = atendimento.getLeito().getSetor().getId().toString();
        return "painel?id=" + id + "faces-redirect=true";
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<Leito> getLeitosVagos() {
        return leitosVagos;
    }

    public Long getIdLeitoDestino() {
        return idLeitoDestino;
    }

    public void setIdLeitoDestino(Long idLeitoDestino) {
        this.idLeitoDestino = idLeitoDestino;
    }
}
