package com.roytrack.collectionTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;



public class HashMapGetNull {
	public static void main(String[] args) {
		HashMap hm=new HashMap();
		hm.put("type", "manual");
		boolean flag=true;
		if(hm.get("aaa")==null){
			System.out.println("get  aaa null print null");
		}
		if(hm.get("type")==null){
			System.out.println("get  type  null print null");
		}else{
			System.out.println(hm.get("type").toString());
		}
		//null²»ÄÜtoString
//		if(hm.get("aaa").toString()==null){
//			System.out.println("aaa to string still null");
//		}
//		System.out.println(hm.get("aaa").toString());
		System.out.println("hashmap size is "+hm.size());
		hm.clear();
		System.out.println("hashmap size after clear  "+hm.size());
		String  cc="aa,bb,vv,";
		cc=cc.substring(0, cc.lastIndexOf(","));
		System.out.println(cc);
		hm.put("aa", "11");
		hm.put("bb", "12");
		hm.put("cc", "13");
		hm.put("dd", "11");
		Collection aa=hm.values();
		Object[] bb=aa.toArray();
		String hs="";
		for(int i = 0;i<bb.length;i++){
			hs+=(String)bb[i]+",";
		}
		System.out.println(hs.toString());
		System.out.println(hm.get("aa").toString());
		
	}

}
