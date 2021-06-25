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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Admissao;
import model.Atendimento;
import model.Paciente;
import model.Setor;
import model.Usuario;
import model.dao.AdmissaoDao;
import model.dao.AtendimentoDao;
import service.AdmissaoService;
import service.AtendimentoService;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AdmissaoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private AtendimentoService service = new AtendimentoService();
    private AdmissaoService admissaoService = new AdmissaoService();

    private Admissao admissao = new Admissao();
    private Setor destino = new Setor();

    private Atendimento atendimento;
    private Atendimento atendimentoEditado;

    @PostConstruct
    private void init() {

//        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//
//        if (chave != null) {
//            Long id = Long.parseLong(chave);
//            atendimento = service.buscarPorId(id);
//        }
    }

    public void selecionar() {
        this.admissao.setAtendimento(atendimento);
        this.atendimentoEditado = atendimento;
        this.atendimento = null;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Admissao getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Admissao admissao) {
        this.admissao = admissao;
    }

    public Setor getDestino() {
        return destino;
    }

    public void setDestino(Setor destino) {
        this.destino = destino;
    }
    
    

    public void admitir() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
//        Usuario usuario = (Usuario) httpSession.getAttribute("currentUser");
//
//        admissao.setUsuarioAdmissao(usuario);
//        admissao.setSetorOrigem(atendimentoEditado.getSetor());
//        
//        atendimentoEditado.setSetor(admissao.getSetorDestino());
//        try {
//            admissaoService.admitir(admissao);
//            atendimentoEditado = service.buscarPorId(atendimentoEditado.getId());
//            service.salvar(atendimentoEditado);
//            JsfUtil.addMessage("Admissão feita com sucesso!");
//        } catch (DBException e) {
//            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
//            e.printStackTrace();
//        } catch (NegocioException negocio) {
//            JsfUtil.addErrorMessage(negocio.getMessage());
//        }
    }

    public List<Atendimento> completeAtendimento(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Atendimento> atendimentosFiltrados = service.getAtendimentosEmAndamento();
        return atendimentosFiltrados.stream().filter(a -> a.getPaciente().getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }
    public void novo() {
        admissao = new Admissao();
    }

}
