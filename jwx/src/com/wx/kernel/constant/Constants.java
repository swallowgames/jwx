package com.wx.kernel.constant;


public final class Constants {

	/**万达介绍*/
	public final static String CONTENT_TYPE_WDINTRO = "wdIntro";
	/**小区介绍*/
	public final static String CONTENT_TYPE_XQINTRO = "xqIntro";
	/**物业介绍*/
	public final static String CONTENT_TYPE_WYINTRO = "wyIntro";
	/**小区公告*/
	public final static String CONTENT_TYPE_XQNOTICE = "xqNotice";
	/**办事指南*/
	public final static String CONTENT_TYPE_BSGUIDE = "bsGuide";
	
	public final static String COOKIE_COMMUNITY_ID = "cookieCommunityId";
	
	public final static String COOKIE_OPENID_ID = "cookieOpenId";
	/**公众号accessToken*/
	public final static String PUBLIC_ACCESS_TOKEN = "publicAccessToken";
	/**企业号accessToken*/
	public final static String ENTERPRISE_ACCESS_TOKEN = "enterpriseAccessToken";
	/**业主会员*/
	public final static String WXMEMBER_RESIDENT = "resident";
	/**关注会员*/
	public final static String WXMEMBER_ATTENTION = "attention";
	
	
	//前台接口类型 start
	/**会员同步类型*/
	public final static String INTERFACE_TYPE_MEMBER = "wxMember";
	/**自定义菜单类型*/
	public final static String INTERFACE_TYPE_MENU = "wxMenu";
	/**查询分组类型*/
	public final static String INTERFACE_TYPE_QUERY_GROUP = "wxQueryGroup";
	/**上传缩略图类型*/
	public final static String INTERFACE_TYPE_SEND_FILE = "wxSendFile";
	/**上传图文类型*/
	public final static String INTERFACE_TYPE_UPLOAD_NEWS = "wxUploadNews";
	/**群发消息类型*/
	public final static String INTERFACE_TYPE_MASS = "wxMass";
	/**预览群发消息类型*/
	public final static String INTERFACE_TYPE_PREVIEW_MASS = "wxPreviewMass";
	/**发送48小时客服消息类型*/
	public final static String INTERFACE_TYPE_CUSTOMER_MSG = "wxCustomerMsg";
	/**增加企业号用户类型*/
	public final static String INTERFACE_TYPE_ENTERPRISE_CREATE_USER = "wxEnterpriseCreateUser";
	/**更新企业号用户类型*/
	public final static String INTERFACE_TYPE_ENTERPRISEM_UPDATE_USER = "wxEnterpriseUpdateUser";
	/**批量删除企业号用户类型*/
	public final static String INTERFACE_TYPE_ENTERPRISE_BATCH_DELETE_USER = "wxEnterpriseBatchDeleteUser";
	/**增加企业号部门类型*/
	public final static String INTERFACE_TYPE_ENTERPRISE_CREATE_DEPT = "wxEnterpriseCreateDept";
	/**更新企业号部门类型*/
	public final static String INTERFACE_TYPE_ENTERPRISEM_UPDATE_DEPT = "wxEnterpriseUpdateDept";
	/**更新企业号AccesssToken*/
	public final static String INTERFACE_TYPE_ENTERPRISEM_UPDATE_TOKEN = "wxEnterprisemUpdateToken";
	//前台接口类型 end
	//前段接口类型新增加部分，用户分组管理接口
	/**创建用户分组*/
	public final static String INTERFACE_TYPE_USERGROUP_CREATE = "wxUserGroupCreate";
	/**获取所有的分组信息*/
	public final static String INTERFACE_TYPE_USERGROUP_GETALL = "wxUserGroupGetAll";
	/**查询用户所在的分组*/
	public final static String INTERFACE_TYPE_USERGROUP_GETUSERINFO = "wxUserGroupGetUserInfo";
	/**修改用户分组名*/
	public final static String INTERFACE_TYPE_USERGROUP_MODIFYNAME = "wxUserGroupModifyName";
	/**移动用户到某一个分组*/
	public final static String INTERFACE_TYPE_USERGROUP_MOVEUSER = "wxUserGroupMoveUser";
	/**批量移动用户*/
	public final static String INTERFACE_TYPE_USERGROUP_QUATITYMOVE = "wxUserGroupQuatityMove";
	/**删除用户分组*/
	public final static String INTERFACE_TYPE_USERGROUP_DELETE = "wxUserGroupDelete";
}
