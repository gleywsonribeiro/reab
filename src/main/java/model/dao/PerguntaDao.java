/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Pergunta;

/**
 *
 * @author Gleywson
 */
public class PerguntaDao extends Dao<Pergunta>{

    EntityManager em = HibernateUtil.getEntityManager();
    
    public PerguntaDao() {
        super(Pergunta.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
