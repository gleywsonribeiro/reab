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
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dt_avaliacao", nullable = false)
    private Date dataAvaliacao;
    @Column(name = "sn_liberado")
    private Boolean liberadoMobilizacao = false;

    @Enumerated(EnumType.STRING)
    private Opcao pressaoArterial;
    @Enumerated(EnumType.STRING)
    private Opcao frequenciaCardiaca;

    @Enumerated(EnumType.STRING)
    private Opcao FrequenciaRespiratoria;

    private Boolean suporteVentilacao;

    @Enumerated(EnumType.STRING)
    private Opcao psv;

    @Enumerated(EnumType.STRING)
    private Opcao peep;

    @Enumerated(EnumType.STRING)
    private Opcao fio2;

    @Enumerated(EnumType.STRING)
    private Opcao hemoglobina;

    @Enumerated(EnumType.STRING)
    private Opcao lactato;

    @Enumerated(EnumType.STRING)
    private Opcao plaquetas;

    @Enumerated(EnumType.STRING)
    private Opcao glasgow;

    @Enumerated(EnumType.STRING)
    private Opcao rass;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Atendimento atendimento;

    @ManyToOne
    private Score score;

    @ManyToOne
    private Treinamento treinamento;

    public Avaliacao() {
        dataAvaliacao = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Boolean getLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public void setLiberadoMobilizacao(Boolean liberadoMobilizacao) {
        this.liberadoMobilizacao = liberadoMobilizacao;
    }

    public Opcao getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(Opcao pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public Opcao getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(Opcao frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public Opcao getFrequenciaRespiratoria() {
        return FrequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(Opcao FrequenciaRespiratoria) {
        this.FrequenciaRespiratoria = FrequenciaRespiratoria;
    }

    public Boolean getSuporteVentilacao() {
        return suporteVentilacao;
    }

    public void setSuporteVentilacao(Boolean suporteVentilacao) {
        this.suporteVentilacao = suporteVentilacao;
    }

    public Opcao getPsv() {
        return psv;
    }

    public void setPsv(Opcao psv) {
        this.psv = psv;
    }

    public Opcao getPeep() {
        return peep;
    }

    public void setPeep(Opcao peep) {
        this.peep = peep;
    }

    public Opcao getFio2() {
        return fio2;
    }

    public void setFio2(Opcao fio2) {
        this.fio2 = fio2;
    }

    public Opcao getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(Opcao hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Opcao getLactato() {
        return lactato;
    }

    public void setLactato(Opcao lactato) {
        this.lactato = lactato;
    }

    public Opcao getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(Opcao plaquetas) {
        this.plaquetas = plaquetas;
    }

    public Opcao getGlasgow() {
        return glasgow;
    }

    public void setGlasgow(Opcao glasgow) {
        this.glasgow = glasgow;
    }

    public Opcao getRass() {
        return rass;
    }

    public void setRass(Opcao rass) {
        this.rass = rass;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
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
        return "model.Triagem[ id=" + id + " ]";
    }

}
