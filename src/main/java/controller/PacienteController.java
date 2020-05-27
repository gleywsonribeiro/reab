/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private PacienteDao dao = new PacienteDao();
    private Paciente paciente = new Paciente();

    private List<Paciente> pacientes = new ArrayList<>();

    @PostConstruct
    private void init() {
        pacientes = dao.findAll();
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            paciente = dao.find(id);
        }

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
            pacientes = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Paciente> completePaciente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Paciente> pacientesFiltrados = dao.findAll();
        return pacientesFiltrados.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void novo() {
        paciente = new Paciente();
    }

    public void remover() {
        try {
            dao.remove(paciente);
            pacientes = null;
            JsfUtil.addMessage(paciente.getNome() + " apagado com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + paciente.getNome() + ": " + e.getMessage());
        }
    }

    public List<Paciente> getPacientes() {
        if (pacientes == null) {
            pacientes = dao.findAll();
        }
        return pacientes;
    }

}
