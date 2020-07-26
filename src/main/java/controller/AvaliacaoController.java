/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Atendimento;
import model.Opcao;

import model.Avaliacao;
import model.ItemExercicio;
import model.ItemTreinamento;
import model.Score;
import model.Treinamento;
import model.Treino;
import model.dao.TreinamentoDao;
import model.service.AtendimentoService;
import model.service.AvaliacaoService;
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

    private Avaliacao avaliacao = new Avaliacao();

    private final AvaliacaoService service = new AvaliacaoService();
    private TreinamentoDao treinamentoDao= new TreinamentoDao();

    @PostConstruct
    private void init() {

        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            avaliacao = service.buscarPorId(id);
        }

    }

    public String salvar() {
        try {
            Treinamento treinamento = new Treinamento();
            Treino treino = avaliacao.getScore().getTreino();
            treinamento.setTreino(treino);
            treino.getExercicios().forEach((item) -> {
                ItemTreinamento itemTreinamento = new ItemTreinamento();
                itemTreinamento.setItemExercicio(item);
                itemTreinamento.setPrincipal(item.getPrincipal());
                
                treinamento.getItemTreinamentos().add(itemTreinamento);
                itemTreinamento.setTreinamento(treinamento);
                
            });

            treinamentoDao.create(treinamento);

            avaliacao.setTreinamento(treinamento);
            service.salvar(avaliacao);

            return "sucesso?faces-redirect=true";

        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
            return "";
        }
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

}
