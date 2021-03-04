/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author gleyw
 */
public class Sessao {

    public static Usuario getUsuarioSessao() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(false);
        Usuario usuario = (Usuario) httpSession.getAttribute("currentUser");
        return usuario;
    }
}
