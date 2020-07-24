/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;

import jpa.util.HibernateUtil;
import model.Treinamento;

/**
 *
 * @author Gleywson
 */
public class TreinamentoDao extends Dao<Treinamento> {

    EntityManager em = HibernateUtil.getEntityManager();

    public TreinamentoDao() {
        super(Treinamento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
