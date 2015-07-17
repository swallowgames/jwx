package com.wx.kernel.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.wx.kernel.model.SystemConstant;


@Component
public class SystemConstantDao extends BaseDao {
	/**
	 * 系统常量键查询系统对象
	 * 
	 * @param openId
	 * @param nominateId
	 * @return
	 */
	public SystemConstant queryConstant(String key) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from SystemConstant where constantKey = ?";
		Query query = session.createQuery(hql);
		query.setString(0, key);
		List<SystemConstant> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 获取所有系统常量
	 * 
	 * @param openId
	 * @param nominateId
	 * @return
	 */
	public HashMap<String, String> queryAllConstant() {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from SystemConstant";
		Query query = session.createQuery(hql);
		List<SystemConstant> list = query.list();
		HashMap<String, String> result = new HashMap<String, String>();
		if (list == null || list.size() == 0) {
			return null;
		}

		for (int i = 0; i < list.size(); i++) {
			result.put(list.get(i).getConstantKey(), list.get(i)
					.getConstantValue());
		}
		return result;
	}
}
