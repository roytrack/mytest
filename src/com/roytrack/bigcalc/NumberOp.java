package com.roytrack.bigcalc;

import java.math.BigDecimal;

public class NumberOp {
	public static void main(String[] args) {
		double a,b,c;
		String sa,sb,sc;
		sa="700";
		sb="1200";
		sc="11471.46";
		BigDecimal ba=new BigDecimal(sa);
		BigDecimal bb=new BigDecimal(sb);
		BigDecimal bc=new BigDecimal(sc);
		System.out.println(ba.doubleValue());
		a=700;
		b=1200;
		c=11471.46;
		System.out.println(a/b*c);
		BigDecimal bd=(ba.divide(bb,15,BigDecimal.ROUND_HALF_DOWN).multiply(bc));
	//	bd=bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println((ba.divide(bb,15,BigDecimal.ROUND_HALF_DOWN).multiply(bc)) );
		System.out.println(bd);
		
		//doubleµÄ¼Ó¼õ
		System.out.println("***********************************");
		double zba=10.03,zbb=4.19,zbc=5.84;
		double zbd=zbb+zbc;
		System.out.println(zba+"@"+zbd);
		BigDecimal zb1=new BigDecimal(zba);
		BigDecimal zb2=new BigDecimal(zbb);
		BigDecimal zb3=new BigDecimal(zbc);
		BigDecimal zb4=new BigDecimal(0);
		zb4=zb2.add(zb3);
		System.out.println(zb1.doubleValue()+"#"+zb2.doubleValue()+"#"+zb3.doubleValue());
		if(zb4.equals(zb1)){
			System.out.println(111);
		}else{
			System.out.println(222+"@"+zb1.subtract(zb4).doubleValue()+"@"+zb4.subtract(zb1).doubleValue());
		}
		zb4=zb4.setScale(2,BigDecimal.ROUND_HALF_UP);
		System.out.println(zb4.doubleValue());
//		   System.out.println(0.05 + 0.01);
//		    System.out.println(1.0 - 0.42);
//		    System.out.println(4.015 * 100);
//		    System.out.println(123.3 / 100);
//		      System.out.println(new BigDecimal(123456789.02).toString());
	      //System.out.println(new BigDecimal("123456789.02").toString());
	}

}
