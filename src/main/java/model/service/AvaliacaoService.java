/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.Admissao;
import model.Triagem;
import model.dao.AdmissaoDao;
import model.dao.AvaliacaoDao;
import model.dao.TriagemDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AvaliacaoService {

    private AvaliacaoDao dao;

    public AvaliacaoService(AvaliacaoDao dao) {
        this.dao = dao;
    }

    
}
