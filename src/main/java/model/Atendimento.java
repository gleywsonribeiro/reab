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
@Table(name = "atendimento")
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dt_atendimento")
    private Date dataAtendimento;
    
    @Column(name = "dt_pri_sedestacao")
    @Temporal(TemporalType.DATE)
    private Date dataPrimeiraSedestacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_pri_ortostase")
    private Date dataPrimeiraOrtostase;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_pri_deambulacao")
    private Date dataPrimeiraDeambulacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_intubacao")
    private Date dataIntubacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_extubacao")
    private Date dataExtubacao;

    @Column(name = "sucesso_ext")
    private Boolean sucessoExtubacao;

    @Column(name = "motivo_falha")
    private String motivoFalha;
    
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dt_alta")
    private Date dataAlta;
    
    @Column(name = "sn_mobilizacao")
    private Boolean liberadoMobilizacao = false;

    @Column(name = "mob_pre_hospitalar")
    private Integer mobilidadePreHospitalar;

    @Column(name = "mob_admissao")
    private Integer mobilidadeAdmissao;

    @Column(name = "mob_alta")
    private Integer mobilidadeAlta;
    
    @Column(name = "ext_menor")
    private Boolean extMenor;
    
    @Column(name = "mot_ext_maior")
    private String motivoExtMaior;
    
    @ManyToOne(optional = false)
    private Usuario atendente;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    private Motivo motivo;
    
    @ManyToOne
    private Leito leito;

    public Atendimento() {
        this.dataAtendimento = new Date();
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public void setLiberadoMobilizacao(Boolean liberadoMobilizacao) {
        this.liberadoMobilizacao = liberadoMobilizacao;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Usuario getAtendente() {
        return atendente;
    }

    public void setAtendente(Usuario atendente) {
        this.atendente = atendente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }
    
    public boolean isEmAtendimento() {
        return getDataAlta() == null;
    }

    public Date getDataPrimeiraSedestacao() {
        return dataPrimeiraSedestacao;
    }

    public void setDataPrimeiraSedestacao(Date dataPrimeiraSedestacao) {
        this.dataPrimeiraSedestacao = dataPrimeiraSedestacao;
    }

    public Date getDataPrimeiraOrtostase() {
        return dataPrimeiraOrtostase;
    }

    public void setDataPrimeiraOrtostase(Date dataPrimeiraOrtostase) {
        this.dataPrimeiraOrtostase = dataPrimeiraOrtostase;
    }

    public Date getDataPrimeiraDeambulacao() {
        return dataPrimeiraDeambulacao;
    }

    public void setDataPrimeiraDeambulacao(Date dataPrimeiraDeambulacao) {
        this.dataPrimeiraDeambulacao = dataPrimeiraDeambulacao;
    }

    public Date getDataIntubacao() {
        return dataIntubacao;
    }

    public void setDataIntubacao(Date dataIntubacao) {
        this.dataIntubacao = dataIntubacao;
    }

    public Date getDataExtubacao() {
        return dataExtubacao;
    }

    public void setDataExtubacao(Date dataExtubacao) {
        this.dataExtubacao = dataExtubacao;
    }

    public Boolean getSucessoExtubacao() {
        return sucessoExtubacao;
    }

    public void setSucessoExtubacao(Boolean sucessoExtubacao) {
        this.sucessoExtubacao = sucessoExtubacao;
    }

    public String getMotivoFalha() {
        return motivoFalha;
    }

    public void setMotivoFalha(String motivoFalha) {
        this.motivoFalha = motivoFalha;
    }

    public Boolean getLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public Integer getMobilidadePreHospitalar() {
        return mobilidadePreHospitalar;
    }

    public void setMobilidadePreHospitalar(Integer mobilidadePreHospitalar) {
        this.mobilidadePreHospitalar = mobilidadePreHospitalar;
    }

    public Integer getMobilidadeAdmissao() {
        return mobilidadeAdmissao;
    }

    public void setMobilidadeAdmissao(Integer mobilidadeAdmissao) {
        this.mobilidadeAdmissao = mobilidadeAdmissao;
    }

    public Integer getMobilidadeAlta() {
        return mobilidadeAlta;
    }

    public void setMobilidadeAlta(Integer mobilidadeAlta) {
        this.mobilidadeAlta = mobilidadeAlta;
    }

    public Boolean getExtMenor() {
        return extMenor;
    }

    public void setExtMenor(Boolean extMenor) {
        this.extMenor = extMenor;
    }

    public String getMotivoExtMaior() {
        return motivoExtMaior;
    }

    public void setMotivoExtMaior(String motivoExtMaior) {
        this.motivoExtMaior = motivoExtMaior;
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
        if (!(object instanceof Atendimento)) {
            return false;
        }
        Atendimento other = (Atendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "id=" + id + '}';
    }

    

   
    
}
