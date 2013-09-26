package com.roytrack.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.yss.util.YssException;

public class ZipDiffDirectory {
	public static void main(String[] args) throws YssException {
		String path=ZipDiffDirectory.class.getResource("/").getPath();
		System.out.println(path);
		File a=new File(path+"report.xsl");
		File b=new File(path+"pageManage.xml");
		File c=new File("D:/bxfund.xml");
		File[] aa={a,b,c};
		File d=new File("abc.zip");
		zipFiles(aa, d);
		
		
	}
	
	/**
	 * ����:ѹ������ļ���һ��zip�ļ�
	 * 
	 * @param srcfile��Դ�ļ��б�
	 * @param zipfile��ѹ������ļ�
	 */
	public static void zipFiles(File[] srcfile, File zipfile) throws YssException{// liuynni
																// 2012-05-03
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new YssException("ѹ���ļ�����" + "\r\n" + e);
		}
	}

	

}
