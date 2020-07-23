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
import model.Score;
import model.Usuario;
import util.Seguranca;

/**
 *
 * @author Gleywson
 */
public class ResultadoDao extends Dao<Score> {

    EntityManager em = HibernateUtil.getEntityManager();

    public ResultadoDao() {
        super(Score.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}
