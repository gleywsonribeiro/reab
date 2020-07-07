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

    CAMINHAR_76_METROS(8),
    CAMINHAR_7_METROS(7),
    CAMINHAR_10_PASSOS(6),
    FICAR_1_MINUTO(5),
    TRANSFERIR_CADEIRA(4),
    SENTAR_BEIRADA_CAMA(3),
    EXERCICIOS_CAMA(2),
    SOMENTE_DEITADO(1);

    private Score(Integer valor) {
        this.valor = valor;
    }
    
    private final Integer valor;

    public Integer getValor() {
        return valor;
    }
    
    

}
