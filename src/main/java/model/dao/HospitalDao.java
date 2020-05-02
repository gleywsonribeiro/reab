/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Hospital;

/**
 *
 * @author gleyw
 */
public class HospitalDao extends Dao<Hospital>{

    EntityManager em = HibernateUtil.getEntityManager();

    public HospitalDao() {
        super(Hospital.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
