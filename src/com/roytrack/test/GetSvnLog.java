package com.roytrack.test;

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
 * 可以依赖jar包后单独运行，也可以后期依赖ant。
 * main方法 手工输入输出路径，showsvnlog方法调用ParametersInfo.xml来做。
 * */


public class GetSvnLog {
	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	static String xlsPath="";//excel的路径
	//http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.1/directreport
	// static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/trunk/directreport"; //svn路径
	// static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.1/directreport"; //svn路径
	//static String url = "http://172.16.30.206/svn/DirectReport/sourcecode/branches/V1.2/directreport"; //svn路径
	static String url ="http://172.16.30.206/svn/DirectReportNew/sourcecode/trunk/directRreportNew";
	static String name = "ruanchangming";//用户名
	static String password = "123456";//密码
	static String today=sdf2.format(new Date());
	static int rownum=2;//行数
	static long startRevision = 2;
	static long endRevision = -1; //HEAD (the latest) revision
	static WritableSheet todaySheet=null;
	static WritableWorkbook wwb=null;
	static String resultpath="";
	public static void main(String[] args) throws Exception {//main方法用于手动获取从1版本到最新版本的excel
		DAVRepositoryFactory.setup( );
		SAXReader reader = new SAXReader();
		Document doc=null;
		xlsPath="SvnLog.xls";
		File ff=new File("D:\\ccid_versionManageRelated\\svnauth");//svn保存授权的路径
		if(!ff.exists())ff.mkdirs();
		//   setbeginVersion();//设置起始版本号
		ExcelTitle();//设置excel的标题
		SVNRepository repository = null;
		try {
			repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( ff,name, password,true );
			repository.setAuthenticationManager( authManager );
			Collection logEntries = null;
        /* 获取最新版本号，存下来*/
			long latest =repository.getLatestRevision();
			File tmp=new File("D:\\ccid_versionManageRelated\\svnauth\\version.tmp");
			tmp.createNewFile();
			FileWriter fw=new FileWriter(tmp);
			fw.write(String.valueOf(latest));
			fw.close();
        
        /*开始获取日志，遍历日志*/
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
	 *该方法是给ant准备的，到时候用ant调用起来，结果保存路径从ParametersInfo.xml读取
	 * @throws IOException
	 */
/*	public void SvnLog() throws IOException{ 
		DAVRepositoryFactory.setup( );
		 SAXReader reader = new SAXReader();  
			Document doc=null;
			File f=new File("/ParametersInfo.xml");
			
			try {
				if(f.exists()){//文件存在就读
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
	    File ff=new File("D:\\yss_gz_ant\\svnauth");//svn保存授权的路径
	    if(!ff.exists())ff.mkdirs();
	    setbeginVersion();//设置起始版本号
	    ExcelTitle();//设置excel的标题
	    SVNRepository repository = null;
	    try {
	        repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( url ) );
	        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( ff,name, password,true );
	        repository.setAuthenticationManager( authManager );
	        Collection logEntries = null;
	         获取最新版本号，存下来
	        long latest =repository.getLatestRevision();
	        File tmp=new File("D:\\yss_gz_ant\\svnauth\\version.tmp");
	        tmp.createNewFile();
	        FileWriter fw=new FileWriter(tmp);
	        fw.write(String.valueOf(latest));
	        fw.close();
	        
	        开始获取日志，遍历日志
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
	 * 写入excel的表头，路径通过上一层传过来，并且定义成类静态变量
	 * */
	static void ExcelTitle(){
		File xls=new File(xlsPath);

		try {
			if(xls.exists()){
				Workbook old=Workbook.getWorkbook(xls);
				wwb =Workbook.createWorkbook(xls,old);
				if(wwb.getSheet(today)!=null){//如果该天数据存在 就删除
					wwb.removeSheet(wwb.getNumberOfSheets()-1);
				}
			}else{
				wwb=Workbook.createWorkbook(xls);
			}

			todaySheet=wwb.createSheet(today, wwb.getNumberOfSheets());
			// 版本号 提交日期时间  提交人 备注  改动文件
			WritableCellFormat [] format=formatList();
			Label reversion=new Label(0, 0, "svn_log");
			reversion.setCellFormat(format[0]);
			todaySheet.addCell(reversion);
			todaySheet.mergeCells(0, 0, 4, 0);

			reversion=new Label(0, 1, "版本号");
			reversion.setCellFormat(format[1]);
			todaySheet.addCell(reversion);

			reversion=new Label(1, 1, "提交日期时间");
			reversion.setCellFormat(format[1]);
			todaySheet.addCell(reversion);

			reversion=new Label(2, 1, "提交人");
			reversion.setCellFormat(format[1]);
			todaySheet.addCell(reversion);

			reversion=new Label(3, 1, "备注");
			reversion.setCellFormat(format[1]);
			todaySheet.addCell(reversion);

			reversion=new Label(4, 1, "改动文件");
			reversion.setCellFormat(format[1]);
			todaySheet.addCell(reversion);


			//设置列宽
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
	 * 写入excel具体内容
	 * @param
	 * reversion commitTime  author  comment changeFile
	 * 版本号 提交日期时间  提交人 备注  改动文件   
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
	/**单元格格式集合
	 * 1.title表名小三粗体
	 * 2.columnName列名粗体
	 * 3.autoWrap自动换行的单元格
	 * */
	static WritableCellFormat [] formatList(){
		WritableCellFormat [] formats=new WritableCellFormat[3];
		try {
			WritableFont wf1=new WritableFont(WritableFont.ARIAL, 22,WritableFont.BOLD, false);
			WritableCellFormat title=new WritableCellFormat(wf1);
			title.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //边框样式
			title.setAlignment(Alignment.CENTRE);
			title.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableFont wf2=new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD, false);
			WritableCellFormat columnName=new WritableCellFormat(wf2);
			columnName.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //边框样式
			columnName.setAlignment(Alignment.CENTRE);
			columnName.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableFont wf3=new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD, false);
			WritableCellFormat autoWrap=new WritableCellFormat(wf3);
			autoWrap.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  //边框样式
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
	 * 设置开始版本号，防止每次都从头开始，数据不方便查找
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
