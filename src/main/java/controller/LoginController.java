/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Hospital;
import model.Usuario;
import model.dao.HospitalDao;
import model.dao.UsuarioDao;
import service.Sessao;
import util.jsf.JsfUtil;

/**
 *
 * @author Gleywson
 */
@ManagedBean
@SessionScoped ////@ViewScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private String novaSenha;

    private UsuarioDao dao = new UsuarioDao();

    //hospital da sessao
    private Long hospitalID;

    private HospitalDao hospitalDao = new HospitalDao();
    private List<Hospital> hospitais = new ArrayList<>();

    public LoginController() {
        this.usuario = new Usuario();
    }

    public void buscarUsuario() {
        Usuario user = dao.getUsuarioPorLogin(usuario.getLogin().toLowerCase());
        if (user == null) {
            JsfUtil.addErrorMessage("Usuário não encontrado!");
            hospitais.clear();
        } else {
            hospitais.addAll(user.getHospitais());
        }
    }

    public String login() {
        Usuario usuarioLogado = dao.getUsuarioByLoginSenha(usuario.getLogin().toLowerCase(), usuario.getSenha());

        if (usuarioLogado == null) {
            //usuario = new Usuario();
            JsfUtil.addErrorMessage("Senha inválida!");
            return "";
        } else if (usuario.getLogin().toLowerCase().equals(usuario.getSenha())) {
            addNaSessao(usuarioLogado);
            return "/usuario/senha?faces-redirect=true";
        } else {
            addNaSessao(usuarioLogado);
            return "dashboard?faces-redirect=true";
        }

    }

    public void logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
        httpSession.setAttribute("currentUser", null);
        try {
            fc.getExternalContext().redirect(fc.getExternalContext().getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        httpSession.invalidate();
        JsfUtil.redirect("/index.xhtml");
    }

    public void alteraSenha() {
        Usuario user = dao.find(usuario.getId());
        user.setSenha(novaSenha);
        dao.resetPassword(user);
        JsfUtil.redirect("/dashboard.xhtml");
//        return "dashboard?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public Long getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Long hospitalID) {
        this.hospitalID = hospitalID;
    }

    public List<Hospital> getHospitais() {
        return hospitais;
    }

    private void addNaSessao(Usuario usuarioLogado) {
        usuario = usuarioLogado;
        usuario.setHospitalLogado(hospitalDao.find(hospitalID));
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
        httpSession.setAttribute("currentUser", usuario);
    }

}
