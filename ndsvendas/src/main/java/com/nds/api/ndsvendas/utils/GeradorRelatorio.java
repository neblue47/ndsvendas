package com.nds.api.ndsvendas.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.AbstractView; 
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

 @Service
public class GeradorRelatorio extends AbstractView{
	
	@Autowired private ResourceLoader resourceLoader;
	public JasperPrint exportPdfFile() throws SQLException, JRException,IOException{
		Connection conexao = ConexaoUtil.getConexao();
		Map<String,Object> parametros = new HashMap<String,Object>();
		//String relat = "C:\\Users\\nelson.joao\\eclipse-workspace\\ndsvendas\\src\\main\\resources\\Teste.jrxml";
		String path = resourceLoader.getResource("classpath:Teste.jrxml").getURI().getPath();
		System.out.println(path);
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros,conexao);
		return print ;
	}
	
	public void geraPdf(String jrxml, Map<String, Object> parametros, OutputStream saida) {

        try {
        	Connection conexao = ConexaoUtil.getConexao();
            // compila jrxml em memoria
            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

            // exporta para pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

            exporter.exportReport();
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0, HttpServletRequest arg1, HttpServletResponse arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
