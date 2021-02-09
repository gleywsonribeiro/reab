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
    
    public List<Setor> getSetoresPorHospital(Hospital h) {
        Query query = em.createQuery("SELECT s FROM Setor as s where s.hospital = :h", Setor.class);
        return query.getResultList();
    }

}
