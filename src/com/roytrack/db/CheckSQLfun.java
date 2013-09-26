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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;




public class CheckSQLfun {
	private static List sqlFunction =new ArrayList();
	private static long count=0;
	static String path="";
	static String resultpath="";
	static String filename="";
	public static long getCount() {
		return count;
	}
	public static void setCount(long count) {
		CheckSQLfun.count = count;
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		CheckSQLfun.path = path;
	}
	public static String getResultpath() {
		return resultpath;
	}
	public static void setResultpath(String resultpath) {
		CheckSQLfun.resultpath = resultpath;
	}
	public static String getFilename() {
		return filename;
	}
	public static void setFilename(String filename) {
		CheckSQLfun.filename = filename;
	}

	
	public  static void CheckSql() {
		fulllist();
        String fileName = path;
        
        File newFile = new File(fileName);
        File subFiles[]=newFile.listFiles();
        // ��ɾ�����е��ļ�
        File file = new File(resultpath);  
        if(!file.exists()){
        	file.mkdir();
        }
        file=new File(resultpath+filename);
        if(file.exists()){
        	file.delete();
        }
        FileList(subFiles);
        createStatementInfo("�����ļ��а���sql����������Ϊ��"+count);

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
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void fulllist(){
		sqlFunction.add("to_Char(".toUpperCase());
		sqlFunction.add("chr(".toUpperCase());
		sqlFunction.add("cube".toUpperCase());
		sqlFunction.add("to_date(".toUpperCase());
		sqlFunction.add("Trim(".toUpperCase());
		sqlFunction.add("floor(".toUpperCase());
		sqlFunction.add("sign(".toUpperCase());
		sqlFunction.add("abs(".toUpperCase());
		sqlFunction.add("NVL(".toUpperCase());
		sqlFunction.add("SubStr(".toUpperCase());
		sqlFunction.add("length(".toUpperCase());
		sqlFunction.add("to_number(".toUpperCase());
		sqlFunction.add("decode(".toUpperCase());
		sqlFunction.add("decode(".toUpperCase());
		sqlFunction.add("convert(".toUpperCase());
		sqlFunction.add("char(".toUpperCase());
		sqlFunction.add("with cube".toUpperCase());
		sqlFunction.add("datediff(".toUpperCase());
		sqlFunction.add("cast(".toUpperCase());
		sqlFunction.add("to_number(".toUpperCase());
		sqlFunction.add("RTrim(".toUpperCase());
		sqlFunction.add("lTrim(".toUpperCase());
		sqlFunction.add("IsNull(".toUpperCase());
		sqlFunction.add("LEFT(".toUpperCase());
		sqlFunction.add("len(".toUpperCase());
		sqlFunction.add("SubString(".toUpperCase());
		sqlFunction.add("month(".toUpperCase());
		sqlFunction.add("cast(".toUpperCase());
		sqlFunction.add("right(".toUpperCase());
		sqlFunction.add("YEAR(".toUpperCase());
		sqlFunction.add("Char(".toUpperCase());
		sqlFunction.add("chr(".toUpperCase());
		sqlFunction.add("cube".toUpperCase());
		sqlFunction.add("DATE(".toUpperCase());
		sqlFunction.add("TIMESTAMP(".toUpperCase());
		sqlFunction.add("LEFT(".toUpperCase());
		sqlFunction.add("length(".toUpperCase());
		sqlFunction.add("mod(".toUpperCase());
		sqlFunction.add("cast(".toUpperCase());
		sqlFunction.add("right(".toUpperCase());
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
					if(ff.getName().toUpperCase().equals("CHECKSQLFUN.JAVA")){continue;	
					}else if(ff.getName().toUpperCase().equals("ERRORANALYSIS.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("EXECUTEREGRESSION.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("GENERATEEXCEL.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("GENERATEHTML.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("INITREGRESSION.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("RESULTCOMPARE.JAVA")){continue;
					}else if(ff.getName().toUpperCase().equals("TABLESQL.JAVA")){continue;	
					}
					readFileByLines(ff.getPath());
				}
			}
		}
		
	}
	private static void readFileByLines(String fileName){
        File file = new File(fileName);
        String result = null;
        BufferedReader reader = null;
        StringBuffer sb1 = new StringBuffer() ;
        String method=null,onMethod=null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line=0;
            boolean iszhushi=false;
            while ((tempString = reader.readLine()) != null) {        	
            	line++;
            	if(iszhushi&&!Pattern.compile(".*?\\*/"+"[ \\t\\n\\x0B\\f\\r]*").matcher(tempString).matches()){//�����ע�Ϳ鲢���Ҳ���ע�ͽ�β����˵�����
            		continue;
            	}
            	if(Pattern.compile("[ \\t\\n\\x0B\\f\\r]"+"*"+"/\\*.*?").matcher(tempString).matches()){//�����������\*��ͷ������Ϊ��ע�Ϳ�ʼ��� ע�ͱ���Ϊtrue
            		iszhushi=true;
            		continue;
            	}
            	if(Pattern.compile(".*?\\*/"+"[ \\t\\n\\x0B\\f\\r]*").matcher(tempString).matches()){//�����������*/��β����Ϊ��ע�ͽ�β
            		iszhushi=false;
            		continue;
            	}
            	if(Pattern.compile("[ \\t\\n\\x0B\\f\\r]"+"*"+"//.*?").matcher(tempString).matches()){//����Ƿ�б�߿�ͷ�ľ�ֱ�ӹ���
            		continue;
            	}
            	if(tempString.indexOf("(")>-1&&(tempString.toUpperCase().indexOf("PUBLIC")>-1||tempString.toUpperCase().indexOf("PRIVATE")>-1||tempString.toUpperCase().indexOf("PROTECTED")>-1)){
            		  method = tempString;
            		  if(sb1.length()>0){
                      	result = ""+fileName + " ���ڷ���:"+ onMethod+ sb1.toString()+"\r\n";
	                       try{
	                    	   createStatementInfo(result);
	                       }catch(Exception e){
	                    	   e.printStackTrace();
	                       }
	                       sb1.setLength(0);
	                      }
            	}else{
            		onMethod =  method;
            		if(checkString(tempString.toUpperCase())){
            			sb1.append("\r\n"+"line:"+line+" "+tempString.trim());
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
	
	private static boolean checkString(String linestr){	
		String strstart=".*?[\\.[0-9][A-Z]]";
		String strend="\\(.*?";
		String strspace="[ \\t\\n\\x0B\\f\\r]";
		boolean temp=false;
		for(int i=0;i<sqlFunction.size();i++){
			if(linestr.toUpperCase().indexOf((String)sqlFunction.get(i))>-1){
				if(!Pattern.compile(strstart+((String)sqlFunction.get(i)).replace("(", "")+strend).matcher(linestr).matches()){						
					if(((String)sqlFunction.get(i)).toUpperCase().equals("DATE(")){
						if(!Pattern.compile(".*?NEW"+strspace+"+"+"DATE"+strend).matcher(linestr).matches()){//ר�Ź���new date
							temp= true;
						}
					}else{
						temp= true;
					}
				}
			}
		}
		if(temp){
			count++;
		}
		return temp;
	}


}
