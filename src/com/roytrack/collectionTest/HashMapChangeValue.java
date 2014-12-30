package com.roytrack.collectionTest;

import java.util.HashMap;

public class HashMapChangeValue {
public static void main(String[] args) {
	HashMap hm=new HashMap();
	hm.put("rcm", "god");
	hm.put("rcm", "better");
	System.out.println(hm.get("rcm").toString());
}
}
