/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author gleyw
 */
@Entity
@Table(name = "treinamento")
public class Treinamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Treino treino;

    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.ALL)
    private List<ItemTreinamento> itemTreinamentos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public List<ItemTreinamento> getItemTreinamentos() {
        return itemTreinamentos;
    }

    public void setItemTreinamentos(List<ItemTreinamento> itemTreinamentos) {
        this.itemTreinamentos = itemTreinamentos;
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
        if (!(object instanceof Treinamento)) {
            return false;
        }
        Treinamento other = (Treinamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Treinamento[ id=" + id + " ]";
    }

}
