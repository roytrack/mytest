package com.roytrack;

import java.net.URL;

public class GetPath {
	public   static   void   main(String   args[]) 
	{ 
		GetPath   j=new   GetPath(); 
	System.out.println(getClassPath()); 
	System.out.println(getWebinfoPath()); 
	System.out.println(getJxvaHome()); 
	System.out.println(getAppPath()); 
	System.out.println(getRootPath()); 
	System.out.println(getJxvaFramework()); 
	System.out.println(j.getRealPath()); 
	} 

	/** 
	  *   
	  *   @return   ~/classes/ 
	  */ 
	public   static   String   getClassPath(){ 
		GetPath   j=new   GetPath(); 
	String   t=j.getRealPath(); 
	t=t.replaceAll( "file:/ ",   " ");	//windows 
	t=t.replaceAll( "file: ",   " ");	//linux,unix 
	t=t.replaceAll( "wsjar: ", " ");	//websphere   wsjar:   has   to   at   jar:   before 
	t=t.replaceAll( "jar: ", " ");	 //tomcat,jboss,resin,wasce,apusic 
	t=t.replaceAll( "zip: ", " ");	 //weblogic 
	t=t.replaceAll( "/./ ", "/ ");	 //weblogic 
	//if   this   class   be   included   .jar   file,will   replace   "/lib/*.!/ "   to   "/classes/ " 
	//t=t.replaceAll( "/lib/([^\ ' ']+)!/ ", "/classes/ ");   //jar 
	t=t.split( "/classes/ ")[0]+ "/classes/ "; 
	return   t; 
	} 

	/** 
	  *   if   this   path   include   "WEB-INF "   will   return   "WEB-INF " 's   absolute   path 
	  *   @return   ~/WEB-INF/ 
	  */ 
	public   static   String   getWebinfoPath(){ 
	//remove   string   "classes/ " 
	return   getClassPath().substring(0,getClassPath().length()-8); 
	} 

	/** 
	  *   
	  *   @return   ~/JxvaHome/ 
	  */ 
	public   static   String   getJxvaHome(){ 
	return   getWebinfoPath()+ "JxvaHome/ "; 
	} 

	/** 
	  *   
	  *   @return   application 's   path 
	  */ 
	public   static   String   getAppPath(){ 
	String[]   s=getWebinfoPath().split( "/ "); 
	StringBuffer   sb=new   StringBuffer(); 
	for(int   i=0;i <s.length-1;i++){ 
	sb.append(s[i]+ "/ "); 
	} 
	return   sb.toString(); 
	} 

	/** 
	  *   
	  *   @return   root 's   path 
	  */ 
	public   static   String   getRootPath(){ 
	return   getClassPath().split( "/ ")[0]+ "/ "; 
	} 

	/** 
	  *   JxvaFramework��ܵĹ���Ŀ¼,ȷ���Ը�Ŀ¼�п�дȨ��,һ�������������޷�ʹ�� 
	  *   @return   JxvaFrameowrk 
	  */ 
	public   static   String   getJxvaFramework(){ 
	return   getRootPath()+ "JxvaFramework/ "; 
	} 

	private   String   getRealPath() 
	{ 
	String   strClassName   =   getClass().getName(); 
	String   strPackageName   =   " "; 
	if   (getClass().getPackage()   !=   null)   strPackageName   =   getClass().getPackage().getName(); 
	String   strClassFileName   =   " "; 
	if   (! " ".equals(strPackageName)) 
	strClassFileName   =   strClassName.substring(strPackageName.length()   +   1,   strClassName.length()); 
	else 
	strClassFileName   =   strClassName; 
	URL   url   =   getClass().getResource(strClassFileName   +   ".class "); 
	String   strURL   =   url.toString(); 
	strURL   =   strURL.replaceAll( "%20 ",   "   "); 
	return   strURL; 
	} 
}
