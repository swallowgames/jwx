package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.wx.kernel.controller.BaseController;

/**
 * 所有使用 Api 的 controller 需要继承此类
 */
public abstract class ApiController extends BaseController {
	public abstract ApiConfig getApiConfig();
}
