/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Atendimento;
import model.Hospital;
import model.Leito;
import model.Ocupacao;
import model.Paciente;
import model.Usuario;
import model.dao.AtendimentoDao;
import model.dao.LeitoDao;
import service.AtendimentoService;
import service.LeitoService;
import service.Sessao;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AtendimentoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private AtendimentoService service = new AtendimentoService();
    private Atendimento atendimento = new Atendimento();
    private Paciente paciente;

    private List<Atendimento> atendimentos = new ArrayList<>();

    //    private Leito leito = new Leito();
    private LeitoService leitoService = new LeitoService();

    @PostConstruct
    private void init() {
        Hospital hospital = Sessao.getUsuarioSessao().getHospitalLogado();
        atendimentos = service.listarTodos(hospital);
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimento = service.buscarPorId(id);
        }

    }

    public void selecionar() {
        this.atendimento.setPaciente(paciente);
//        this.paciente = null;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void darAlta() {
        try {
            atendimento.setDataAlta(new Date());
            Leito leito = atendimento.getLeito();
            service.salvar(atendimento);
            leito.setOcupacao(Ocupacao.VAGO);
            leitoService.salvar(leito);
            JsfUtil.addMessage("Alta realizada com sucesso!");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erro ao realizar alta: " + e.getMessage());
        }

    }

    public void salvar() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
            Usuario usuario = (Usuario) httpSession.getAttribute("currentUser");
            atendimento.setAtendente(usuario);
            atendimento.setDataAtendimento(new Date());

            Leito leito = atendimento.getLeito();
            leito.setOcupacao(Ocupacao.OCUPADO);

            service.salvar(atendimento);
            leitoService.salvar(leito);
            atendimentos = null;

            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        } catch (NegocioException negocio) {
            JsfUtil.addErrorMessage(negocio.getMessage());
        }
    }

    public void editar() {
        try {
            service.edicao(atendimento);
            JsfUtil.addMessage("Alterado com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        } catch (NegocioException negocio) {
            JsfUtil.addErrorMessage(negocio.getMessage());
        }
    }

    public boolean renderedMotivoFalha() {
        return atendimento.getDataExtubacao() != null
                && atendimento.getSucessoExtubacao() != null
                && !atendimento.getSucessoExtubacao();
    }

    public void novo() {
        atendimento = new Atendimento();
    }

    public void remover() {
        try {
            Leito leito = atendimento.getLeito();
            service.remover(atendimento);
            atendimentos = null;
            leito.setOcupacao(Ocupacao.VAGO);
            leitoService.salvar(leito);
            JsfUtil.addMessage("Atendimento apagado com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public Long getPacientesInternados() {
        Hospital hospital = Sessao.getUsuarioSessao().getHospitalLogado();
        return service.getPacientesInternados(hospital);
    }

    public List<Atendimento> getAtendimentosEmAndamento() {
        return service.getAtendimentosEmAndamento();
    }

    public String voltarUnidade() {
        String id = atendimento.getLeito().getSetor().getId().toString();
        return "painel?id=" + id + "faces-redirect=true";
    }
    
    public void atualizaMotivoExtubacao() {
        if(atendimento.getDataExtubacao() == null) {
            atendimento.setSucessoExtubacao(null);
            atendimento.setMotivoFalha("");
            atendimento.setExtMenor(null);
            atendimento.setMotivoExtMaior("");
        }
    }
}
