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
import model.Hospital;
import model.Perfil;
import model.Setor;
import model.Usuario;
import model.dao.HospitalDao;
import model.dao.SetorDao;
import model.dao.UsuarioDao;
import util.exception.DBException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class HospitalController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Hospital hospital;
    private List<Hospital> hospitais;
    private HospitalDao dao = new HospitalDao();
    
    private SetorDao setorDao = new SetorDao();
    private List<Setor> setores = new ArrayList<>();
    private Setor setor = new Setor();

    @PostConstruct
    private void init() {
        hospital = new Hospital();
        hospitais = dao.findAll();
        
        setores = setorDao.getSetoresPorHospital(hospital);
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Hospital> getHospitais() {
        if (hospitais == null) {
            hospitais = dao.findAll();
        }
        return hospitais;
    }

    public void setHospitais(List<Hospital> hospitais) {
        this.hospitais = hospitais;
    }

    public void salvar() {
        try {
            if (hospital.getId() == null) {
                dao.create(hospital);
            } else {
                dao.edit(hospital);
            }
            hospitais = null;
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Hospital> completeHospital(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Hospital> hospitaisFiltrados = getHospitais();
        return hospitaisFiltrados.stream().filter(t -> t.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void remover() {
        try {
            dao.remove(hospital);
            hospitais = null;
            JsfUtil.addMessage(hospital.getNome() + " removido com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao remover " + hospital.getNome() + ": " + e.getMessage());
        }
    }

}
