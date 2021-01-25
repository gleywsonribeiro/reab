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
import model.DadoMensal;
import model.Paciente;
import model.Usuario;
import model.service.AtendimentoService;
import model.service.DataService;
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
       
    private BarChartModel sedestacao;
    private BarChartModel ortostase;
    private BarChartModel deambulacao;
    private BarChartModel intubacao;
    private BarChartModel extubacao;
    
    List<Atendimento> atendimentos =  new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();

    public GraficoController() {
        createSedestacao();
        createOrtostase();
//        createOrtostase();
//        createOrtostase();
//        createOrtostase();
    }
    

    public BarChartModel getSedestacao() {
        return sedestacao;
    }

    public BarChartModel getOrtostase() {
        return ortostase;
    }

    public BarChartModel getDeambulacao() {
        return deambulacao;
    }

    public BarChartModel getIntubacao() {
        return intubacao;
    }

    public BarChartModel getExtubacao() {
        return extubacao;
    }

    private void createSedestacao() {
        sedestacao = new BarChartModel();
        
        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
 
        ChartSeries qtdPacientes = new ChartSeries();
        qtdPacientes.setLabel("Nº Pacientes");
        
        
        ChartSeries mediaDia = new ChartSeries();
        mediaDia.setLabel("Média Dias");
 
        DataService dataService = new DataService(atendimentoService.getAtendimentosEmAndamento());
        
        for(int i = 0; i < meses.length; i++) {
            DadoMensal dm = dataService.getInfoSedestacao(i);
            qtdPacientes.set(meses[i], dm.getNumeroPaciente());
            mediaDia.set(meses[i], dm.getMediaDias());
        }
        
        
        sedestacao.addSeries(qtdPacientes);
        sedestacao.addSeries(mediaDia);
        sedestacao.setAnimate(true);
        sedestacao.setLegendPosition("ne");
        sedestacao.setTitle("1ª Sedestação");
        
    }

    private void createOrtostase() {
        ortostase = new BarChartModel();
    
        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
 
        ChartSeries qtdPacientes = new ChartSeries();
        qtdPacientes.setLabel("Nº Pacientes");
        
        
        ChartSeries mediaDia = new ChartSeries();
        mediaDia.setLabel("Média Dias");
 
        DataService dataService = new DataService(atendimentoService.getAtendimentosEmAndamento());
        
        for(int i = 0; i < meses.length; i++) {
            DadoMensal dm = dataService.getInfoOrtostase(i);
            qtdPacientes.set(meses[i], dm.getNumeroPaciente());
            mediaDia.set(meses[i], dm.getMediaDias());
        }
        
        
        ortostase.addSeries(qtdPacientes);
        ortostase.addSeries(mediaDia);
        ortostase.setAnimate(true);
        ortostase.setLegendPosition("ne");
        ortostase.setTitle("1ª Ortostase");
    }
}
