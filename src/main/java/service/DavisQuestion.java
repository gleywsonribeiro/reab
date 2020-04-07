/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.Opcao;
import model.Pergunta;
import model.Pesquisa;
import model.Qualificador;
import model.Tipo;
import model.dao.PerguntaDao;

/**
 *
 * @author gleyw
 */
public class DavisQuestion {
    private PerguntaDao dao;
    
    public static void addPerguntas(Pesquisa pesquisa){
        List<Pergunta> perguntasDavis = Arrays.asList(
                new Pergunta("1. Eu sonho acordado(a) e fantasio com alguma regularidade sobre coisas que possam acontecer a mim.", false, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("2. Frequentemente me sensibilizo e tenho sentimentos de preocupação com pessoas menos afortunadas que eu.", false, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("3. Às vezes acho difícil ver as coisas sob o ponto de vista do outro. ", true, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("4. Às vezes não sinto tanta pena de outras pessoas quando estão passando por problemas.", true, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("5. Eu me envolvo de verdade com os sentimentos de personagens de um romance.", false, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("6. Em situações emergenciais, fico apreensivo(a) e ansioso(a).", false, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("7. Normalmente fico indiferente quando assisto a um filme ou peça de teatro e não costumo me envolver por completo.", true, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("8. Em uma discordância, procuro ver o lado de todos antes de tomar uma decisão.", false, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("9. Quando vejo alguém sendo passado para trás, sinto vontade de protegê-lo(a) de certa forma.", false, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("10. Às vezes me sinto vulnerável quando estou em meio a uma situação muito emotiva.", false, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("11. Às vezes tento compreender melhor os meus amigos imaginando como as coisas são na perspectiva deles.", false, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("12. É um tanto raro para mim me envolver extremamente com um bom livro ou filme.", true, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("13. Quando vejo alguém se machucar, tendo a ficar calmo.", true, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("14. Os sofrimentos de outras pessoas geralmente não me incomodam tanto.", true, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("15. Quando tenho certeza de que estou certo sobre alguma coisa, não perco muito tempo escutando os argumentos das outras pessoas.", true, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("16. Depois de assistir a um filme ou peça de teatro, sinto como se eu fosse um dos personagens.", false, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("17. Estar em uma situação de tensão emocional me assusta.", false, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("18. Quando vejo alguém sendo tratado injustamente, às vezes não sinto tanta pena dele (a).", true, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("19. Geralmente sou bem eficiente ao lidar com emergências.", true, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("20. Frequentemente fico comovido por coisas que vejo acontecer.", false, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("21. Acredito que existem dois lados para cada questão e procuro ver os dois.", false, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("22. Descrevo-me como uma pessoa de coração meio mole.", false, Qualificador.EC, Tipo.DAVIS, pesquisa),
                new Pergunta("23. Quando assisto a um bom filme, sou capaz de me colocar facilmente no lugar do personagem principal.", false, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("24. Tendo a perder o controle durante emergências.", false, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("25. Quando estou chateado(a) com alguém, geralmente tento me colocar em seu lugar por um momento.", false, Qualificador.PT, Tipo.DAVIS, pesquisa),
                new Pergunta("26. Quando estou lendo uma história ou romance interessante, imagino como eu me sentiria se os eventos da história estivessem acontecendo comigo.", false, Qualificador.FS, Tipo.DAVIS, pesquisa),
                new Pergunta("27. Desmorono quando vejo alguém que precisa de ajuda desesperadamente em uma emergência.", false, Qualificador.PD, Tipo.DAVIS, pesquisa),
                new Pergunta("28. Antes de criticar alguém, procuro imaginar como eu me sentiria se estivesse em seu lugar.", false, Qualificador.PT, Tipo.DAVIS, pesquisa)
        );
        
        
        for (Pergunta pergunta : perguntasDavis) {
            pergunta.setOpcoes(geraOpcoesDavis(pergunta));
        }

        PerguntaDao perguntaDao = new PerguntaDao();
        perguntaDao.createAll(perguntasDavis);
        System.out.println("Fui chamado!!!");
        
        
    }
    
        private static List<Opcao> geraOpcoesDavis(Pergunta p) {
        List<Opcao> listaOpcoes = new ArrayList<>();
        List<String> opcoesDavis = Arrays.asList(
                "A - Não me descreve bem",
                "B - Não me descreve",
                "C - Sou neutro",
                "D - Me descreve",
                "E - Me descreve muito bem"
        );

        if (p.isInvertido()) {
            Collections.reverse(opcoesDavis);
            for (int i = 0; i < opcoesDavis.size(); i++) {
                listaOpcoes.add(new Opcao(opcoesDavis.get(i), i, p));
            }
        } else {
            for (int i = 0; i < opcoesDavis.size(); i++) {
                listaOpcoes.add(new Opcao(opcoesDavis.get(i), i, p));
            }
        }

        return listaOpcoes;
    }
}
