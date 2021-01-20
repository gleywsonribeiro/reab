/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Atendimento;
import model.Paciente;
import model.Usuario;
import model.service.AtendimentoService;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import util.exception.DBException;
import util.exception.NegocioException;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class GraficoController implements Serializable {

    private static final long serialVersionUID = 1L;
       
    private BarChartModel deambulacao;
    private BarChartModel ortostase;
    private BarChartModel sedestacao;
    private BarChartModel intubacao;
    private BarChartModel extubacao;
    
    List<Atendimento> atendimentos =  new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();
    
    @PostConstruct
    public void init() {
        createDeambulacao();

    }

    public BarChartModel getDeambulacao() {
        return deambulacao;
    }

    public BarChartModel getOrtostase() {
        return ortostase;
    }

    public BarChartModel getSedestacao() {
        return sedestacao;
    }

    public BarChartModel getIntubacao() {
        return intubacao;
    }

    public BarChartModel getExtubacao() {
        return extubacao;
    }
    
    

    private void createDeambulacao() {
        deambulacao = new BarChartModel();
        
        List<Month> meses = Arrays.asList(Month.JANUARY, Month.FEBRUARY, 
                Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, 
                Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, 
                Month.DECEMBER);
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
 
        Random random = new Random();
        
        for (Month mes : meses) {
            boys.set(mes.toString(), random.nextInt(10));
            girls.set(mes.toString(), random.nextInt(10));
        }
        deambulacao.addSeries(boys);
        deambulacao.addSeries(girls);
    }
}
