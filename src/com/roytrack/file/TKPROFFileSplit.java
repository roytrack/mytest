package com.roytrack.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.crypto.Data;

public class TKPROFFileSplit {
	public static void main(String[] args) throws Exception {
		File f=new File("D:\\v880\\tkprofReport.txt");
		FileInputStream  fis=new FileInputStream(f);
		byte[] dataInbyte=new byte[(int)f.length()];
		fis.read(dataInbyte);
		fis.close();
		String data=new String (dataInbyte,"GBK");
		String[] splitData=data
				//.replaceAll("[\r\n]{6}", "\r\n\r\n").replaceAll("[\r\n]{5}", "\r\n\r\n").replaceAll("[\r\n]{4}", "\r\n\r\n").replaceAll("[\r\n]{3}", "\r\n\r\n")
				.split("\r\n\r\n");
		StringBuffer bf=new StringBuffer();
		for(int j=0;j<splitData.length;j++){
			if( splitData[j].length()<=4){
				bf.append("@@"+j);
			}
		}
		System.out.println(splitData.length);
		System.out.println(bf.toString());
		System.out.println("%%%");
		File ff=new File("D:\\v880\\thesql.txt");
		if(!ff.exists()){
			ff.createNewFile();
		}else{
			ff.delete();
			ff.createNewFile();
		};
		FileOutputStream fos=new FileOutputStream(ff);
		
		int i=1;
		for(String line:splitData){
			
			fos.write(line.getBytes());
			fos.write(("\r\n@@@@@@#"+i+"#@@@@@@\r\n").getBytes());
			i++;
			//////报告里面3，8等以5递增就是sql
		}
		fos.close();
		String getExecute =splitData[4+17*5];
		System.out.println(getExecute.length());
		System.out.println(getExecute);
		String result=getExecute.substring(getExecute.indexOf("Execute"), getExecute.indexOf("Execute")+16);
		String[] finalresult=result.split("\\s+");
		System.out.println(finalresult[0]+"@@@"+finalresult[1]);
	}
	
	

}
