/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import model.Avaliacao;
import model.Treino;
import model.dao.AvaliacaoDao;
import model.dao.TreinoDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class TreinoService {

    private TreinoDao dao = new TreinoDao();

    public void salvar(Treino treino) {
        try {
            if(treino.getId() == null) {
                dao.create(treino);
            } else {
                dao.edit(treino);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException("Erro ao salvar!");
            
        }
    }
    
    public List<Treino> ListarTreinos() {
        return dao.findAll();
    }

    public Treino buscarPorId(Long id) {
        return dao.find(id);
    }

    public void remover(Treino treino) {
        dao.remove(treino);
    }
    
}
