package com.roytrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.yss.util.YssException;

public class JdbcAndJson {

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
		
	class Product{
		private String uuid;
		private String parentuuid;
		@Expose
		private String productName;
		@Expose
		private String productCode;
		@Expose
		private String productUnit;
		private String order;
		@Expose
		private int isleaf;
		private int level;
		private String parent;
		@Expose
		private String mem;
		@Expose
		private LinkedList<Product> children =new LinkedList<Product>();
		public LinkedList<Product> getChildren() {
			return children;
		}
		public void setChildren(LinkedList<Product> children) {
			this.children = children;
		}
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public String getParentuuid() {
			return parentuuid;
		}
		public void setParentuuid(String parentuuid) {
			this.parentuuid = parentuuid;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public String getMem() {
			return mem;
		}
		public void setMem(String mem) {
			this.mem = mem;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getProductUnit() {
			return productUnit;
		}
		public void setProductUnit(String productUnit) {
			this.productUnit = productUnit;
		}
		public int getIsleaf() {
			return isleaf;
		}
		public void setIsleaf(int isleaf) {
			this.isleaf = isleaf;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getParent() {
			return parent;
		}
		public void setParent(String parent) {
			this.parent = parent;
		}
		public void printString(){
			System.out.println("uuid is "+uuid+"@ name is "+productName+"@ code is "+productCode+"@ unit is "+productUnit+"@ order is "+order +
					" @ isleaf is "+isleaf+" @ level is "+ level+" @ parent is "+parent+" @ mem is "+ mem);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		ResultSet rs=st.executeQuery("select uuid, name,code,unit,orders, isleaf, level,parentuuid,scope from T_NEWGEN_PRODUCT where newgenproducttype='NEWGENPRODUCT_PRODUCT' ");
		LinkedList<Product> list=new LinkedList<Product>(); 
		while(rs.next()){
			Product pro= ( new JdbcAndJson()).new Product();
			pro.setIsleaf(rs.getInt("isleaf"));
			pro.setLevel(rs.getInt("level"));
			pro.setUuid(rs.getString("uuid"));
			pro.setProductName(rs.getString("name"));
			pro.setProductCode(rs.getString("code"));
			pro.setProductUnit(rs.getString("unit"));
			if(rs.getString("uuid").equals("420")){
				System.out.println("@"+rs.getString("unit"));
			}
			pro.setOrder(rs.getString("orders"));
			pro.setParent(rs.getString("parentuuid"));
			pro.setMem(rs.getString("scope"));
			list.add(pro);
		}
		
		for (int i = 4; i > 1; i--) {
			for (Iterator<Product> iter1=list.iterator();iter1.hasNext();) {
				Product p=iter1.next();
				if (p.getLevel() == i) {
					for (int j=0;j<list.size();j++) {
						Product q=list.get(j);
						if (p.getParent().equals(q.getUuid())) {
							q.getChildren().add(p);
							p.printString();
							iter1.remove();
							System.out.println(list.size());
						}
					}
				}
			}
		}
		GsonBuilder gb=new GsonBuilder();
		gb.excludeFieldsWithoutExposeAnnotation();
		Gson g=gb.create();
		String jsonStr=g.toJson(list);
		jsonStr=jsonStr.replace(",\"children\":[]", "");
		System.out.println(jsonStr);
		
		
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
