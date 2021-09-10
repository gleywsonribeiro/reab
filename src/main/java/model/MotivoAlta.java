/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author gleyw
 */
@Entity
@Table(name = "motivo_alta")
public class MotivoAlta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotivoAlta)) return false;

        MotivoAlta that = (MotivoAlta) o;

        return getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return "MotivoAlta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
