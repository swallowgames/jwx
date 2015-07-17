package com.wx.kernel.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.wx.kernel.util.page.PageObject;

@Component
public class BaseDao<T> extends HibernateDaoSupport {
	
	protected String primaryKeyName;
	
	public Log log = LogFactory.getLog(this.getClass());
	@Resource
	protected JdbcTemplate jdbcTemplate;

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	

	public void add(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	public T get(Class<T> clazz, Serializable id) {
		logger.info("className:" + clazz);
		
		return this.getHibernateTemplate().get(clazz, id);
	}

	public void saveOrUpdate(Object obj) {
		this.getHibernateTemplate().merge(obj);
	}

	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @author hwy
	 */
	public void delete(Class<T> clazz, Serializable id) {
		T t =get(clazz, id);
		if(t != null)this.getHibernateTemplate().delete(t);
	}

	/**
	 * 批量删除
	 * 
	 * @param id
	 * @author hwy
	 */
	public void deleteBatch(Class<T> clazz, Serializable[] idlist) {
		if (idlist == null || idlist.length <= 0) {
			return;
		}
		StringBuffer sb = new StringBuffer(1000);
		// sb.append("delete from ");
		sb.append("update ");
		sb.append(getClassWithoutPackageName(clazz));
		sb.append(" set status=0 ");
		sb.append(" where " + this.getPrimaryKeyName() + " in (");
		for (int i = 0; i < idlist.length; i++) {
			sb.append("?,");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		Query query = currentSession().createQuery(sb.toString());
		for (int i = 0; i < idlist.length; i++) {
			query.setParameter(i, idlist[i]);
		}
		query.executeUpdate();
	}

	/**
	 * 确定主键
	 * 
	 * @return
	 */
	public String getPrimaryKeyName() {
		if (StringUtils.isEmpty(this.primaryKeyName)) {
			this.primaryKeyName = "id";
		}
		return this.primaryKeyName;
	}

	/**
	 * 取总条数
	 * 
	 * @param hql
	 * @param paramMap
	 * @return
	 */
	private Integer totalCount(String hql, Map<String, Object> paramMap) {
		Query query = currentSession().createQuery(hql);
		setQueryParam(query, paramMap);
		Object obj = query.list().get(0);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	/**
	 * 分页取数
	 * 
	 * @param hql
	 * @param paramMap
	 * @param paramPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageObject<T> pagedQuery(String hqlquery, String hqlcount,
			Map<String, Object> paramMap, PageObject<T> paramPage) {
		Integer localLong = totalCount(hqlcount, paramMap);
		if (localLong != null) {
			paramPage.setTotalCount(localLong);
		}
		Query query = currentSession().createQuery(hqlquery);
		query.setFirstResult(paramPage.getFirstResult());
		query.setMaxResults(paramPage.getPageSize());
		setQueryParam(query, paramMap);
		paramPage.setPageData(query.list());
		return paramPage;
	}

	/**
	 * 条件
	 * 
	 * @param query
	 * @param paramMap
	 */
	private void setQueryParam(Query query, Map<String, Object> paramMap) {
		if (paramMap != null) {
			Set<Entry<String, Object>> set = paramMap.entrySet();
			for (Iterator<Entry<String, Object>> it = set.iterator(); it
					.hasNext();) {
				Entry<String, Object> enty = it.next();
				if (query.getQueryString().contains(":" + enty.getKey())) {
					if (enty.getKey().endsWith("Id")) {
						query.setParameter(enty.getKey(), Integer.parseInt(enty
								.getValue().toString()));
					} else if (enty.getKey().endsWith("status")) {
						query.setParameter(enty.getKey(), Boolean
								.parseBoolean(enty.getValue().toString()));
					} else if (enty.getKey().endsWith("Like")) {
						query.setParameter(enty.getKey(), "%"
								+ enty.getValue().toString() + "%");
					} else {
						query.setParameter(enty.getKey(), enty.getValue()
								.toString());
					}

				}
			}
		}
	}

	private String getClassWithoutPackageName(Class paramClass) {
		String str = paramClass.getName();
		int i = str.lastIndexOf('.');
		return i >= 0 ? str.substring(i + 1) : str;
	}

	public void bathSaveOrUpdate(List<Object> list) {
		Session session = super.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int count = 0;
		try {
			for (Object obj : list) {
				session.merge(obj);
				if (++count % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

}
