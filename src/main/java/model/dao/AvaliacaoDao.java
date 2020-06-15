/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import jpa.util.HibernateUtil;
import model.Admissao;
import model.Atendimento;
import model.Avaliacao;
import model.Triagem;
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
    
    
}
