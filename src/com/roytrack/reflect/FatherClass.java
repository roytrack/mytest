package com.roytrack.reflect;

import java.lang.reflect.InvocationTargetException;

public class FatherClass {
	
	public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FatherClass aa=new FatherClass();
		String []meths={"Aaa","Bbb","Ccc"};
		Object[] value={"11","22","33"};
		aa.setAll(aa, meths, value);
		aa.myPrintln();
	}
	
	private String aaa;
	private String bbb;
	private String ccc;
	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	public String getBbb() {
		return bbb;
	}
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	public String getCcc() {
		return ccc;
	}
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}
	
public void myPrintln(){
	System.out.println(this.aaa+"@"+this.bbb+"@"+this.ccc);
}

public void setAll(FatherClass target,String[] methods,Object[] value) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	for(int i=0;i<methods.length ;i++)
	this.getClass().getMethod("set"+methods[i],   new   Class[]{String.class}).invoke(target,   new   Object[]{value[i]});
}
}
