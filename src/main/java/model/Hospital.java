/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author gleyw
 */
@Entity
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<Setor> setores;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioHospital> usuarioHospitals = new ArrayList<>();

    public Hospital() {
        this.setores = new ArrayList<>();
    }

    public Hospital(Long id, String nome, String cnpj) {
        this.setores = new ArrayList<>();
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<UsuarioHospital> getUsuarioHospitals() {
        return usuarioHospitals;
    }

   
    
    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public int getTotalLeitos() {
        int total = 0;
        for (Setor unidade : setores) {
            total += unidade.getTotalLeitos();
        }
        return total;
    }

    public int getTotalLeitosOcupados() {
        int total = 0;
        for (Setor unidade : setores) {
            total += unidade.getTotalLeitosOcupados();
        }
        return total;
    }

    public int getTotalLeitosVagos() {
        int total = 0;
        for (Setor unidade : setores) {
            total += unidade.getTotalLeitosVagos();
        }
        return total;
    }

    public int getTotalLeitosExtras() {
        int total = 0;
        for (Setor unidade : setores) {
            total += unidade.getTotalLeitosExtras();
        }
        return total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Hospital[ id=" + id + " ]";
    }

}
