package com.nds.api.ndsvendas.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

 

public class Utilitarios {
	@Autowired private ResourceLoader resourceLoader ;
	public final String dtHora()
	{
		Calendar c = Calendar.getInstance(); 
		//c.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH); 
		Date data = c.getTime(); 
		DateFormat dtHora = DateFormat.getDateTimeInstance(); 
		return (dtHora.format(data));
	}

	public String getHoraAtual() {

		Date data = new Date();

		SimpleDateFormat hformatador = new SimpleDateFormat("HH:mm:ss");

		String sHora = hformatador.format(data);
		return sHora;
	}
	public final String getHora()
	{
		Calendar c = Calendar.getInstance(); 
		//c.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH); 
		Date data = c.getTime(); 
		DateFormat dtHora = DateFormat.getDateTimeInstance(); 
		return (dtHora.format(data));
	}
	public java.sql.Date data_registo()
	{
		java.sql.Date dt_registo = null;
		Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
        c.setTime(new Date()); // pega a data atual do sistema
        int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
        int mesAtual = c.get(Calendar.MONTH)+1; // MES
        int anoAtual = c.get(Calendar.YEAR);  // ANO
        String hoje = anoAtual+"/"+mesAtual+"/"+diaAtual;
        DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
	    java.util.Date a; 
		try {
			a = (java.util.Date) formate.parse(hoje);
			dt_registo = new java.sql.Date(a.getTime());
			} catch (ParseException e) { }
		return dt_registo;
	}
	
	public java.sql.Date data_registoHemoterapia()
	{
		java.sql.Date dt_registo = null;
		Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
        c.setTime(new Date()); // pega a data atual do sistema
        int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
        int mesAtual = c.get(Calendar.MONTH)+1; // MES
        int anoAtual = c.get(Calendar.YEAR);  // ANO
        String hoje = diaAtual+"/"+mesAtual+"/"+anoAtual;
        DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
	    java.util.Date a; 
		try {
			a = (java.util.Date) formate.parse(hoje);
			dt_registo = new java.sql.Date(a.getTime());
			} catch (ParseException e) { }
		return dt_registo;
	}
	public String data_str()
	{
		java.sql.Date dt_registo = null;
		Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
        c.setTime(new Date()); // pega a data atual do sistema
        int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
        int mesAtual = c.get(Calendar.MONTH)+1; // MES
        int anoAtual = c.get(Calendar.YEAR);  // ANO
        String hoje = anoAtual+"/"+mesAtual+"/"+diaAtual;
        DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
	    java.util.Date a; 
		try {
			a = (java.util.Date) formate.parse(hoje);
			dt_registo = new java.sql.Date(a.getTime());
			} catch (ParseException e) { }
		String ano = dt_registo.toString().substring(0,4); 
		String mes = dt_registo.toString().substring(5,7);
		String dia = dt_registo.toString().substring(8);
		String data =dia+"/"+mes+"/"+ano;
		return data;
	}
	public Calendar data (String dataEmTexto)
	{
		Calendar dataCalendar=null;
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataCalendar = Calendar.getInstance();
			dataCalendar.setTime(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		return dataCalendar;  
	}
	
	public java.sql.Date diaSql(String data) {
		
		Calendar dataNascimento = null;
		// fazendo a conversão da data
		try {
		 Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(date);
		} catch (ParseException e) {
		System.out.println("Erro de conversao da data: "+data);
		//para a execução do metodo
		}
		java.sql.Date diaSql = new java.sql.Date( dataNascimento.getTimeInMillis());
		return diaSql;
	}
	public java.sql.Date dataSql(String data)
	{
		String dia = data.substring(0,2);
		String mes = data.substring(2,4);
		String ano = data.substring(4);
		String dataSql = ano+"/"+mes+"/"+dia;
		java.sql.Date dataf=null;	
		DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
	    java.util.Date a; 
		try {
			a = (java.util.Date) formate.parse(dataSql);
			dataf = new java.sql.Date(a.getTime());
			} catch (ParseException e) { }
	return dataf;
	}
	
	public String dataBanco(String data)
	{
		String dia = data.substring(0,2);
		String mes = data.substring(2,4);
		String ano = data.substring(4);
		String dataSql = ano+"-"+mes+"-"+dia;
		
	return dataSql;
	}
	public String dataBanco(Date data)
	{
	String dataSql = data.getYear()+"-"+data.getMonth()+"-"+data.getDay();
	System.out.println(data.toString());	
	return dataSql;
	}
	public String dataToPadrao(String data)
	{
		if(data!=null){
			String dia = data.substring(8);
			String mes = data.substring(5,7);
			String ano = data.substring(0,4);
			String dataPadrao = dia+"/"+mes+"/"+ano;
			return dataPadrao;
		}
		
		return "01/01/0001";
	}
	
	public  String horaAtual()
	 {
	  Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
	        c.setTime(new Date()); // pega a data atual do sistema
	        String agora = ""+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
	        return agora;
	 }
	
	public  int diaAtual()
	 {
			Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
	        c.setTime(new Date()); // pega a data atual do sistema
	        int hoje = c.get(Calendar.DAY_OF_WEEK);
	        return hoje;
	 }
	
	// O Metodo q vai verificar se a data de agendamento � superior a data atual 
	// em relacao a data do sistema! 
	public int dataVencido(String data){
		int rs = 0; 
		try {
			Date dSistema = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dataHj = sdf.format(dSistema);
			Date diaH = sdf.parse(dataHj);
			Date diaBd = sdf.parse(data);
			rs =  diaH.compareTo(diaBd);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return rs; // se retorna valor inferior a 0 (Data do agendamento inferior ou igual a data atual  
	} 
	public long diffData(String data1,String data2){
		long rs = 0;
		try {
			// Dando um exemplo: quantos dias se passam desde 07/09/1822 até 05/06/2006?
	        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
	        df.setLenient(false);
	        Date d1 = df.parse (data1);
	        Date d2 = df.parse (data2);	        
	        long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
	        rs = dt / 86400000L;
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public Long fone(String temp)
	{
		//244 924-044-145
		long aux =0;
		try
		{ 
		if(!temp.trim().equals("")){
			String cod  = temp.substring(0,3);
			String num1 = temp.substring(4,7);
			String num2 = temp.substring(8,11);
			String num3 = temp.substring(12);
			String fonet= cod+num1+num2+num3;
			aux = Long.parseLong(fonet);
		}
		
		
		}
		catch(NumberFormatException er){System.out.print(er);}
		return aux;
	}
	public String Editfone(long temp)
	{
		//244924044145
		String fonetmp = ""+temp;
		String aux ="";
		try
		{
		String cod  = fonetmp.substring(0,3);
		String num1 = fonetmp.substring(3,6);
		String num2 = fonetmp.substring(6,9);
		String num3 = fonetmp.substring(9);
		
		String fonet= cod+" "+num1+"-"+num2+"-"+num3;
		aux = fonet;
		
		}
		catch(NumberFormatException er){System.out.print(er);}
		return aux;
	}
	
	public String checkedH(String a)
	{
		String check="";
		if(a.equals("Masculino"))
		{
			check="checked = 'checked'";
		}
		return check;
	}
	public String checkedM(String a)
	{
		String check="";
		if(a.equals("Feminino"))
		{
			check="checked = 'checked'";
		}
		return check;
	}
	public String checkedEa(int a)
	{
		String check="";
		if(a == 1)
		{
			check="checked = 'checked'";
		}
		//System.out.print("Ativo "+check);
		return check;
	}
	public String checkedEi(int a)
	{
		String check="";
		if(a == 0)
		{
			check="checked = 'checked'";
		}
		//System.out.print("Inativo "+check);
		return check;
	}
	
	public String databd(String data)
	{
		String dia = data.substring(8);
		String mes = data.substring(5,7);
		String ano = data.substring(0,4);
		String datatl = dia+"/"+mes+"/"+ano;
		return datatl;
	}
	
public String transforma(String frase){
	
	frase = frase.toLowerCase();
    StringBuffer frase2 = new StringBuffer(frase);
    
    for(int i = 0; i < frase2.length(); i++){
        Character letra = frase2.charAt(i);
        if(i == 0){
            letra = Character.toUpperCase(letra);
            frase2.setCharAt(i, letra);
        }
        else if((i > 0) && (frase2.charAt(i - 1) == ' ')){
            letra = Character.toUpperCase(letra);
            frase2.setCharAt(i, letra);
        }
    }
        
    frase = frase2.toString(); 
	return frase;
}
		
	public String idade(String data)
	 {
	  int idade =0;
	  String mes = data.substring(5,7);
	  String ano = data.substring(0,4);
	  Calendar c = Calendar.getInstance();  
	        c.setTime(new Date());  
	        int mesAtual = c.get(Calendar.MONTH); // MES
	        int anoAtual = c.get(Calendar.YEAR);  // ANO
	        int mesNasc = Integer.parseInt(mes);
	        int anoNasc = Integer.parseInt(ano);
	        if(mesNasc>mesAtual)
	          idade = (anoAtual - anoNasc);
	        else
	          idade = (anoAtual - anoNasc);
	  return "" +idade;
	 }
	
	 
	public int horaVerificada(String aux1,String aux2) throws ParseException{
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		Date data1 = formatador.parse(aux1);
		Date data2 = formatador.parse(aux2);
		
		Time time1 = new Time(data1.getTime());
		Time time2 = new Time(data2.getTime());

		int res = time1.compareTo(time2);
		
		return res;
	}
	
	public String maquinaLogado(){
		String maquina = "";
		try {
			maquina = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maquina;
	}
	
	public String mesToString (int mes)
	{
		String mesString = "";
		switch(mes)
		{
		case 0: mesString = "JAN";
			break;
		case 1: mesString = "FEV";
			break;
		case 2: mesString = "MAR";
			break;
		case 3: mesString = "ABR";
			break;
		case 4: mesString = "MAI";
			break;
		case 5: mesString = "JUN";
			break;
		case 6: mesString = "JUL";
			break;
		case 7: mesString = "AGO";
			break;
		case 8: mesString = "SET";
			break;
		case 9: mesString = "OUT";
			break;
		case 10: mesString = "NOV";
			break;
		case 11: mesString = "DEZ";
			break;
		}
		
		return mesString;
	}
	
	public String mesToStringCompleto (int mes)
	{
		String mesString = "";
		switch(mes)
		{
		case 0: mesString = "Janeiro";
			break;
		case 1: mesString = "Fevereiro";
			break;
		case 2: mesString = "Março";
			break;
		case 3: mesString = "Abril";
			break;
		case 4: mesString = "Maio";
			break;
		case 5: mesString = "Junho";
			break;
		case 6: mesString = "Julho";
			break;
		case 7: mesString = "Agosto";
			break;
		case 8: mesString = "Setembro";
			break;
		case 9: mesString = "Outubro";
			break;
		case 10: mesString = "Novembro";
			break;
		case 11: mesString = "Dezembro";
			break;
		}
		
		return mesString;
	}
	
	public String logoImagem () throws IOException {
		String imagem = resourceLoader.getResource("classpath:NDV-0.png").getURI().getPath();
		return imagem;
	}
}

