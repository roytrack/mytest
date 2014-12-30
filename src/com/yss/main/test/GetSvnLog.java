package com.yss.main.test;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;



import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;



import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;



/**
 * ��������jar���󵥶����У�Ҳ���Ժ�������ant��
 * main���� �ֹ��������·����showsvnlog��������ParametersInfo.xml������
 * */


public class GetSvnLog {
	 static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	 static String xlsPath="";//excel��·�� 
	 //http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.1/directreport
	// static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/trunk/directreport"; //svn·��
	 // static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.1/directreport"; //svn·��
	 //static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.2/directreport"; //svn·��
	 static String url ="http://172.16.30.206/svn/DirectReportNew/sourcecode/trunk/directRreportNew";
	 static String name = "ruanchangming";//�û���
	 static String password = "123456";//����
	 static String today=sdf2.format(new Date());
	 static int rownum=2;//����
	 static long startRevision = 2;
	 static long endRevision = -1; //HEAD (the latest) revision
	 static WritableSheet todaySheet=null;
	 static WritableWorkbook wwb=null;
	 static String resultpath="";
public static void main(String[] args) throws Exception {//main���������ֶ���ȡ��1�汾�����°汾��excel
	DAVRepositoryFactory.setup( );
	 SAXReader reader = new SAXReader();  
		Document doc=null;
	xlsPath="SvnLog.xls";
    File ff=new File("D:\\ccid_versionManageRelated\\svnauth");//svn������Ȩ��·��
    if(!ff.exists())ff.mkdirs();
 //   setbeginVersion();//������ʼ�汾��
    ExcelTitle();//����excel�ı���
    SVNRepository repository = null;
    try {
        repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( ff,name, password,true );
        repository.setAuthenticationManager( authManager );
        Collection logEntries = null;
        /* ��ȡ���°汾�ţ�������*/
        long latest =repository.getLatestRevision();
        File tmp=new File("D:\\ccid_versionManageRelated\\svnauth\\version.tmp");
        tmp.createNewFile();
        FileWriter fw=new FileWriter(tmp);
        fw.write(String.valueOf(latest));
        fw.close();
        
        /*��ʼ��ȡ��־��������־*/
        logEntries = repository.log( new String[] { "" } , null , 4 , endRevision , true , true );
        for ( Iterator entries = logEntries.iterator( ); entries.hasNext( ); ) {
            SVNLogEntry logEntry = ( SVNLogEntry ) entries.next( );
            if ( logEntry.getChangedPaths( ).size( ) > 0 ) {  
                Set changedPathsSet = logEntry.getChangedPaths( ).keySet( );
                StringBuffer sbf=new StringBuffer();
                for ( Iterator changedPaths = changedPathsSet.iterator( ); changedPaths.hasNext( ); ) {
                    SVNLogEntryPath entryPath = ( SVNLogEntryPath ) logEntry.getChangedPaths( ).get( changedPaths.next( ) );
                    sbf.append( " "
                            + entryPath.getType()
                            + " "
                            + entryPath.getPath()
                            + ( ( entryPath.getCopyPath() != null ) ? " (from "
                                    + entryPath.getCopyPath() + " revision "
                                    + entryPath.getCopyRevision() + ")" : "")+"\r\n" );

                }
               XlsWriteLine(String.valueOf(logEntry.getRevision()) , sdf.format(logEntry.getDate( )), logEntry.getAuthor( ), logEntry.getMessage( ), sbf.toString());
            }
        }
        wwb.write();
		 wwb.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
}

	/**
	 *�÷����Ǹ�ant׼���ģ���ʱ����ant�����������������·����ParametersInfo.xml��ȡ
	 * @throws IOException 
	 */
/*	public void SvnLog() throws IOException{ 
		DAVRepositoryFactory.setup( );
		 SAXReader reader = new SAXReader();  
			Document doc=null;
			File f=new File("/ParametersInfo.xml");
			
			try {
				if(f.exists()){//�ļ����ھͶ�
				doc = reader.read(f);
				Element root=doc.getRootElement();
				Element MailParameters=root.element("MailParameters");
				Element Path = MailParameters.element("Path");
				resultpath=Path.getTextTrim();
				}
			} catch (DocumentException e1) {
				resultpath="/YssReport/";
			}  
			
		xlsPath=resultpath+"/SvnLog.xls";
	    File ff=new File("D:\\yss_gz_ant\\svnauth");//svn������Ȩ��·��
	    if(!ff.exists())ff.mkdirs();
	    setbeginVersion();//������ʼ�汾��
	    ExcelTitle();//����excel�ı���
	    SVNRepository repository = null;
	    try {
	        repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
	        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( ff,name, password,true );
	        repository.setAuthenticationManager( authManager );
	        Collection logEntries = null;
	         ��ȡ���°汾�ţ�������
	        long latest =repository.getLatestRevision();
	        File tmp=new File("D:\\yss_gz_ant\\svnauth\\version.tmp");
	        tmp.createNewFile();
	        FileWriter fw=new FileWriter(tmp);
	        fw.write(String.valueOf(latest));
	        fw.close();
	        
	        ��ʼ��ȡ��־��������־
	        logEntries = repository.log( new String[] { "" } , null , startRevision , endRevision , true , true );
	        for ( Iterator entries = logEntries.iterator( ); entries.hasNext( ); ) {
	            SVNLogEntry logEntry = ( SVNLogEntry ) entries.next( );
	            if ( logEntry.getChangedPaths( ).size( ) > 0 ) {  
	                Set changedPathsSet = logEntry.getChangedPaths( ).keySet( );
	                StringBuffer sbf=new StringBuffer();
	                for ( Iterator changedPaths = changedPathsSet.iterator( ); changedPaths.hasNext( ); ) {
	                    SVNLogEntryPath entryPath = ( SVNLogEntryPath ) logEntry.getChangedPaths( ).get( changedPaths.next( ) );
	                    sbf.append( " "
	                            + entryPath.getType()
	                            + " "
	                            + entryPath.getPath()
	                            + ( ( entryPath.getCopyPath() != null ) ? " (from "
	                                    + entryPath.getCopyPath() + " revision "
	                                    + entryPath.getCopyRevision() + ")" : "")+"\r\n" );

	                }
	               XlsWriteLine(String.valueOf(logEntry.getRevision()) , sdf.format(logEntry.getDate( )), logEntry.getAuthor( ), logEntry.getMessage( ), sbf.toString());
	            }
	        }
	        wwb.write();
			 wwb.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	} */
	
	/**
	 * д��excel�ı�ͷ��·��ͨ����һ�㴫���������Ҷ�����ྲ̬����
	 * */
	static void ExcelTitle(){
		File xls=new File(xlsPath);
		
		 try {		
			 		if(xls.exists()){
			 		Workbook old=Workbook.getWorkbook(xls);
			 		wwb =Workbook.createWorkbook(xls,old);
			 		if(wwb.getSheet(today)!=null){//����������ݴ��� ��ɾ��
						wwb.removeSheet(wwb.getNumberOfSheets()-1);
			 			}
			 		}else{
			 			wwb=Workbook.createWorkbook(xls);
			 		}
				 	 
					 todaySheet=wwb.createSheet(today, wwb.getNumberOfSheets());
					 // �汾�� �ύ����ʱ��  �ύ�� ��ע  �Ķ��ļ�   
					 WritableCellFormat [] format=formatList();
					 Label reversion=new Label(0, 0, "svn_log");
					 reversion.setCellFormat(format[0]);
					 todaySheet.addCell(reversion);
					 todaySheet.mergeCells(0, 0, 4, 0);
					 
					 reversion=new Label(0, 1, "�汾��");
					 reversion.setCellFormat(format[1]);
					 todaySheet.addCell(reversion);
					 
					 reversion=new Label(1, 1, "�ύ����ʱ��");
					 reversion.setCellFormat(format[1]);
					 todaySheet.addCell(reversion);
					 
					 reversion=new Label(2, 1, "�ύ��");
					 reversion.setCellFormat(format[1]);
					 todaySheet.addCell(reversion);
					 
					 reversion=new Label(3, 1, "��ע");
					 reversion.setCellFormat(format[1]);
					 todaySheet.addCell(reversion);
					 
					 reversion=new Label(4, 1, "�Ķ��ļ�");
					 reversion.setCellFormat(format[1]);
					 todaySheet.addCell(reversion);
					
					 
					 //�����п�
					 todaySheet.setColumnView(0,10);
					 todaySheet.setColumnView(1,30);
					 todaySheet.setColumnView(2,20);
					 todaySheet.setColumnView(3,100);
					 todaySheet.setColumnView(4,100);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		 }
	
	/**
	 * д��excel��������
	 * @param
	 * reversion commitTime  author  comment changeFile
	 * �汾�� �ύ����ʱ��  �ύ�� ��ע  �Ķ��ļ�   
	 * */
	static void XlsWriteLine(String reversion,String  commitTime,String author,String comment,String changeFile){
		
		try {  
			 WritableCellFormat[] format=formatList();
			 Label theLab=null;
			 Number theversion=new Number(0, rownum, Double.parseDouble(reversion));
			 theversion.setCellFormat(format[2]);
			 todaySheet.addCell(theversion);
			 theLab=new Label(1, rownum, commitTime);
			 theLab.setCellFormat(format[2]);
			 todaySheet.addCell(theLab);
			 theLab=new Label(2, rownum, author);
			 theLab.setCellFormat(format[2]);
			 todaySheet.addCell(theLab);
			 theLab=new Label(3, rownum, comment);
			 theLab.setCellFormat(format[2]);
			 todaySheet.addCell(theLab);
			 theLab=new Label(4, rownum, changeFile);
			 theLab.setCellFormat(format[2]);
			 todaySheet.addCell(theLab);
			 rownum++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		/**��Ԫ���ʽ����
		 * 1.title����С������
		 * 2.columnName��������
		 * 3.autoWrap�Զ����еĵ�Ԫ��
		 * */
		 static WritableCellFormat [] formatList(){
			 WritableCellFormat [] formats=new WritableCellFormat[3];
			 try {
			WritableFont wf1=new WritableFont(WritableFont.ARIAL, 22,WritableFont.BOLD, false);
			WritableCellFormat title=new WritableCellFormat(wf1);
			title.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //�߿���ʽ
			title.setAlignment(Alignment.CENTRE);
			title.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf2=new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD, false);
			WritableCellFormat columnName=new WritableCellFormat(wf2);
			columnName.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //�߿���ʽ
			columnName.setAlignment(Alignment.CENTRE);
			columnName.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf3=new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD, false);
			WritableCellFormat autoWrap=new WritableCellFormat(wf3);
			autoWrap.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //�߿���ʽ
			autoWrap.setVerticalAlignment(VerticalAlignment.CENTRE);
			autoWrap.setWrap(true);
			formats[0]=title;
			formats[1]=columnName;
			formats[2]=autoWrap;
			} catch (WriteException e) {
			}
			
			
			return formats;
		}
		 
	/**
	 * ���ÿ�ʼ�汾�ţ���ֹÿ�ζ���ͷ��ʼ�����ݲ��������
	 * @throws IOException 
	 * */	 
/*		 static void setbeginVersion() throws IOException{
			 File tmp=new File("D:\\ccid_versionManageRelated\\svnauth\\version.tmp");
			 if(!tmp.exists()){
				 tmp.createNewFile();
			 }else{
				 FileReader fr=new FileReader(tmp);
				 BufferedReader br=new BufferedReader(fr);
				 String top =br.readLine();
				 startRevision=Long.parseLong(top.trim());
				 br.close();
				 fr.close();
				 tmp.delete();
			 }
		 }*/
		
	
}
