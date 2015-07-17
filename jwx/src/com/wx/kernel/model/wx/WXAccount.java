package com.wx.kernel.model.wx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.wx.kernel.model.BaseInfo;

@Data
@Entity
@Table(name = "wx_wx_account")
@Where(clause="status=1")
public class WXAccount extends BaseInfo{
	private static final long serialVersionUID = 1L;
	
	/** uuid */
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="id")
	private String id;
	/** appId */
	@Column(name="appid")
	private String appId;
	@Column(name="communityid")
	private String communityId;
	/** appSecret */
	@Column(name="appSecret")
	private String appSecret;
	/** token */
	@Column(name="token")
	private String token;
	/** 名称 */
	@Column(name="name")
	private String name;
	/**公众号原始ID*/
	@Column(name="wxaccount")
	private String wxaccount;
	/**服务器地址*/
	@Column(name="serverurl")
	private String serverurl;
	/** identified */
	@Column(name="identified")
	private String identified;
	/** 微信类型 sub:订阅号 service:服务号*/
	@Column(name="wxtype")
	private String type;
	/** 是否认证 */
	@Column(name="isAuth")
	private Integer isAuth;
	@Column(name="remark")
	private String remark;
	
}
