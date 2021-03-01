/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpa.util.HibernateUtil;
import model.Usuario;
import util.Seguranca;

/**
 *
 * @author Gleywson
 */
public class UsuarioDao extends Dao<Usuario> {

    EntityManager em = HibernateUtil.getEntityManager();

    public UsuarioDao() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void resetPassword(Usuario usuario) {
        usuario.setSenha(Seguranca.criptografe(usuario.getSenha()));
        super.edit(usuario);
    }

    /**
     *
     * @param usuario
     */
    @Override
    public void create(Usuario usuario) {
        usuario.setSenha(Seguranca.criptografe(usuario.getSenha()));
        super.create(usuario);
    }

    public Usuario getUsuarioByLoginSenha(String nomeUsuario, String senha) {

        try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                            "SELECT u from Usuario u where u.login = :name and u.senha = :senha")
                    .setParameter("name", nomeUsuario)
                    .setParameter("senha", Seguranca.criptografe(senha)).getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario getUsuarioPorLogin(String login) {
        try {

            Query query = em.createQuery("SELECT u FROM Usuario AS u WHERE u.login = :login", Usuario.class);
            query.setParameter("login", login);
            return (Usuario) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

}
