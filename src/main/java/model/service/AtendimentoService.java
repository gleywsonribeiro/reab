/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import model.Atendimento;
import model.LeitoSexo;
import model.dao.AtendimentoDao;
import util.exception.DBException;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AtendimentoService implements Serializable {

    private AtendimentoDao dao = new AtendimentoDao();

    public void salvar(Atendimento atendimento) {
        LeitoSexo sexo = atendimento.getLeito().getSexo();
        if(!sexo.getDescricao().equals(atendimento.getPaciente().getSexo().getDescricao()) && sexo != LeitoSexo.AMBOS) {
            throw new NegocioException("Leito incompatível com o paciente!");
        }
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

    public List<Atendimento> getAtendimentosLiberados() {
        List<Atendimento> teste = dao.getAtendimentosEmAndamento().stream().filter(a -> a.isLiberadoMobilizacao() == true).collect(Collectors.toList());
        System.out.println(teste);
        return teste;
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
