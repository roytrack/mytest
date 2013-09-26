<?xml version="1.0" encoding="GBK"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
<html>
		<head>
			<title>保险估值系统回归测试报告</title>
		</head> 
  <body>
  <table  align='center'>
  <tr><td>
  	<h1>保险估值系统回归测试报告</h1>
  	</td></tr>
  	<tr><td>
    <h2>一、测试环境 </h2>
    <p/>
    回归日期：
      <xsl:value-of select="report/dataStatistics/reportDate/today"/>
    <p/>
     本次回归从
    <xsl:value-of select="report/dataStatistics/reportDate/beginDate"/>
    到
    <xsl:value-of select="report/dataStatistics/reportDate/endDate"/>
    的交易数据，凭证，估值表。
    <p/>
    执行时间：从 <xsl:value-of select="report/dataStatistics/executeTime/beginTime"/>
    到
    <xsl:value-of select="report/dataStatistics/executeTime/endTime"/>
    <p/>
    各表记录数统计如下：
    <table border="1"  style="border-collapse: collapse;" bordercolor="#588fc7" >
    <tr bgColor="#608c8c">
    <th align='left'>表名</th>
    <th align='left'>原有记录数</th>
    <th align='left'>回归后记录数</th>
    </tr>
      <xsl:for-each select="report/dataStatistics/tableRecord/table">
    <tr>
      <td><xsl:value-of select="tableName"/></td>
      <td><xsl:value-of select="originalTable"/></td>
      <td><xsl:value-of select="tempTable"/></td>
    </tr>
    </xsl:for-each>
    </table>
    <p/>程序异常情况如下：
    <table border="1"  style="border-collapse: collapse;" bordercolor="#588fc7" >
    <tr bgColor="#608c8c">
    <th align='left'> 异常信息</th>
    <th align='left'>异常数</th>
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
    <h2>二、具体结果 </h2>
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