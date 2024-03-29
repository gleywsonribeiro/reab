/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Hospital;
import model.Leito;
import model.LeitoSexo;
import model.Setor;
import model.Usuario;
import service.LeitoService;
import service.SetorService;
import service.Sessao;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class LeitoController implements Serializable {

    private static final long serialVersionUID = 1L;

    SetorService setorService = new SetorService();
    private List<Setor> setores;
    private Setor setor;

    private List<Leito> leitos = new ArrayList<>();
    private Leito leito = new Leito();
    private LeitoService leitoService = new LeitoService();

    @PostConstruct
    private void init() {

        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            setor = setorService.buscarPorId(id);
            leitos = leitoService.listarPorSetor(setor);
        }

    }

    public void salvar() {
        try {
            leito.setSetor(setor);
            leitoService.salvar(leito);
            leito = new Leito();
            leitos = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (Exception e) {
            //JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Setor> getSetores() {
        if (setores == null) {
            Usuario usuario = Sessao.getUsuarioSessao();
            setores = setorService.getSetoresPorHospital(usuario.getHospitalLogado());
        }
        return setores;
    }
    
    

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    public List<Leito> getLeitos() {
        if (leitos == null) {
            leitos = leitoService.listarPorSetor(setor);
        }
        return leitos;
    }

    public LeitoSexo[] getSexos() {
        return LeitoSexo.values();
    }
    
    public List<Leito> getLeitosVagos() {
        Hospital hospital = Sessao.getUsuarioSessao().getHospitalLogado();
        return leitoService.getLeitosVagos(hospital);
    }

}
