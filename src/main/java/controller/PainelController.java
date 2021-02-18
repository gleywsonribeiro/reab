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
import model.Atendimento;
import model.Ocupacao;
import model.Paciente;
import model.Setor;
import model.dao.PacienteDao;
import model.service.AtendimentoService;
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
public class PainelController implements Serializable {

    private static final long serialVersionUID = 1L;
    private SetorService setorService = new SetorService();
    private AtendimentoService atendimentoService = new AtendimentoService();
    private List<Atendimento> atendimentos = new ArrayList<>();
    private Setor unidade = new Setor();

    @PostConstruct
    private void init() {
        
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            unidade = setorService.buscarPorId(id);
        }

    }

    public Setor getUnidade() {
        return unidade;
    }

    public void setUnidade(Setor unidade) {
        this.unidade = unidade;
    }

    public List<Atendimento> getAtendimentos() {
        atendimentos = atendimentoService.getAtendimentosPorUnidade(unidade);
        return atendimentos;
    }

}
