package com.wx.kernel.util;

/**
 * 这个类是用来表示经纬度的。<br/>
 * 
 * 类属性说明<br/>
 * lngDouble与latDouble是按度数表示的经度和纬度，精度范围是小数点后五位<br/>
 * 其他属性都是经度按照度、分、秒的方式表示的，均为整数<br/>
 * <br/>
 * 东、西经，南、北纬的表示方式<br/>
 * 东经为正值，西经纬负值<br/>
 * 北纬为正值，南纬为负值<br/>
 * <br/>
 * 地球半径取6370996.81米<br/>
 * */
public class EarthPoint {
	// 地球半径,单位m
	public final static double EARTH_RADIUS = 6370996.81;

	// 经度，单位度
	private double lngDouble;

	// 经度，度数
	private long lngDegree;

	// 经度，分数
	private long lngPoint;

	// 经度，秒数
	private long lngSencond;

	// 纬度，单位度
	private double latDouble;

	// 纬度，度数
	private long latDegree;

	// 纬度，分数
	private long latPoint;

	// 纬度，秒数
	private long latSencond;

	public double getLngDouble() {
		return lngDouble;
	}

	public long getLngDegree() {
		return lngDegree;
	}

	public long getLngPoint() {
		return lngPoint;
	}

	public long getLngSencond() {
		return lngSencond;
	}

	public double getLatDouble() {
		return latDouble;
	}

	public long getLatDegree() {
		return latDegree;
	}

	public long getLatPoint() {
		return latPoint;
	}

	public long getLatSencond() {
		return latSencond;
	}

	@Override
	public String toString() {
		return "EarthPoint [lngDouble=" + lngDouble + ", lngDegree="
				+ lngDegree + ", lngPoint=" + lngPoint + ", lngSencond="
				+ lngSencond + ", latDouble=" + latDouble + ", latDegree="
				+ latDegree + ", latPoint=" + latPoint + ", latSencond="
				+ latSencond + "]";
	}

	/**
	 * 直接根据经纬度创建地球点对象
	 * 
	 * @param lngIn
	 *            经度，单位度，东经为正值，西经为负值。
	 * @param latIn
	 *            纬度，单位度，北纬为正值，南纬为负值。
	 */
	public EarthPoint(double lngIn, double latIn) {
		this.lngDouble = Math.round(lngIn * 100000) / 100000.0;
		this.latDouble = Math.round(latIn * 100000) / 100000.0;

		// 换算经度的度分秒
		this.lngDegree = Math.round(lngDouble);
		this.lngPoint = Math.round((lngDouble - this.lngDegree) * 60);
		this.lngSencond = Math
				.round(((lngDouble - this.lngDegree) * 60 - lngPoint) * 60);

		// 换算纬度的度分秒
		this.latDegree = Math.round(latDouble);
		this.latPoint = Math.round((latDouble - this.latDegree) * 60);
		this.latSencond = Math
				.round(((latDouble - this.latDegree) * 60 - latPoint) * 60);
	}

	/**
	 * 直接根据经纬度创建地球点对象
	 * 
	 * @param lngDeg
	 *            经度，单位度，东经为正值，西经为负值。
	 * @param lngPoi
	 *            经度，单位度，东经为正值，西经为负值。
	 * @param lngSec
	 *            经度，单位度，东经为正值，西经为负值。
	 * @param latDeg
	 *            纬度，单位度，北纬为正值，南纬为负值。
	 * @param latPoi
	 *            纬度，单位度，北纬为正值，南纬为负值。
	 * @param latSec
	 *            纬度，单位度，北纬为正值，南纬为负值。
	 */
	public EarthPoint(long lngDeg, long lngPoi, long lngSec, long latDeg,
			long latPoi, long latSec) {
		// 输入经度的度分秒
		this.lngDegree = lngDeg;
		this.lngPoint = lngPoi;
		this.lngSencond = lngSec;

		// 输入纬度的度分秒
		this.latDegree = latDeg;
		this.latPoint = latPoi;
		this.latSencond = latSec;

		// 换算经度
		this.lngDouble = this.lngSencond / 3600.0 + this.lngPoint / 60.0
				+ this.lngDegree / 1.0;
		// 换算纬度
		this.latDouble = this.latSencond / 3600.0 + this.latPoint / 60.0
				+ this.latDegree / 1.0;
	}

	// 计算两个经纬度之间距离的辅助函数
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double countDistance(EarthPoint point1, EarthPoint point2) {
		double radLat1 = rad(point1.getLatDouble());
		double radLat2 = rad(point2.getLatDouble());
		double a = radLat1 - radLat2;
		double b = rad(point1.getLngDouble()) - rad(point2.getLngDouble());

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EarthPoint.EARTH_RADIUS;
		s = Math.round(s * 100000) / 100000.0;
		return s;
	}

	public static void main(String argc[]) {
		EarthPoint a = new EarthPoint(90.12323, 23.435);
		EarthPoint b = new EarthPoint(-80.12145, -35.23040);
		double dis = EarthPoint.countDistance(a, b);
		System.out.println("dis:" + dis);
		System.out.println("a:" + a.toString());
		System.out.println("b:" + b.toString());
	}

}
