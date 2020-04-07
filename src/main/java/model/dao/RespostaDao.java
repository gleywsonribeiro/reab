/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Resposta;

/**
 *
 * @author Gleywson
 */
public class RespostaDao extends Dao<Resposta> {

    EntityManager em = HibernateUtil.getEntityManager();

    public RespostaDao() {
        super(Resposta.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
