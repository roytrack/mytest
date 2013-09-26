package com.roytrack.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
	
		TimerTest tt=new TimerTest();
		tt.rcm_run();
		 
	}
	void rcm_run(){
		 Timer timer = new Timer();
		 TestTimeTask t1=new TestTimeTask();
		 TestTimeTask t2=new TestTimeTask();
		 timer.schedule(t1,1000*10,1000*10 );
		 timer.schedule(t2, 1000*10,1000*10 );
	}
	
	 class TestTimeTask extends TimerTask {

		@Override
		public void run() {
			System.out.println((new Date()).toLocaleString());
			
		}
		 
	 }
	
}
