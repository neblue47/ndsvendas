package com.nds.api.ndsvendas.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoUtil {

	private static Connection conn;
	private static String url  = "jdbc:mysql://localhost/ndsvendasbdxml";
	private static String user = "root";
	private static String pass = "sistema47";
	public static Connection getConexao(){
	 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			System.out.print("Conexao nao efectuada com sucesso..."+e.getMessage());
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		}
	 
		return conn;
	}
}
