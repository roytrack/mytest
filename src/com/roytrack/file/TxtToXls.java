package com.roytrack.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jxl.CellFeatures;
import jxl.CellType;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;

public class TxtToXls {
	public static void main(String[] args) throws Exception {
		File f=new File("D:\\workspace\\mytest\\src\\checkSQL.txt");
		BufferedReader fr=new BufferedReader(new FileReader(f));
		String targetfile="checkSqlAndStatement.xls";
		
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(
				targetfile));
		String content="";
		while(fr.ready()){
			  content=fr.readLine();
			
		}
		WritableSheet sheet=wwb.createSheet("111", 1);
		sheet.addCell(new Label(1,1,content));
		wwb.close();
		
	}

}
