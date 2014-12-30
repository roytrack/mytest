package com.roytrack.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestIsTimerBEGC {
	
	public static void main(String[] args) {
		TestIsTimerBEGC tibg=new TestIsTimerBEGC();
		tibg.themain();
	}
	void themain(){
		String [] rcm={"rcm ","yss ","good  "};
		for(int i=0 ;i<3;i++){
		Timer tm=new Timer();
		TestPrint tp=new TestPrint();
		tp.setLogo(rcm[i]);
		tm.schedule(tp, 1000, 1000);
		}
		
	}
	class TestPrint  extends TimerTask{
		String logo="";
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		@Override
		public void run() {
			System.out.println(logo+(new Date()).toLocaleString());
		}
		
	}

}
