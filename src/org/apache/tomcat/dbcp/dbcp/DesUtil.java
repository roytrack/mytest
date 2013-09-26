package org.apache.tomcat.dbcp.dbcp;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.catalina.util.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {

	private final static String DES = "DES";
	public static final String KEY = "12345678";//加密规则字符串
	public static void main(String[] args) throws Exception {
		String url = "jdbc:sqlserver://172.16.30.94:1433;databaseName=directreport";
		String driverclass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String username = "sa";
		String password = "123456";
		String key = "!@#$QWER";
		String enUrl=encrypt(url, key);
		String enDriverclass=encrypt(driverclass, key);
		String enUsername=encrypt(username, key);
		String enPassword=encrypt(password, key);
		System.out.println(url+"---加密--->"+enUrl);
		System.out.println(driverclass+"---加密--->"+enDriverclass);
		System.out.println(username+"---加密--->"+enUsername);
		System.out.println(password+"---加密--->"+enPassword);
		System.out.println(enUrl+"---解密--->"+decrypt(enUrl,key));
		System.out.println(enDriverclass+"---解密--->"+decrypt(enDriverclass,key));
		System.out.println(enUsername+"---解密--->"+decrypt(enUsername,key));
		System.out.println(enPassword+"---解密--->"+decrypt(enPassword,key));
	}
	
	/**
	 * Description 根据键值进行加密
	 * @param data 
	 * @param key  加密键字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
		String strs = new BASE64Encoder().encode(bt);
		return new String (strs.getBytes("UTF-8"),"UTF-8");
	}

	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  解密键字符串
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException,
			Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		Base64 bb=new Base64();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf,key.getBytes("UTF-8"));
		return new String(bt,"UTF-8");
	}

	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key  加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
	
	
	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key  解密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
}
