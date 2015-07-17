package com.wx.kernel.util.json;

/**
 * 
 * 错误日志提示
 *
 * @author
 */
public class FieldError {

	private String field;

	private String errorMsg;

	public FieldError() {
	}

	public FieldError(String field, String errorMsg) {
		this.field = field;
		this.errorMsg = errorMsg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
