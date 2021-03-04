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
import model.Atendimento;
import model.Hospital;
import model.Paciente;

/**
 *
 * @author gleyw
 */
public class PacienteDao extends Dao<Paciente> {

    EntityManager em = HibernateUtil.getEntityManager();

    public PacienteDao() {
        super(Paciente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Paciente> listarPorHospital(Hospital hospital) {
        Query query = em.createQuery("SELECT p FROM Paciente as p where p.hospital = :hospital", Paciente.class);
        query.setParameter("hospital", hospital);
        return query.getResultList();
    }

}
