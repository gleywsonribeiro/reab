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
public enum Perfil {
    ADMIN("Administrador"),
    USER("Usuário"),
    DEVEL("Desenvolvedor");

    private Perfil(String descricao) {
        this.descricao = descricao;
    }
    
    private final String descricao;

    public String getDescricao() {
        return descricao;
    }
    
    
}
