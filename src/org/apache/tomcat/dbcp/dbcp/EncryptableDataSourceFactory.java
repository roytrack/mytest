/*     */ package org.apache.tomcat.dbcp.dbcp;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.Name;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.spi.ObjectFactory;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ public class EncryptableDataSourceFactory
/*     */   implements ObjectFactory
/*     */ {
/*     */   private static final String PROP_DEFAULTAUTOCOMMIT = "defaultAutoCommit";
/*     */   private static final String PROP_DEFAULTREADONLY = "defaultReadOnly";
/*     */   private static final String PROP_DEFAULTTRANSACTIONISOLATION = "defaultTransactionIsolation";
/*     */   private static final String PROP_DEFAULTCATALOG = "defaultCatalog";
/*     */   private static final String PROP_DRIVERCLASSNAME = "driverClassName";
/*     */   private static final String PROP_MAXACTIVE = "maxActive";
/*     */   private static final String PROP_MAXIDLE = "maxIdle";
/*     */   private static final String PROP_MINIDLE = "minIdle";
/*     */   private static final String PROP_INITIALSIZE = "initialSize";
/*     */   private static final String PROP_MAXWAIT = "maxWait";
/*     */   private static final String PROP_TESTONBORROW = "testOnBorrow";
/*     */   private static final String PROP_TESTONRETURN = "testOnReturn";
/*     */   private static final String PROP_TIMEBETWEENEVICTIONRUNSMILLIS = "timeBetweenEvictionRunsMillis";
/*     */   private static final String PROP_NUMTESTSPEREVICTIONRUN = "numTestsPerEvictionRun";
/*     */   private static final String PROP_MINEVICTABLEIDLETIMEMILLIS = "minEvictableIdleTimeMillis";
/*     */   private static final String PROP_TESTWHILEIDLE = "testWhileIdle";
/*     */   private static final String PROP_PASSWORD = "password";
/*     */   private static final String PROP_URL = "url";
/*     */   private static final String PROP_USERNAME = "username";
/*     */   private static final String PROP_VALIDATIONQUERY = "validationQuery";
/*     */   private static final String PROP_VALIDATIONQUERY_TIMEOUT = "validationQueryTimeout";
/*     */   private static final String PROP_ISENCRYPT = "isEncrypt";
/*     */   private static final String PROP_INITCONNECTIONSQLS = "initConnectionSqls";
/*     */   private static final String PROP_ACCESSTOUNDERLYINGCONNECTIONALLOWED = "accessToUnderlyingConnectionAllowed";
/*     */   private static final String PROP_REMOVEABANDONED = "removeAbandoned";
/*     */   private static final String PROP_REMOVEABANDONEDTIMEOUT = "removeAbandonedTimeout";
/*     */   private static final String PROP_LOGABANDONED = "logAbandoned";
/*     */   private static final String PROP_POOLPREPAREDSTATEMENTS = "poolPreparedStatements";
/*     */   private static final String PROP_MAXOPENPREPAREDSTATEMENTS = "maxOpenPreparedStatements";
/*     */   private static final String PROP_CONNECTIONPROPERTIES = "connectionProperties";
/*  85 */   private static final String[] ALL_PROPERTIES = { 
/*  86 */     "defaultAutoCommit", 
/*  87 */     "defaultReadOnly", 
/*  88 */     "defaultTransactionIsolation", 
/*  89 */     "defaultCatalog", 
/*  90 */     "driverClassName", 
/*  91 */     "maxActive", 
/*  92 */     "maxIdle", 
/*  93 */     "minIdle", 
/*  94 */     "initialSize", 
/*  95 */     "maxWait", 
/*  96 */     "testOnBorrow", 
/*  97 */     "testOnReturn", 
/*  98 */     "timeBetweenEvictionRunsMillis", 
/*  99 */     "numTestsPerEvictionRun", 
/* 100 */     "minEvictableIdleTimeMillis", 
/* 101 */     "testWhileIdle", 
/* 102 */     "password", 
/* 103 */     "url", 
/* 104 */     "username", 
/* 105 */     "validationQuery", 
/* 106 */     "validationQueryTimeout", 
/* 107 */     "initConnectionSqls", 
/* 108 */     "accessToUnderlyingConnectionAllowed", 
/* 109 */     "removeAbandoned", 
/* 110 */     "removeAbandonedTimeout", 
/* 111 */     "logAbandoned", 
/* 112 */     "poolPreparedStatements", 
/* 113 */     "maxOpenPreparedStatements", 
/* 114 */     "connectionProperties", 
/* 115 */     "isEncrypt" };
/*     */ 
/*     */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment)
/*     */     throws Exception
/*     */   {
/* 140 */     if ((obj == null) || (!(obj instanceof Reference))) {
/* 141 */       return null;
/*     */     }
/*     */ 
/* 144 */     Reference ref = (Reference)obj;
/*     */ 
/* 146 */     if (!"javax.sql.DataSource".equals(ref.getClassName())) {
/* 147 */       return null;
/*     */     }
/*     */ 
/* 150 */     Properties properties = new Properties();
/* 151 */     for (int i = 0; i < ALL_PROPERTIES.length; i++) {
/* 152 */       String propertyName = ALL_PROPERTIES[i];
/* 153 */       RefAddr ra = ref.get(propertyName);
/*     */ 
/* 155 */       if (ra != null) {
/* 156 */         String propertyValue = ra.getContent().toString();
/* 157 */         properties.setProperty(propertyName, propertyValue);
/*     */       }
/*     */     }
/* 160 */     return createDataSource(properties);
/*     */   }
/*     */ 
/*     */   public static DataSource createDataSource(Properties properties)
/*     */     throws Exception
/*     */   {
/* 173 */     BasicDataSource dataSource = new BasicDataSource();
/* 174 */     String value = null;
/*     */ 
/* 176 */     value = properties.getProperty("defaultAutoCommit");
/* 177 */     if (value != null) {
/* 178 */       dataSource.setDefaultAutoCommit(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 181 */     value = properties.getProperty("defaultReadOnly");
/* 182 */     if (value != null) {
/* 183 */       dataSource.setDefaultReadOnly(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 186 */     value = properties.getProperty("defaultTransactionIsolation");
/* 187 */     if (value != null) {
/* 188 */       int level = -1;
/* 189 */       if ("NONE".equalsIgnoreCase(value)) {
/* 190 */         level = 0;
/*     */       }
/* 192 */       else if ("READ_COMMITTED".equalsIgnoreCase(value)) {
/* 193 */         level = 2;
/*     */       }
/* 195 */       else if ("READ_UNCOMMITTED".equalsIgnoreCase(value)) {
/* 196 */         level = 1;
/*     */       }
/* 198 */       else if ("REPEATABLE_READ".equalsIgnoreCase(value)) {
/* 199 */         level = 4;
/*     */       }
/* 201 */       else if ("SERIALIZABLE".equalsIgnoreCase(value))
/* 202 */         level = 8;
/*     */       else {
/*     */         try
/*     */         {
/* 206 */           level = Integer.parseInt(value);
/*     */         } catch (NumberFormatException e) {
/* 208 */           System.err.println("Could not parse defaultTransactionIsolation: " + value);
/* 209 */           System.err.println("WARNING: defaultTransactionIsolation not set");
/* 210 */           System.err.println("using default value of database driver");
/* 211 */           level = -1;
/*     */         }
/*     */       }
/* 214 */       dataSource.setDefaultTransactionIsolation(level);
/*     */     }
/*     */ 
/* 217 */     value = properties.getProperty("defaultCatalog");
/* 218 */     if (value != null) {
/* 219 */       dataSource.setDefaultCatalog(value);
/*     */     }
/*     */ 
/* 222 */     value = properties.getProperty("maxActive");
/* 223 */     if (value != null) {
/* 224 */       dataSource.setMaxActive(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 227 */     value = properties.getProperty("maxIdle");
/* 228 */     if (value != null) {
/* 229 */       dataSource.setMaxIdle(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 232 */     value = properties.getProperty("minIdle");
/* 233 */     if (value != null) {
/* 234 */       dataSource.setMinIdle(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 237 */     value = properties.getProperty("initialSize");
/* 238 */     if (value != null) {
/* 239 */       dataSource.setInitialSize(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 242 */     value = properties.getProperty("maxWait");
/* 243 */     if (value != null) {
/* 244 */       dataSource.setMaxWait(Long.parseLong(value));
/*     */     }
/*     */ 
/* 247 */     value = properties.getProperty("testOnBorrow");
/* 248 */     if (value != null) {
/* 249 */       dataSource.setTestOnBorrow(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 252 */     value = properties.getProperty("testOnReturn");
/* 253 */     if (value != null) {
/* 254 */       dataSource.setTestOnReturn(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 257 */     value = properties.getProperty("timeBetweenEvictionRunsMillis");
/* 258 */     if (value != null) {
/* 259 */       dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(value));
/*     */     }
/*     */ 
/* 262 */     value = properties.getProperty("numTestsPerEvictionRun");
/* 263 */     if (value != null) {
/* 264 */       dataSource.setNumTestsPerEvictionRun(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 267 */     value = properties.getProperty("minEvictableIdleTimeMillis");
/* 268 */     if (value != null) {
/* 269 */       dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(value));
/*     */     }
/*     */ 
/* 272 */     value = properties.getProperty("testWhileIdle");
/* 273 */     if (value != null) {
/* 274 */       dataSource.setTestWhileIdle(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 278 */     String isEncrypt = properties.getProperty("isEncrypt");
/* 279 */     if ((isEncrypt != null) && (isEncrypt.equals("0"))) {
/* 280 */       value = properties.getProperty("url");
/* 281 */       if (value != null) {
/* 282 */         dataSource.setUrl(value);
/*     */       }
/* 284 */       value = properties.getProperty("driverClassName");
/* 285 */       if (value != null) {
/* 286 */         dataSource.setDriverClassName(value);
/*     */       }
/* 288 */       value = properties.getProperty("username");
/* 289 */       if (value != null) {
/* 290 */         dataSource.setUsername(value);
/*     */       }
/* 292 */       value = properties.getProperty("password");
/* 293 */       if (value != null) {
/* 294 */         dataSource.setPassword(value);
/*     */       }
/* 296 */       String path = System.getProperty("user.dir");
/* 297 */       path = path.substring(0, path.indexOf("bin"));
/* 298 */       XMLConfigUtil.updateXML(path + "conf\\Catalina\\localhost\\CCIDPlatform.xml");
/* 299 */     } else if ((isEncrypt != null) && (isEncrypt.equals("1"))) {
/* 300 */       value = properties.getProperty("url");
/* 301 */       if (value != null) {
/* 302 */         dataSource.setUrl(AESUtil.decrypt(value, "!@#$QWER"));
/*     */       }
/* 304 */       value = properties.getProperty("driverClassName");
/* 305 */       if (value != null) {
/* 306 */         dataSource.setDriverClassName(AESUtil.decrypt(value, "!@#$QWER"));
/*     */       }
/* 308 */       value = properties.getProperty("username");
/* 309 */       if (value != null) {
/* 310 */         dataSource.setUsername(AESUtil.decrypt(value, "!@#$QWER"));
/*     */       }
/* 312 */       value = properties.getProperty("password");
/* 313 */       if (value != null) {
/* 314 */         dataSource.setPassword(AESUtil.decrypt(value, "!@#$QWER"));
/*     */       }
/*     */     }
/*     */ 
/* 318 */     value = properties.getProperty("validationQuery");
/* 319 */     if (value != null) {
/* 320 */       dataSource.setValidationQuery(value);
/*     */     }
/*     */ 
/* 323 */     value = properties.getProperty("validationQueryTimeout");
/* 324 */     if (value != null) {
/* 325 */       dataSource.setValidationQueryTimeout(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 328 */     value = properties.getProperty("accessToUnderlyingConnectionAllowed");
/* 329 */     if (value != null) {
/* 330 */       dataSource.setAccessToUnderlyingConnectionAllowed(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 333 */     value = properties.getProperty("removeAbandoned");
/* 334 */     if (value != null) {
/* 335 */       dataSource.setRemoveAbandoned(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 338 */     value = properties.getProperty("removeAbandonedTimeout");
/* 339 */     if (value != null) {
/* 340 */       dataSource.setRemoveAbandonedTimeout(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 343 */     value = properties.getProperty("logAbandoned");
/* 344 */     if (value != null) {
/* 345 */       dataSource.setLogAbandoned(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 348 */     value = properties.getProperty("poolPreparedStatements");
/* 349 */     if (value != null) {
/* 350 */       dataSource.setPoolPreparedStatements(Boolean.valueOf(value).booleanValue());
/*     */     }
/*     */ 
/* 353 */     value = properties.getProperty("maxOpenPreparedStatements");
/* 354 */     if (value != null) {
/* 355 */       dataSource.setMaxOpenPreparedStatements(Integer.parseInt(value));
/*     */     }
/*     */ 
/* 358 */     value = properties.getProperty("initConnectionSqls");
/* 359 */     if (value != null) {
/* 360 */       StringTokenizer tokenizer = new StringTokenizer(value, ";");
/* 361 */       dataSource.setConnectionInitSqls(Collections.list(tokenizer));
/*     */     }
/*     */ 
/* 364 */     value = properties.getProperty("connectionProperties");
/* 365 */     if (value != null) {
/* 366 */       Properties p = getProperties(value);
/* 367 */       Enumeration e = p.propertyNames();
/* 368 */       while (e.hasMoreElements()) {
/* 369 */         String propertyName = (String)e.nextElement();
/* 370 */         dataSource.addConnectionProperty(propertyName, p.getProperty(propertyName));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 376 */     if (dataSource.getInitialSize() > 0) {
/* 377 */       dataSource.getLogWriter();
/*     */     }
/*     */ 
/* 381 */     return dataSource;
/*     */   }
/*     */ 
/*     */   private static Properties getProperties(String propText)
/*     */     throws Exception
/*     */   {
/* 391 */     Properties p = new Properties();
/* 392 */     if (propText != null) {
/* 393 */       p.load(new ByteArrayInputStream(propText.replace(';', '\n').getBytes()));
/*     */     }
/* 395 */     return p;
/*     */   }
/*     */ }

/* Location:           D:\赛迪文件\工作文件_detail\20130829tomcat的jndi加密\玉伟\tomcat-dbcp.jar
 * Qualified Name:     org.apache.tomcat.dbcp.dbcp.EncryptableDataSourceFactory
 * JD-Core Version:    0.6.0
 */