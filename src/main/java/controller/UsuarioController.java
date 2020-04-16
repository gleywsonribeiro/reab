/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Perfil;
import model.Usuario;
import model.dao.UsuarioDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer activeIndex = 0;

    private Usuario usuario;

    private List<Usuario> usuarios;
    private UsuarioDao dao = new UsuarioDao();



    @PostConstruct
    private void init() {
        usuario = new Usuario();
        usuarios = dao.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = dao.findAll();
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

    public void salvar() {
        try {
            if (usuario.getId() == null) {
                usuario.setSenha(usuario.getLogin());
                dao.create(usuario);
                activeIndex = 1;
                limpar();
                usuarios = null;
                JsfUtil.addMessage("Salvo com sucesso!");
            } else {
                dao.edit(usuario);
                activeIndex = 1;
                limpar();
                usuarios = null;
                JsfUtil.addMessage("Alterado com sucesso!");
            }
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
//            JsfUtil.addFatalMessage(e.getCause().getMessage());
        }
    }

    public void remover() {
        dao.remove(usuario);
        limpar();
        usuarios = null;
        activeIndex = 1;
        JsfUtil.addMessage("Usuário removido com sucesso!");
    }

    public void editar() {
        activeIndex = 0;
    }

    public void resete() {
        usuario.setSenha(usuario.getLogin().toLowerCase());
        dao.resetPassword(usuario);
        usuario = new Usuario();
        activeIndex = 1;
        JsfUtil.addMessage("Senha do usuário alterada com sucesso!");
    }

    public void limpar() {
        this.usuario = new Usuario();
    }

}
