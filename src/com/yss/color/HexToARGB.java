package com.yss.color;

class HexToARGB {

	public static void main(String[] args) {
		//Ŀ��   aabbcf  385ba9
		//RGB  BGR   cfbbaa   a95b38
		//13613994   11098936
		//������RRGGBBģʽ����ɫ   0   0  128  0     vb��RGB  ps��BGR
		String color="a95b38";
		int bb=Integer.valueOf(color, 16); 
		System.out.println(bb);  //ʮ��������ɫת����
		System.out.println((bb>>24)&255);  // alphaͨ��
		System.out.println((bb>>16)&255);  //��һλ��ɫ
		System.out.println((bb>>8)&255);  //�ڶ�λ��ɫ
		System.out.println((bb>>0)&255);  //����λ��ɫ
//		int a=(bb>>24)&255;
//		int r=(bb>>16)&255;
//		int g=(bb>>8)&255;
//		int b=(bb>>0)&255;
//		String saString=Integer.toHexString(a);
//		String srString=Integer.toHexString(r);
//		String sgString=Integer.toHexString(g);
//		String sbString=Integer.toHexString(b);
//		String argbIntString=""+r+g+b;
//		System.out.println(argbIntString);
//		int cc=Integer.valueOf(argbIntString);
//		System.out.println((cc>>24)&255);
//		System.out.println((cc>>16)&255);
//		System.out.println((cc>>8)&255);
//		System.out.println((cc>>0)&255);
//		System.out.println("@@@@@");
//		System.out.println((0xff<<24)+"@"+(8<<16)+"@"+(0<<8)+"@");
//		System.out.println(Integer.toHexString(128));
//		
		
	
	}
		
	
}
