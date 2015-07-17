package com.wx.kernel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@MappedSuperclass
@Where(clause="status=1")
public class BaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 状态 1:正常 */
	@Column(name="status", insertable = false)
	private Boolean status;
	@Column(name="ctime", insertable = false)
	private Date ctime;

}
