<?xml version="1.0" encoding="GBK"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
<html>
		<head>
			<title>���չ�ֵϵͳ�ع���Ա���</title>
		</head> 
  <body>
  <table  align='center'>
  <tr><td>
  	<h1>���չ�ֵϵͳ�ع���Ա���</h1>
  	</td></tr>
  	<tr><td>
    <h2>һ�����Ի��� </h2>
    <p/>
    �ع����ڣ�
      <xsl:value-of select="report/dataStatistics/reportDate/today"/>
    <p/>
     ���λع��
    <xsl:value-of select="report/dataStatistics/reportDate/beginDate"/>
    ��
    <xsl:value-of select="report/dataStatistics/reportDate/endDate"/>
    �Ľ������ݣ�ƾ֤����ֵ��
    <p/>
    ִ��ʱ�䣺�� <xsl:value-of select="report/dataStatistics/executeTime/beginTime"/>
    ��
    <xsl:value-of select="report/dataStatistics/executeTime/endTime"/>
    <p/>
    �����¼��ͳ�����£�
    <table border="1"  style="border-collapse: collapse;" bordercolor="#588fc7" >
    <tr bgColor="#608c8c">
    <th align='left'>����</th>
    <th align='left'>ԭ�м�¼��</th>
    <th align='left'>�ع���¼��</th>
    </tr>
      <xsl:for-each select="report/dataStatistics/tableRecord/table">
    <tr>
      <td><xsl:value-of select="tableName"/></td>
      <td><xsl:value-of select="originalTable"/></td>
      <td><xsl:value-of select="tempTable"/></td>
    </tr>
    </xsl:for-each>
    </table>
    <p/>�����쳣������£�
    <table border="1"  style="border-collapse: collapse;" bordercolor="#588fc7" >
    <tr bgColor="#608c8c">
    <th align='left'> �쳣��Ϣ</th>
    <th align='left'>�쳣��</th>
    </tr>
       <xsl:for-each select="report/dataStatistics/exceptionStats/exception">
    <tr>
      <td><xsl:value-of select="exceptionText"/></td>
      <td><xsl:value-of select="exceptionNo"/></td>
    </tr>
    </xsl:for-each>
    </table>
   </td></tr>
   
  	<tr><td>
    <h2>���������� </h2>
    <table border="1" style="border-collapse: collapse;" bordercolor="#588fc7" >
    <tr bgColor="#608c8c" >
      <th align='left'>Title</th>
      <th align='left'>Artist</th>
    </tr>
    </table>
    </td></tr>
    </table>
    </body>
    </html>
    
	</xsl:template>
</xsl:stylesheet>