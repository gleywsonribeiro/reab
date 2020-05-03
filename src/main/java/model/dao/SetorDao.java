/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Hospital;
import model.Setor;

/**
 *
 * @author gleyw
 */
public class SetorDao extends Dao<Setor>{

    EntityManager em = HibernateUtil.getEntityManager();

    public SetorDao() {
        super(Setor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
