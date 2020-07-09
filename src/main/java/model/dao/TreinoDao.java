/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Hospital;
import model.Treino;

/**
 *
 * @author gleyw
 */
public class TreinoDao extends Dao<Treino>{

    EntityManager em = HibernateUtil.getEntityManager();

    public TreinoDao() {
        super(Treino.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
