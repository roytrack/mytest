package com.roytrack.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSql {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		
		
		String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=directreport";
		Connection con=DriverManager.getConnection(url,"sa","111");
		 Statement st= con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM MII_Enterprise");
		while(rs.next()){
			
			System.out.println(rs.getString("XM1"));
		}
		
		
		
	}

}
