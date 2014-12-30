package com.roytrack.function;

import java.math.BigDecimal;


public class CalcUtil {

	/**
	 * �ṩ��ȷ�ļӷ�����
	 * ��������������(double,Double,float,Float,String,Bigdecimal)������������һ�������򷵻�0
	 * ����BigDecimal ���н��о��Ⱥ����뷽ʽ��ȷ��
	 * @param T ...ds ������������double,Double,float,Float,String�� 
	 * @return  Bigdecimal  n�������ĺ� ʵ�ʷ��ص���Bigdecimal 
	 * */
	public static <T> BigDecimal add(T... ds ){
		BigDecimal  BCops=new BigDecimal ("0");
		if(ds!=null&&ds.length>0){
			for (T theop:ds){
				BCops=BCops.add(new BigDecimal(String.valueOf(theop instanceof String?StringUtils.convertNull((String)theop):theop)));
			}
		}
		return BCops;
	}
	/**
	 * �ṩ��ȷ�ļ�������
	 * ����a-b����
	 * ����ͨ���Ƿ���дscale������̬ѡ���Ƿ����þ��� ���뷽ʽ��
	 * ���������scale��Ĭ��Ϊ2,���뷽ʽΪBigDecimal.ROUND_HALF_UP���������룩,
	 * �������scale��������������ȡ��һ��Ϊ���ȣ��ڶ���Ϊ���뷽ʽ
	 * �������scale����һ����������ȡ��Ϊ���ȣ����뷽ʽΪBigDecimal.ROUND_HALF_UP���������룩
	 * ������ó��������������������Ч
	 * #���뷽ʽ�ο�
	 *  ����								 								intֵ
	 *	 BigDecimal.ROUND_UP   								0
	 *	 BigDecimal.ROUND_DOWN							1
	 *	 BigDecimal.ROUND_CEILING						2
	 *	 BigDecimal.ROUND_FLOOR							3
	 *	 BigDecimal.ROUND_HALF_UP						4
	 *	 BigDecimal.ROUND_HALF_DOWN				5
	 *	 BigDecimal.ROUND_HALF_EVEN					6
	 *	 BigDecimal.ROUND_UNNECESSARY				7
	 * @param T a��double,Double,float,Float,String�� ����
	 * @param T b��double,Double,float,Float,String�� ������
	 * @param int... scale��double,Double,float,Float,String�����ȡ����뷽ʽ
	 * @return  Bigdecimal   a-b
	 * */
	public static <T> BigDecimal subtract(T a,T b, int... scale ){
		BigDecimal  BCa=new BigDecimal (String.valueOf(a instanceof String?StringUtils.convertNull((String)a):a));
		BigDecimal  BCb=new BigDecimal (String.valueOf(b instanceof String?StringUtils.convertNull((String)b):b));
		BCa=BCa.subtract(BCb);
		int theScale=2;
		int sheru=BigDecimal.ROUND_HALF_UP;
		if(scale!=null&&scale.length>0){
			theScale=scale[0];			
		}
		if(scale!=null&&scale.length>=2){
			sheru=scale[1];
		}
		BCa=BCa.setScale(theScale,sheru);
		return BCa;
	}
	/**
	 * �ṩ��ȷ�ĳ˷����㣬
	 * ��������������(double,Double,float,Float,String,Bigdecimal)������������һ�������򷵻�0
	 * ����BigDecimal ���н��о��Ⱥ����뷽ʽ��ȷ��
	 * @param T ...ds ������������double,Double,float,Float,String�� 
	 * @return  Bigdecimal  n�������ĺ� ʵ�ʷ��ص���Bigdecimal 
	 * */
	public static <T>BigDecimal multiply(T ...ds ){
		BigDecimal  BCops=new BigDecimal ("0");
		if(ds!=null&&ds.length>0){
			BCops=new BigDecimal ("1");
			for (T theop:ds){
				BCops=BCops.multiply(new BigDecimal(String.valueOf(theop instanceof String?StringUtils.convertNull((String)theop):theop)));
			}
		}
		return BCops;
	}
	
	/**
	 * �ṩ��ȷ�ĳ�������
	 * ����a/b����
	 * ����ͨ���Ƿ���дscale������̬ѡ���Ƿ����þ��� ���뷽ʽ��
	 * ���������scale��Ĭ��Ϊ2,���뷽ʽΪBigDecimal.ROUND_HALF_UP���������룩,
	 * �������scale��������������ȡ��һ��Ϊ���ȣ��ڶ���Ϊ���뷽ʽ
	 * �������scale����һ����������ȡ��Ϊ���ȣ����뷽ʽΪBigDecimal.ROUND_HALF_UP���������룩
	 * ������ó��������������������Ч
	 * #���뷽ʽ�ο�
	 *  ����								 								intֵ
	 *	 BigDecimal.ROUND_UP   								0
	 *	 BigDecimal.ROUND_DOWN							1
	 *	 BigDecimal.ROUND_CEILING						2
	 *	 BigDecimal.ROUND_FLOOR							3
	 *	 BigDecimal.ROUND_HALF_UP						4
	 *	 BigDecimal.ROUND_HALF_DOWN				5
	 *	 BigDecimal.ROUND_HALF_EVEN					6
	 *	 BigDecimal.ROUND_UNNECESSARY				7
	 * @param T a��double,Double,float,Float,String�� ����
	 * @param T b��double,Double,float,Float,String�� ������
	 * @param int... scale��double,Double,float,Float,String�����ȡ����뷽ʽ
	 * @return  Bigdecimal   a/b
	 * */
	public static <T> BigDecimal divide(T a,T b, int... scale ){
		BigDecimal  BCa=new BigDecimal (String.valueOf(a instanceof String?StringUtils.convertNull((String)a):a));
		BigDecimal  BCb=new BigDecimal (String.valueOf(b instanceof String?StringUtils.convertNull((String)b):b));
		int theScale=2;
		int sheru=BigDecimal.ROUND_HALF_UP;
		if(scale!=null&&scale.length>0){
			theScale=scale[0];			
		}
		if(scale!=null&&scale.length>=2){
			sheru=scale[1];
		}
		BCa=BCa.divide(BCb,theScale,sheru);
		return BCa;
	}
	/**
	 * �Ƚ���ֵ�Ĵ�С
	 * a<b		-1
	 * a=b		0
	 * a>b		1
	 * @param <T> a 
	 * @param <T> b
	 * @return int 
	 * */
	public static <T> int compare(T a,T b){
		BigDecimal  BCa=new BigDecimal (String.valueOf(a instanceof String?StringUtils.convertNull((String)a):a));
		BigDecimal  BCb=new BigDecimal (String.valueOf(b instanceof String?StringUtils.convertNull((String)b):b));
		return BCa.compareTo(BCb);
	}
	
	//demo
	public static void main(String[] args) {
		
		   System.out.println(0.05 + 0.01);
		    System.out.println(1.0 - 0.42);
		    System.out.println(4.015 * 100);
		    System.out.println(123.3 / 100);

		System.out.println(StringUtils.convertNull((String)null));
		BigDecimal adda=new BigDecimal("555");
		System.out.println("add   "+add(adda,999999999.04));
		System.out.println("subtract   "+subtract(49493.6666,2344323.555,4,BigDecimal.ROUND_HALF_UP));
		System.out.println("multiply   "+multiply(11,22.22,33.4533));
		System.out.println("divide   "+divide(5555,234,5,BigDecimal.ROUND_HALF_UP));
		System.out.println(String.valueOf(999999999.04));
		BigDecimal abc= new BigDecimal(String.valueOf(999999999.04));
		abc=abc.add(new BigDecimal("0.01"));
		abc=abc.setScale(2);
		abc=abc.setScale(0,BigDecimal.ROUND_HALF_UP);
		System.out.println(abc.toString());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
		double a=55.0000,b=55;
		  a = 99.0123456789123456789d;
		  b = 99.0123456789123456788d;
		
		System.out.println((a==b)+"@");
		
		
	}


}
