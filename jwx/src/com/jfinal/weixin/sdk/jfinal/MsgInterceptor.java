/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.SignatureCheckKit;

/**
 * Msg 拦截器
 * 1：通过 MsgController.getApiConfig() 得到 ApiConfig 对象，并将其绑定到当前线程之上(利用了 ApiConfigKit 中的 ThreadLocal 对象)
 * 2：响应开发者中心服务器配置 URL 与 Token 请求
 * 3：签名检测
 * 注意： MsgController 的继承类如果覆盖了 index 方法，则需要对该 index 方法声明该拦截器
 * 		因为子类覆盖父类方法会使父类方法配置的拦截器失效，从而失去本拦截器的功能
 */
public class MsgInterceptor extends  HandlerInterceptorAdapter{
	
	private static final Logger logger =  Logger.getLogger(MsgInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		ApiConfigKit.removeThreadLocalApiConfig();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		// 将 ApiConfig 对象与当前线程绑定，以便在后续操作中方便获取该对象： ApiConfigKit.getApiConfig();
		// ApiConfigKit.setThreadLocalApiConfig(((MsgController)controller).getApiConfig());
		
		// 如果是服务器配置请求，则配置服务器并返回
		if (isConfigServerRequest(request)) {
			configServer(request, response);
			return false;
		}
		
		// 签名检测
		if (checkSignature(request, response)) {
			return true;
		}
		else {
			this.writeText(response, "签名验证失败，请确定是微信服务器在发送消息过来");
			return false;
		}
	}
	
	public void writeText(HttpServletResponse response, String text){
		response.setContentType("text/x-json;charset=utf-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write(text);
			out.close();
		} catch (IOException e) {
			logger.error("error write json data", e);
		}
	}
	
	/**
	 * 检测签名
	 */
	private boolean checkSignature(HttpServletRequest request,HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StringUtils.isNotEmpty(signature) || StringUtils.isNotEmpty(timestamp) || StringUtils.isNotEmpty(nonce)) {
			this.writeText(response, "check signature failure");
			return false;
		}
		
		if (SignatureCheckKit.me.checkSignature(signature, timestamp, nonce)) {
			return true;
		}
		else {
			logger.error("check signature failure: " +
					" signature = " + request.getParameter("signature") +
					" timestamp = " + request.getParameter("timestamp") +
					" nonce = " + request.getParameter("nonce"));
			
			return false;
		}
	}
	
	/**
	 * 是否为开发者中心保存服务器配置的请求
	 */
	private boolean isConfigServerRequest(HttpServletRequest request) {
		return StringUtils.isNotEmpty(request.getParameter("echostr"));
	}
	
	/**
	 * 配置开发者中心微信服务器所需的 url 与 token
	 * @return true 为config server 请求，false 正式消息交互请求
	 */
	public void configServer(HttpServletRequest request,HttpServletResponse response) {
		// 通过 echostr 判断请求是否为配置微信服务器回调所需的 url 与 token
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
		boolean isOk = SignatureCheckKit.me.checkSignature(signature, timestamp, nonce);
		if (isOk)
			this.writeText(response, echostr);
		else
			logger.error("验证失败：configServer");
	}
	
}



