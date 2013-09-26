package com.roytrack.junit;
import junit.framework.*;
public class Demo {
	
	
	private FindChar fc=null;
	private char[] chars={'a','b','c'};
	public void setUp(){
	fc=new FindChar();
	}
}
	public void testSearchFound(){
	int index=fc.search(chars,'c');
	assertEquals(index,2);
	}
	public void testSearchNotFound(){
	int index=fc.search(chars,'e');
	assertTrue(index==-1);
	}
	public void testSearchIllegalArguments(){
	char[] chs=null;
	fc.search(chs,'a');
	fail("this is failure");
	}
	

