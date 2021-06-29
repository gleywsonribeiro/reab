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
import model.Setor;
import service.*;
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
//    private BarChartModel intubacao;
    private BarChartModel extubacao;

    private BarChartModel taxaDeFalhas;

//    private LineChartModel falhaExtubacaoArea;

    List<Atendimento> atendimentos = new ArrayList<>();
    List<Atendimento> atendimentosExt = new ArrayList<>();
    AtendimentoService atendimentoService = new AtendimentoService();

    private String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            Setor setor = new SetorService().buscarPorId(id);
            atendimentos = atendimentoService.getAtendimentosPorUnidade(setor);
            atendimentosExt = atendimentoService.getPacientesExtubados(setor);

        }

        createSedestacao();
        createOrtostase();
        createDeambulacao();
        createExtubacao();
//        createfalhaExtubacaoArea();
        createTaxaFalha();
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

//    public BarChartModel getIntubacao() {
//        return intubacao;
//    }

    public BarChartModel getExtubacao() {
        return extubacao;
    }

    public BarChartModel getTaxaDeFalhas() {
        return taxaDeFalhas;
    }

    private void createSedestacao() {
        sedestacao = new BarChartModel();
        DataService data = new InfoDataSedestacao(atendimentos);
        GraficoService service = new GraficoService(data);

        ChartSeries quantidade = new ChartSeries();
        quantidade.setLabel("Nº Sedestações");

        ChartSeries media = new ChartSeries();
        media.setLabel("Média");

        for (int i = 0; i < meses.length; i++) {
            quantidade.set(meses[i], service.getDadosMensais()[i].getContador());
            media.set(meses[i], service.getDadosMensais()[i].getValor());
        }

        sedestacao.addSeries(quantidade);
        sedestacao.addSeries(media);
        sedestacao.setAnimate(true);
        sedestacao.setShowPointLabels(true);
        sedestacao.setLegendPosition("ne");
        sedestacao.setTitle("1ª Sedestação");

    }

    private void createOrtostase() {
        ortostase = new BarChartModel();
        DataService data = new InfoDataOrtostase(atendimentos);
        GraficoService service = new GraficoService(data);

        ChartSeries quantidade = new ChartSeries();
        quantidade.setLabel("Nº ocorrências");

        ChartSeries media = new ChartSeries();
        media.setLabel("Média");

        for (int i = 0; i < meses.length; i++) {
            quantidade.set(meses[i], service.getDadosMensais()[i].getContador());
            media.set(meses[i], service.getDadosMensais()[i].getValor());
        }

        ortostase.addSeries(quantidade);
        ortostase.addSeries(media);
        ortostase.setAnimate(true);
        ortostase.setShowPointLabels(true);
        ortostase.setLegendPosition("ne");
        ortostase.setTitle("1ª Ortostase");
    }

    private void createDeambulacao() {
        deambulacao = new BarChartModel();
        DataService data = new InfoDataDeambulacao(atendimentos);
        GraficoService service = new GraficoService(data);

        ChartSeries quantidade = new ChartSeries();
        quantidade.setLabel("Nº ocorrências");

        ChartSeries media = new ChartSeries();
        media.setLabel("Média");

        for (int i = 0; i < meses.length; i++) {
            quantidade.set(meses[i], service.getDadosMensais()[i].getContador());
            media.set(meses[i], service.getDadosMensais()[i].getValor());
        }

        deambulacao.addSeries(quantidade);
        deambulacao.addSeries(media);
        deambulacao.setAnimate(true);
        deambulacao.setShowPointLabels(true);
        deambulacao.setLegendPosition("ne");
        deambulacao.setTitle("1ª Deambulação");
    }

    private void createExtubacao() {
        extubacao = new BarChartModel();
        DataService data = new InfoDataExtubacao(atendimentos);
        GraficoService service = new GraficoService(data);

        ChartSeries quantidade = new ChartSeries();
        quantidade.setLabel("Nº Extubações");

        ChartSeries quantidadeFalhas = new ChartSeries();
        quantidadeFalhas.setLabel("Nº de Falhas");

        for (int i = 0; i < meses.length; i++) {
            quantidade.set(meses[i], service.getDadosMensais()[i].getContador());
            quantidadeFalhas.set(meses[i], service.getDadosMensais()[i].getValor());
        }

        extubacao.addSeries(quantidade);
        extubacao.addSeries(quantidadeFalhas);
        extubacao.setAnimate(true);
        extubacao.setShowPointLabels(true);
        extubacao.setLegendPosition("ne");
        extubacao.setTitle("Extubação");
    }

    private void createTaxaFalha() {
        taxaDeFalhas = new BarChartModel();

        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        DataService service = new InfoDataTaxaFalha(atendimentos);
        GraficoService graficoService = new GraficoService(service);
        ChartSeries taxa = new ChartSeries();
        taxa.setLabel("Taxa de Falhas de Extubação (%)");


        for (int i = 0; i < meses.length; i++) {
            taxa.set(meses[i], graficoService.getDadosMensais()[i].getValor());
        }

        taxaDeFalhas.addSeries(taxa);

        taxaDeFalhas.setAnimate(true);
        taxaDeFalhas.setShowPointLabels(true);
        taxaDeFalhas.setLegendPosition("ne");
        taxaDeFalhas.setTitle("Falência de Extubação");

    }

//    private long getFalhasExtubacao(int mes) {
//        int contador = 0;
//
//        int ano = new Date().toInstant().atZone(ZoneId.systemDefault()).getYear();
//        //filter the current year
//        List<Atendimento> atendimentosMesAno = atendimentosExt.stream()
//                .filter(a -> ano == a.getDataAtendimento().toInstant().atZone(ZoneId.systemDefault()).getYear()).collect(Collectors.toList());
//
//        GregorianCalendar calendar = new GregorianCalendar();
//
//        for (Atendimento atendimento : atendimentosMesAno) {
//            calendar.setTime(atendimento.getDataExtubacao());
//            int month = calendar.get(GregorianCalendar.MONTH);
//            if (!atendimento.getSucessoExtubacao() && mes == month) {
//                contador++;
//            }
//        }
//        return contador;
//    }

//    private BarChartModel createChartAux(DataService service, String titulo) {
//        BarChartModel bcm = new BarChartModel();
//        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
//
//        ChartSeries qtdPacientes = new ChartSeries();
//        qtdPacientes.setLabel("Nº Pacientes");
//
//        ChartSeries mediaDia = new ChartSeries();
//        mediaDia.setLabel("Média Dias");
//
//        for (int i = 0; i < meses.length; i++) {
//            DadoMensal dm = service.getInfoData(i);
//            qtdPacientes.set(meses[i], dm.getNumeroPaciente());
//            mediaDia.set(meses[i], dm.getMediaDias());
//        }
//
//        bcm.addSeries(qtdPacientes);
//        bcm.addSeries(mediaDia);
//        bcm.setAnimate(true);
//        bcm.setShowPointLabels(true);
//        bcm.setLegendPosition("ne");
//        bcm.setTitle(titulo);
//
//        return bcm;
//    }
    
//    private BarChartModel createChartAux2(DataService service, String titulo) {
//        BarChartModel bcm = new BarChartModel();
//        String meses[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
//
//        ChartSeries extubacoes = new ChartSeries();
//        extubacoes.setLabel("Nº Extubações");
//
//        ChartSeries taxaFalha = new ChartSeries();
//        taxaFalha.setLabel("Taxa de Falha");
//
//        for (int i = 0; i < meses.length; i++) {
//            DadoMensal dm = service.getInfoData(i);
//            extubacoes.set(meses[i], dm.getNumeroPaciente());
//            taxaFalha.set(meses[i], dm.getTaxaFalha());
//        }
//
//        bcm.addSeries(extubacoes);
//        bcm.addSeries(taxaFalha);
//        bcm.setAnimate(true);
//        bcm.setShowPointLabels(true);
//        bcm.setLegendPosition("ne");
//        bcm.setTitle(titulo);
//
//        return bcm;
//    }

//    private void createfalhaExtubacaoArea() {
//        falhaExtubacaoArea = new LineChartModel();
//        LineChartSeries boys = new LineChartSeries();
//        //boys.setFill(true);
//        boys.setLabel("Boys");
//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
// 
//        LineChartSeries girls = new LineChartSeries();
//        //girls.setFill(true);
//        girls.setLabel("Girls");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 110);
//        girls.set("2007", 90);
//        girls.set("2008", 120);
// 
//        falhaExtubacaoArea.addSeries(boys);
//        falhaExtubacaoArea.addSeries(girls);
//        falhaExtubacaoArea.setTitle("Area Chart");
//        falhaExtubacaoArea.setLegendPosition("ne");
//        //falhaExtubacaoArea.setStacked(true);
//        falhaExtubacaoArea.setShowPointLabels(true);
// 
//        Axis xAxis = new CategoryAxis("Years");
//        falhaExtubacaoArea.getAxes().put(AxisType.X, xAxis);
//        Axis yAxis = falhaExtubacaoArea.getAxis(AxisType.Y);
//        yAxis.setLabel("Births");
//        yAxis.setMin(0);
//        yAxis.setMax(300);
//    }

}