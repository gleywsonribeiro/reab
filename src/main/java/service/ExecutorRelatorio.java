/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Gleywson
 */
public class ExecutorRelatorio {

    private String caminhoRelatorio;
    private HttpServletResponse response;
    private Map<String, Object> parametros;
    private String nomeArquivoSaida;

    private boolean relatorioGerado;

    public ExecutorRelatorio(String caminhoRelatorio,
                             HttpServletResponse response, Map<String, Object> parametros,
                             String nomeArquivoSaida) {
        this.caminhoRelatorio = caminhoRelatorio;
        this.response = response;
        this.parametros = parametros;
        this.nomeArquivoSaida = nomeArquivoSaida;

        this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
    }

    public void execute(Connection connection) throws SQLException {
        try {
            InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);

            JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
            this.relatorioGerado = print.getPages().size() > 0;

            if (this.relatorioGerado) {
                Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exportador = new JRPdfExporter();
                exportador.setExporterInput(new SimpleExporterInput(print));
                exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + this.nomeArquivoSaida + "\"");

                exportador.exportReport();
            }
        } catch (IOException e) {
            throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
        } catch (JRException e) {
            throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
        }
    }

    public void executeToExcel(Connection connection) throws SQLException {
        try {
            InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);

            JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
            this.relatorioGerado = print.getPages().size() > 0;

            if (this.relatorioGerado) {
                Exporter<ExporterInput, XlsReportConfiguration, XlsExporterConfiguration, OutputStreamExporterOutput> exportador2 = new JRXlsExporter();
                exportador2.setExporterInput(new SimpleExporterInput(print));
                exportador2.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setDetectCellType(true);
                configuration.setWhitePageBackground(false);
              
                configuration.setDetectCellType(true);
                
                exportador2.setConfiguration(configuration);
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + this.nomeArquivoSaida + "\"");
                exportador2.exportReport();
            }
        } catch (IOException e) {
            throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
        } catch (JRException e) {
            throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
        }
    }

    public boolean isRelatorioGerado() {
        return relatorioGerado;
    }
}
