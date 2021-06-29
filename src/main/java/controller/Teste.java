package controller;

import model.Atendimento;
import model.Setor;
import service.*;

import java.util.List;

public class Teste {
    public static void main(String[] args) {
        SetorService setorService = new SetorService();
        Setor setor = setorService.buscarPorId(2L);

        List<Atendimento> atendimentos = new AtendimentoService().getAtendimentosPorUnidade(setor);
        DataService service = new InfoDataTaxaFalha(atendimentos);
        GraficoService gs = new GraficoService(service);

        System.out.println(gs.getDadosMensais()[5].getValor());
    }
}
