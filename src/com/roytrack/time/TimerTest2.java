package com.roytrack.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.roytrack.time.TimerTest.TestTimeTask;

public class TimerTest2 {
	public static void main(String[] args) {
		
		TimerTest tt=new TimerTest();
		tt.rcm_run();
		 
	}
	void rcm_run(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE,10);
		calendar.set(Calendar.SECOND, 0);
		Date d=calendar.getTime(); 
		 Timer timer = new Timer();
		 Timer timer1 = new Timer();
		 TestTimeTask t1=new TestTimeTask();
		 TestTimeTask t2=new TestTimeTask();
		 timer.schedule(t1,d,30*1000 );
		 timer1.schedule(t2, d,1000*30);
	}
	
	 class TestTimeTask extends TimerTask {

		@Override
		public void run() {
			System.out.println((new Date()).toLocaleString());
			
		}
}
}
