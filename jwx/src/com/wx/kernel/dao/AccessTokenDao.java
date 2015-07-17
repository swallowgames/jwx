package com.wx.kernel.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.wx.kernel.constant.Constants;
import com.wx.kernel.util.wx.WXEnterpriseUtil;
import com.wx.kernel.util.wx.WXUtil;

@Component
public class AccessTokenDao {
	
	public Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @param communityId
	 * @param constantKey publicAccessToken:公众号  enterpriseAccessToken:企业号
	 * @return
	 */
	public String getAccessToken(String communityId, String constantKey, String appId, String appSecret) {
		String sql = "select id, constantvalue  from wx_system_constant where communityid=? and constantkey=? ";
		String accessToken = null;
		Integer id = null;
		try {
			Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{communityId, constantKey});
			accessToken = (String) map.get("constantvalue");
			id = (Integer) map.get("id");
			
		} catch (EmptyResultDataAccessException e) {
		}
		log.info("accessToken = " + accessToken + " || id = " + id);
		if(id == null) {
			//数据库中查不到AccessToken,则从微信上获取AccessToken并更新到数据库表
			String insertSql = "insert into wx_system_constant(communityId, constantKey, constantValue, name, type, updateTime) values(?,?,?,'','',CURRENT_TIMESTAMP)";
			if(Constants.PUBLIC_ACCESS_TOKEN.equals(constantKey)) {
				accessToken = WXUtil.getAccessToken(appId, appSecret);
			} else if(Constants.ENTERPRISE_ACCESS_TOKEN.equals(constantKey)) {
				accessToken = WXEnterpriseUtil.getAccessToken(appId, appSecret);
			}
			
			jdbcTemplate.update(insertSql, new Object[]{communityId, constantKey, accessToken});
		}
		return accessToken;
	}
	
	/**更新accessToken*/
	public String updateAccessToken(String communityId, String constantKey, String appId, String appSecret) {
		String sql = "select id, constantvalue from wx_system_constant where communityid=? and constantkey=? ";
		Integer id = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = jdbcTemplate.queryForMap(sql, new Object[]{communityId, constantKey});
			id = (Integer) map.get("id");
		} catch (EmptyResultDataAccessException e) {
		}
		System.out.println("id = " + id);
		String accessToken = "";
		if(Constants.ENTERPRISE_ACCESS_TOKEN.equals(constantKey)) {
			accessToken = WXEnterpriseUtil.getAccessToken(appId, appSecret);
		} else {
			accessToken = WXUtil.getAccessToken(appId, appSecret);
		}
		
		if(accessToken != null){
			if(id == null || id == 0) {
				String insertSql = "insert into wx_system_constant(communityId, constantKey, constantValue, name, type, updateTime) values(?,?,?,'','',CURRENT_TIMESTAMP)";
				jdbcTemplate.update(insertSql, new Object[]{communityId, constantKey, accessToken});
			} else {
				String updateSql = "update wx_system_constant set constantValue=? ,updateTime=CURRENT_TIMESTAMP where id=?";
				jdbcTemplate.update(updateSql, new Object[]{accessToken, id});
			}
		}else{
			//获取不到最新的accessToken，则用原来数据库中的accessToken
			accessToken = (String) map.get("constantvalue");
		}
		return accessToken;
	}
	
}
