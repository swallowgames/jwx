package com.wx.kernel.util.wx;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wx.kernel.util.ParseUrlResponseData;

public class WXUtil {
	
	public static Log logger = LogFactory.getLog("logfile");
	private static String charset = "UTF-8";
	public static void main(String[] args) {
		//String str = getAccessToken("wxc88a5d65cd83be91", "6e7ee419e6677476d50ea7cefb0d5180");
		//logger.info(str);
		String accessToken = "5VJctPJw1YitJP-KQB4n1j28LSFLZt_DzkYh4YFynOwZKNM1YFX7pVKsi8TrqKmtddegZUFOzMIAV4inTwfZqBeL-iS_twEcfBuZ9LcCBUs";
		//queryGroup(accessToken);
		getJsApiTicket(accessToken);
	}

	/**
	 * 获取jsapi_ticket
	 * GET请求
	 * 
	 * @return
	 */
	public static String getJsApiTicket(String accessToken) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken + "&type=jsapi";
//		String result = sendWxSoap(null, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, "UTF-8");
		JSONObject json = (JSONObject) JSONObject.parse(result);
		if (json.containsKey("ticket")) {
			return (String) json.get("ticket");
		} else {
			return null;
		}
	}
	
	/**
	 * 获取accesstoken
	 * GET请求
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static String getAccessToken(String appId, String appSecret) {
		logger.info("appId = " + appId + " || appSecret" + appSecret);
		String postUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
//		String result = sendWxSoap(null, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, charset);
		JSONObject json = (JSONObject) JSONObject.parse(result);
		if (json != null && !json.containsKey("errcode")) {
			return (String) json.get("access_token");
		} else {
			return null;
		}
	}

	/**
	 * 创建分组
	 * post
	 * @param accessToken
	 * @return
	 */
	public static Integer createGroup(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
		JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
		if (jsonObj != null &&  !jsonObj.containsKey("errcode")) {
			return (Integer) jsonObj.getJSONObject("group").get("id");
		} else {
			return null;
		}
	}

	/**
	 * 查询分组
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String queryGroup(String accessToken) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, null, null, null);
		logger.info("开始调用查询分组接口；");
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "GET", null, charset);
		logger.info("调用查询分组接口结束，返回的数据信息为：" + result);
		return result;
	}

	/**
	 * 查询用户所在分组
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String queryUserGroup(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
		return result;
	}

	/**
	 * 修改分组名
	 * POST
	 * @param accessToken
	 */
	public static void modifyGroupName(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + accessToken;
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
//		String result = sendWxSoap(accessToken, postUrl, json, null);
	}

	/**
	 * 移动用户分组
	 * post
	 * @param accessToken
	 */
	public static void moveUserGroup(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
	}

	/**
	 * 添加用户备注
	 * post
	 * @param accessToken
	 */
	public static void createUserRemark(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
	}

	/**
	 * 获取关注者列表
	 * get
	 * @param accessToken
	 * @return
	 */
	public static String queryUserList(String accessToken, String nextOpenId) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/user/get";
		if (nextOpenId == null) {
			postUrl += "?access_token="+accessToken;
		} else {
			postUrl += "?access_token="+accessToken + "&next_openid=" + nextOpenId;
		}
//		String result = sendWxSoap(null, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, charset);
		return result;
	}

	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * get
	 * @param accessToken
	 * @return
	 */
	public static String queryUserUnionID(String accessToken, String openId) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken + "&openid=" + openId + "&lang=zh_CN";
//		String result = sendWxSoap(null, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, charset);
		return result;
		
	}

	/**
	 * 自定义菜单创建接口
	 * post
	 * @param accessToken
	 * @return
	 */
	public static boolean createMenu(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, "UTF-8");
		logger.info("create menu interface return code : " + result);
		JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
		if (jsonObj != null && "0".equals(jsonObj.getString("errcode"))) {
			return true;
		}
		return false;
	}

	/**
	 * 自定义菜单查询接口
	 * get
	 * @param accessToken
	 * @return
	 */
	public static void queryMenu(String accessToken) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, charset);
	}

	/**
	 * 自定义菜单删除接口
	 * 
	 * @param accessToken
	 * @return
	 */
	public static boolean deleteMenu(String accessToken) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
		String result = ParseUrlResponseData.getDataByUrl(postUrl, null, null, "UTF-8");
		
		JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
		if (jsonObj != null && "0".equals(jsonObj.getString("errcode"))) {
			return true;
		}
		return false;
	}

	/**
	 * 创建二维码ticket
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String createTicket(String accessToken) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, null, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", null, "UTF-8");
		JSONObject json = (JSONObject) JSONObject.parse(result);
		if (json != null && !json.containsKey("errcode")) {
			logger.info(URLEncoder.encode((String) json
					.get("ticket")));
			logger.info(json.get("expire_seconds"));
			logger.info(json.get("url"));
			return (String) json.get("group");
		} else {
			return null;
		}
	}

	/**
	 * 通过ticket换取二维码
	 * 
	 * @param accessToken
	 * @param ticket
	 * @return
	 */
	public static void getCodeByTicket(String accessToken, String ticket) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/showqrcode?access_token="+accessToken+"&ticket="+ticket;
		String result = sendWxSoap(null, postUrl, null, null);
	}

	/**
	 * 长链接转短链接接口
	 * 
	 * @param accessToken
	 * @return
	 */
	public static void long2short(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/shorturl";
		String result = sendWxSoap(accessToken, postUrl, json, null);
	}

	/**
	 * 长链接转短链接接口
	 * 
	 * @param accessToken
	 * @return
	 */
	public static void sendTemplate(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send";
		String result = sendWxSoap(accessToken, postUrl, json, null);
	}
	
	/**
	 * 通过分组群发消息
	 * @param accessToken
	 * @param json
	 * @return msg_id 消息id
	 */
	public static String sendMass(String accessToken, String json){
		String postUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, "UTF-8");
		return result;
	}
	
	/**
	 * 预览群发消息
	 * @param accessToken
	 * @param json
	 * @return
	 */
	public static String previewMass(String accessToken, String json){
		String postUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, "UTF-8");
		return result;
	}
	
	/**
	 * 删除群发消息(只能删除图文/视频消息)
	 * @param accessToken
	 * @param json
	 * @return
	 */
	public static boolean deleteMass(String accessToken, String json){
		String postUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, "UTF-8");
		JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
		if (jsonObj != null && "0".equals(jsonObj.getString("errcode"))) {
			return true;
		}
		return false;
	}
	
	/**
	 * 上传图文消息素材
	 * @param accessToken
	 * @param json
	 * @return
	 */
	public static String uploadNews(String accessToken, String json){
		String postUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
		String result = sendWxSoap(accessToken, postUrl, json, null);
		JSONObject jsonObj = (JSONObject) JSONObject.parse(result);
		String media_id = null;
		if (jsonObj != null && jsonObj.getString("errcode") == null) {
			media_id = jsonObj.getString("media_id");
		}
		return media_id;
	}
	
	/**
     * 文件上传到微信服务器(HTTP请求)
     * @param fileType 文件类型
     * @param filePath 文件路径
     * @return JSONObject
     * @throws Exception
     */
	public static String sendFile(String accessToken, String fileType, String filePath) throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		URL urlObj = new URL("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=" + fileType + "");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			logger.error("发送POST请求出现异常！", e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}
	
	/**
	 * 发送客服消息
	 * 
	 * @param accessToken
	 * @return
	 */
	public static String sendCustomerMsg(String accessToken, String json) {
		String postUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
//		String result = sendWxSoap(accessToken, postUrl, json, null);
		String result = ParseUrlResponseData.getDataByUrl(postUrl, "POST", json, charset);
		return result;
	}

	/** 公众发送微信接口方法 */
	public static String sendWxSoap(String accessToken, String url, String json, NameValuePair[] values) {
		if(!StringUtils.isEmpty(accessToken)) {
			url = url + "?access_token=" + accessToken;
		}
		String result = ParseUrlResponseData.getDataByUrl(url, "POST", json, "UTF-8");
		return result;
	}
}
