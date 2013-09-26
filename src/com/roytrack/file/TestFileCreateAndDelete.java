package com.roytrack.file;

import java.io.File;


public class TestFileCreateAndDelete {
	public static void main(String[] args) {
	
		try {
			File f=new File("aaa.txt");
			System.out.println(f.exists());
			f.createNewFile();
			System.out.println(f.exists());
			f.delete();
			System.out.println(f.exists());
			f.createNewFile();
			System.out.println(f.exists());
			File f1=new File("aaa.txt");
			System.out.println(f1.exists());
			f1.delete();
			System.out.println(f1.exists());
			System.out.println(f.exists());
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		
		
	}

}
