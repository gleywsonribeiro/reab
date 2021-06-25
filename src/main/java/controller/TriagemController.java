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
import service.AtendimentoService;
import service.AvaliacaoService;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class TriagemController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Avaliacao avaliacao = new Avaliacao();

    private List<Avaliacao> avaliacoes;
    private final AvaliacaoService service = new AvaliacaoService();
    private AtendimentoService as = new AtendimentoService();

    public String salvar() {
        try {
            Atendimento temp = as.buscarPorId(avaliacao.getAtendimento().getId());
            boolean liberado = validar();
            temp.setLiberadoMobilizacao(liberado);
            avaliacao.setLiberadoMobilizacao(liberado);

            service.salvar(avaliacao);
            as.salvar(temp);
            
            String id = avaliacao.getId().toString();
            avaliacao = new Avaliacao();

            return liberado ? "cadastro?faces-redirect=true&id=" + id : "reprovado?faces-redirect=true"; //"/avaliacao/cadastro?faces-redirect=true&id=" + triagem.getId()

        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
            return "";
        }
    }

    public Opcao[] getOpcoes() {
        return Opcao.values();
    }

    public boolean validar() {
        Class<?> classeAvaliacao = avaliacao.getClass();
        Field[] campos = classeAvaliacao.getDeclaredFields();

        String nomeAtributo = "";
        Object valorAtributo = null;
        for (Field campo : campos) {
            try {
                nomeAtributo = campo.getName();
                campo.setAccessible(true); //Necessário por conta do encapsulamento das variáveis (private)
                valorAtributo = campo.get(avaliacao);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(nomeAtributo + ": " + valorAtributo);
            if (valorAtributo != null && valorAtributo.equals(Opcao.NAO)) {
                return false;
            }
        }
        return true;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

}
