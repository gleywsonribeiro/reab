/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Entrevista;
import model.Opcao;
import model.Pergunta;
import model.Pesquisa;
import model.Resposta;
import model.Tipo;
import model.dao.EntrevistaDao;
import model.dao.PesquisaDao;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import service.DavisQuestion;
import util.jsf.JsfUtil;

/**
 *
 * @author gleyw
 */
@ManagedBean
@ViewScoped
public class PesquisaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PesquisaDao dao = new PesquisaDao();
    private final EntrevistaDao entrevistaDao = new EntrevistaDao();

    private Pesquisa pesquisa = new Pesquisa();
    private List<Pesquisa> pesquisas = new ArrayList<>();

    private Pergunta pergunta = new Pergunta();
    private Opcao opcao = new Opcao();

    @PostConstruct
    private void init() {
        String chave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        if (chave != null) {
            Long id = Long.parseLong(chave);
            pesquisa = dao.find(id);
        }
    }

    public String novo() {
        pesquisa = new Pesquisa();
        pergunta = new Pergunta();
        opcao = new Opcao();
        return "cadastro?faces-redirect=true";
    }

    public void salvar() {
        if (pesquisa.getId() == null) {
            dao.create(pesquisa);
            DavisQuestion.addPerguntas(pesquisa);
            JsfUtil.addMessage("Pesquisa criada com sucesso!");
        } else {
            dao.edit(pesquisa);
            JsfUtil.addMessage("Pesquisa alterada com sucesso!");
        }
        novo();
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Opcao getOpcao() {
        return opcao;
    }

    public void setOpcao(Opcao opcao) {
        this.opcao = opcao;
    }

    public boolean isTemOpcoes() {
        return pergunta.getOpcoes() != null && !pergunta.getOpcoes().isEmpty();
    }

    public void inserirOpcao() {
        opcao.setPergunta(pergunta);
        pergunta.getOpcoes().add(opcao);
        opcao = new Opcao();
    }

    public void removerOpcao() {
        pergunta.getOpcoes().remove(opcao);
        opcao = new Opcao();
    }

    public void editarPergunta() {

    }

    public void inserirPergunta() {
        pergunta.setPesquisa(pesquisa);
        if (pesquisa.getPerguntas().contains(pergunta)) {
            pesquisa.getPerguntas().remove(pergunta);
        }
        pesquisa.getPerguntas().add(pergunta);
        pergunta = new Pergunta();
    }

    public List<Pesquisa> getPesquisas() {
        pesquisas = dao.findAll();
        return pesquisas;
    }

    public void exportarPesquisa() throws IOException {
        List<Entrevista> entrevistas = entrevistaDao.getEntrevistasPorPesquisa(pesquisa);
        if (entrevistas.isEmpty()) {
            JsfUtil.addWarnMessage("Sem entrevistas para esta pesquisa!");
        } else {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("tabulacao");

            entrevistas.forEach((entrevista) -> {
                entrevista.getRespostas().sort((p1, p2) -> p1.getPergunta().getId().compareTo(p2.getPergunta().getId()));
            });

            int line = 1;
            int coluna = 0;

            //cabecalho
            Row cabecalho = sheet.createRow(line - 1);
            for (Resposta resposta : entrevistas.get(0).getRespostas()) {
                Cell celula = cabecalho.createCell(coluna++);
                if (resposta.getPergunta().getTipo().equals(Tipo.DAVIS)) {
                    String[] valor = resposta.getPergunta().getDescricao().split(" ");
                    celula.setCellValue(valor[0]);
                } else {
                    celula.setCellValue(resposta.getPergunta().getDescricao());
                }

                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                Font font = sheet.getWorkbook().createFont();
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);

                cellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                cellStyle.setFont(font);
                celula.setCellStyle(cellStyle);
            }

            //Estilo
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            Font font = sheet.getWorkbook().createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);

            cellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            cellStyle.setFont(font);

            Cell cell = cabecalho.createCell(coluna++);
            cell.setCellValue("PT");
            cell.setCellStyle(cellStyle);

            cell = cabecalho.createCell(coluna++);
            cell.setCellValue("PD");
            cell.setCellStyle(cellStyle);

            cell = cabecalho.createCell(coluna++);
            cell.setCellValue("EC");
            cell.setCellStyle(cellStyle);

            cell = cabecalho.createCell(coluna++);
            cell.setCellValue("FS");
            cell.setCellStyle(cellStyle);
            
            //corpo da tabela
        for (Entrevista entrevista : entrevistas) {
            Row linha = sheet.createRow(line++);
            int col = 0;
            for (Resposta resposta : entrevista.getRespostas()) {
                Cell celula = linha.createCell(col++);
                celula.setCellValue(resposta.getOpcao().getDescricao());
            }
            Cell celula = linha.createCell(col++);
            celula.setCellValue(entrevista.getPT());

            celula = linha.createCell(col++);
            celula.setCellValue(entrevista.getPD());

            celula = linha.createCell(col++);
            celula.setCellValue(entrevista.getEC());

            celula = linha.createCell(col++);
            celula.setCellValue(entrevista.getFS());

        }

        int quantidadeColunas = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < quantidadeColunas; i++) {
            sheet.autoSizeColumn(i);
        }

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=dados.xls");

        try {
            try (ServletOutputStream out = response.getOutputStream()) {
                workbook.write(out);
                out.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        FacesContext faces = FacesContext.getCurrentInstance();
        faces.responseComplete();
        }
    }

}
