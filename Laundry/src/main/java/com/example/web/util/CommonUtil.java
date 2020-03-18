package com.example.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.sql.DataSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;




@SuppressWarnings({ "unused", "deprecation" })
public class CommonUtil {

	HashMap<String, Object> hmap = new HashMap<String, Object>();
	
	private static CommonUtil dbconnection;
	private Connection connection;
	public static int COMPARETYPE_NAME = 0;
	public static int COMPARETYPE_DATE = 1;
	
	
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	private Properties connectionProperties = new Properties();
	public static CommonUtil getInstance() {
		if (dbconnection == null)
			dbconnection = new CommonUtil();
		return dbconnection;
	}
	public Connection openmysql() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource) envContext.lookup("jdbc/mysql");
		conn = dataSource.getConnection();
		return conn;
	}
	public static void closeDBTool(AutoCloseable closeObj) {
		try {
			if (closeObj != null)
				closeObj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void sqlclose(AutoCloseable conn, AutoCloseable stmt, AutoCloseable rset) {
		if (conn != null) {
			closeDBTool(conn);
		}
		if (stmt != null) {
			closeDBTool(stmt);
		}
		if (rset != null) {
			closeDBTool(rset);
		}
	}
	public String setUTFchar(String str) throws Exception {
		String utfstr=null;
		if(!str.equals(null)) {
			utfstr=new String(str.getBytes("UTF-16"),"UTF-16");
		}
		return utfstr;
	}
	public int check(Date a,Date b) throws Exception {
		 java.util.Date cdate=sdf.parse(sdf.format(a));
		 java.util.Date sdate=sdf.parse(sdf.format(b));
		int i=0;
		if(a.compareTo(b)>0) {
		i=1;
		}
		return i;
		
	}
	public int vcheck(Date a,Date b) throws Exception {
		 java.util.Date cdate=sdf.parse(sdf.format(a));
		 java.util.Date sdate=sdf.parse(sdf.format(b));
		int i=0;
		if(a.compareTo(b)<0) {
		i=1;
		}
		return i;
		
	}
}