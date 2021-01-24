/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.Atendimento;
import model.DadoMensal;
import org.joda.time.DateTime;
import org.joda.time.Days;

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

        try {
            int soma = 0;
            float media;
            int contador = 0;

            for (Atendimento atendimento : atendimentos) {
                Date current = atendimento.getDataPrimeiraSedestacao();
                if (current != null) {
                    int month = toMonth(current);
                    if (month == mes) {
                        soma += DiffData(atendimento.getDataAtendimento(), current);
                        contador++;
                    }
                }
                
            }

            media = soma / contador;
            DadoMensal dm = new DadoMensal(contador, media);

            return dm;
        } catch (ArithmeticException e) {
            System.out.println("Erro tratado: " + e.getMessage());
            return new DadoMensal(0, 0);
        }
    }

    public DadoMensal getInfoOrtostase(int mes) {

        try {
            int soma = 0;
            float media;
            int contador = 0;

            for (Atendimento atendimento : atendimentos) {
                Date current = atendimento.getDataPrimeiraOrtostase();
                if (current != null) {
                    int month = toMonth(current);
                    if (month == mes) {
                        soma += DiffData(atendimento.getDataAtendimento(), current);
                        contador++;
                    }
                }
                
            }

            media = soma / contador;
            DadoMensal dm = new DadoMensal(contador, media);

            return dm;
        } catch (ArithmeticException e) {
            System.out.println("Erro tratado: " + e.getMessage());
            return new DadoMensal(0, 0);
        }
    }
    private int toMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH);
    }

    private long DiffData(Date data1, Date data2) {
        return Math.abs(Days.daysBetween(new DateTime(data1), new DateTime(data2)).getDays());
    }
}
