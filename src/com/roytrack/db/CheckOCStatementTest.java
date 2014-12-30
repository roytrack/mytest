package com.roytrack.db;

import junit.framework.TestCase;
public class CheckOCStatementTest extends TestCase {
	public  void testRunStatement(){
		CheckOCStatement.setCount(0);
		CheckOCStatement.setPath("D:/yss_gz_ant/source_JK3/src/com/yss");
		CheckOCStatement.setResultpath("D:/yss_gz_ant/report/");
		CheckOCStatement.setFilename("jk3_checkStateMent.txt");
		CheckOCStatement.checkStateMent();
//		CheckOCStatement.setCount(0);
//		CheckOCStatement.setPath("D:/yss_gz_ant/source_JK4/src/com/yss");
//		CheckOCStatement.setFilename("jk4_checkStateMent.txt");
//		CheckOCStatement.checkStateMent();
//		CheckOCStatement.setCount(0);
//		CheckOCStatement.setPath("D:/yss_gz_ant/source_QS/src/com/yss");
//		CheckOCStatement.setFilename("qs_checkStateMent.txt");
//		CheckOCStatement.checkStateMent();
	}
	
}
