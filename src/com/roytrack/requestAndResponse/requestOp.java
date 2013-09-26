package com.roytrack.requestAndResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class requestOp {
	private String urlStr;  
	private URL url;  
	private HttpURLConnection httpURLConnection;  
	private String response; 
	public static void main(String[] args) throws IOException {  
		requestOp ro=new requestOp();
		ro.SendURLPost("708");  
		 }  
	public void SendURLPost(String articleId) throws IOException {  
		   
		 urlStr = "http://localhost:8080/bxfund/index.jsp?sys=rcm";  
		 url = new URL(urlStr);  
		 httpURLConnection = (HttpURLConnection) url.openConnection(); //获取连接  
		 httpURLConnection.setRequestMethod("POST"); //设置请求方法为POST, 也可以为GET  
		 httpURLConnection.setDoOutput(true);  
		   
		 StringBuffer param = new StringBuffer("ArticleId=");  //请求URL的查询参数  
		  param.append(articleId);  
		  OutputStream os = httpURLConnection.getOutputStream();  
		  os.write(param.toString().getBytes());  
		  os.flush();  
		  os.close();  
		    
		  InputStream is = httpURLConnection.getInputStream();  
		  BufferedReader br = new BufferedReader(new InputStreamReader(is));  
		  StringBuilder sb = new StringBuilder();  
		  while (br.read() != -1) {  
		   sb.append(br.readLine());  
		  }  
		  String content = new String(sb);  
		  content = new String(content.getBytes("GBK"), "GBK");  
		  System.out.println(content);  
		  br.close();  
		 }
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public HttpURLConnection getHttpURLConnection() {
		return httpURLConnection;
	}
	public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
		this.httpURLConnection = httpURLConnection;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}  
	


}
