/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Atendimento;
import model.Opcao;

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
@ViewScoped
public class TriagemController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Triagem triagem = new Triagem();

    private List<Triagem> triagens;
    private final TriagemService service = new TriagemService(new TriagemDao());
    private AtendimentoService as = new AtendimentoService(new AtendimentoDao());

    public String salvar() {
        try {
            Atendimento temp = as.buscarPorId(triagem.getAtendimento().getId());
            boolean liberado = validar();
            temp.setLiberadoMobilizacao(liberado);
            triagem.setLiberadoMobilizacao(liberado);

            service.salvar(triagem);
            as.salvar(temp);
            triagem = new Triagem();

            return liberado ? "aprovado?faces-redirect=true" : "reprovado?faces-redirect=true";
        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
            return "";
        }
    }

    public Opcao[] getOpcoes() {
        return Opcao.values();
    }

    public boolean validar() {
        Class<?> classeTriagem = triagem.getClass();
        Field[] campos = classeTriagem.getDeclaredFields();

        String nomeAtributo = "";
        Object valorAtributo = null;
        for (Field campo : campos) {
            try {
                nomeAtributo = campo.getName();
                campo.setAccessible(true); //Necessário por conta do encapsulamento das variáveis (private)
                valorAtributo = campo.get(triagem);
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
