package com.roytrack.charOP;

public class ReplaceString {
	public static void main(String[] args) {
		String aa="@@@RE_HZJKQSYSS_COMPARE_SQL  :FHGDATE date,FBDLX number(2),Fdate date not null,FIndate date not null,FZqdm varchar2(10) not null,FSzsh varchar2(1)  not null,FGddm varchar2(18) not null,FJyxwh varchar2(10) not null,FBje number(18,4) not null,FSje number(18,4) not null,FBsl number(18,4) not null,FSsl number(18,4) not null,FByj number(18,4) not null,FSyj number(18,4) not null,FBjsf number(18,4) not null,FSjsf number(18,4) not null,FByhs number(18,4) not null,FSyhs number(18,4) not null,FBzgf number(18,4) not null,FSzgf number(18,4) not null,FBghf number(18,4) not null,FSghf number(18,4) not null,FBgzlx number(18,4) not null,FSgzlx number(18,4) not null,FHggain number(18,4) not null,FBfxj number(18,4) not null,FSfxj number(18,4) not null,FBsfje number(18,4) not null,FSssje number(18,4) not null,FFxj number(18,4) not null,FZqbz varchar2(10) not null,FYwbz varchar2(10) not null,FQsbz varchar2(1) not null,FSetCode varchar2(22) not null,FTzbz varchar2(1) not null,FBfy1 number(18,4),FSfy1 number(18,4),FBfy2 number(18,4),FSfy2 number(18,4),FBfy3 number(18,4),FSfy3 number(18,4),FXGR  varchar2(20) default ' ' not null,FXGR1 varchar2(20) default ' ' not null,ZQDM varchar2(10) default ' ',DataSource number(5) default 0 not null";
		System.out.println(aa.replaceAll("not null", "%%%%%%"));
	}

}
