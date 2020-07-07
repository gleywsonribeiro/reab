/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author gleyw
 */
@Entity
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private Triagem triagem;
    
    @Column(name = "controle_cervical")
    private Boolean controleCervical;
    
    @Column(name = "controle_tronco")
    private Boolean controleTronco;
   
    private Boolean bipesdacao;

    private Boolean deambulacao;

    public Avaliacao() {
        this.controleCervical = false;
        this.controleTronco = false;
        this.bipesdacao = false;
        this.deambulacao = false;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }


    public Boolean getControleCervical() {
        return controleCervical;
    }

    public void setControleCervical(Boolean controleCervical) {
        this.controleCervical = controleCervical;
    }

    public Boolean getControleTronco() {
        return controleTronco;
    }

    public void setControleTronco(Boolean controleTronco) {
        this.controleTronco = controleTronco;
    }

    public Boolean getBipesdacao() {
        return bipesdacao;
    }

    public void setBipesdacao(Boolean bipesdacao) {
        this.bipesdacao = bipesdacao;
    }

    public Boolean getDeambulacao() {
        return deambulacao;
    }

    public void setDeambulacao(Boolean deambulacao) {
        this.deambulacao = deambulacao;
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
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliacao[ id=" + id + " ]";
    }
    
}
