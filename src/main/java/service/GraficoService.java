/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Atendimento;
import model.DadoMensal;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author Gleywson
 */
public class GraficoService {
    private DataService service;

    public GraficoService(DataService service) {
        this.service = service;
    }

    public DadoMensal[] getDadosMensais() {
        DadoMensal dados[] = new DadoMensal[12];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = service.getInfoData(i);
        }
        return dados;
    }


}
