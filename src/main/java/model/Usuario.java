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
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Gleywson
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private boolean ativo;

    @Column(length = 50)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @ManyToMany
    @JoinTable(name = "tb_usuario_hospital",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id"))
    private Set<Hospital> hospitais = new HashSet<>();
    
    @Transient
    private Hospital hospitalLogado = new Hospital();

    public Usuario() {
        this.ativo = true;
    }

    public Usuario(Long id, String login, String nome, boolean ativo, String senha, Perfil perfil) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.ativo = ativo;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Hospital> getHospitais() {
        return hospitais;
    }

    public void setHospitais(Set<Hospital> hospitais) {
        this.hospitais = hospitais;
    }

    public Hospital getHospitalLogado() {
        return hospitalLogado;
    }

    public void setHospitalLogado(Hospital hospitalLogado) {
        this.hospitalLogado = hospitalLogado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public boolean isAdministrator() {
        return this.getPerfil().equals(Perfil.ADMIN);
    }

    public boolean isDeveloper() {
        return this.getPerfil().equals(Perfil.DEVEL);
    }

    public boolean isUser() {
        return this.getPerfil().equals(Perfil.USER);
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", nome=" + nome + ", perfil=" + perfil + '}';
    }

}
