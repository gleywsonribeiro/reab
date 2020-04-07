/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpa.util.HibernateUtil;
import model.Pesquisa;

/**
 *
 * @author Gleywson
 */
public class PesquisaDao extends Dao<Pesquisa>{

    EntityManager em = HibernateUtil.getEntityManager();
    
    public PesquisaDao() {
        super(Pesquisa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Pesquisa> findAllActive() {
        Query query = getEntityManager().createQuery("SELECT P FROM Pesquisa AS P WHERE P.ativo = :ativo").setParameter("ativo", true);
        return query.getResultList();
    }
    
}
