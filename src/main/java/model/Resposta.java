/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author gleywson
 */
@Entity
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    private Pergunta pergunta;
    @ManyToOne(optional = false)
    private Opcao opcao;
    
    
    @ManyToOne(optional = false)
    private Entrevista entrevista;
    
    private int valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Opcao getOpcao() {
        return opcao;
    }

    public void setOpcao(Opcao opcao) {
        this.opcao = opcao;
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        final Resposta other = (Resposta) obj;
        if (this.pergunta != other.pergunta && (this.pergunta == null || !this.pergunta.equals(other.pergunta))) {
            return false;
        }
        if (this.opcao != other.opcao && (this.opcao == null || !this.opcao.equals(other.opcao))) {
            return false;
        }
        if (this.entrevista != other.entrevista && (this.entrevista == null || !this.entrevista.equals(other.entrevista))) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "br.gleywson.modelo.Resposta[ id=" + id + " ]";
    }
    
    
}
