package com.wx.kernel.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.wx.kernel.util.wx.MyTrustAnyTrustManager;
import com.wx.kernel.util.wx.SSLClient;

public class ParseUrlResponseData {
	
	private static Logger logger = Logger.getLogger(ParseUrlResponseData.class);
	/**
	 * 访问页面并解析页面返回的数据信息
	 * @param link 要访问的页面路径
	 * @param sendType 访问方式（GET,POST）
	 * @param data 要发送的数据
	 * @param encode 字符编码
	 * @return 返回字符串
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static String getDataByUrl(String url,String sendType,String json_param_data,String charset)  {
		
		logger.info("请求路径: " + url);
		logger.info("所携带的参数为:" + json_param_data);
		final javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        final javax.net.ssl.TrustManager tm = new MyTrustAnyTrustManager();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc;
		try {
			 sc = javax.net.ssl.SSLContext.getInstance( "SSL" );
			 sc.init( null, trustAllCerts, null );
		     javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory( sc.getSocketFactory() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        if(charset == null) {
        	charset = "UTF-8";
        }
        try {
    		httpClient = new SSLClient();  
    		httpPost = new HttpPost(url);  
    		//设置参数  
    		 StringEntity entity = new StringEntity(json_param_data == null ? "" : json_param_data,charset);//解决中文乱码问题    
	         entity.setContentEncoding(charset);    
	         entity.setContentType("application/json"); 
	         httpPost.setEntity(entity); 
            HttpResponse response = httpClient.execute(httpPost); 
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset); 
                }  
            }  
            logger.info("返回的数据：" + result);
        } catch (Exception e) {
	        e.printStackTrace();
	    } 
	        return result;
	}
}
