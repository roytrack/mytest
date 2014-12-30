package com.ysstech.db;
import java.sql.*;


public class testConnectJdbc {
	
	public static void main(String[] args){
		try{
		byte[] bpass;
		String encodePs= "FF03CD31CD319E759700FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FB70";
		bpass=encodePs.getBytes("gb2312");
		System.out.println(encodePs );
		for(byte b:bpass)System.out.print( (char)b);
		System.out.println( );
		System.out.println( (char)bpass[3]);
		System.out.println("%%%");
		String plainPs=decodePass(bpass);
		System.out.print( "!!!!!"+plainPs);
		byte[] t=encodePass("1");
		for(byte b:t)System.out.print( (int)b);
		System.out.println( );
		System.out.println(new String(t) );
		System.out.println(decodePass(encodePass("1")));  //加密解密后输出一致，说明是这个方法
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
//		
		
//		String pass="111";
//		byte[] bpass;
//		byte[] bpass1=new byte[200];
//		int ltmp,i;
//		//todo应该先用随即数填充bpass1
//		bpass=pass.getBytes();
//		ltmp=bpass.length-1;
//		System.out.println("ltmp:  "+ltmp );
//		for(i=0;i<=ltmp;i++){
//			bpass1[ltmp+2-i]=bpass[i];
//			System.out.println("bpass1[ltmp+2-i]:  "+bpass[i] );
//		}
//		bpass1[1]=(byte)(ltmp/2);
//		bpass1[198]=(byte)(ltmp+1-bpass1[1]);
//		ltmp=0;
//		for(i=0;i<=198;i++){
//			ltmp+=bpass1[i];
//			if((i%2)==0){
//				bpass1[i]=(byte)(255-bpass1[i]);
//				ltmp+=bpass1[i];
//			}
//		}
//		bpass1[i]=(byte)((ltmp+bpass1[0]+bpass1[i-1])%256);
//		for(byte b:bpass1)System.out.print( (int)b +"@");
//		System.out.println();
//		for(byte b:bpass1)System.out.print( (char)b);
		
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	//	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","rcm","rcm");
		//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.252:1521:oradev","nhfund","nhfund");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.71:1521:zdcw","bxfund","bxfund");
		PreparedStatement pstm=con.prepareStatement("select 1111 from dual");
//		pstm.setString(1, "tatest");
		ResultSet rs=pstm.executeQuery();
		while(rs.next()){
			System.out.println( rs.getInt(1));
//			int i=0;
//			i++;
//		byte[] bArray=rs.getBytes("fpassword");
//		System.out.println(decodePass(bArray));
		//for(byte b:bArray)System.out.print( (int)b +"@");
//		if(i>3)break;
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * //密码编码过程
	 * @param pass String
	 * @return byte[]
	 */
	public static byte[] encodePass(String pass){
		byte[] bpass;
		byte[] bpass1=new byte[200];
		int ltmp,i;
		//todo应该先用随即数填充bpass1
		bpass=pass.getBytes();
		ltmp=bpass.length-1;
		for(i=0;i<=ltmp;i++){
			bpass1[ltmp+2-i]=bpass[i];
		}
		bpass1[1]=(byte)(ltmp/2);
		bpass1[198]=(byte)(ltmp+1-bpass1[1]);
		ltmp=0;
		for(i=0;i<=198;i++){
			ltmp+=bpass1[i];
			if((i%2)==0){
				bpass1[i]=(byte)(255-bpass1[i]);
				ltmp+=bpass1[i];
			}
		}
		bpass1[i]=(byte)((ltmp+bpass1[0]+bpass1[i-1])%256);
		return bpass1;
	}

	/**
	 * //密码解密
	 * @param bpass byte[]
	 * @return String
	 */
	public static String decodePass(byte[] bpass){
		byte[] bpass1;  //初始化bpass1 byte数组
	
		int i,ltmp;     //初始化i ltmp
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
