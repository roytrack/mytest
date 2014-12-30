
package com.yss;

import com.yss.util.YssException;
import java.io.File;
import java.io.FileInputStream;

public final class LicInfo
{

    private static final String yssHeadTag = "Ysstech 软件产品许可";
    private static final int yssLicLength = 32767;
    private int crc32Tab[];

    public LicInfo()
    {
    }

    private void initCRC32Tab(int i)
    {
        if(crc32Tab == null)
        {
            crc32Tab = new int[256];
            for(int j = 0; j <= 255; j++)
                crc32Tab[j] = calcCRC32(j, 0, i);

        }
    }

    private int calcCRC32(int i, int j, int k)
    {
        j ^= i;
        for(int l = 0; l <= 7; l++)
            if((j & 0x1) != 0)
                j = j >>> 1 ^ k;
            else
                j >>>= 1;

        return j;
    }

    private String doCRC32(byte abyte0[], int i, int j)
    {
        initCRC32Tab(0xedb88320);
        int k = 0;
        for(int l = i; l <= j; l++)
            k = crc32Tab[(k ^ abyte0[l] & 0xff) & 0xff] ^ k >>> 8;

        String s = Integer.toHexString(k);
        if(s.length() < 8)
            return "00000000".substring(s.length()) + s;
        else
            return s;
    }

    public String yssGetLicAll(String s)
        throws YssException
    {
        String s1 = null;
        if(s.toLowerCase().indexOf(".lic") < 0)
            s = s + "fundacc.lic";
        try
        {
            File file = new File(s);
            FileInputStream fileinputstream = new FileInputStream(file);
            if((int)file.length() != 32767)
                throw new Exception("");
            byte abyte0[] = new byte[32767];
            int i = 0;
            do
            {
                if(i >= 32767)
                    break;
                int j1 = fileinputstream.read(abyte0, i, 32767 - i);
                if(j1 < 0)
                    break;
                i += j1;
            } while(true);
            fileinputstream.close();
            s1 = "";
            for(i = 1; i <= 4; i++)
            {
                if((abyte0[32767 - i] & 0xff) < 16)
                    s1 = s1 + "0";
                s1 = s1 + Integer.toHexString(abyte0[32767 - i] & 0xff);
            }

            if(doCRC32(abyte0, 611, 32758).compareToIgnoreCase(s1) != 0)
                throw new Exception("");
            byte abyte1[] = "Ysstech 软件产品许可".getBytes("GB2312");
            int j2 = 0;
            for(i = 0; i < abyte1.length; i++)
            {
                int l2 = abyte1[i] & 0xff;
                if(i % 2 == 0)
                    j2 += l2 / 16 + l2 % 16;
                else
                    j2 += l2 / 100 + (l2 % 100) / 10 + l2 % 10;
            }

            int i3 = abyte0[i] & 0xff;
            abyte0[i] = (byte)(i3 / 8 + (i3 % 8) * 8);
            j2 = ((j2 + (abyte0[i] & 0xff) + (abyte0[i + 1] & 0xff)) / i) % 255;
            i3 = ((abyte0[i + 1] & 0xff) * 255 + (abyte0[i + 2] & 0xff)) - (abyte0[i] & 0xff);
            int k2 = abyte0[i] & 0xff;
            abyte0[i] = (byte)(k2 / 8 + (k2 % 8) * 8);
            byte abyte3[] = new byte[99];
            k2 = 0;
            for(int j = 0; j <= j2; j++)
            {
                int k1;
                for(k1 = 0; k1 <= k2 - 1 && abyte3[k1] != ((abyte0[j] & 0xff) + j2) / 2; k1++);
                if(k1 != k2)
                    continue;
                abyte3[k1] = (byte)(((abyte0[j] & 0xff) + j2) / 2);
                if(++k2 > 99)
                    break;
            }

            byte abyte2[] = new byte[k2];
            System.arraycopy(abyte3, 0, abyte2, 0, k2);
            abyte1 = (byte[])null;
            abyte1 = new byte[i3];
            int l1 = abyte0.length - 9;
            for(int k = 0; k <= i3 - 1; k++)
                abyte1[k] = abyte0[l1 - k * (29389 / i3)];

            for(int l = 0; l <= i3 - 1; l += 3)
            {
                abyte1[l] = (byte)((abyte1[l] & 0xff) / 16 + ((abyte1[l] & 0xff) % 16) * 16);
                if(l + 2 <= i3 - 1)
                {
                    int i2 = abyte1[l + 2] & 0xff;
                    abyte1[l + 2] = (byte)(255 - abyte1[l + 1]);
                    abyte1[l + 1] = (byte)((255 - i2) / 16 + ((255 - i2) % 16) * 16);
                }
            }

            for(int i1 = 0; i1 < abyte1.length; i1++)
                if(i1 < k2)
                {
                    if((abyte1[i1] & 0xff) < (abyte2[i1] & 0xff))
                        abyte1[i1] = (byte)((abyte1[i1] - abyte2[i1]) + (((abyte2[8] & 0xff) - (abyte1[i1] & 0xff)) / 255 + 1) * 255);
                    else
                        abyte1[i1] = (byte)(abyte1[i1] - abyte2[i1]);
                } else
                if((abyte1[i1] & 0xff) < (abyte0[(j2 + i1) - k2] & 0xff))
                    abyte1[i1] = (byte)((abyte1[i1] - abyte0[(j2 + i1) - k2]) + (((abyte0[(j2 + i1) - k2] & 0xff) - (abyte1[i1] & 0xff)) / 255 + 1) * 255);
                else
                    abyte1[i1] = (byte)(abyte1[i1] - abyte0[(j2 + i1) - k2]);

            abyte0 = (byte[])null;
            s1 = new String(abyte1, "GB2312");
        }
        catch(Exception exception)
        {
            throw new YssException("读取许可文件出错！\n错误：" + (exception.getMessage().length() == 0 ? "非法许可文件！" : exception.getMessage()));
        }
        return s1;
    }

    public String[][] yssGetLicAll_A(String s)
        throws YssException
    {
        String as[] = yssGetLicAll(s).split("\r\n", -1);
        String as1[][] = new String[as.length][];
        for(int i = 0; i < as.length; i++)
            as1[i] = as[i].split("\t", -1);

        return as1;
    }

    public String yssGetModuleLicense(String s, String s1)
        throws YssException
    {
        if(s.length() == 0 || s1.length() == 0)
            return "";
        String as[] = yssGetLicAll(s).split("\r\n", -1);
        int i;
        for(i = 1; i < as.length && as[i].indexOf("\t" + s1 + "\t") != 1; i++);
        if(i > as.length - 1)
            return as[0];
        String s2 = as[0] + "\r\n" + as[i].substring(2);
        char c = as[i].charAt(0);
        for(i++; i < as.length && as[i].charAt(0) != c; i++)
            s2 = s2 + "\r\n" + as[i].substring(2);

        return s2;
    }

    public String[][] yssGetModuleLicense_A(String s, String s1)
        throws YssException
    {
        String as[] = yssGetModuleLicense(s, s1).split("\r\n", -1);
        String as1[][] = new String[as.length][];
        for(int i = 0; i < as.length; i++)
            as1[i] = as[i].split("\t", -1);

        return as1;
    }
}
