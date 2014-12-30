package com.roytrack.collectionTest;

import java.util.HashMap;

public class HashMapSize {
public static void main(String[] args) {
	HashMap hm=new HashMap();
	hm.put("rcm", "rcm");
	System.out.println(hm.size());
	hm.remove("rcm");
	System.out.println(hm.size());
	hm.put("rcm", "rcm");
	hm.put("rcm", "rcm1");
	System.out.println(hm.size());
	System.out.println(hm.get("rcm").toString());
}
}
