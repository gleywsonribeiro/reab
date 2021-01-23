/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.Atendimento;
import model.DadoMensal;

/**
 *
 * @author Gleywson
 */
public class DataService implements Serializable {

    private List<Atendimento> atendimentos;

    public DataService(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public DadoMensal getInfoSedestacao(int mes) {

        int soma = 0;
        float media;
        int contador = 0;

        for (Atendimento atendimento : atendimentos) {
            
            int month = toMonth(atendimento.getDataPrimeiraSedestacao());
            if (atendimento.getDataPrimeiraSedestacao() != null && month == mes) {
                soma += DiffData(atendimento.getDataAtendimento(), atendimento.getDataPrimeiraSedestacao());
                contador++;
            }
        }

        media = soma / contador;
        DadoMensal dm = new DadoMensal(contador, media);

        return dm;
    }

    private int toMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH);
    }

    private long DiffData(Date data1, Date data2) {
        LocalDate localDate1 = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Math.abs(ChronoUnit.DAYS.between(localDate1, localDate2));
    }
}
