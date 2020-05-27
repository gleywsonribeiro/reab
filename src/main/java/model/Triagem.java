/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author gleyw
 */
@Entity
public class Triagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dt_triagem", nullable = false)
    private Date dataTriagem;
    @Column(name = "sn_liberado")
    private boolean liberadoMobilizacao;
    
    @ManyToOne
    private Paciente paciente;

    public Triagem() {
        dataTriagem = new Date();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTriagem() {
        return dataTriagem;
    }

    public void setDataTriagem(Date dataTriagem) {
        this.dataTriagem = dataTriagem;
    }

    public boolean isLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public void setLiberadoMobilizacao(boolean liberadoMobilizacao) {
        this.liberadoMobilizacao = liberadoMobilizacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        if (!(object instanceof Triagem)) {
            return false;
        }
        Triagem other = (Triagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Triagem[ id=" + id + " ]";
    }
    
}
