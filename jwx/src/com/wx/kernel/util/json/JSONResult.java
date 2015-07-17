package com.wx.kernel.util.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 用于AJAX JSON返回
 *
 * @author
 */
public class JSONResult {
	/**
	 * JSON输入编码，默认是utf-8
	 */
	private String characterEncoding = "UTF-8";
	/**
	 * Action执行的结果
	 */
	private boolean result = true;
	/**
	 * Action执行的结果代码
	 */
	private String resultCode;
	/**
	 * 提示信息
	 */
	private String message;
	/**
	 * 需要返回页面的对象，将被封装成json对象
	 */
	private final Map<String, Object> jsonObjectMap = new HashMap<String, Object>();
	/**
	 * 字段提示信息的错误
	 */
	private final Set<FieldError> fieldError = new HashSet<FieldError>();

	/**
	 * @description: 需要返回页面的对象压进jsonObjectMap
	 * @param:
	 * @return:
	 * @throws:
	 */
	public void put(String key, Object value) {
		jsonObjectMap.put(key, value);
	}

	public void addFieldError(String field, String errorMsg) {
		fieldError.add(new FieldError(field, errorMsg));
	}

	@Override
	public String toString() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (fieldError.isEmpty()) {
			map.put("result", result);
			map.put("resultCode", resultCode);
			map.putAll(jsonObjectMap);
			map.put("message", message);
		} else {
			map.put("result", false);
			map.put("type", "fieldError");
			map.put("errors", fieldError);
			map.put("message", message);
		}
		return JsonUtils.map2json(map);
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getJsonObjectMap() {
		return jsonObjectMap;
	}
}
