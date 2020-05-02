/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Arrays;
import model.Hospital;
import model.Perfil;
import model.Usuario;
import model.dao.Dao;
import model.dao.HospitalDao;
import model.dao.UsuarioDao;

/**
 *
 * @author gleyw
 */
public class Testes {

    public static void main(String[] args) {
        Dao dao = new UsuarioDao();
        
        Usuario usuario = new Usuario(null, "admin", "Gleywson", true, "admin", Perfil.DEVEL);
        
        dao.create(usuario);
        

    }
}
