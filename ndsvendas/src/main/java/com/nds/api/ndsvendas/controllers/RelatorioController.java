package com.nds.api.ndsvendas.controllers;

import java.awt.Desktop; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.VendaDTO;
import com.nds.api.ndsvendas.dtos.VendaFilterDTO;
import com.nds.api.ndsvendas.enums.ENumVenda;
import com.nds.api.ndsvendas.implments.ClienteImplements;
import com.nds.api.ndsvendas.implments.ProdutoImplements;
import com.nds.api.ndsvendas.implments.VendaImplements;
import com.nds.api.ndsvendas.security.service.impl.UserDetailsServiceImpl;
import com.nds.api.ndsvendas.utils.ConexaoUtil;
import com.nds.api.ndsvendas.utils.Utilitarios;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

	@Autowired VendaImplements _saleServices;
	@Autowired ProdutoImplements _productServices;
	@Autowired ClienteImplements _clientServices;
	@Autowired ResourceLoader _resourceLoader;
	@Autowired UserDetailsServiceImpl _userAuth;
	private Utilitarios utilitario = new Utilitarios();
	String _currentUser =  _userAuth == null ? "Sem Utilizador" : _userAuth.getUserLogado().getEmail(); 
	
	@GetMapping("/vendas") 
    public ResponseEntity<Page<VendaDTO>> getAllVendas(@PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_saleServices.findAllToDTO(pageable));
    }
	@GetMapping("/vendas-diaria") 
    public ResponseEntity<Page<VendaDTO>> getAllVendasDiaria(@PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		   
		var filter = new VendaFilterDTO ();
		var curdate = dateStringToday();
        filter.setDataInicial(curdate);
        filter.setDataFim(curdate);  
		return ResponseEntity.status(HttpStatus.OK).body(_saleServices.findAllByFilterToDTO(filter, pageable));
    }
	@PostMapping("/vendasByFilter") 
    public ResponseEntity<Page<VendaDTO>> getAllVendasByFilter(@RequestBody VendaFilterDTO filter,@PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		filter.setEnumeradoVenda(ENumVenda.fromId(filter.getEnumVenda()));
        return ResponseEntity.status(HttpStatus.OK).body(_saleServices.findAllByFilterToDTO(filter, pageable));
    }
	
	@RequestMapping(value = "/printRelatorioRes/{vendaId}", method = RequestMethod.GET)
	public @ResponseBody byte[]  printRelatorioRes(HttpServletRequest request,HttpServletResponse response,@PathVariable(value = "vendaId") String vendaId) throws Exception {
		response.setContentType("application/pdf");
		Connection conexao = ConexaoUtil.getConexao();
		String path = _resourceLoader.getResource("classpath:Teste.jrxml").getURI().getPath();
		String pathIcon = _resourceLoader.getResource("classpath:NDV-0.png").getURI().getPath();
		String dataHoje = utilitario.data_str();
		dataHoje = dataHoje.replace("/", ""); 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("VendaId", vendaId); 
		param.put("Utilizador", _userAuth.getUserLogado().getEmail());
		param.put("LogoIcon", pathIcon);  
		JasperReport jasperReport = JasperCompileManager.compileReport(path);		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conexao);
		ClearFiles(); 
		String pdfFile = "C:\\temp\\"+vendaId+".pdf";
		JasperExportManager.exportReportToPdfFile(jasperPrint,pdfFile);
		 
		byte[] bytes = Files.readAllBytes(Paths.get(pdfFile));
		return bytes; 
	}
	@RequestMapping(value = "/printRelatorioVendaPeriodo", method = RequestMethod.POST)
	public @ResponseBody byte[]  printRelatorioVendaPeriodo(HttpServletRequest request,HttpServletResponse response,@RequestBody VendaFilterDTO filter) throws Exception {
		response.setContentType("application/pdf");
		Connection conexao = ConexaoUtil.getConexao();
		String path = _resourceLoader.getResource("classpath:RelatorioVendaPeriodicoBoth.jrxml").getURI().getPath();
		String pathIcon = _resourceLoader.getResource("classpath:NDV-0.png").getURI().getPath();
		String dataHoje = utilitario.data_str();
		dataHoje = dataHoje.replace("/", ""); 
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("Periodo", filter.getDataInicial()); 
		param.put("PeriodoFinal", filter.getDataFim());
		param.put("Utilizador", _userAuth.getUserLogado().getEmail());
		param.put("LogoIcon", pathIcon);  
		JasperReport jasperReport = JasperCompileManager.compileReport(path);		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conexao);
		ClearFiles(); 
		String pdfFile = "C:\\temp\\"+dataHoje+".pdf";
		JasperExportManager.exportReportToPdfFile(jasperPrint,pdfFile);
		 
		byte[] bytes = Files.readAllBytes(Paths.get(pdfFile));
		return bytes; 
	}
	@RequestMapping(value = "/printFactura/{vendaId}", method = RequestMethod.GET) 
	public @ResponseBody byte[]  printFactura(HttpServletRequest request,HttpServletResponse response,@PathVariable(value = "vendaId") String vendaId) throws Exception {
		response.setContentType("application/pdf");
		Connection conexao = ConexaoUtil.getConexao();
		String path = _resourceLoader.getResource("classpath:Factura.jrxml").getURI().getPath();
		String dataHoje = utilitario.data_str();
		 
		dataHoje = dataHoje.replace("/", "");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("VendaId", vendaId); 
		param.put("Utilizador", _userAuth.getUserLogado().getEmail());
		OutputStream saida = response.getOutputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conexao); 
		ClearFiles();
		String pdfFile = "C:\\temp\\"+vendaId+".pdf";
		JasperExportManager.exportReportToPdfFile(jasperPrint,pdfFile);
		 
		byte[] bytes = Files.readAllBytes(Paths.get(pdfFile));
		return bytes; 
		//JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\nelson.diwidi\\source\\repos\\adminlte-3-angular\\src\\assets\\_resource\\"+vendaId+".pdf");
				 
	}
	private void ClearFiles() {	
		String pathPDF = "C:\\Users\\nelson.diwidi\\\\source\\repos\\adminlte-3-angular\\src\\assets\\_resource"; 
        try {  
            File directory = new File(pathPDF);
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            
        }
	}
	private String dateStringToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
		return formatter.format(new Date());
	
	}
	private Resource GeneratePDF(String pathPDF) throws Exception {
		
        try {
        	Path filePath =  Paths.get(pathPDF); 
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
              } 
            else {
                throw new Exception("File not found ");
             }
            
        } catch (MalformedURLException ex) {
            throw new Exception("File not found ", ex);
        }
	}
	private void DirectPrinter() {
		
		try {
			Desktop desktop = null;
			if(Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			desktop.print(new File("C:\\temp\\Factura.pdf"));
		} catch (IOException e) { 			e.printStackTrace(); 		}
	}
}
