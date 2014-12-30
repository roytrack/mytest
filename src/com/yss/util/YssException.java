package com.yss.util;
import java.sql.BatchUpdateException;

/**
 * <p>Title: </p>
 * <p>Description: �쳣�࣬���г����׳����쳣��������쳣��<br>
 * ��Ҫ���ϴ��ݵ��쳣Ӧ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Ysstech</p>
 * @author alex
 * @version 1.0
 */

public final class YssException extends Exception{
   private String errMsg = null;
   public YssException(){}
   /**
    * Programmer: Dranson  ��֤���ݿ�������õ���ϸ�쳣��Ϣ
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
    * ���׳�����쳣ʱ����ָ��������Ϣmsg
    * @param msg��ָ����ȷ��������Ϣ
    */
   public YssException(String msg, Exception ex){
      buildMsg(msg, ex);
   }
   /**
    * �����׳�һ��������Ϣ
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
    * û���Զ�����Ϣ��ֱ���׳���YssException��������������insertMessage�����Ϣ
    * @param e Exception
    */
   public YssException(Exception e){
      buildMsg("", e);
   }
   /**
    * ����Զ��������Ϣ��һ���YssException(Exception)����ʹ��
    * @param msg String
    */
   public void insertMessage(String msg){
      errMsg = msg + "\r\n" + errMsg;
   }
//�����Զ���ʽ���Ĵ�����Ϣ
   public String getMessage(){
      return errMsg;
   }
   public String toString(){
      return errMsg;
   }
   private void buildMsg(String msg, Exception ex){
      StackTraceElement[] ss = ex.getStackTrace();
      //�����쳣��Ϣ
      for (int i = 0; i < ss.length; i++)
         if (ss[i].getClassName().indexOf("com.yss") >= 0){
            errMsg = ss[i].toString();
            break;
         }
      if (errMsg == null || errMsg.length() == 0)
         errMsg = ex.getMessage();
      else
         errMsg += "\r\n\t" + ex.getMessage();
      //��ϳ������쳣��Ϣ
      if (msg.length() == 0)
         errMsg = "������Ϣ��" + errMsg;
      else
         errMsg = msg + "\r\n\r\n��ϸ��Ϣ��" + errMsg;
   }
}
