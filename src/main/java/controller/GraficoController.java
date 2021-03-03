/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Atendimento;
import model.DadoMensal;
import model.service.AtendimentoService;
import model.service.DataService;
import model.service.InfoDataDeambulacao;
import model.service.InfoDataExtubacao;
import model.service.InfoDataIntubacao;
import model.service.InfoDataOrtostase;
import model.service.InfoDataSedestacao;
import model.service.SetorService;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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

    List<Atendimento> atendimentos = new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();
    

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            atendimentos = atendimentoService.getAtendimentosPorUnidade(new SetorService().buscarPorId(id));
        }
        
        createSedestacao();
        createOrtostase();
        createDeambulacao();
        createIntubacao();
        createExtubacao();

    }

//    public GraficoController() {
//        atendimentos = atendimentoService.listarTodos();
//        createSedestacao();
//        createOrtostase();
//        createDeambulacao();
//        createIntubacao();
//        createExtubacao();
//    }

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
        sedestacao = createChartAux(new InfoDataSedestacao(atendimentos), "1ª Sedestação");
    }

    private void createOrtostase() {
        ortostase = createChartAux(new InfoDataOrtostase(atendimentos), "1ª Ortostase");
    }

    private void createDeambulacao() {
        deambulacao = createChartAux(new InfoDataDeambulacao(atendimentos), "1ª Deambulação");
    }

    private void createIntubacao() {
        intubacao = createChartAux(new InfoDataIntubacao(atendimentos), "Intubação");
    }

    private void createExtubacao() {
        extubacao = createChartAux(new InfoDataExtubacao(atendimentos), "Extubação");
    }

    private BarChartModel createChartAux(DataService service, String titulo) {
        BarChartModel bcm = new BarChartModel();
        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        ChartSeries qtdPacientes = new ChartSeries();
        qtdPacientes.setLabel("Nº Pacientes");

        ChartSeries mediaDia = new ChartSeries();
        mediaDia.setLabel("Média Dias");

        for (int i = 0; i < meses.length; i++) {
            DadoMensal dm = service.getInfoData(i);
            qtdPacientes.set(meses[i], dm.getNumeroPaciente());
            mediaDia.set(meses[i], dm.getMediaDias());
        }

        bcm.addSeries(qtdPacientes);
        bcm.addSeries(mediaDia);
        bcm.setAnimate(true);
        bcm.setLegendPosition("ne");
        bcm.setTitle(titulo);

        return bcm;
    }
}
