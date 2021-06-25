/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Atendimento;
import model.Hospital;
import model.Paciente;
import model.dao.AtendimentoDao;
import model.dao.PacienteDao;
import util.exception.DBException;
import util.exception.NegocioException;


/**
 *
 * @author gleyw
 */
public class PacienteService {

    private PacienteDao dao = new PacienteDao();

    public void salvar(Paciente paciente) {
        
        if (paciente.getId() == null) {
            dao.create(paciente);
        } else {
            dao.edit(paciente);
        }
        
    }
    
    public List<Paciente> listarTodos() {
        return dao.findAll();
    }
    
    public List<Paciente> listarPorHospital(Hospital hospital) {
        return dao.listarPorHospital(hospital);
    }
    
    public Paciente buscarPorId(Long id) {
        return dao.find(id);
    }
    
    public void remover(Paciente paciente) {
        try {
            dao.remove(paciente);
        } catch (Exception e) {
            throw new DBException("Não foi possível remover:" + e.getMessage());
        }
    }
   
}
