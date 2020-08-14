/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author gleyw
 */
@Entity
public class ItemTreinamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    private ItemExercicio itemExercicio;
    
    
    @ManyToOne
    private Treinamento treinamento;
    
    @Column(columnDefinition = "text")
    private String observacao;
    
    private Boolean realizado;
    private Boolean principal;

    @Enumerated(EnumType.STRING)
    private Turno turno;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public ItemExercicio getItemExercicio() {
        return itemExercicio;
    }

    public void setItemExercicio(ItemExercicio itemExercicio) {
        this.itemExercicio = itemExercicio;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getRealizado() {
        return realizado;
    }
    
    public Boolean getNaoRealizado() {
        if(realizado == null) {
            return Boolean.FALSE;
        } else {
            return !realizado;
        }
        
    }
    
    public String carregaClasseCSS() {
        if(principal) {
            return "principal";
        } else {
            return "";
        }
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
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
        if (!(object instanceof ItemTreinamento)) {
            return false;
        }
        ItemTreinamento other = (ItemTreinamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemTreinamento[ id=" + id + " ]";
    }
    
}
