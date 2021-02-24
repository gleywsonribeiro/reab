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
import model.Paciente;
import model.dao.PacienteDao;
import model.service.PacienteService;
import org.hibernate.exception.ConstraintViolationException;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class PacienteController implements Serializable {

    private static final long serialVersionUID = 1L;
    private PacienteService service = new PacienteService();
    private Paciente paciente = new Paciente();

    private List<Paciente> pacientes = new ArrayList<>();

    @PostConstruct
    private void init() {
        pacientes = service.listarTodos();
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            paciente = service.buscarPorId(id);
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
            service.salvar(paciente);
            pacientes = null;
            JsfUtil.addMessage("Salvo com sucesso!");
        }  catch (DBException e) {
            JsfUtil.addErrorMessage("Já existe paciente com essa matrícula: " + e.getMessage());
        } catch (NegocioException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }

    }

    public List<Paciente> completePaciente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Paciente> pacientesFiltrados = service.listarTodos();
        return pacientesFiltrados.stream().filter(t -> t.getNome().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void novo() {
        paciente = new Paciente();
    }

    public void remover() {
        try {
            service.remover(paciente);
            pacientes = null;
            JsfUtil.addMessage(paciente.getNome() + " apagado com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + paciente.getNome() + ": " + e.getMessage());
        }
    }

    public List<Paciente> getPacientes() {
        if (pacientes == null) {
            pacientes = service.listarTodos();
        }
        return pacientes;
    }

    public String admitir() {
        StringBuilder builder = new StringBuilder();
        builder.append("atendimento?id=");
        builder.append(paciente.getId());
        builder.append("faces-redirect=true");
        return builder.toString();
    }

}
