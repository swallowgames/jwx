package com.wx.kernel.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ServletUtils {
	
	private static final Logger logger = Logger.getLogger(ServletUtils.class);
	
	 public static final String TEXT_TYPE = "text/plain";
	 public static final String JSON_TYPE = "application/json";
	 public static final String XML_TYPE = "text/xml";
	 public static final String HTML_TYPE = "text/html";
	 public static final String JS_TYPE = "text/javascript";
	 public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	 public static final String WORD_TYPE = "application/vnd.ms-word";
	 public static final String PDF_TYPE = "application/pdf";
	 public static final String PRINT_TYPE = "application/octet-stream";
	 public static final String AUTHENTICATION_HEADER = "Authorization";
	 public static final long ONE_YEAR_SECONDS = 31536000L;

	 public static void setExpiresHeader(HttpServletResponse paramHttpServletResponse, long paramLong){
	    paramHttpServletResponse.setDateHeader("Expires", System.currentTimeMillis() + paramLong * 1000L);
	    paramHttpServletResponse.setHeader("Cache-Control", "private, max-age=" + paramLong);
	  }

	  public static void setDisableCacheHeader(HttpServletResponse paramHttpServletResponse) {
	    paramHttpServletResponse.setDateHeader("Expires", 1L);
	    paramHttpServletResponse.addHeader("Pragma", "no-cache");
	    paramHttpServletResponse.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	  }

	  public static void setLastModifiedHeader(HttpServletResponse paramHttpServletResponse, long paramLong){
	    paramHttpServletResponse.setDateHeader("Last-Modified", paramLong);
	  }

	  public static void setEtag(HttpServletResponse paramHttpServletResponse, String paramString){
	    paramHttpServletResponse.setHeader("ETag", paramString);
	  }

	  public static boolean checkIfModifiedSince(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, long paramLong){
	    long l = paramHttpServletRequest.getDateHeader("If-Modified-Since");
	    if ((l != -1L) && (paramLong < l + 1000L))
	    {
	      paramHttpServletResponse.setStatus(304);
	      return false;
	    }
	    return true;
	  }

	 public static boolean checkIfNoneMatchEtag(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString){
	    String str1 = paramHttpServletRequest.getHeader("If-None-Match");
	    if (str1 != null){
	      int i = 0;
	      if (!"*".equals(str1)){
	        StringTokenizer localStringTokenizer = new StringTokenizer(str1, ",");
	        while ((i == 0) && (localStringTokenizer.hasMoreTokens())){
	          String str2 = localStringTokenizer.nextToken();
	          if (str2.trim().equals(paramString))
	            i = 1;
	        }
	      }else {
	        i = 1;
	      }
	      if (i != 0){
	        paramHttpServletResponse.setStatus(304);
	        paramHttpServletResponse.setHeader("ETag", paramString);
	        return false;
	      }
	    }
	    return true;
	 }

	public static Map<String, Object> getParametersStartingWith(
			HttpServletRequest request, String paramString) {
		Enumeration localEnumeration = request.getParameterNames();
		TreeMap localTreeMap = new TreeMap();
		if (paramString == null)
			paramString = "";
		while ((localEnumeration != null) && (localEnumeration.hasMoreElements())) {
			String str1 = (String) localEnumeration.nextElement();
			if (("".equals(paramString)) || (str1.startsWith(paramString))) {
				String str2 = str1.substring(paramString.length());
				String[] arrayOfString = request.getParameterValues(str1);
				if ((arrayOfString != null) && (arrayOfString.length != 0)){
					for (int i = 0; i < arrayOfString.length; i++) {
						try {
							if(StringUtils.isNotEmpty(arrayOfString[i]))
								arrayOfString[i] = java.net.URLDecoder.decode(new String(arrayOfString[i].getBytes("ISO-8859-1"), "utf-8") , "UTF-8");
						} catch (UnsupportedEncodingException e) {
							logger.error("参数转换错误", e);
						}
					}
					if (arrayOfString.length > 1)
						localTreeMap.put(str2, arrayOfString);
					else
						localTreeMap.put(str2, arrayOfString[0]);
				}
			}
		}
		return localTreeMap;
	}

  
	public static String getRemortIP(HttpServletRequest paramHttpServletRequest){
	    if (paramHttpServletRequest.getHeader("x-forwarded-for") == null)
	      return paramHttpServletRequest.getRemoteAddr();
	    return paramHttpServletRequest.getHeader("x-forwarded-for");
	}

	public String getIpAddr(HttpServletRequest paramHttpServletRequest){
	    String str = paramHttpServletRequest.getHeader("x-forwarded-for");
	    if ((str == null) || (str.length() == 0) || ("unknown".equalsIgnoreCase(str)))
	      str = paramHttpServletRequest.getHeader("Proxy-Client-IP");
	    if ((str == null) || (str.length() == 0) || ("unknown".equalsIgnoreCase(str)))
	      str = paramHttpServletRequest.getHeader("WL-Proxy-Client-IP");
	    if ((str == null) || (str.length() == 0) || ("unknown".equalsIgnoreCase(str)))
	      str = paramHttpServletRequest.getRemoteAddr();
	    return str;
	  }
}