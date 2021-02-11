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
import model.Setor;
import model.dao.HospitalDao;
import model.dao.SetorDao;
import model.service.SetorService;
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

    private Hospital hospital = new Hospital();
    private List<Hospital> hospitais;
    private HospitalDao dao = new HospitalDao();

    private Setor setor = new Setor();
    private List<Setor> setores;
    SetorService setorService = new SetorService();
    

    @PostConstruct
    private void init() {
        hospitais = dao.findAll();
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            hospital = dao.find(id);
            setores = setorService.getSetoresPorHospital(hospital);
        }

    }
    
    public void novo() {
        hospital = new Hospital();
        setores = new ArrayList<>();
    }

    public void removeSetor() {
        setorService.remover(setor);
        setores = setorService.getSetoresPorHospital(hospital);
        setor = new Setor();
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

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }
    
    

    public void salvar() {
        try {
            if (hospital.getId() == null) {
                dao.create(hospital);
            } else {
                dao.edit(hospital);
            }
            hospitais = null;
            setor = new Setor();
            JsfUtil.addMessage("Salvo com sucesso!");
        } catch (DBException e) {
            JsfUtil.addErrorMessage("Erro ao salvar: " + e.getMessage());
        }
    }

    public void inserirSetor() {
       setor.setHospital(hospital);

       setorService.salvar(setor);
       setor = new Setor();
       setores = setorService.getSetoresPorHospital(hospital);
       
    }
    
    public void editarSetor() {
        setorService.salvar(setor);
    }
    
    
    public boolean isCadastrado() {
        return hospital.getId() != null;
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
