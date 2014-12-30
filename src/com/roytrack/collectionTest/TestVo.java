package com.roytrack.collectionTest;

public class TestVo {
private String name,sex;


public   TestVo(String name ,String sex){
	this.name=name;
	this.sex=sex;
}
public void say(){
	System.out.println("name is "+this.name+"  sex is "+ this.sex);
}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}
}
