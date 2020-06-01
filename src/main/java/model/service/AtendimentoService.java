/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.Atendimento;
import model.dao.AtendimentoDao;
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
            throw new NegocioException("Paciente já está em atendimento");
        }
        if (atendimento.getId() == null) {
            dao.create(atendimento);
        } else {
            dao.edit(atendimento);
        }
    }
}
