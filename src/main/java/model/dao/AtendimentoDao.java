/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpa.util.HibernateUtil;
import model.Atendimento;
import model.Hospital;
import model.Paciente;
import model.Setor;

/**
 *
 * @author gleyw
 */
public class AtendimentoDao extends Dao<Atendimento> {

    EntityManager em = HibernateUtil.getEntityManager();

    public AtendimentoDao() {
        super(Atendimento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public boolean isPacienteEmAtendimento(Paciente paciente) {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.dataAlta IS NULL and a.paciente = :paciente", Atendimento.class);
        try {
            Atendimento atendimento = (Atendimento) query.setParameter("paciente", paciente).getSingleResult();
            return atendimento != null;
        } catch (NoResultException nr) {
            return false;
        }
    }

    public Long getPacientesInternados(Hospital hospital) {
        Query query = em.createQuery("SELECT COUNT(A) FROM Atendimento AS A WHERE A.dataAlta IS NULL AND A.paciente.hospital =:hospital");
        query.setParameter("hospital", hospital);
        return (Long) query.getSingleResult();
    }

    public List<Atendimento> getAtendimentosEmAndamento() {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.dataAlta IS NULL", Atendimento.class);
        return query.getResultList();
    }

    public List<Atendimento> getAtendimentosPorUnidade(Setor setor) {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.leito.setor = :setor and a.dataAlta is null order by a.leito.id asc", Atendimento.class);
        query.setParameter("setor", setor);
        return query.getResultList();
    }

    public List<Atendimento> getAtendimentos(Setor setor) {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.leito.setor = :setor", Atendimento.class);
        query.setParameter("setor", setor);
        return query.getResultList();
    }

    public List<Atendimento> listarPorHospital(Hospital hospital) {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.paciente.hospital = :hospital", Atendimento.class);
        query.setParameter("hospital", hospital);
        return query.getResultList();
    }

    public List<Atendimento> getPacientesExtubados(Setor setor) {
        Query query = em.createQuery("SELECT a FROM Atendimento as a where a.leito.setor = :setor and a.dataExtubacao is not null and a.sucessoExtubacao is not null", Atendimento.class);
        query.setParameter("setor", setor);
        return query.getResultList();
    }

}
