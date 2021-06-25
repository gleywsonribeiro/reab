/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Admissao;
import model.Avaliacao;
import model.dao.AdmissaoDao;
import model.dao.AvaliacaoDao;
import util.exception.NegocioException;

/**
 *
 * @author gleyw
 */
public class AdmissaoService {

    private AdmissaoDao dao = new AdmissaoDao();

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
