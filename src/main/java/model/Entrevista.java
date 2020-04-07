/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author gleywson
 */
@Entity
public class Entrevista implements Serializable {

    @OneToMany(mappedBy = "entrevista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;
    private Double resultado;

    @ManyToOne
    private Pesquisa pesquisa;

    public Entrevista() {
        this.dataHora = new Date();
        this.respostas = new ArrayList<Resposta>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public int getPT() {
        return somaEscala(Qualificador.PT);
    }

    public int getPD() {
        return somaEscala(Qualificador.PD);
    }

    public int getEC() {
        return somaEscala(Qualificador.EC);
    }

    public int getFS() {
        return somaEscala(Qualificador.FS);
    }

    private int somaEscala(Qualificador qualificador) {
        int acumulador = 0;
        for (Resposta resposta : respostas) {
            if (resposta.getPergunta().getQualificador() == qualificador) {
                acumulador += resposta.getOpcao().getPeso();
            }
        }
        return acumulador;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrevista)) {
            return false;
        }
        Entrevista other = (Entrevista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gleywson.modelo.Avaliacao[ id=" + id + " ]";
    }

}
