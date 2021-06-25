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
    private int contador;
    private double valor;

    public DadoMensal(int contador, double valor) {
        this.contador = contador;
        this.valor = valor;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "DadoMensal{" +
                "contador=" + contador +
                ", valor=" + valor +
                '}';
    }
}
