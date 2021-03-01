/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gleyw
 */
@Entity
@Table(name = "unidade")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hospital hospital;

    @OneToMany(mappedBy = "setor")
    private List<Leito> leitos = new ArrayList<>();

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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Leito> getLeitos() {
        return leitos;
    }

    public void setLeitos(List<Leito> leitos) {
        this.leitos = leitos;
    }

    public int getTotalLeitos() {
        return getLeitos().size();
    }

    public int getTotalLeitosOcupados() {
        return getLeitos().stream()
                .filter(leito -> leito.getOcupacao() == Ocupacao.OCUPADO)
                .collect(Collectors.toList()).size();
    }

    public int getTotalLeitosVagos() {
        return getLeitos().stream()
                .filter(leito -> leito.getOcupacao() == Ocupacao.VAGO)
                .collect(Collectors.toList()).size();
    }

    public int getTotalLeitosExtras() {
        return getLeitos().stream()
                .filter(leito -> leito.getExtra() == true)
                .collect(Collectors.toList()).size();
    }

    public String getTaxaOcupacao() {
        double todo = getTotalLeitos();
        double parte = getTotalLeitosOcupados();
        double taxa = parte / todo * 100;
        
        DecimalFormat format = new DecimalFormat("00.0");
        return format.format(taxa);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.nome);
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
        final Setor other = (Setor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Setor[ id=" + id + " ]";
    }

}
