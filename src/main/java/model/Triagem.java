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
    private boolean liberadoMobilizacao = false;

    private boolean pressaoArterial; //50-100
    private boolean frequenciaCardiaca; //60-100

    private boolean FrequenciaRespiratoria; //12-20
    private boolean suporteVentilacao;
    private boolean psv;  //<10
    private boolean peep; //<8
    private boolean fio2; // <0.6

    private boolean hemoglobina; //< 7
    private boolean lactato; // > 2
    private boolean plaquetas; //< 35

    private boolean glasgow; // glasgow < 9
    private boolean rass; // -2 ---- 2

    @ManyToOne
    @JoinColumn(nullable = false)
    private Atendimento atendimento;

    public Triagem() {
        dataTriagem = new Date();
        setPressaoArterial(true);
        setFrequenciaCardiaca(true);

        setFrequenciaRespiratoria(true);

        setSuporteVentilacao(true);
        setPeep(true);
        setPsv(true);
        setFio2(true);

        setHemoglobina(true);
        setLactato(true);
        setPlaquetas(true);

        setGlasgow(true);
        setRass(true);

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

    public boolean isLiberadoMobilizacao() {
        return liberadoMobilizacao;
    }

    public void setLiberadoMobilizacao(boolean liberadoMobilizacao) {
        this.liberadoMobilizacao = liberadoMobilizacao;
    }

    public boolean isPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(boolean pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public boolean isFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(boolean frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public boolean isFrequenciaRespiratoria() {
        return FrequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(boolean FrequenciaRespiratoria) {
        this.FrequenciaRespiratoria = FrequenciaRespiratoria;
    }

    public boolean isSuporteVentilacao() {
        return suporteVentilacao;
    }

    public void setSuporteVentilacao(boolean suporteVentilacao) {
        this.suporteVentilacao = suporteVentilacao;
    }

    public boolean isPsv() {
        return psv;
    }

    public void setPsv(boolean psv) {
        this.psv = psv;
    }

    public boolean isPeep() {
        return peep;
    }

    public void setPeep(boolean peep) {
        this.peep = peep;
    }

    public boolean isFio2() {
        return fio2;
    }

    public void setFio2(boolean fio2) {
        this.fio2 = fio2;
    }

    public boolean isHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(boolean hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public boolean isLactato() {
        return lactato;
    }

    public void setLactato(boolean lactato) {
        this.lactato = lactato;
    }

    public boolean isPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(boolean plaquetas) {
        this.plaquetas = plaquetas;
    }

    public boolean isGlasgow() {
        return glasgow;
    }

    public void setGlasgow(boolean glasgow) {
        this.glasgow = glasgow;
    }

    public boolean isRass() {
        return rass;
    }

    public void setRass(boolean rass) {
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
