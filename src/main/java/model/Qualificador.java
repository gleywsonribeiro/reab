/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gleywson
 */
public enum Qualificador {

    PT("Escala de Tomada de Perspectiva"),
    FS("Escala de Fantasia"),
    EC("Escala de Consideração Empática"),
    PD("Escala de Angústia Pessoal");

    private final String descricao;

    private Qualificador(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
