package com.cibertec.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConexion {

	public static  Connection obtenerConexion() {
		Connection cn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bdProyectoDSW2?serverTimezone=CST","root","mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
