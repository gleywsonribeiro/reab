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
import model.Entrevista;
import model.Pergunta;
import model.Pesquisa;

/**
 *
 * @author Gleywson
 */
public class EntrevistaDao extends Dao<Entrevista>{

    EntityManager em = HibernateUtil.getEntityManager();
    
    public EntrevistaDao() {
        super(Entrevista.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Entrevista> getEntrevistasPorPesquisa(Pesquisa pesquisa) {
        Query query = em.createQuery("SELECT e FROM Entrevista AS e WHERE e.pesquisa = :pesquisa", Entrevista.class);
        query.setParameter("pesquisa", pesquisa);
        return query.getResultList();
    }
    
}
