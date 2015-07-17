package com.wx.kernel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.wx.kernel.constant.Constants;
import com.wx.kernel.util.CookieUtil;

@Controller
public class BaseController {

	public Log logger = LogFactory.getLog(this.getClass());

	public Integer pageSize = 5;
	
	private String COMMUNITY_ID = "914bbf99-a4f2-4b85-9875-cfcccf4a5141";
	
	private String OPEN_ID = "4028e4814cc853bb014cc857fb750001";
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;

	/**
	 * 设置菜单被选中
	 * 
	 * @param model
	 * @param i
	 */
	public void setMenu(Model model, int i) {
		model.addAttribute("menuid", i);
	}

	/**
	 * 获取当前小区ID
	 * @param request
	 * @return
	 */
	public String getCommunityId(HttpServletRequest request) {
//		return COMMUNITY_ID;
		Cookie cookie = CookieUtil.getCookieByName(request, Constants.COOKIE_COMMUNITY_ID);
		if(cookie != null) {
			return cookie.getValue();
		}
		return null;
	}
	
	/**
	 * 获取OpenID
	 * @param request
	 * @return
	 */
	public String getOpenId(HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookieByName(request, Constants.COOKIE_OPENID_ID);
		if(cookie != null) {
			return cookie.getValue();
		}
		return null;
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
			//logger.error("error write json data", str);
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
