/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.Admissao;
import model.Triagem;
import model.dao.AdmissaoDao;
import model.dao.TriagemDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AdmissaoService {

    private AdmissaoDao dao;

    public AdmissaoService(AdmissaoDao dao) {
        this.dao = dao;
    }

    public void admitir(Admissao admissao) {
        try {
            if (admissao.getSetorOrigem().equals(admissao.getSetorDestino())) {
                throw new NegocioException("Não é possível admitir para o mesmo setor");
            } else {
                dao.create(admissao);
            }
        } catch (Exception e) {
            throw new NegocioException("Erro ao realizar admissão: " + e.getMessage());
        }
    }
}
