package com.wx.kernel.cron;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DemoCron {

	public Log logger = LogFactory.getLog(this.getClass());
	
	//处理逻辑的方法
	public void run() {
		long l = System.currentTimeMillis();
		System.out.println(l);
	}

}
