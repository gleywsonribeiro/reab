/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;

import model.Atendimento;
import model.Paciente;
import model.Usuario;
import model.dao.AtendimentoDao;
import model.dao.PacienteDao;
import model.dao.UsuarioDao;
import util.exception.DBException;
import util.exception.NegocioException;

/**
 * @author gleyw
 */
public class UsuarioService {

    private UsuarioDao dao = new UsuarioDao();

    public void salvar(Usuario usuario) {
        dao.create(usuario);
    }
    public void editar(Usuario usuario) {
        dao.edit(usuario);
    }

    public List<Usuario> listarTodos() {
        return dao.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return dao.find(id);
    }

    public void remover(Usuario usuario) {
        try {
            dao.remove(usuario);
        } catch (Exception e) {
            throw new DBException("Não foi possível remover:" + e.getMessage());
        }
    }

    public void resetPassword(Usuario usuario) {
        dao.resetPassword(usuario);
    }

}
