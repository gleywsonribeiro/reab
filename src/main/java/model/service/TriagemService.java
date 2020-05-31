/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.Triagem;
import model.dao.TriagemDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class TriagemService {
    private TriagemDao dao;

    public TriagemService(TriagemDao dao) {
        this.dao = dao;
    }
    
    
    public void salvar(Triagem triagem) {
        try {
            dao.create(triagem);
        } catch (Exception e) {
            throw new NegocioException("Erro ao realizar triagem");
        }
    }
}
