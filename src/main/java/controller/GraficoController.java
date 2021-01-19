/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    private List<String> meses = new ArrayList<>();
    
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
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("Jan", 120);
        boys.set("Fev", 100);
        boys.set("Mar", 44);
        boys.set("Abr", 150);
        boys.set("Mai", 25);
        boys.set("Jun", 15);
        boys.set("Jul", 26);
        boys.set("Ago", 48);
        boys.set("Set", 59);
        boys.set("Out", 19);
        boys.set("Nov", 91);
        boys.set("Dez", 82);
        
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("Jan", 52);
        girls.set("Fev", 60);
        girls.set("Mar", 110);
        girls.set("Abr", 135);
        girls.set("Mai", 120);
        girls.set("Jun", 58);
        girls.set("Jul", 23);
        girls.set("Ago", 47);
        girls.set("Set", 69);
        girls.set("Out", 36);
        girls.set("Nov", 25);
        girls.set("Dez", 14);
 
        deambulacao.addSeries(boys);
        deambulacao.addSeries(girls);
        
        
    }
}
