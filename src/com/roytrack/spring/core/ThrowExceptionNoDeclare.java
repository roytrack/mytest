package com.roytrack.spring.core;

public class ThrowExceptionNoDeclare {
	
	public static void main(String[] args) {
		
	}

	//uncheckedException ��jvm��Ϊϵͳ���ܹ�������쳣ֻ�ܹ��׳�����  һ��̳���RuntimeException����
	public void gogogo(boolean expression, String message){
		if (!expression) {
			throw new IllegalArgumentException(message);
			
		}
		
	}
	
	//checkedException ��Ҫ������쳣 jvm��Ϊ���Խ��д���
	public void gogogo1(boolean expression, String message){
		if (!expression) {
	//		throw new Exception(message);
			
		}
		
	}
}
