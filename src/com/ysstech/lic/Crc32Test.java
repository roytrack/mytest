package com.ysstech.lic;

import java.io.File;
import java.io.FileInputStream;

import com.yss.util.YssException;

public class Crc32Test {
	
	  int[] crc32Tab;
	  void initCRC32Tab(int i)
	 {
	      if (this.crc32Tab == null)
	    {
	       this.crc32Tab = new int[256];
	      for (int j = 0; j <= 255; j++)
	        this.crc32Tab[j] = calcCRC32(j, 0, i);
	     }
     }
	  int calcCRC32(int i, int j, int k)
 {
     j ^= i;
	    for (int l = 0; l <= 7; l++) {
	       if ((j & 0x1) != 0)
	         j = j >>> 1 ^ k;
	     else
	        j >>>= 1;
	      }
	    return j;
	   }
	 
	  
	  private String doCRC32(byte[] abyte0, int i, int j)
	   {
	     initCRC32Tab(-306674912);
	     int k = 0;
	       for (int l = i; l <= j; l++) {
	       k = this.crc32Tab[((k ^ abyte0[l] & 0xFF) & 0xFF)] ^ k >>> 8;
	     }
	    String s = Integer.toHexString(k);
	    if (s.length() < 8) {
	       return "00000000".substring(s.length()) + s;
	  }
	      return s;
	    }
	  
	  public String yssGetLicAll(String s)
			    throws YssException
			  {
			    String s1 = null;
			  
			      s =  "fundacc.lic";
			 
			    try
			    {
			      File file = new File(s);
			      System.out.println(file.exists()+file.getAbsolutePath());
			      FileInputStream fileinputstream = new FileInputStream(file);
			      if ((int)file.length() != 32767)
			        throw new Exception(" 文件长度不到32767");
			      byte[] abyte0 = new byte[32767];
			      int i = 0;

			      while (i < 32767)
			      {
			        int j1 = fileinputstream.read(abyte0, i, 32767 - i);
			        if (j1 < 0)
			          break;
			        i += j1;
			      }
			      fileinputstream.close();
			      s1 = "";		
			      for (i = 1; i <= 4; i++)
			      {
			        if ((abyte0[(32767 - i)] & 0xFF) < 16)
			          s1 = s1 + "0";
			        s1 = s1 + Integer.toHexString(abyte0[(32767 - i)] & 0xFF);
			        System.out.println("s1 this "+i+" is "+s1);
			      }

		      if (doCRC32(abyte0, 611, 32758).compareToIgnoreCase(s1) != 0){
		    	  System.out.println(doCRC32(abyte0, 611, 32758));
		        throw new Exception("验证crc32的时候出问题了");}
			      byte[] abyte1 = "Ysstech 软件产品许可".getBytes("GB2312");
			      int j2 = 0;
			      for (i = 0; i < abyte1.length; i++)
			      {
			        int l2 = abyte1[i] & 0xFF;
			        if (i % 2 == 0)
			          j2 += l2 / 16 + l2 % 16;
			        else {
			          j2 += l2 / 100 + l2 % 100 / 10 + l2 % 10;
			        }
			      }
			      int i3 = abyte0[i] & 0xFF;
			      abyte0[i] = (byte)(i3 / 8 + i3 % 8 * 8);
			      j2 = (j2 + (abyte0[i] & 0xFF) + (abyte0[(i + 1)] & 0xFF)) / i % 255;
			      i3 = (abyte0[(i + 1)] & 0xFF) * 255 + (abyte0[(i + 2)] & 0xFF) - (abyte0[i] & 0xFF);
			      int k2 = abyte0[i] & 0xFF;
			      abyte0[i] = (byte)(k2 / 8 + k2 % 8 * 8);
			      byte[] abyte3 = new byte[99];
			      k2 = 0;
			      for (int j = 0; j <= j2; j++)
			      {
			        for (int k1 = 0; (k1 <= k2 - 1) && (abyte3[k1] != ((abyte0[j] & 0xFF) + j2) / 2); k1++){
			        if (k1 != k2)
			          continue;
			        abyte3[k1] = (byte)(((abyte0[j] & 0xFF) + j2) / 2);
			        k2++; if (k2 > 99) {
			          break;
			        }
			      }
			      byte[] abyte2 = new byte[k2];
			      System.arraycopy(abyte3, 0, abyte2, 0, k2);
			      abyte1 = (byte[])null;
			      abyte1 = new byte[i3];
			      int l1 = abyte0.length - 9;
			      for (int k = 0; k <= i3 - 1; k++) {
			        abyte1[k] = abyte0[(l1 - k * (29389 / i3))];
			      }
			      for (int l = 0; l <= i3 - 1; l += 3)
			      {
			        abyte1[l] = (byte)((abyte1[l] & 0xFF) / 16 + (abyte1[l] & 0xFF) % 16 * 16);
			        if (l + 2 > i3 - 1)
			          continue;
			        int i2 = abyte1[(l + 2)] & 0xFF;
			        abyte1[(l + 2)] = (byte)(255 - abyte1[(l + 1)]);
			        abyte1[(l + 1)] = (byte)((255 - i2) / 16 + (255 - i2) % 16 * 16);
			      }

			      for (int i1 = 0; i1 < abyte1.length; i1++)
			      {
			        if (i1 < k2)
			        {
			          if ((abyte1[i1] & 0xFF) < (abyte2[i1] & 0xFF))
			            abyte1[i1] = (byte)(abyte1[i1] - abyte2[i1] + (((abyte2[8] & 0xFF) - (abyte1[i1] & 0xFF)) / 255 + 1) * 255);
			          else {
			            abyte1[i1] = (byte)(abyte1[i1] - abyte2[i1]);
			          }
			        }
			        else if ((abyte1[i1] & 0xFF) < (abyte0[(j2 + i1 - k2)] & 0xFF))
			          abyte1[i1] = (byte)(abyte1[i1] - abyte0[(j2 + i1 - k2)] + (((abyte0[(j2 + i1 - k2)] & 0xFF) - (abyte1[i1] & 0xFF)) / 255 + 1) * 255);
			        else {
			          abyte1[i1] = (byte)(abyte1[i1] - abyte0[(j2 + i1 - k2)]);
			        }
			      }
			      abyte0 = (byte[])null;
			      s1 = new String(abyte1, "GB2312");
			    }}
			    catch (Exception exception)
			    {
			      throw new YssException("读取许可文件出错！\n错误：" + (exception.getMessage().length() != 0 ? exception.getMessage() : "非法许可文件！"));
			    }
			    return s1;
			  }
	 public static void main(String[] args) throws YssException {
		 Crc32Test cc=new Crc32Test();
		 cc.initCRC32Tab(1);
		 for(int i=0;i<cc.crc32Tab.length;i++){
		 System.out.print(cc.crc32Tab[i]+"	 ");
		 if((i+1)%16==0 )System.out.println();}
		System.out.println( cc.yssGetLicAll("a"));
	}
}
