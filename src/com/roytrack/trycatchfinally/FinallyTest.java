package com.roytrack.trycatchfinally;

public class FinallyTest {
public static void main(String[] args) {
	try{
		System.out.println("11");
	}catch (Exception e) {
		System.out.println("22");
	}finally{
		System.out.println("33");
	}
	System.out.println("44");
}
}
