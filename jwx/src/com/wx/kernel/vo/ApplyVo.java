package com.wx.kernel.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApplyVo implements Serializable {

	private String openid;
	private String wxaccount;
	private String yetaiid;
	private String orgid;
	private String building;
	private String unit;
	private String room;
	private String username;
	private String mobile;
}
