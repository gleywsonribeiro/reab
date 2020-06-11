/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import model.Atendimento;
import model.dao.AtendimentoDao;
import util.exception.DBException;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AtendimentoService {

    private AtendimentoDao dao;

    public AtendimentoService(AtendimentoDao dao) {
        this.dao = dao;
    }

    public void salvar(Atendimento atendimento) {
        if (dao.isPacienteEmAtendimento(atendimento.getPaciente())) {
            if (atendimento.getId() != null) {
                dao.edit(atendimento);
            } else {
                throw new NegocioException("Paciente já está em atendimento");
            }

        }
        dao.create(atendimento);
    }

    public List<Atendimento> listarTodos() {
        return dao.findAll();
    }
    
    public List<Atendimento> getAtendimentosEmAndamento() {
        return dao.getAtendimentosEmAndamento();
    }
    
    public Long getPacientesInternados() {
        return dao.getPacientesInternados();
    }

    public Atendimento buscarPorId(Long id) {
        return dao.find(id);
    }

    public void remover(Atendimento atendimento) {
        try {
            dao.remove(atendimento);
        } catch (Exception e) {
            throw new DBException("Não foi possível remover:" + e.getMessage());
        }
    }
}
