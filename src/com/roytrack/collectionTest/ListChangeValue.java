package com.roytrack.collectionTest;

import java.util.ArrayList;

public class ListChangeValue {
	public static void main(String[] args) {
		ArrayList<TestVo> list=new ArrayList<TestVo>();
		TestVo a=new TestVo("11","m");
		TestVo b=new TestVo("11","m");
		list.add(a);
		list.add(b);
		for(TestVo c:list){
			c.setName("rcm");
			c.setSex("gg");
		}
		for(TestVo c:list){
			c.say();
		}
		
	}

}
