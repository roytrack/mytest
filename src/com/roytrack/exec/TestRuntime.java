package com.roytrack.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestRuntime {
	public static void main(String[] args) {
		Runtime rt=Runtime.getRuntime();
		//String exeCmd="tkprof D:/oracle/product/10.2.0/admin/zdcw/udump/zdcw_ora_2616.trc   D:/111/tkprofReport2012-06-20.txt  sys=no sort=execnt   AGGREGATE=yes  ";
		//String exeCmd1="tkprof D:/oracle/product/10.2.0/admin/zdcw/udump/zdcw_ora_2612.trc   D:/111/tkprofReport2012-06-21.txt  sys=no sort=execnt   AGGREGATE=yes  ";
		String execmd1="cmd.exe /c D:\\yss_gz_ant\\jk_qs.cmd";
		String	exeCmd="";
	
	String[] aa={execmd1,exeCmd};
		try {
			
			Process pro=rt.exec(execmd1);
			
			InputStream in = pro.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			while(line!=null ){
			    System.out.println(line);  // ‰≥ˆ≤‚ ‘
			    line = br.readLine();
			}
			int returnValue=pro.waitFor();
			System.out.println(returnValue);
			pro.destroy();
			//pro=rt.exec(exeCmd1);
			returnValue=pro.waitFor();
			System.out.println(returnValue);
			pro.destroy();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
