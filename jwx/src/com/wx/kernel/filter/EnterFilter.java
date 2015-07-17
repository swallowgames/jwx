package com.wx.kernel.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class EnterFilter extends OncePerRequestFilter {

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String agent = request.getHeader("user-agent");
		// System.out.println("useragent" + agent);
		agent = agent.toLowerCase();
		// 正常入口
		if (agent.contains("micromessenger")) {
			filterChain.doFilter(request, response);
			return;
		}
		String uri = request.getRequestURI().toLowerCase();
		// System.out.println("uri" + uri);
		// 特殊入口
		if (uri.contains("diyuzhimen") || uri.contains("showmsg")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 特殊入口，需要的辅助请求
		if (uri.contains(".js") || uri.contains(".css")
				|| uri.contains("images")) {
			filterChain.doFilter(request, response);
			return;
		}
		String openId = (String) request.getSession().getAttribute("openId");
		if (openId != null) {
			filterChain.doFilter(request, response);
			return;
		}

		// System.out.println("openId" + openId);
		// if ("4028ea814be4a37b014be4a390c10000".equals(openId)) {
		// filterChain.doFilter(request, response);
		// return;
		// }
		response.sendRedirect("/showMsg.html");

	}
}
