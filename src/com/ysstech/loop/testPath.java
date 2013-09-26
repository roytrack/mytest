package com.ysstech.loop;

public class testPath {
	public static void main(String[] args) {
		
		String resourcePath = testPath.class.getResource("/").toString();
		System.out.println(resourcePath);
		String removeFilePath = resourcePath.substring(6);
		System.out.println(removeFilePath);
		String aPath=removeFilePath.replace("bin/","");
		System.out.println(aPath);
		System.out.println(resourcePath.replace("file:/", "").replace("bin/", ""));
		// 把xml放到web-inf下面就ok啊。

	}
}
