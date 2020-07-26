/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import jpa.util.HibernateUtil;
import model.Atendimento;
import model.Avaliacao;
import model.Usuario;
import util.Seguranca;

/**
 *
 * @author Gleywson
 */
public class AvaliacaoDao extends Dao<Avaliacao> {

    EntityManager em = HibernateUtil.getEntityManager();

    public AvaliacaoDao() {
        super(Avaliacao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Avaliacao getUltimaAvaliacaoAtendimento(Atendimento atendimento) {
        List<Avaliacao> avaliacoes = em.createQuery("SELECT A FROM Avaliacao AS A WHERE A.atendimento = :atendimento")
                .setParameter("atendimento", atendimento).getResultList();
        return avaliacoes.stream().max(Comparator.comparing(Avaliacao::getId)).orElseThrow(NoSuchElementException::new );
    }
}
