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
import model.Avaliacao;
import model.Usuario;
import util.Seguranca;

/**
 *
 * @author Gleywson
 */
public class AdmissaoDao extends Dao<Admissao> {

    EntityManager em = HibernateUtil.getEntityManager();

    public AdmissaoDao() {
        super(Admissao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
