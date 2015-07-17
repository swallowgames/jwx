package com.wx.kernel.freemarker;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 定义在Freemarker模板中使用的全局变量。在FreeMarker模版中可以使用“ctx”取得当前应用上下文路径名称，“request”取得当前http请求。
 * @author
 */
public class FreeMarkerVariables extends FreeMarkerView {
	
	/**
	 * 应用部署路径
	 */
	public static final String CONTEXT_PATH = "base";
	public static final String IMGURL = "imgUrl";
	/**
	 * 静态文件根目录
	 * 
	 */
	public static final String STATICCONTEXT_PATH = "stx";
	/**
	 * 当前http请求
	 */
	public static final String REQUEST = "request";

	/**
	 * 请求时间戳，可以用于js的版本
	 */
	public static final String ST_VERSION = "stversion";
	public static final Integer ST_VERSION_VALUE = new Random().nextInt();
	
	@Override
	protected final void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
		model.put(CONTEXT_PATH, request.getContextPath());
		model.put(IMGURL, request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getContextPath())) + "/img");
		model.put(ST_VERSION, ST_VERSION_VALUE);

		model.put(REQUEST, request);
		//model.put(STATICCONTEXT_PATH, (request.getContextPath() + "/resources"));
		// 设置当前Request的来源地址
		model.put("REQUEST_REFERER", request.getHeader("REFERER"));
		this.putVariables(model, request);
		
	}

	/**
	 *  在Model中put参数方便在模版中使用。子类可以重写该方法put其他数据到model中。
	 *
	 *	@param model - model。
	 *	@param request - 当前http请求。
	 */
	protected void putVariables(Map model, HttpServletRequest request) {
	}

}
