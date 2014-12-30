package com.roytrack.jvm;

import java.io.IOException;

public class TestSomething {
	public TestSomething()
    {
    }

    public TestSomething(String s)
    {
        _$1 = s;
    }

    public void SetKeyFilePath(String s)
    {
        _$1 = s;
    }

    public byte[] getInitKey()
        throws IOException, Exception
    {
//        byte abyte6[] = new byte[8];
//        abyte6;
//        abyte6[0] = 97;
//        abyte6;
//        JVM INSTR swap ;
//        1;
//        100;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        2;
//        85;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        3;
//        115;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        4;
//        37;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        5;
//        94;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        6;
//        38;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        7;
//        83;
//        JVM INSTR bastore ;
//        byte abyte0[];
//        abyte0;
//        JVM INSTR new #5   <Class DataInputStream>;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        JVM INSTR new #4   <Class BufferedInputStream>;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        JVM INSTR new #6   <Class FileInputStream>;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        _$1;
//        FileInputStream();
//        BufferedInputStream();
//        DataInputStream();
//        DataInputStream datainputstream;
//        datainputstream;
//        byte abyte7[] = new byte[8];
//        abyte7;
//        abyte7[0] = 1;
//        abyte7;
//        JVM INSTR swap ;
//        1;
//        2;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        2;
//        3;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        3;
//        4;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        4;
//        5;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        5;
//        6;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        6;
//        7;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        7;
//        8;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        byte abyte1[];
//        abyte1;
//        abyte0;
//        DES.getKey();
//        byte abyte2[];
//        abyte2;
//        byte abyte8[] = new byte[8];
//        abyte8;
//        abyte8[0] = 100;
//        abyte8;
//        JVM INSTR swap ;
//        1;
//        115;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        2;
//        3;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        3;
//        37;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        4;
//        5;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        5;
//        8;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        6;
//        9;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        7;
//        10;
//        JVM INSTR bastore ;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        byte abyte3[];
//        abyte3;
//        abyte1;
//        DES.getKey();
//        byte abyte4[];
//        abyte4;
//        JVM INSTR new #2   <Class DES>;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        abyte2;
//        DES();
//        DES des;
//        des;
//        boolean flag = false;
//        _$2 = new byte[32];
//        datainputstream.read(_$2, 0, 32);
//        datainputstream.close();
//        byte abyte5[];
//        byte abyte9[] = des.doDecrypt(_$2, 32);
//        abyte5 = abyte9;
//        return abyte9;

        return new byte[4];
    }

    public byte[] getInitKey(String s)
        throws IOException, Exception
    {
        _$1 = s;
        return getInitKey();
    }

    public static void main(String args[])
    {
//        Object obj = null;
//        JVM INSTR new #3   <Class InitKey>;
//        JVM INSTR dup ;
//        JVM INSTR swap ;
//        "sysdevice";
//        InitKey();
//        InitKey initkey;
//        initkey;
//        try
//        {
//            byte abyte0[] = initkey.getInitKey();
//            return;
//        }
//        catch(IOException _ex)
//        {
//            return;
//        }
//        catch(Exception _ex)
//        {
//            return;
//        }
    }

    private String _$1;
    private byte _$2[];


}
