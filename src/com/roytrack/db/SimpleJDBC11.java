package com.roytrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yss.util.YssException;

public class SimpleJDBC11 {

	static Connection thecon=null;
	static Statement st=null;
	static String trcPath="";
	static{
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
			thecon= DriverManager.getConnection(
						"jdbc:sqlserver:// 172.16.30.236:1433;databaseName=directreport", "sa",
						"111");
			st=thecon.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	
	public static void main(String[] args) throws SQLException {
		
		//st.execute("INSERT INTO myuniquetab(name,sex) VALUES('rcm','m')");
		
//		try {
//		SimpleJDBC11 sj=new SimpleJDBC11();
//		sj.dbTrace("true");
//		sj.rcm();
//		sj.dbTrace("false");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void dbTrace(String flag) throws YssException {
		String sql;
	
		try {
			if ("true".equalsIgnoreCase(flag)) {
				sql = "alter session set events '10046 trace name context forever, level 8'";
				st.execute(sql);
				String	all=" SELECT d.value|| '/' ||lower(rtrim(i.instance, chr( 0 )))|| '_ora_' ||p.spid|| '.trc' trace_file_name ,p.sid,p.paddr  " +
						"from ( select p.spid as spid , s.sid as sid,s.paddr as paddr from v$mystat m,v$session s, v$process p where m.statistic# = 1 and s.sid = m.sid and p.addr = s.paddr) p,"+
						"( select t.instance from v$thread t,v$parameter v where v.name = 'thread' and (v.value = 0 or t.thread# = to_number(v.value))) i,"+
						"( select value from v$parameter where name = 'user_dump_dest' ) d";
				st.execute(all);
				ResultSet rs=st.getResultSet();
				all="";
				String filePath="";
				while(rs.next()){
					filePath=rs.getString(1);
					System.out.println(filePath);
					trcPath=filePath;
				}
			rs.close();
			} else {
				sql = " alter session set events '10046 trace name context off'";
				st.execute(sql);
			}
		}catch(Exception e){
		e.printStackTrace();	
		}
		}
	public void rcm(){
		try {
		
			PreparedStatement pstm = thecon
					.prepareStatement("select fterm from a2011047fcwvch");
			ResultSet rs1=null;
			ResultSet rs =null;
			 rs = pstm.executeQuery();
			 rs.next();
			 System.out.println("!!!!!"+rs.getInt(1));
			 rs.setFetchSize(5);
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
			System.out.println("#######################");
			rs1=rs;	
			rs1.setFetchSize(3);
			while (rs1.next()) {
				System.out.println(rs1.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
