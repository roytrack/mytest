package com.ysstech.loop;

import java.util.Random;

public class LoopFor {

	
	public static void main(String[] args) {
		
		boolean flag ;
		for (int i=0;i<100;i++) {
			flag=false;
			System.out.println("the i is"+i);
			for(int j=0;j<100;j++)
			{
				Random r=new Random();
				int p=r.nextInt(100);
				System.out.println("###"+p);
				System.out.println("the j is"+j);
				if(p>50){flag=true;
				break;
				}
				
			}
			System.out.println("break out");
		}
	}
}
