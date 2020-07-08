/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gleywson
 */
public enum Score {

    CAMINHAR_76_METROS(8, "Caminhar mais de 76 metros"),
    CAMINHAR_7_METROS(7, "Caminhar mais de 7 metros"),
    CAMINHAR_10_PASSOS(6, "Caminhar mais de 10 passos"),
    FICAR_1_MINUTO(5, "Ficar >= 1 minuto"),
    TRANSFERIR_CADEIRA(4, "Tranferir para cadeira"),
    SENTAR_BEIRADA_CAMA(3, "Sentar na beira da cama"),
    EXERCICIOS_CAMA(2, "Virar-se/exercícios na cama"),
    SOMENTE_DEITADO(1, "Somente deitado");

    private Score(Integer valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }
    
    private final Integer valor;
    private final String descricao;

    public Integer getValor() {
        return valor;
    }
    
    public String getDescricao() {
        return descricao;
    }

}
