/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author gleyw
 */
@Entity
@Table(name = "admissao")
public class Admissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_admissao", nullable = false)
    private Date dataAdmissao;
    @Column(columnDefinition = "text")
    private String observacao;
    
    @ManyToOne
    @JoinColumn(columnDefinition = "usuario_admitiu_id", nullable = false)
    private Usuario usuarioAdmissao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Atendimento atendimento;
    
    
    @ManyToOne
    @JoinColumn(nullable = false, columnDefinition = "setor_origem_id")
    private Setor setorOrigem;
    
    @ManyToOne
    @JoinColumn(nullable = false, columnDefinition = "setor_destino_id")
    private Setor setorDestino;

    public Admissao() {
        this.dataAdmissao = new Date();
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getUsuarioAdmissao() {
        return usuarioAdmissao;
    }

    public void setUsuarioAdmissao(Usuario usuarioAdmissao) {
        this.usuarioAdmissao = usuarioAdmissao;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Setor getSetorOrigem() {
        return setorOrigem;
    }

    public void setSetorOrigem(Setor setorOrigem) {
        this.setorOrigem = setorOrigem;
    }

    public Setor getSetorDestino() {
        return setorDestino;
    }

    public void setSetorDestino(Setor setorDestino) {
        this.setorDestino = setorDestino;
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
        if (!(object instanceof Admissao)) {
            return false;
        }
        Admissao other = (Admissao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Admissao[ id=" + id + " ]";
    }
    
}
