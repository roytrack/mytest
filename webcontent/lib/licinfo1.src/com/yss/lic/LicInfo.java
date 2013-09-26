/*     */ package com.yss.lic;
/*     */ 
/*     */ import com.yss.util.YssException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ 
/*     */ public final class LicInfo
/*     */ {
/*     */   private static final String yssHeadTag = "Ysstech 软件产品许可";
/*     */   private static final int yssLicLength = 32767;
/*     */   private int[] crc32Tab;
/*     */ 
/*     */   private void initCRC32Tab(int i)
/*     */   {
/*  20 */     if (this.crc32Tab == null)
/*     */     {
/*  22 */       this.crc32Tab = new int[256];
/*  23 */       for (int j = 0; j <= 255; j++)
/*  24 */         this.crc32Tab[j] = calcCRC32(j, 0, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   private int calcCRC32(int i, int j, int k)
/*     */   {
/*  31 */     j ^= i;
/*  32 */     for (int l = 0; l <= 7; l++) {
/*  33 */       if ((j & 0x1) != 0)
/*  34 */         j = j >>> 1 ^ k;
/*     */       else
/*  36 */         j >>>= 1;
/*     */     }
/*  38 */     return j;
/*     */   }
/*     */ 
/*     */   private String doCRC32(byte[] abyte0, int i, int j)
/*     */   {
/*  43 */     initCRC32Tab(-306674912);
/*  44 */     int k = 0;
/*  45 */     for (int l = i; l <= j; l++) {
/*  46 */       k = this.crc32Tab[((k ^ abyte0[l] & 0xFF) & 0xFF)] ^ k >>> 8;
/*     */     }
/*  48 */     String s = Integer.toHexString(k);
/*  49 */     if (s.length() < 8) {
/*  50 */       return "00000000".substring(s.length()) + s;
/*     */     }
/*  52 */     return s;
/*     */   }
/*     */ 
/*     */   public String yssGetLicAll(String s)
/*     */     throws YssException
/*     */   {
/*  58 */     String s1 = null;
/*  59 */     if (s.toLowerCase().indexOf(".lic") < 0)
/*     */     {
/*  63 */       s = s + "fundacc.lic";
/*     */     }
/*     */     try
/*     */     {
/*  67 */       File file = new File(s);
/*  68 */       FileInputStream fileinputstream = new FileInputStream(file);
/*  69 */       if ((int)file.length() != 32767)
/*  70 */         throw new Exception("");
/*  71 */       byte[] abyte0 = new byte[32767];
/*  72 */       int i = 0;
/*     */ 
/*  75 */       while (i < 32767)
/*     */       {
/*  77 */         int j1 = fileinputstream.read(abyte0, i, 32767 - i);
/*  78 */         if (j1 < 0)
/*     */           break;
/*  80 */         i += j1;
/*     */       }
/*  82 */       fileinputstream.close();
/*  83 */       s1 = "";
/*  84 */       for (i = 1; i <= 4; i++)
/*     */       {
/*  86 */         if ((abyte0[(32767 - i)] & 0xFF) < 16)
/*  87 */           s1 = s1 + "0";
/*  88 */         s1 = s1 + Integer.toHexString(abyte0[(32767 - i)] & 0xFF);
/*     */       }
/*     */ 
/*  91 */       if (doCRC32(abyte0, 611, 32758).compareToIgnoreCase(s1) != 0)
/*  92 */         throw new Exception("");
/*  93 */       byte[] abyte1 = "Ysstech 软件产品许可".getBytes("GB2312");
/*  94 */       int j2 = 0;
/*  95 */       for (i = 0; i < abyte1.length; i++)
/*     */       {
/*  97 */         int l2 = abyte1[i] & 0xFF;
/*  98 */         if (i % 2 == 0)
/*  99 */           j2 += l2 / 16 + l2 % 16;
/*     */         else {
/* 101 */           j2 += l2 / 100 + l2 % 100 / 10 + l2 % 10;
/*     */         }
/*     */       }
/* 104 */       int i3 = abyte0[i] & 0xFF;
/* 105 */       abyte0[i] = (byte)(i3 / 8 + i3 % 8 * 8);
/* 106 */       j2 = (j2 + (abyte0[i] & 0xFF) + (abyte0[(i + 1)] & 0xFF)) / i % 255;
/* 107 */       i3 = (abyte0[(i + 1)] & 0xFF) * 255 + (abyte0[(i + 2)] & 0xFF) - (abyte0[i] & 0xFF);
/* 108 */       int k2 = abyte0[i] & 0xFF;
/* 109 */       abyte0[i] = (byte)(k2 / 8 + k2 % 8 * 8);
/* 110 */       byte[] abyte3 = new byte[99];
/* 111 */       k2 = 0;
/* 112 */       for (int j = 0; j <= j2; j++)
/*     */       {
/* 115 */         for (int k1 = 0; (k1 <= k2 - 1) && (abyte3[k1] != ((abyte0[j] & 0xFF) + j2) / 2); k1++);
/* 116 */         if (k1 != k2)
/*     */           continue;
/* 118 */         abyte3[k1] = (byte)(((abyte0[j] & 0xFF) + j2) / 2);
/* 119 */         k2++; if (k2 > 99) {
/*     */           break;
/*     */         }
/*     */       }
/* 123 */       byte[] abyte2 = new byte[k2];
/* 124 */       System.arraycopy(abyte3, 0, abyte2, 0, k2);
/* 125 */       abyte1 = (byte[])null;
/* 126 */       abyte1 = new byte[i3];
/* 127 */       int l1 = abyte0.length - 9;
/* 128 */       for (int k = 0; k <= i3 - 1; k++) {
/* 129 */         abyte1[k] = abyte0[(l1 - k * (29389 / i3))];
/*     */       }
/* 131 */       for (int l = 0; l <= i3 - 1; l += 3)
/*     */       {
/* 133 */         abyte1[l] = (byte)((abyte1[l] & 0xFF) / 16 + (abyte1[l] & 0xFF) % 16 * 16);
/* 134 */         if (l + 2 > i3 - 1)
/*     */           continue;
/* 136 */         int i2 = abyte1[(l + 2)] & 0xFF;
/* 137 */         abyte1[(l + 2)] = (byte)(255 - abyte1[(l + 1)]);
/* 138 */         abyte1[(l + 1)] = (byte)((255 - i2) / 16 + (255 - i2) % 16 * 16);
/*     */       }
/*     */ 
/* 142 */       for (int i1 = 0; i1 < abyte1.length; i1++)
/*     */       {
/* 144 */         if (i1 < k2)
/*     */         {
/* 146 */           if ((abyte1[i1] & 0xFF) < (abyte2[i1] & 0xFF))
/* 147 */             abyte1[i1] = (byte)(abyte1[i1] - abyte2[i1] + (((abyte2[8] & 0xFF) - (abyte1[i1] & 0xFF)) / 255 + 1) * 255);
/*     */           else {
/* 149 */             abyte1[i1] = (byte)(abyte1[i1] - abyte2[i1]);
/*     */           }
/*     */         }
/* 152 */         else if ((abyte1[i1] & 0xFF) < (abyte0[(j2 + i1 - k2)] & 0xFF))
/* 153 */           abyte1[i1] = (byte)(abyte1[i1] - abyte0[(j2 + i1 - k2)] + (((abyte0[(j2 + i1 - k2)] & 0xFF) - (abyte1[i1] & 0xFF)) / 255 + 1) * 255);
/*     */         else {
/* 155 */           abyte1[i1] = (byte)(abyte1[i1] - abyte0[(j2 + i1 - k2)]);
/*     */         }
/*     */       }
/* 158 */       abyte0 = (byte[])null;
/* 159 */       s1 = new String(abyte1, "GB2312");
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/* 163 */       throw new YssException("读取许可文件出错！\n错误：" + (exception.getMessage().length() != 0 ? exception.getMessage() : "非法许可文件！"));
/*     */     }
/* 165 */     return s1;
/*     */   }
/*     */ 
/*     */   public String[][] yssGetLicAll_A(String s)
/*     */     throws YssException
/*     */   {
/* 171 */     String[] as = yssGetLicAll(s).split("\r\n", -1);
/* 172 */     String[][] as1 = new String[as.length];
/* 173 */     for (int i = 0; i < as.length; i++) {
/* 174 */       as1[i] = as[i].split("\t", -1);
/*     */     }
/* 176 */     return as1;
/*     */   }
/*     */ 
/*     */   public String yssGetModuleLicense(String s, String s1)
/*     */     throws YssException
/*     */   {
/* 182 */     if ((s.length() == 0) || (s1.length() == 0))
/* 183 */       return "";
/* 184 */     String[] as = yssGetLicAll(s).split("\r\n", -1);
/*     */ 
/* 186 */     for (int i = 1; (i < as.length) && (as[i].indexOf("\t" + s1 + "\t") != 1); i++);
/* 187 */     if (i > as.length - 1)
/* 188 */       return as[0];
/* 189 */     String s2 = as[0] + "\r\n" + as[i].substring(2);
/* 190 */     char c = as[i].charAt(0);
/* 191 */     for (i++; (i < as.length) && (as[i].charAt(0) != c); i++) {
/* 192 */       s2 = s2 + "\r\n" + as[i].substring(2);
/*     */     }
/* 194 */     return s2;
/*     */   }
/*     */ 
/*     */   public String[][] yssGetModuleLicense_A(String s, String s1)
/*     */     throws YssException
/*     */   {
/* 200 */     String[] as = yssGetModuleLicense(s, s1).split("\r\n", -1);
/* 201 */     String[][] as1 = new String[as.length];
/* 202 */     for (int i = 0; i < as.length; i++) {
/* 203 */       as1[i] = as[i].split("\t", -1);
/*     */     }
/* 205 */     return as1;
/*     */   }
/*     */ }

/* Location:           D:\workspace\mytest\lib\licinfo1.jar
 * Qualified Name:     com.yss.lic.LicInfo
 * JD-Core Version:    0.6.0
 */