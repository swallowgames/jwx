package com.wx.kernel.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtil {
	public final static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static DateFormat format2 = new SimpleDateFormat("MM/dd/yy");
	public final static DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	public final static DateFormat format4 = new SimpleDateFormat("MMddyy");
	public final static DateFormat format5 = new SimpleDateFormat("yyMMdd");
	public final static DateFormat format6 = new SimpleDateFormat("yyyy-MM-dd-HH");
	public final static DateFormat format7 = new SimpleDateFormat("HHmmssSSS");
	public final static DateFormat format8 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public final static DateFormat format9 = new SimpleDateFormat("MMMM d, yyyy",Locale.ENGLISH);
	public final static DateFormat format10 = new SimpleDateFormat("M/d/yyyy");
    public final static DateFormat format11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
	public final static DateFormat format12 = new SimpleDateFormat("d/M/yyyy");
	public final static DateFormat format13 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public final static DateFormat format14 = new SimpleDateFormat("MM-dd-yyyy");
	public final static DateFormat format15 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getIso(){
		return DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date());
	}
	public static int getWeek(String time){
		Date date=null;
		if(time==null)date=new Date();
		else date=DateUtil.rFormat(DateUtil.format3, time);
		GregorianCalendar c=new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK)-1;
	}
	/**
	 * 计算date距今有多少天.不包括周六周末
	 */
	public static int getInterval(Date date){
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int d=(int)((now.getTimeInMillis()-calendar.getTimeInMillis())/(24*60*60*1000));
		int n=0;
		for(int i=0;i<d;i++){
			calendar.add(Calendar.DATE,1);
			int week=calendar.get(Calendar.DAY_OF_WEEK);
			if(week==1 ||week==7)n++;
		}
		d=d-n+1;
		return d;
	}
	/**
	 * 计算date距今有多少天.包括周六周末
	 */
	public static int getIntervalIncludeWeekend(Date date){
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int d=(int)((now.getTimeInMillis()-calendar.getTimeInMillis())/(24*60*60*1000));
		return (int)d;
	}
	/**
	 * 计算2个日期相隔多少天
	 */
	public static int getInterval(Date date1, Date date2){
		int d=(int)((date2.getTime()-date1.getTime())/(24*60*60*1000));
		return (int)d;
	}
	
	public static long getDelay(int hour,int min){
		if(hour==-1)return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long delay= calendar.getTime().getTime()-new Date().getTime();
		if(delay<=0)delay=delay+24*60*60*1000;
		return delay;
	}
	
	public static XMLGregorianCalendar dateToXml(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		XMLGregorianCalendar sdate=null;
		try {
			sdate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			sdate.setYear(calendar.get(Calendar.YEAR));sdate.setMonth(calendar.get(Calendar.MONTH)+1);
			sdate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
	    	sdate.setHour(calendar.get(Calendar.HOUR_OF_DAY));
	    	sdate.setMinute(calendar.get(Calendar.MINUTE));
	    	sdate.setSecond(calendar.get(Calendar.SECOND));
	    	sdate.setTimezone(calendar.get(Calendar.ZONE_OFFSET) / 60000 );
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return sdate;
	}
	public static String ToPre(String today) {
		today = today.trim();
		if (today.contains(" ")) {
			return today;
		}
		return today + " 00:00:00";
	}
	public static String ToAft(String today) {
		today = today.trim();
		if (today.contains(" ")) {
			return today;
		}
		return today + " 23:59:59";
	}
	public static String ToSht(String today) {
		today = today.trim();
		if (today.contains(" ")) {
			return today.split(" ")[0];
		}
		return today;
	}
	public static String getNow(DateFormat format) {
		return format.format(Calendar.getInstance().getTime().getTime());
	}
	/***
	 * 获取zone和locale的long
	 * @param zone
	 * @param locale
	 * @param preday
	 * @return
	 */
	public static long getNow(String zone,String locale,int preday) {
		GregorianCalendar ca = new GregorianCalendar(TimeZone.getTimeZone(zone),new Locale(locale));
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)preday*24*60*60*1000);
		return ca.getTime().getTime();
	}
	/***
	 * 获取具体format的date的long
	 * @param format
	 * @param date
	 * @return
	 */
	public static long getNow(DateFormat format,String date) {
		return tsFormat(format,date).getTime();
	}
	/***
	 * 获取日期
	 * @param zone
	 * @param locale
	 * @param format
	 * @param preday
	 * @return
	 */
	public static String getLocale(String zone,String locale,DateFormat format,int preday) {
		GregorianCalendar ca = new GregorianCalendar(TimeZone.getTimeZone(zone),new Locale(locale));
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)preday*24*60*60*1000);
		return format.format(ca.getTime().getTime());
	}
	public static String getNow(String zone,String locale,String format,int preday) {
		GregorianCalendar ca = new GregorianCalendar(TimeZone.getTimeZone(zone),new Locale(locale));
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)preday*24*60*60*1000);
		int month = ca.get(Calendar.MONTH) + 1;
		int date = ca.get(Calendar.DATE);
		int year=ca.get(Calendar.YEAR);
		String dd=(date > 9 ? "" : "0")+date;
		String mm=(month > 9 ? "" : "0")+month;
		String yyyy=""+year;
		format=format.replace("yyyy", yyyy);
		format=format.replace("MM", mm);
		format=format.replace("dd", dd);
		String d=""+date;
		String m=""+month;
		String yy=""+yyyy.substring(2);
		format=format.replace("yy", yy);
		format=format.replace("M", m);
		format=format.replace("d", d);
		return format;
	}
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	public static int getYear() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.YEAR);
	}

	public static String getMonth() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int month = ca.get(Calendar.MONTH) + 1;
		return "" + ca.get(Calendar.YEAR) + (month > 9 ? month : ("0" + month));
	}
	public static int getMonthInt() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.MONTH) + 1;
	}

	public static int getDayInt() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHourInt() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinuteInt() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.MINUTE);
	}
	public static String preMonth(Date date,int m) {
		Calendar calendar=Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-m);
		return Format(format3, calendar.getTime());
	}
	public static String getDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int day=ca.get(Calendar.DATE);
		return day > 9 ? String.valueOf(day) : ("0" +day );
	}
	public static String getDay() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int month = ca.get(Calendar.MONTH) + 1;
		int day=ca.get(Calendar.DATE);
		return ca.get(Calendar.YEAR) + "-"
				+ (month > 9 ? month : ("0" + month)) + "-"
				+ (day > 9 ? day : ("0" + day));
	}

	public static String toChinaString(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		return "" + ca.get(Calendar.YEAR) + "年"
				+ (month > 9 ? month : ("0" + month)) + "月"
				+ (day > 9 ? day : ("0" + day)) + "日";
	}

	public static String aftYears(int n) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		return (year + n) + (month < 10 ? "-0" : "-") + month
				+ (day < 10 ? "-0" : "-") + day + " 00:00:00";
	}

	public static String aftDays(int n) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		int nday = day + n;
		if (nday > 30) {
			day = nday - 30;
			month = month + 1;
			if (month > 12) {
				year = year + 1;
				month = 1;
			}
		} else
			day = nday;
		return "" + year + (month < 10 ? "-0" : "-") + month
				+ (day < 10 ? "-0" : "-") + day + " 00:00:00";
	}

     public static String aftDays(DateFormat cdf, DateFormat rdf, String date, int n){
        try{
             Date da = cdf.parse(date);
             if(null ==da)return null;
             Calendar c = Calendar.getInstance();
            c.clear();
            c.setTime(da);
            c.add(Calendar.DAY_OF_MONTH, n);
            return rdf.format(c.getTime());
        }catch(Exception e){}
        return null;
    }

 	public static String preDay(int n,DateFormat format){
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)n*24*60*60*1000);
		return Format(format,ca.getTime());
 	}
	public static String preDays(int n){
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(new java.util.Date());
		ca.add(Calendar.DAY_OF_MONTH, 0-n);
		return Format(format3,ca.getTime());
	}
	public static String preDays(String time,DateFormat format,int n){
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(DateUtil.tsFormat(DateUtil.format3, time));
		ca.add(Calendar.DAY_OF_MONTH, 0-n);
		return Format(format,ca.getTime());
	}
	public static String preDay(Date date,DateFormat format,int n){
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, 0-n);
		return Format(format,ca.getTime());
	}
	
	public static String preHour(Date date,DateFormat format,int n){
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTime(date);
		ca.add(Calendar.HOUR_OF_DAY, 0-n);
		return Format(format,ca.getTime());
	}
	public static String preHours(int hour,DateFormat format){
		if(format==null)format=format3;
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)hour*60*60*1000);
		return Format(format,ca.getTime());
	}
	
	/**
	 *  以当前日期为基准往前往后跳跃几天
	 */
	public static String skipDays(int n){
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, n);
		return Format(format3, ca.getTime());
	}

	public static String aftMins(int n) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR_OF_DAY);
		int min = ca.get(Calendar.MINUTE) + n;
		int sec = ca.get(Calendar.SECOND);
		return year + (month < 10 ? "-0" : "-") + month
				+ (day < 10 ? "-0" : "-") + day + " " + (hour < 10 ? "0" : "")
				+ hour + ":" + (min < 10 ? "0" : "") + min + ":"
				+ (sec < 10 ? "0" : "") + sec;
	}
	public static String Hours(){
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;
		int date=c.get(Calendar.DATE);
		int hour=c.get(Calendar.HOUR_OF_DAY);
		return year+"-"+(month<10?"0":"")+month+"-"+(date<10?"0":"")+date+"-"+(hour<10?"0":"")+hour;
	}
	
	public final static String[][] MONTHS=new String[][]{
		{"01","Jan","January","Januar","Gennaio","Janvier","janv"},
		{"02","Feb","February","Februar","Febbraio","Février","févr"},
		{"03","Mar","March","März","Marzo","Mars"},
		{"04","Apr","April","April","Aprile","Avril","avr"},
		{"05","May","May","Mai","Maggio","Mai"},
		{"06","Jun","June","Juni","Giugno","Juin"},
		{"07","Jul","July","Juli","Luglio","Juillet","juil"},
		{"08","Aug","August","August","Agosto","Août"},
		{"09","Sep","September","September","Settembre","Septembre"},
		{"10","Oct","October","Oktober","Ottobre","Octobre"},
		{"11","Nov","November","November","Novembre","Novembre"},
		{"12","Dec","December","Dezember","Dicembre","Décembre","déc"}
		};
	
	public static String TransferMonth(String month){
		if(null == month)return null;
		for(String[] m:MONTHS){
			for(int i=1;i<m.length;i++){
				if(month.equalsIgnoreCase(m[i]))return m[0];
			}
		}
		return null;
	}

	/***
	 * 转换
	 * 
	 * @param time
	 * @return
	 */
	public static String Format(DateFormat format, Timestamp time) {
		String t = "";
		try {
			t = format.format(time);
		} catch (Exception e) {
			return t;
		}
		return t;
	}

	public static String Format(DateFormat format, Date time) {
		String t = "";
		try {
			t = format.format(time).replace("24:", "00:");
		} catch (Exception e) {
			return t;
		}
		return t;
	}

	public static String DFormat(Date time) {
		String t = "";
		try {
			t = format1.format(time).split(" ")[0];
		} catch (Exception e) {
			return t;
		}
		return t;
	}

	/***
	 * 逆转换
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static Date rFormat(DateFormat format, String time) {
		if (time == null || time.trim().equals(""))
			return null;
		if (time.indexOf(" ") == -1)
			time += " 00:00:00";
		String[] ts = StringUtils.split(time, "-");
		if (ts[1].length() == 1)
			ts[1] = "0" + ts[1];
		String[] ds = StringUtils.split(ts[2], " ");
		if (ds[0].length() == 1)
			ds[0] = "0" + ds[0];
		String[] ms = StringUtils.split(ds[1], ":");
		if (ms[0].length() == 1)
			ms[0] = "0" + ms[0];
		if (ms[1].length() == 1)
			ms[1] = "0" + ms[1];
		if (ms[2].length() == 1)
			ms[2] = "0" + ms[2];
		time = ts[0] + "-" + ts[1] + "-" + ds[0] + " " + ms[0] + ":" + ms[1]
				+ ":" + ms[2];
		Date date = null;
		try {
			date = format.parse(time.substring(0, 19));
		} catch (Exception e) {
			return date;
		}
		return date;
	}

	public static Date tsFormat(DateFormat format, String time) {
		try {
			return format.parse(time);
		} catch (ParseException e) {
		}
		return null;
	}
	public static String transferDate(String date,String format,DateFormat tformat){
		try {
			return tformat.format(new SimpleDateFormat(format).parse(date));
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 计算一年中某周的第一天和最后一天
	 * @param year
	 * @param week
	 * @param first
	 * @return
	 */
	public static String getDayOfWeek(int year, int week,boolean isFirst) {
		return getDayOfWeek( year,  week, isFirst, true);
    }
	
	/**
	 * 计算一年中某周的第一天和最后一天<br>
	 * 扩展:可以设置周的起始日期<br>
	 * @param year
	 * @param week
	 * @param isFirst true为求出入周的开始日期，false为求last日期
	 * @param m  日期是否是以星期一开始,否的话,默认为星期日为一周的开始日期<br>
	 * @return isMonday
	 */
	public static String getDayOfWeek(int year, int week,boolean isFirst,boolean isMonday) {
    	Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);//设置为 ：year-0月-7号
        Date firstDate = getFirstDayOfWeek(calFirst.getTime(),isMonday);
        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime(),isMonday);
        return isFirst?Format(format3, firstDate):aftDays(6,Format(format3, firstDate));
    }
	
    private static Date getFirstDayOfWeek(Date date,boolean monday) {
        Calendar c = new GregorianCalendar();
        if(monday){//add by weizi  是否需要以星期一作为一周的开始,否则是星期日为一周的开始
        	c.setFirstDayOfWeek(Calendar.MONDAY);
        }
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }
    /**
     * 从固定时间往后推n天
     * @param n
     * @param time
     * @return
     */
    public static String aftDays(int n,String time){
    	Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTimeInMillis(rFormat(DateUtil.format3, time).getTime()+(long)n*24*60*60*1000);
		return Format(format3,ca.getTime());
    }
    /**
     * 返回当前时间min分钟前的时间
     * @param min
     * @return
     */
    public static String preMins(int min){
    	Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.setTimeInMillis(new java.util.Date().getTime()-(long)min*60*1000);
		return Format(format8,ca.getTime());
    }
    
    
    /**
     * 将字符串转换为Date
     * @param format
     * @param s
     * @return
     */
    public static Date getDateFromStr(DateFormat format, String s){
    	if(s==null || s.trim().equals("")){
    		return null;
    	}
    	try {
    		if(format == null){
        		if(s.length() == 10){
        			format = format3;
        		}else if(s.length()==16){
        			format = format13;
        		}else{
        			format = format1;
        		}
        	}
			return format.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
}
