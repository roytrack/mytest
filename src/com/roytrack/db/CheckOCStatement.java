package com.roytrack.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;







public class CheckOCStatement {
	
	static String path="";
	static String resultpath="";
	static int wrongCount=0;//��̬���� ��¼��
	static String filename="";
	public static int getCount() {
		return wrongCount;
	}
	public static void setCount(int count) {
		wrongCount = count;
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		CheckOCStatement.path = path;
	}
	public static String getResultpath() {
		return resultpath;
	}
	public static void setResultpath(String resultpath) {
		CheckOCStatement.resultpath = resultpath;
	}
	public static String getFilename() {
		return filename;
	}
	public static void setFilename(String filename) {
		CheckOCStatement.filename = filename;
	}

	public static boolean checkStateMent() {
		try{
        String fileName = path;
        
        File newFile = new File(fileName);
        File subFiles[]=newFile.listFiles();
        //��ɾ�����е��ļ�
        File file = new File(resultpath);  
        if(!file.exists()){
        	file.mkdir();
        }
        file=new File(resultpath+filename);
        if(file.exists()){
        	file.delete();
        }
      
        FileList(subFiles);
        createStatementInfo("�����ļ��д򿪹رս�������ȵļ�¼��Ϊ��"+wrongCount);//��¼ �� �رղ��ȵ�����
        return true;
		}catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}
        
	}
	
	private static void FileList(File file[]){
		File ff=null;
		for(int i=0;i<file.length;i++){
			ff=file[i];
			if(ff.isDirectory()&&ff.listFiles().length>0){
				File files[]=ff.listFiles();
				FileList(files);
			}else{
				if(ff.getName().endsWith("java")){
					readFileByLines(ff.getPath());
				}
			}
		}
		
	}
	private static void readFileByLines(String fileName){
        File file = new File(fileName);
        String result = null;
        BufferedReader reader = null;
        int openCount = 0;
        int closeCount = 0;
        String method=null,onMethod=null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	if(tempString.indexOf("(")>-1&&(tempString.toUpperCase().indexOf("PUBLIC")>-1||tempString.toUpperCase().indexOf("PRIVATE")>-1||tempString.toUpperCase().indexOf("PROTECTED")>-1)){
            		
            		method = tempString;
            		  if(openCount !=0 && openCount !=closeCount){
                      	result = ""+fileName + " ���ڷ���:"+ onMethod+ " �򿪵�st������"+openCount+"    �رյ�st������"+ closeCount;
	                       try{
	                    	   createStatementInfo(result);
	                    	   wrongCount++; 
	                       }catch(Exception e){
	                    	   e.printStackTrace();
	                       }
                      }
            		openCount = 0;
            		closeCount = 0;
            	}else{
            		onMethod =  method;
            		if(tempString.toUpperCase().indexOf("OPENRESULTSET")>-1||tempString.toUpperCase().indexOf("EXECUTEQUERY")>-1){
                		openCount++;
                	}
                	if(tempString.toUpperCase().indexOf("GETSTATEMENT().CLOSE()")>-1||tempString.toUpperCase().indexOf("CLOSERESULTSETFINAL")>-1){
                		closeCount++;
                	}
            	}
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	
	public static void createStatementInfo(String writeStr){
		try{
			String fname = resultpath+filename;

			File filename = new File(fname);
			//����ļ������ھʹ�������
			if (!filename.exists()) {
	            filename.createNewFile();
	        }
			
            FileInputStream fis=new FileInputStream(fname); 
            InputStreamReader isr=new InputStreamReader(fis); 
            BufferedReader br=new BufferedReader(isr); 
            String bead ;
            StringBuffer readStr = new StringBuffer();
            while((bead = br.readLine()) !=null){
            	readStr.append(bead).append("\r\n");
            }
            br.close(); 
            
            //�ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
	        readStr.append(writeStr).append("\r\n");
	        
	        FileOutputStream fos=new FileOutputStream(fname); 
	        OutputStreamWriter osw=new OutputStreamWriter(fos); 
	        BufferedWriter bw=new BufferedWriter(osw); 
	        bw.write(readStr.toString()); 
	        bw.close(); 
	        fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
