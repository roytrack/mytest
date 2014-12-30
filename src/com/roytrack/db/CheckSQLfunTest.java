package com.roytrack.db;

import junit.framework.TestCase;

public class CheckSQLfunTest extends TestCase {
	public void testSQLfun(){
		CheckSQLfun.setCount(0);
		CheckSQLfun.setPath("D:/yss_gz_ant/source_JK3/src/com/yss");
		CheckSQLfun.setResultpath("D:/yss_gz_ant/report/");
		CheckSQLfun.setFilename("jk3_checksql.txt");
		CheckSQLfun.CheckSql();
		CheckSQLfun.setCount(0);
		CheckSQLfun.setPath("D:/yss_gz_ant/source_JK4/src/com/yss");
		CheckSQLfun.setFilename("jk4_checksql.txt");
		CheckSQLfun.CheckSql();
		CheckSQLfun.setCount(0);
		CheckSQLfun.setPath("D:/yss_gz_ant/source_QS/src/com/yss");
		CheckSQLfun.setFilename("qs_checksql.txt");
		CheckSQLfun.CheckSql();
	}

}
