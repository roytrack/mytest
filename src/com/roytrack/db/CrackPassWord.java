package com.roytrack.db;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CrackPassWord {
public static void main(String[] args) {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	 
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.100.71:1521:orcl", "BXFUND",
				"BXFUND");
		PreparedStatement pstm = con
				.prepareStatement("select distinct fuser,fpassword from lusers order by fuser");
		ResultSet rs =null;
		 rs = pstm.executeQuery();
		 File f=new File("ps.txt");
		 while(f.exists())f.delete();
		 f.createNewFile();
		 FileOutputStream fis=new FileOutputStream(f);
		while (rs.next()) {
			String aa=rs.getString(1)+"\t\t"+decodePass(rs.getBytes(2))+"\r\n";
			System.out.println(aa);
			fis.write(aa.getBytes());
			
		}
		System.out.println("#######################");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	/**
	 * //密码解密
	 * @param bpass byte[]
	 * @return String
	 */
	public static String decodePass(byte[] bpass){
		byte[] bpass1;
		int i,ltmp;
		ltmp=(int)bpass[0]+bpass[bpass.length-2];
		for(i=0;i<=bpass.length-2;i++){
			if((i%2)==0){
				ltmp+=bpass[i];
				bpass[i]=(byte)(255-bpass[i]);
			}
			ltmp+=bpass[i];
		}
		if((byte)(ltmp%256)!=bpass[i]){ return ""; //校验错误
		}
		ltmp=bpass[1]+bpass[i-1]-1;
		bpass1=new byte[ltmp+1];
		for(i=0;i<=ltmp;i++){
			bpass1[ltmp-i]=bpass[i+2];
		}
		return new String(bpass1); //不能用bpass1.toString！
	}
}

