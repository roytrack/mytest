/*     */ package org.apache.tomcat.dbcp.dbcp;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class AESUtil
/*     */ {
/*     */   public static final String KEY = "!@#$QWER";
/*     */ 
/*     */   public static byte[] encryptByte(String content, String password)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       KeyGenerator kgen = KeyGenerator.getInstance("AES");
/*  29 */       kgen.init(128, new SecureRandom(password.getBytes()));
/*  30 */       SecretKey secretKey = kgen.generateKey();
/*  31 */       byte[] enCodeFormat = secretKey.getEncoded();
/*  32 */       SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
/*  33 */       Cipher cipher = Cipher.getInstance("AES");
/*  34 */       byte[] byteContent = content.getBytes("UTF-8");
/*  35 */       cipher.init(1, key);
/*  36 */       byte[] result = cipher.doFinal(byteContent);
/*  37 */       return result;
/*     */     } catch (NoSuchAlgorithmException e) {
/*  39 */       e.printStackTrace();
/*     */     } catch (NoSuchPaddingException e) {
/*  41 */       e.printStackTrace();
/*     */     } catch (InvalidKeyException e) {
/*  43 */       e.printStackTrace();
/*     */     } catch (UnsupportedEncodingException e) {
/*  45 */       e.printStackTrace();
/*     */     } catch (IllegalBlockSizeException e) {
/*  47 */       e.printStackTrace();
/*     */     } catch (BadPaddingException e) {
/*  49 */       e.printStackTrace();
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   public static byte[] decryptByte(byte[] content, String password)
/*     */   {
/*     */     try
/*     */     {
/*  60 */       KeyGenerator kgen = KeyGenerator.getInstance("AES");
/*  61 */       kgen.init(128, new SecureRandom(password.getBytes()));
/*  62 */       SecretKey secretKey = kgen.generateKey();
/*  63 */       byte[] enCodeFormat = secretKey.getEncoded();
/*  64 */       SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
/*  65 */       Cipher cipher = Cipher.getInstance("AES");
/*  66 */       cipher.init(2, key);
/*  67 */       byte[] result = cipher.doFinal(content);
/*  68 */       return result;
/*     */     } catch (NoSuchAlgorithmException e) {
/*  70 */       e.printStackTrace();
/*     */     } catch (NoSuchPaddingException e) {
/*  72 */       e.printStackTrace();
/*     */     } catch (InvalidKeyException e) {
/*  74 */       e.printStackTrace();
/*     */     } catch (IllegalBlockSizeException e) {
/*  76 */       e.printStackTrace();
/*     */     } catch (BadPaddingException e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   public static String parseByte2HexStr(byte[] buf)
/*     */   {
/*  87 */     StringBuffer sb = new StringBuffer();
/*  88 */     for (int i = 0; i < buf.length; i++) {
/*  89 */       String hex = Integer.toHexString(buf[i] & 0xFF);
/*  90 */       if (hex.length() == 1) {
/*  91 */         hex = '0' + hex;
/*     */       }
/*  93 */       sb.append(hex.toUpperCase());
/*     */     }
/*  95 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static byte[] parseHexStr2Byte(String hexStr)
/*     */   {
/* 103 */     if (hexStr.length() < 1)
/* 104 */       return null;
/* 105 */     byte[] result = new byte[hexStr.length() / 2];
/* 106 */     for (int i = 0; i < hexStr.length() / 2; i++) {
/* 107 */       int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
/* 108 */       int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
/* 109 */       result[i] = (byte)(high * 16 + low);
/*     */     }
/* 111 */     return result;
/*     */   }
/*     */ 
/*     */   public static String encrypt(String data, String key)
/*     */   {
/* 123 */     byte[] encrypt = encryptByte(data, key);
/* 124 */     String str = parseByte2HexStr(encrypt);
/* 125 */     return str;
/*     */   }
/*     */ 
/*     */   public static String decrypt(String data, String key)
/*     */   {
/* 136 */     byte[] bt = parseHexStr2Byte(data);
/* 137 */     byte[] decrypt = decryptByte(bt, key);
/* 138 */     String str = new String(decrypt);
/* 139 */     return str;
/*     */   }
/*     */   public static void main(String[] args) {
/* 142 */     String url = "jdbc:jtds:sqlserver://172.16.30.94:1433/directreport";
/*     */ 
/* 144 */     System.out.println("加密前：" + url);
/* 145 */     String encryptResult = encrypt(url, "!@#$QWER");
/* 146 */     System.out.println("加密后：" + encryptResult);
/*     */ 		   encryptResult="BAA6A7DE5CED7839C45770BDA6918191A1E79FEC6888E9B1E6C1B24F669753B43151DB0FB8D9DBC85476380687A6CC1C862307882B33695EC69D1C234F96BB5A";
/* 148 */     String decryptResult = decrypt(encryptResult, "!@#$QWER");
/* 149 */     System.out.println("解密后：" + decryptResult);
/*     */   }
/*     */ }

/* Location:           D:\赛迪文件\工作文件_detail\20130829tomcat的jndi加密\玉伟\tomcat-dbcp.jar
 * Qualified Name:     org.apache.tomcat.dbcp.dbcp.AESUtil
 * JD-Core Version:    0.6.0
 */