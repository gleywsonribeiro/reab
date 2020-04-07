/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author gleyw
 */
public class Autorizacao implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String currentPage = fc.getViewRoot().getViewId();

        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        Usuario currentUser = (Usuario) session.getAttribute("currentUser");

        if (!isLoginPage && currentUser == null) {
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "loginPage");
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
