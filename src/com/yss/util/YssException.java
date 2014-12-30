package com.yss.util;
import java.sql.BatchUpdateException;

/**
 * <p>Title: </p>
 * <p>Description: 异常类，所有程序抛出的异常都用这个异常类<br>
 * 需要向上传递的异常应该</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ysstech</p>
 * @author alex
 * @version 1.0
 */

public final class YssException extends Exception{
   private String errMsg = null;
   public YssException(){}
   /**
    * Programmer: Dranson  保证数据库批处理得到详细异常信息
    * @param msg String
    * @param ex BatchUpdateException
    */
   public YssException(String msg, BatchUpdateException ex){
      try{
         if (ex.getNextException() == null)
            buildMsg(msg + "\t#" + ex.getUpdateCounts().length, ex);
         else
            buildMsg(msg, ex.getNextException());
      }
      catch (Exception e){}
   }
   /**
    * 在抛出这个异常时可以指定错误信息msg
    * @param msg：指定的确定错误信息
    */
   public YssException(String msg, Exception ex){
      buildMsg(msg, ex);
   }
   /**
    * 自行抛出一个错误信息
    * @param msg String
    */
   public YssException(String msg){
      errMsg = msg;
   }
   public YssException(BatchUpdateException ex){
      try{
         if (ex.getNextException() == null)
            buildMsg("#" + ex.getUpdateCounts().length, ex);
         else
            buildMsg("", ex.getNextException());
      }
      catch (Exception e){}
   }
   /**
    * 没有自定义消息，直接抛出成YssException，后续可自行用insertMessage添加消息
    * @param e Exception
    */
   public YssException(Exception e){
      buildMsg("", e);
   }
   /**
    * 添加自定义错误信息，一般和YssException(Exception)配套使用
    * @param msg String
    */
   public void insertMessage(String msg){
      errMsg = msg + "\r\n" + errMsg;
   }
//返回自动格式化的错误信息
   public String getMessage(){
      return errMsg;
   }
   public String toString(){
      return errMsg;
   }
   private void buildMsg(String msg, Exception ex){
      StackTraceElement[] ss = ex.getStackTrace();
      //具体异常信息
      for (int i = 0; i < ss.length; i++)
         if (ss[i].getClassName().indexOf("com.yss") >= 0){
            errMsg = ss[i].toString();
            break;
         }
      if (errMsg == null || errMsg.length() == 0)
         errMsg = ex.getMessage();
      else
         errMsg += "\r\n\t" + ex.getMessage();
      //组合成整个异常信息
      if (msg.length() == 0)
         errMsg = "出错信息：" + errMsg;
      else
         errMsg = msg + "\r\n\r\n详细信息：" + errMsg;
   }
}
