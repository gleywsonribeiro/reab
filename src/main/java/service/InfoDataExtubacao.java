/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import model.Atendimento;
import model.DadoMensal;

/**
 * @author gleyw
 */
public class InfoDataExtubacao extends DataService {

    public InfoDataExtubacao(List<Atendimento> atendimentos) {
        super(atendimentos);
    }

    @Override
    public Date getDateReferencia(Atendimento atendimento) {
        return atendimento.getDataExtubacao();
    }

    public DadoMensal getInfoData(int mes) {
        try {
            int contador = 0;
            int contadorDeFalhas = 0;

            //extrai o ano corrente
            int ano = new Date().toInstant().atZone(ZoneId.systemDefault()).getYear();
            //filtra apenas os atendimentos do ano corrente
            List<Atendimento> atendimentosDoAno = getAtendimentos().stream()
                    .filter(a -> ano == a.getDataAtendimento().toInstant().atZone(ZoneId.systemDefault()).getYear()).collect(Collectors.toList());

            for (Atendimento atendimento : atendimentosDoAno) {
                Date current = getDateReferencia(atendimento);
                if (current != null) {
                    int month = toMonth(current);
                    if (month == mes) {
                        contador++;
                        if (atendimento.getSucessoExtubacao() != null && atendimento.getSucessoExtubacao() == false) {
                            contadorDeFalhas++;
                        }
                    }
                }
            }

            double taxa = (contadorDeFalhas / contador) * 100;
            DadoMensal dm = new DadoMensal(contador, taxa);
            return dm;
        } catch (ArithmeticException e) {
            System.out.println("Erro tratado: " + e.getMessage());
            return new DadoMensal(0, 0);
        }
    }

}
