package org.apache.tomcat.dbcp.dbcp;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static final String KEY = "!@#$QWER";

    public static byte[] encryptByte(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decryptByte(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String encrypt(String data, String key) {
        byte[] encrypt = encryptByte(data, key);
        String str = parseByte2HexStr(encrypt);
        return str;
    }

    public static String decrypt(String data, String key) {
        byte[] bt = parseHexStr2Byte(data);
        byte[] decrypt = decryptByte(bt, key);
        String str = new String(decrypt);
        return str;
    }

    public static void main(String[] args) {
        String url = "jdbc:jtds:sqlserver://172.16.30.94:1433/directreport";

        System.out.println("加密前：" + url);
        String encryptResult = encrypt(url, "!@#$QWER");
        System.out.println("加密后：" + encryptResult);
        encryptResult = "BAA6A7DE5CED7839C45770BDA6918191A1E79FEC6888E9B1E6C1B24F669753B43151DB0FB8D9DBC85476380687A6CC1C862307882B33695EC69D1C234F96BB5A";
        String decryptResult = decrypt(encryptResult, "!@#$QWER");
        System.out.println("解密后：" + decryptResult);
    }
}

