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
import model.Avaliacao;
import model.Paciente;
import model.Setor;
import model.Triagem;
import model.Usuario;
import model.dao.AdmissaoDao;
import model.dao.AtendimentoDao;
import model.dao.TriagemDao;
import model.service.AdmissaoService;
import model.service.AtendimentoService;
import model.service.TriagemService;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class AvaliacaoController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Triagem triagem;
    
    private Avaliacao avaliacao = new Avaliacao();
    
    private TriagemService triagemService = new TriagemService(new TriagemDao());
    

    @PostConstruct
    private void init() {

        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimento = triagemService.buscarPorId(id);
        }
    
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Atendimento> completeAtendimento(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Atendimento> atendimentosFiltrados = atendimentoService.getAtendimentosLiberados();
        return atendimentosFiltrados.stream().filter(a -> a.getPaciente().getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

}
