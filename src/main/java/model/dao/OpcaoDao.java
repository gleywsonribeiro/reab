/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Opcao;

/**
 *
 * @author Gleywson
 */
public class OpcaoDao extends Dao<Opcao> {

    EntityManager em = HibernateUtil.getEntityManager();

    public OpcaoDao() {
        super(Opcao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
