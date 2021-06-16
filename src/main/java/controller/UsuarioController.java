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

import model.Hospital;
import model.Perfil;
import model.Usuario;
import model.dao.HospitalDao;
import model.dao.UsuarioDao;
import model.service.UsuarioService;
import util.Seguranca;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer activeIndex = 0;

    private Usuario usuario = new Usuario();

    private List<Usuario> usuarios;
    private UsuarioService usuarioService = new UsuarioService();

    private List<Hospital> hospitais;
    private List<Hospital> hospitaisSelecionados = new ArrayList<>();
    private final HospitalDao hospitalDao = new HospitalDao();

    @PostConstruct
    private void init() {
        usuarios = usuarioService.listarTodos();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = usuarioService.listarTodos();
        }
        return usuarios;
    }

    public Perfil[] getPerfis() {
        return Perfil.values();
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<Hospital> getHospitais() {
        if (hospitais == null) {
            hospitais = hospitalDao.findAll();
        }
        return hospitais;
    }

    public List<Hospital> getHospitaisSelecionados() {
        return hospitaisSelecionados;
    }

    public void setHospitaisSelecionados(List<Hospital> hospitaisSelecionados) {
        this.hospitaisSelecionados = hospitaisSelecionados;
    }


    public void setHospitais(List<Hospital> hospitais) {
        this.hospitais = hospitais;
    }

    public void salvar() {
        try {
            if (usuario.getId() == null) {
                usuario.setSenha(Seguranca.criptografe(usuario.getLogin()));
                usuario.getHospitais().addAll(hospitaisSelecionados);
                usuarioService.salvar(usuario);
            } else {
                usuario.getHospitais().clear();
                usuario.getHospitais().addAll(hospitaisSelecionados);
            }
            activeIndex = 1;
            limpar();
            usuarios = null;

            JsfUtil.addMessage("Salvo com sucesso!");

        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
//            JsfUtil.addFatalMessage(e.getCause().getMessage());
        }
    }

    public void remover() {
        usuarioService.remover(usuario);
        limpar();
        usuarios = null;
        activeIndex = 1;
        JsfUtil.addMessage("Usuário removido com sucesso!");
    }

    public void editar() {
        usuario.getHospitais().forEach(h -> hospitaisSelecionados.add(h));
        activeIndex = 0;
    }

    public void resete() {
        usuario.setSenha(usuario.getLogin().toLowerCase());
        usuarioService.resetPassword(usuario);
        usuario = new Usuario();
        activeIndex = 1;
        JsfUtil.addMessage("Senha do usuário alterada com sucesso!");
    }

    public void limpar() {
        this.usuario = new Usuario();
    }

}
