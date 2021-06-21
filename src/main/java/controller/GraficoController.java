/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Atendimento;
import model.DadoMensal;
import model.Setor;
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

    private BarChartModel falhaExtubacao;

    List<Atendimento> atendimentos = new ArrayList<>();
    List<Atendimento> atendimentosExt = new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();


    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            Setor setor = new SetorService().buscarPorId(id);
            atendimentos = atendimentoService.getAtendimentosPorUnidade(setor);
//            atendimentosExt = atendimentoService.getPacientesExtubados(setor);

        }

        createSedestacao();
        createOrtostase();
        createDeambulacao();
        createIntubacao();
        createExtubacao();
        //createFalhaExtubacao();
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

    public BarChartModel getFalhaExtubacao() {
        return falhaExtubacao;
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

    private void createFalhaExtubacao() {
        falhaExtubacao = new BarChartModel();

        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        ChartSeries qtdFalhas = new ChartSeries();
        qtdFalhas.setLabel("Nº Falhas de Extubação");

        ChartSeries oter = new ChartSeries();
        oter.setLabel("oter");

        for (int i = 0; i < meses.length; i++) {
            //qtdFalhas.set(5, 10);
        }
        qtdFalhas.set("Jan", 100);
        falhaExtubacao.addSeries(qtdFalhas);
        falhaExtubacao.addSeries(oter);

        falhaExtubacao.setAnimate(true);
        falhaExtubacao.setLegendPosition("ne");
        falhaExtubacao.setTitle("Falhas de Extubação");

    }

    private long getFalhasExtubacao(int mes) {
        int contador = 0;

        int ano = new Date().toInstant().atZone(ZoneId.systemDefault()).getYear();
        //filter the current year
        List<Atendimento> atendimentosMesAno = atendimentosExt.stream()
                .filter(a -> ano == a.getDataAtendimento().toInstant().atZone(ZoneId.systemDefault()).getYear()).collect(Collectors.toList());


        GregorianCalendar calendar = new GregorianCalendar();

        for (Atendimento atendimento : atendimentosMesAno) {
            calendar.setTime(atendimento.getDataExtubacao());
            int month = calendar.get(GregorianCalendar.MONTH);
            if (!atendimento.getSucessoExtubacao() && mes == month) {
                contador++;
            }
        }
        return contador;
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
