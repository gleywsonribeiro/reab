/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.Atendimento;
import model.DadoMensal;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * @author Gleywson
 */
public abstract class DataService implements Serializable, InfoData {

    private List<Atendimento> atendimentos;

    public DataService(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public DadoMensal getInfoData(int mes) {

        try {
            int soma = 0;
            float media;
            int contador = 0;
            int contadorDeFalhas = 0; //apenas para falhas de extubacao
            //extrai o ano corrente
            int ano = new Date().toInstant().atZone(ZoneId.systemDefault()).getYear();
            //filtra apenas os atendimentos do ano corrente
            List<Atendimento> atendimentosDoAno = atendimentos.stream()
                    .filter(a -> ano == a.getDataAtendimento().toInstant().atZone(ZoneId.systemDefault()).getYear()).collect(Collectors.toList());

            for (Atendimento atendimento : atendimentosDoAno) {
                Date current = getDateReferencia(atendimento);
                if (current != null) {
                    int month = toMonth(current);
                    if (month == mes) {
                        soma += DiffData(atendimento.getDataAtendimento(), current);
                        contador++;
                        if (atendimento.getSucessoExtubacao() != null && atendimento.getSucessoExtubacao() == false) {
                            contadorDeFalhas++;
                        }
                    }
                }
            }

            media = soma / contador;
            DadoMensal dm = new DadoMensal(contador, media);
            return dm;
        } catch (ArithmeticException e) {
//            System.out.println("Erro tratado: " + e.getMessage());
            return new DadoMensal(0, 0);
        }
    }

    protected int toMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH);
    }

    protected long DiffData(Date data1, Date data2) {
        return Days.daysBetween(new DateTime(data1), new DateTime(data2)).getDays();
    }

    protected List<Atendimento> getAtendimentos() {
        return atendimentos;
    }
}
