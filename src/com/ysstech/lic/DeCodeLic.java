package com.ysstech.lic;

import com.yss.*;
public class DeCodeLic {

	public static void main(String[] args) {
		try{
		LicInfo li=new LicInfo();
		System.out.println(li.yssGetLicAll("D:\\workspace\\mytest\\src\\com\\ysstech\\lic\\fundacc.lic"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
