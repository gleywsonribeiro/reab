/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import jpa.util.HibernateUtil;
import model.Motivo;
import model.MotivoAlta;

import javax.persistence.EntityManager;

/**
 *
 * @author gleyw
 */
public class MotivoAltaDao extends Dao<MotivoAlta>{

    EntityManager em = HibernateUtil.getEntityManager();

    public MotivoAltaDao() {
        super(MotivoAlta.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
