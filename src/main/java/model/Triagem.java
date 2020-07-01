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
import javax.persistence.JoinColumn;
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
    private Boolean liberadoMobilizacao = false;

    private Boolean pressaoArterial; //50-100
    private Boolean frequenciaCardiaca; //60-100

    private Boolean FrequenciaRespiratoria; //12-20
    private Boolean suporteVentilacao;
    private Boolean psv;  //<10
    private Boolean peep; //<8
    private Boolean fio2; // <0.6

    private Boolean hemoglobina; //< 7
    private Boolean lactato; // > 2
    private Boolean plaquetas; //< 35

    private Boolean glasgow; // glasgow < 9
    private Boolean rass; // -2 ---- 2

    @ManyToOne
    @JoinColumn(nullable = false)
    private Atendimento atendimento;

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

    public void setLiberadoMobilizacao(Boolean liberadoMobilizacao) {
        this.liberadoMobilizacao = liberadoMobilizacao;
    }

    public void setPressaoArterial(Boolean pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public void setFrequenciaCardiaca(Boolean frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public void setFrequenciaRespiratoria(Boolean FrequenciaRespiratoria) {
        this.FrequenciaRespiratoria = FrequenciaRespiratoria;
    }

    public void setSuporteVentilacao(Boolean suporteVentilacao) {
        this.suporteVentilacao = suporteVentilacao;
    }

    public void setPsv(Boolean psv) {
        this.psv = psv;
    }

    public void setPeep(Boolean peep) {
        this.peep = peep;
    }

    public void setFio2(Boolean fio2) {
        this.fio2 = fio2;
    }

    public void setHemoglobina(Boolean hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public void setLactato(Boolean lactato) {
        this.lactato = lactato;
    }

    public void setPlaquetas(Boolean plaquetas) {
        this.plaquetas = plaquetas;
    }

    public void setGlasgow(Boolean glasgow) {
        this.glasgow = glasgow;
    }

    public void setRass(Boolean rass) {
        this.rass = rass;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public Boolean getPressaoArterial() {
        return pressaoArterial;
    }

    public Boolean getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public Boolean getFrequenciaRespiratoria() {
        return FrequenciaRespiratoria;
    }

    public Boolean getPsv() {
        return psv;
    }

    public Boolean getPeep() {
        return peep;
    }

    public Boolean getFio2() {
        return fio2;
    }

    public Boolean getHemoglobina() {
        return hemoglobina;
    }

    public Boolean getLactato() {
        return lactato;
    }

    public Boolean getPlaquetas() {
        return plaquetas;
    }

    public Boolean getGlasgow() {
        return glasgow;
    }

    public Boolean getRass() {
        return rass;
    }

    public Boolean getSuporteVentilacao() {
        return suporteVentilacao;
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
