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
@Table(name = "wx_wx_enterprise_account")
@Where(clause="status=1")
public class WXEnterpriseAccount extends BaseInfo{
	private static final long serialVersionUID = 1L;
	
	/** uuid */
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="id")
	private String id;
	/** corpid */
	@Column(name="corpid")
	private String corpId;
	@Column(name="communityid")
	private String communityId;
	/** secret */
	@Column(name="corpsecret")
	private String corpSecret;
	/** token */
	@Column(name="token")
	private String token;
	/** encodingAESKey */
	@Column(name="encodingAESKey")
	private String encodingAESKey;
	@Column(name="applicationid")
	private Integer applicationId;
	/**企业号名称*/
	@Column(name="name")
	private String name;
	/**公众号原始ID*/
	@Column(name="wxaccount")
	private String wxaccount;
	@Column(name="remark")
	private String remark;
	
}
