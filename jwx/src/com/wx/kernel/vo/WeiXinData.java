package com.wx.kernel.vo;

import java.util.Map;

import com.wx.kernel.model.wx.WXAccount;
import com.wx.kernel.model.wx.WXEnterpriseAccount;

/**
 * 微信数据
 * @author yuanzh
 *
 */
public class WeiXinData {
	
	/**key：小区ID或公众号原始ID*/
	private Map<String, WXAccount> publicAccountMap;
	
	/**key：小区ID或者企业号原始ID*/
	private Map<String, WXEnterpriseAccount> enterpriseAccountMap;
	
	private static WeiXinData weiXinData;
	
	public static WeiXinData getInstance() {
		if(weiXinData == null) {
			weiXinData = new WeiXinData();
		}
		return weiXinData;
	}
	
	private WeiXinData() {
	}
	
	public WXAccount getWXAccount(String communityId) {
		return this.publicAccountMap.get(communityId);
	}
	
	public WXEnterpriseAccount getWXEnterpriseAccount(String communityId) {
		return this.enterpriseAccountMap.get(communityId);
	}

	public Map<String, WXAccount> getPublicAccountMap() {
		return publicAccountMap;
	}

	public void setPublicAccountMap(Map<String, WXAccount> publicAccountMap) {
		this.publicAccountMap = publicAccountMap;
	}

	public Map<String, WXEnterpriseAccount> getEnterpriseAccountMap() {
		return enterpriseAccountMap;
	}

	public void setEnterpriseAccountMap(
			Map<String, WXEnterpriseAccount> enterpriseAccountMap) {
		this.enterpriseAccountMap = enterpriseAccountMap;
	}

}
