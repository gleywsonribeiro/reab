/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.Atendimento;
import model.DadoMensal;

/**
 *
 * @author gleyw
 */
public class InfoDataSedestacao extends DataService {
    
    public InfoDataSedestacao(List<Atendimento> atendimentos) {
        super(atendimentos);
    }

    @Override
    public Date getDateReferencia(Atendimento atendimento) {
        return atendimento.getDataPrimeiraSedestacao();
    }
}
