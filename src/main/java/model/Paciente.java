/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

import org.joda.time.DateTime;
import org.joda.time.Years;

/**
 *
 * @author gleyw
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_nascimento")
    private Date dataNascimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_cadastro", nullable = false)
    private Date dataCadastro;
    @Column(unique = true)
    private String matricula;
    
    @OneToMany(mappedBy = "paciente")
    private List<Atendimento> atendimentos;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Hospital hospital;

    public Paciente() {
        this.dataCadastro = new Date();
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
    
    public void add(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }
    
    public void remove(Atendimento atendimento) {
        atendimentos.remove(atendimento);
    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    
    
    
    public int getIdade() {
        DateTime nascimento = new DateTime(getDataNascimento().getTime());
        DateTime hoje = new DateTime();
        
        Years idade = Years.yearsBetween(nascimento, hoje);
        
        return idade.getYears();
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + '}';
    }

    public boolean isEmAtendimento() {
        List<Atendimento> corrente = getAtendimentos().stream().filter(a -> a.getDataAlta() == null).collect(Collectors.toList());
        return (!corrente.isEmpty());
    }
}
