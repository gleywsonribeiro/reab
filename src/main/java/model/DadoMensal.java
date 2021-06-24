/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Gleywson
 */
public class DadoMensal implements Serializable {
    private int numeroPaciente;
    private float mediaDias;
    private double taxaFalha;

    public DadoMensal(int numeroPaciente, float mediaDias) {
        this.numeroPaciente = numeroPaciente;
        this.mediaDias = mediaDias;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public float getMediaDias() {
        return mediaDias;
    }

    public void setMediaDias(float mediaDias) {
        this.mediaDias = mediaDias;
    }

    public double getTaxaFalha() {
        return taxaFalha;
    }

    public void setTaxaFalha(double taxaFalha) {
        this.taxaFalha = taxaFalha;
    }
    
    
}
