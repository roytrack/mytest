package com.roytrack.spring.core;

public class ThrowExceptionNoDeclare {
	
	public static void main(String[] args) {
		
	}

	//uncheckedException 是jvm认为系统不能够处理的异常只能够抛出错误  一般继承了RuntimeException都是
	public void gogogo(boolean expression, String message){
		if (!expression) {
			throw new IllegalArgumentException(message);
			
		}
		
	}
	
	//checkedException 是要求处理的异常 jvm认为可以进行处理
	public void gogogo1(boolean expression, String message){
		if (!expression) {
	//		throw new Exception(message);
			
		}
		
	}
}
