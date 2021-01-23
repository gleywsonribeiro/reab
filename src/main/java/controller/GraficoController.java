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
       
    private BarChartModel deambulacao;
    private BarChartModel ortostase;
    private BarChartModel sedestacao;
    private BarChartModel intubacao;
    private BarChartModel extubacao;
    
    List<Atendimento> atendimentos =  new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();

    public GraficoController() {
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
        
        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
 
        ChartSeries qtdPacientes = new ChartSeries();
        qtdPacientes.setLabel("Pacientes");

        ChartSeries mediaDia = new ChartSeries();
        mediaDia.setLabel("Dias");
 
        DataService dataService = new DataService(atendimentoService.listarTodos());
        
        for(int i = 0; i < meses.length; i++) {
            DadoMensal dm = dataService.getInfoSedestacao(i);
            qtdPacientes.set(meses[i], dm.getNumeroPaciente());
            mediaDia.set(meses[i], dm.getMediaDias());
        }
        
        
        deambulacao.addSeries(qtdPacientes);
        deambulacao.addSeries(mediaDia);
    }
}
