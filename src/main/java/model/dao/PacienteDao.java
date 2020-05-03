/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Hospital;
import model.Paciente;

/**
 *
 * @author gleyw
 */
public class PacienteDao extends Dao<Paciente>{

    EntityManager em = HibernateUtil.getEntityManager();

    public PacienteDao() {
        super(Paciente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
