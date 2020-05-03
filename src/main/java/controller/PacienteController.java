/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Hospital;
import model.Paciente;
import model.Perfil;
import model.Usuario;
import model.dao.HospitalDao;
import model.dao.PacienteDao;
import model.dao.UsuarioDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class PacienteController implements Serializable {

    private static final long serialVersionUID = 1L;
    PacienteDao dao = new PacienteDao();
    private Paciente paciente;


    @PostConstruct
    private void init() {
      paciente = new Paciente();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    public void salvar() {
        try {
            if (paciente.getId() == null) {
                dao.create(paciente);
            } else {
                dao.edit(paciente);
            }
          
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

//    public List<Hospital> completeHospital(String query) {
//        String queryLowerCase = query.toLowerCase();
//        List<Hospital> hospitaisFiltrados = getHospitais();
//        return hospitaisFiltrados.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
//    }

//    public void remover() {
//        try {
//            dao.remove(hospital);
//            hospitais = null;
//            JsfUtil.addMessage(hospital.getNome() + " removido com sucesso!");
//        } catch (DBException e) {
//            JsfUtil.addErrorMessage("Erro ao remover " + hospital.getNome() + ": " + e.getMessage());
//        }
//    }

}
