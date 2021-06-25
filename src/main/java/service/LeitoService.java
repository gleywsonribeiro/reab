/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import model.Atendimento;
import model.Hospital;
import model.Leito;
import model.Setor;
import model.dao.AtendimentoDao;
import model.dao.LeitoDao;
import util.exception.DBException;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class LeitoService implements Serializable {

    LeitoDao dao = new LeitoDao();
    
    public List<Leito> listar() {
        return dao.findAll();
    }
    
    public List<Leito> listarPorSetor(Setor setor) {
        return dao.getLeitosPorSetor(setor);
    }
    
    public void salvar(Leito leito) {
        if(leito.getId() == null) {
            dao.create(leito);
        } else {
            dao.edit(leito);
        }
        
    }
    
    public List<Leito> getLeitosVagos(Hospital hospital) {
        return dao.getLeitosVagos(hospital);
    }
    
}
