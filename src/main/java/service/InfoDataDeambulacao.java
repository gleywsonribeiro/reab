/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.Atendimento;

/**
 *
 * @author gleyw
 */
public class InfoDataDeambulacao extends DataService{

    public InfoDataDeambulacao(List<Atendimento> atendimentos) {
        super(atendimentos);
    }

    @Override
    public Date getDateReferencia(Atendimento atendimento) {
        return atendimento.getDataPrimeiraDeambulacao();
    }
    
}
