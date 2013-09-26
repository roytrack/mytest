package com.roytrack.referOrValue;

import java.util.ArrayList;

public class PutInListOneObjTwoValue {
	
	public static void main(String[] args) {
		 Man a=new PutInListOneObjTwoValue().ooxx();
		 a.setName("11");
		 a.setSex("m");
		 ArrayList<Man> array=new ArrayList<Man>();
		 array.add(a);
		 a.setSex("fm");
		 array.add(a);
		 array.get(0).play();
		 array.get(1).play();
	}
	class Man{
		private String name;
		private String sex;
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
		
		public void play(){
			System.out.println("name "+name +" sex " + sex );
		}
	}
	public Man ooxx(){  
        return new Man();  
    }  

}
