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
import model.Leito;
import model.Motivo;
import model.Ocupacao;
import model.Setor;

/**
 *
 * @author gleyw
 */
public class LeitoDao extends Dao<Leito>{

    EntityManager em = HibernateUtil.getEntityManager();

    public LeitoDao() {
        super(Leito.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Leito> getLeitosPorSetor(Setor setor) {
        Query query = em.createQuery("SELECT l FROM Leito as l where l.setor =: setor", Leito.class);
        query.setParameter("setor", setor);
        return query.getResultList();
    }
    
    public List<Leito> getLeitosVagos() {
        Query query = em.createQuery("SELECT l FROM Leito as l where l.ocupacao =: ocupacao", Leito.class);
        query.setParameter("ocupacao", Ocupacao.VAGO);
        return query.getResultList();
    }

}
