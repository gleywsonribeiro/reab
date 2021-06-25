/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Avaliacao;
import model.dao.AvaliacaoDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AvaliacaoService {

    private AvaliacaoDao dao = new AvaliacaoDao();

    

    public Avaliacao buscarPorId(Long id) {
        return this.dao.find(id);
    }

    public void salvar(Avaliacao aval) {
        try {
            dao.create(aval);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException("Erro ao realizar avaliação: " + e.getMessage());
            
        }
    }
}
