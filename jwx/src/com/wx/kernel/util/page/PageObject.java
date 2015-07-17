package com.wx.kernel.util.page;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页对象
 * @author yuanzh
 *
 */
public class PageObject<T> implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	
	public static  Integer DEFAULT_PAGE_SIZE = 10;
	protected Integer pageIndex = 1;
	protected Integer pageSize;
	protected Integer totalCount = 0;
	protected Collection<T> pageData;
	
	/**
	 * 默认构造方法
	 *
	 */
	public PageObject(Integer pageIndex){
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.pageIndex = pageIndex;	
	}

	/**
	 * 构造方法
	 *
	 */
	public PageObject(Integer pageIndex, Integer pageSize){
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	
	/**
	 * 取得页数
	 * @return
	 */
	public Integer getPageIndex(){	
		if(pageIndex > this.getTotalPage()){
			return this.getTotalPage();
		}else{
			return pageIndex;
		}
		
	}
	/**
	 * 取得每一页的条目数
	 * @return
	 */
	public Integer getPageSize(){
		return pageSize;
	}
	/**
	 * 取得总的条目数
	 * @return
	 */
	public Integer getTotalCount(){
		return totalCount;
	}
	/**
	 * 设置总条目数
	 * @param totalCount
	 */
	public void setTotalCount(Integer totalCount){
		this.totalCount = totalCount;
	}
	/**
	 * 取得总页数
	 * @return
	 */
	public Integer getTotalPage(){
		if(this.totalCount == 0 || this.totalCount % this.pageSize != 0){
			return  (totalCount / pageSize) + 1;
		}else{
			return (totalCount / pageSize);
		}
	}
	/**
	 * 取得分页数据
	 * @return
	 */
	public Collection<T> getPageData(){
		return pageData;
	}
	/**
	 * 设置分页数据
	 * @param pageData
	 */
	public void setPageData(Collection<T> pageData){
		this.pageData = pageData;
	}
	/**
	 * 取得分页的第一条记录的位置
	 * @return
	 */
	public Integer getFirstResult(){
		return (this.getPageIndex() - 1) * this.pageSize;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("index:").append(this.pageIndex);
		sb.append("       totalCount:").append(this.getTotalCount());
		sb.append("    pageData:");
		for(Object object : this.pageData){
			sb.append("[").append(object).append("],");
		}
		return sb.toString();
	}
	public PageObject<T> clone(){
		try {
			return (PageObject<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
