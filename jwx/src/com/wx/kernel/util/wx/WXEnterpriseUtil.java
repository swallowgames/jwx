package com.wx.kernel.util.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class WXEnterpriseUtil {
	
	public static Logger logger = LoggerFactory.getLogger(WXEnterpriseUtil.class);

	public static void main(String[] args) {
		
	}
	
	public static String getAccessToken(String corpid, String corpsecret) {
		System.out.println("corpid = " + corpid + " || corpsecret= " + corpsecret);
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		
		JSONObject json = (JSONObject) JSONObject.parse(result);
		if (!json.containsKey("errcode")) {
			return (String) json.get("access_token");
		} else {
			return null;
		}
	}
	
	/** 创建部门 
	 * @throws Exception */
	public static Integer createDept(String accessToken, String json){
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return (Integer)jsonObj.get("id");
			}
		}
		return 0;
	}
	
	/** 更新部门 
	 * @throws Exception */
	public static boolean updateDept(String accessToken, String json){
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 删除部门 
	 * @throws Exception */
	public static boolean deleteDept(String accessToken, Integer deptId) throws Exception {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+accessToken + "&id=" + deptId;
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 获取部门列表 
	 * @throws Exception */
	public static String queryDept(String accessToken, Integer deptId) throws Exception {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken + (deptId == null ? "" : "&id=" + deptId);
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		return result;
	}
	
	/** 创建成员 
	 * @throws Exception */
	public static boolean createUser(String accessToken, String json) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/create";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 更新成员 
	 * @throws Exception */
	public static boolean updateUser(String accessToken, String json) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/update";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 删除成员 
	 * @throws Exception */
	public static boolean deleteUser(String accessToken, String userId) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token="+accessToken+"&userid" + userId;
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 批量删除成员 
	 * @throws Exception */
	public static boolean batchDeleteUser(String accessToken, String json){
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** 获取成员 
	 * @throws Exception */
	public static String queryUser(String accessToken, String userId) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken + "&userid="+userId;
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		return result;
	}
	
	/** 获取部门成员 
	 * @throws Exception */
	public static String queryDeptUser(String accessToken, Integer deptId) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+accessToken + "&department_id="+deptId;
		String result = WXUtil.sendWxSoap(null, postUrl, null, null);
		return result;
	}
	
	/** 发送消息接口 
	 * @throws Exception */
	public static boolean sentMsg(String accessToken, String json) {
		String postUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
		String result = WXUtil.sendWxSoap(accessToken, postUrl, json, null);
		if(result != null) {
			JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
			if((Integer)jsonObj.get("errcode") == 0) {
				return true;
			}
		}
		return false;
	}
}
