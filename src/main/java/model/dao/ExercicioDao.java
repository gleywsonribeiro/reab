/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import jpa.util.HibernateUtil;
import model.Exercicio;
import model.Hospital;

/**
 *
 * @author gleyw
 */
public class ExercicioDao extends Dao<Exercicio>{

    EntityManager em = HibernateUtil.getEntityManager();

    public ExercicioDao() {
        super(Exercicio.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
