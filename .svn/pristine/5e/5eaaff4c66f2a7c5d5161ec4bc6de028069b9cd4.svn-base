package org.hy.foundation.utils.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 日期常用类的整理，包括几大常用功能:<br>
 * <ol>
 * <li>java.util.Date类转为字符串</li>
 * <li>字符串转为java.util.Date类</li>
 * <li>快捷获取当前指定格式日期（字符串与java.util.Date类两种格式）</li>
 * <li>java.util.Date的日期类型的运算操作</li>
 * <li>快捷获取特定日期（本月第一天，本月最后一天）</li>
 * <li>其他</li>
 * <li></li>
 * </ol>
 * 
 * @date 2011-4-19
 * @author MipatchTeam#hqj,MipatchTeam#guob,MipatchTeam#xujl,MipatchTeam#chenc
 * 
 */
public class DateUtil {

	public final static int YEAR = Calendar.YEAR; // 年
	public final static int MONTH = Calendar.MONTH;// 月
	public final static int DATE = Calendar.DATE;// 日
	public final static int HOUR = Calendar.HOUR_OF_DAY;// 小时
	public final static int MINUTE = Calendar.MINUTE;// 分
	public final static int SECOND = Calendar.SECOND;// 秒
	public final static int MILLISECOND = Calendar.MILLISECOND; // 毫秒

	// public final static int WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;
	// public final static int WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;

	// --------------------- 1. --------------------------------
	/**
	 * 按照 yyyy-MM-dd 格式化日期（java.util.Date -> 字符串）
	 * 
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDate(java.util.Date date) {
		if (date == null)
			date = now();
		return (formatDate(date, "yyyy-MM-dd"));
	}

	/**
	 * 按照 yyyy-MM-dd HH:mm:ss 格式化日期（java.util.Date -> 字符串）
	 * 
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDateTime(java.util.Date date) {
		if (date == null)
			date = now();
		return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 按照 HH:mm:ss 格式化日期（java.util.Date -> 字符串）
	 * 
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatTime(java.util.Date date) {
		if (date == null)
			date = now();
		return (formatDate(date, "HH:mm:ss"));
	}

	/**
	 * Date类型转时间核心方法，默认转换格式为今天的日期格式（java.util.Date -> 字符串）
	 * 
	 * @param date
	 *            日期，若null默认今天
	 * @param pattern
	 *            格式化字符串
	 * @return 时间格式字符串
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDate(java.util.Date date, String pattern) {
		if (date == null)
			date = now();

		if (pattern == null)
			pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return (sdf.format(date));
	}

	// -----------------------------------------------------

	// --------------------- 2. --------------------------------
	/**
	 * 按照 yyyy-MM-dd 格式化日期（字符串 -> java.util.Date）
	 * 
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date parseDate(String date) {
		if (date == null)
			return now();
		return parseDate(date, "yyyy-MM-dd");
	}

	/**
	 * 按照 yyyy-MM-dd HH:mm:ss 格式化日期（字符串 -> java.util.Date）
	 * 
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date parseDateTime(String datetime) {
		if (datetime == null)
			return now();
		return parseDate(datetime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 截断给定日期，只留下 yyyy-MM-dd （去除时间部分）
	 * 
	 * @param datetime
	 *            给定日期
	 * @return
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date parseDate(java.util.Date datetime) {
		if (datetime == null)
			return now();
		return parseDate(datetime, "yyyy-MM-dd");
	}

	/**
	 * 截断给定日期，只留下 yyyy-MM-dd HH:mm:ss （去除毫秒部分）
	 * 
	 * @param datetime
	 *            给定日期
	 * @return
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date parseDateTime(java.util.Date datetime) {
		if (datetime == null)
			return now();
		return parseDate(datetime, "yyyy-MM-dd  HH:mm:ss");
	}

	/**
	 * 按照给定格式修正日期中的部分
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date parseDate(java.util.Date datetime,
			String pattern) {
		if (datetime == null)
			return now();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(formatter.format(datetime));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Date类型转时间核心方法，默认转换格式为今天的日期格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date parseDate(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		if ((date == null) || (date.equals(""))) {
			return now();
		} else {
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	// -----------------------------------------------------

	// --------------------- 3. --------------------------------
	/**
	 * 按照 yyyy-MM-dd 格式化<b> 当前 </b>日期
	 * 
	 * @return 字符串
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDate() {
		return (formatDate(now(), "yyyy-MM-dd"));
	}

	/**
	 * 按照 yyyy-MM-dd HH:mm:ss 格式化<b> 当前 </b>日期（字符串 -> java.util.Date）
	 * 
	 * @return 字符串
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDateTime() {
		return (formatDate(now(), "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 按照 HH:mm:ss 格式化<b> 当前 </b>日期（字符串 -> java.util.Date）
	 * 
	 * @return 字符串
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatTime() {
		return (formatDate(now(), "HH:mm:ss"));
	}

	/**
	 * 按照指定 <i>patten</i> 格式化<b> 当前 </b>日期（字符串 -> java.util.Date）
	 * 
	 * @return 字符串
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static String formatDate(String patten) {
		return (formatDate(now(), patten));
	}

	/**
	 * 获取当前 java.util.Date 格式完整日期
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date now() {
		return (new java.util.Date());
	}

	/**
	 * 获取当前 java.util.Date <i>除去时间部分<i> 日期
	 * 
	 * @return java.util.Date
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date nowDate() {
		return parseDate(formatDate());
	}

	/**
	 * 获取当前 java.util.Date <i>除去毫秒部分<i> 日期时间
	 * 
	 * @return java.util.Date
	 * @throws Exception
	 *             有可能出现的转换异常
	 */
	public static java.util.Date nowDateTime() {
		return parseDateTime(formatDateTime());
	}

	// -----------------------------------------------------

	// --------------------- 4. --------------------------------
	/**
	 * 计算单个日期的加法运算
	 * 
	 * @param field
	 *            如
	 *            <ul>
	 *            <li>DateUtil.SECOND</li>
	 *            <li>DateUtil.MINUTE</li>
	 *            <li>DateUtil.HOUR</li>
	 *            <li>DateUtil.DATE</li>
	 *            <li>...</li>
	 *            </ul>
	 * @param date
	 *            指定日期 默认今日
	 * @param amount
	 *            移动差值
	 * @return
	 */
	public static java.util.Date add(java.util.Date date, int field, int amount) {
		if (date == null)
			date = now();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	public static java.util.Date addDay(java.util.Date date, int amount) {
		return add(date, DateUtil.DATE, amount);
	}

	public static java.util.Date addMonth(java.util.Date date, int amount) {
		return add(date, DateUtil.MONTH, amount);
	}

	public static java.util.Date addHour(java.util.Date date, int amount) {
		return add(date, DateUtil.HOUR, amount);
	}

	public static java.util.Date preMonth(java.util.Date date) {
		return add(date, DateUtil.MONTH, -1);
	}

	public static java.util.Date nextMonth(java.util.Date date) {
		return add(date, DateUtil.MONTH, 1);
	}

	public static java.util.Date preDay(java.util.Date date) {
		return add(date, DateUtil.DATE, -1);
	}

	public static java.util.Date nextDay(java.util.Date date) {
		return add(date, DateUtil.DATE, 1);
	}

	/**
	 * 计算单个日期的加法运算（yyyy-MM-dd）（字符串参数）
	 * 
	 * @param dateStr
	 * @param field
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addDate(String dateStr, int field, int amount) {
		java.util.Date date = parseDate(dateStr);
		if (date == null)
			date = now();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * 计算单个日期的加法运算（yyyy-MM-dd）（java.util.Date 参数）
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addDate(java.util.Date date, int field,
			int amount) {
		return addDate(formatDate(date), field, amount);
	}

	/**
	 * 计算单个日期时间的加法运算（yyyy-MM-dd HH:mm:ss）（字符串参数）
	 * 
	 * @param dateStr
	 * @param field
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addDateTime(String dateStr, int field,
			int amount) {
		java.util.Date date = parseDateTime(dateStr);

		if (date == null)
			date = now();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * 计算单个日期时间的加法运算（yyyy-MM-dd HH:mm:ss）（java.util.Date 参数）
	 * 
	 * @param dateStr
	 * @param field
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static java.util.Date addDateTime(java.util.Date date, int field,
			int amount) {
		return addDateTime(formatDateTime(date), field, amount);
	}

	/**
	 * 计算两个日期之间的差值
	 * 
	 * @param i
	 *            固定下面几项
	 *            <ul>
	 *            <li>DateUtil.SECOND</li>
	 *            <li>DateUtil.MINUTE</li>
	 *            <li>DateUtil.HOUR</li>
	 *            <li>DateUtil.DATE</li>
	 *            </ul>
	 * @param k
	 *            日期1
	 * @param d
	 *            日期2
	 * @return
	 */
	public static int diffDate(int i, java.util.Date k, java.util.Date d) {
		int diffnum = 0;
		int needdiff = 0;
		switch (i) {
		case DateUtil.SECOND: {
			needdiff = 1000;
			break;
		}
		case DateUtil.MINUTE: {
			needdiff = 60 * 1000;
			break;
		}
		case DateUtil.HOUR: {
			needdiff = 60 * 60 * 1000;
			break;
		}
		case DateUtil.DATE: {
			needdiff = 24 * 60 * 60 * 1000;
			break;
		}
		}
		if (needdiff != 0) {
			diffnum = (int) (d.getTime() / needdiff)
					- (int) (k.getTime() / needdiff);
			;
		}

		return diffnum;
	}

	// -----------------------------------------------------

	// --------------------- 5. --------------------------------

	/**
	 * 返回当天凌晨的时刻(0点0分0秒)
	 * 
	 * @param date
	 *            输入日期
	 * @return xx
	 * @throws Exception
	 */
	public static java.util.Date beginningOfDay(java.util.Date date) {
		if (date == null)
			date = now();
		return parseDate(date);
	}

	/**
	 * 返回当天最后的时刻(23点59分59秒)
	 * 
	 * @param date
	 *            输入日期
	 * @return xx
	 * @throws Exception
	 */
	public static java.util.Date endOfDay(java.util.Date date) {
		if (date == null)
			date = now();
		return addDate(nextDay(parseDate(date)), DateUtil.SECOND, -1);
	}

	/**
	 * 本月最后一天
	 */
	public static java.util.Date getLastDateByMonth() {
		return getLastDateByMonth(new java.util.Date());
	}

	/**
	 * 指定日期所在月最后一天
	 */
	public static java.util.Date getLastDateByMonth(java.util.Date date) {
		if (date == null)
			date = now();
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
		now.set(Calendar.DATE, 1);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	/**
	 * 本月第一天
	 */
	public static java.util.Date getFirstDateByMonth() {
		return getFirstDateByMonth(new java.util.Date());
	}

	/**
	 * 指定日期所在月第一天
	 */
	public static java.util.Date getFirstDateByMonth(java.util.Date d) {
		if (d == null)
			d = now();
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, 1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	/**
	 * 获取制定日期内的周内的第<b> K </b>天
	 * 
	 * @param date
	 * @param k
	 * @return
	 */
	public static java.util.Date getWeekDay(java.util.Date date, int k) {
		if (date == null)
			date = now();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, k);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	// -----------------------------------------------------

	// --------------------- 6. --------------------------------
	/**
	 * java.sql.Date转换到java.util.Date
	 * 
	 * @param paraDate
	 * @return
	 */
	public static java.util.Date getUtilDateFromSql(java.sql.Date paraDate) {
		return new java.util.Date(paraDate.getTime());
	}

	/**
	 * util的Date类型保存进数据库时需要的转换，若通过getTime方法会丢失时分秒部分
	 * 
	 * @param paraDate
	 * @return
	 */
	public static java.sql.Timestamp getSqlDateFromUtil(java.util.Date paraDate) {
		if (paraDate == null)
			return null;
		String dateFormat = "yyyy-MM-dd HH:mm:ss";// 注意使用HH，24小时制
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return Timestamp.valueOf(sdf.format(paraDate));
	}

	/**
	 * 猜测传入对象的类型（yyyy-MM-dd） 包含三种：String\java.util.Date\Timestamp
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Object o) {
		if (o == null)
			return "";
		if (o.getClass() == String.class)
			return formatDate((String) o);
		else if (o.getClass() == java.util.Date.class)
			return formatDate((java.util.Date) o);
		else if (o.getClass() == Timestamp.class) {
			return formatDate(new java.util.Date(((Timestamp) o).getTime()));
		} else
			return o.toString();
	}

	/**
	 * 猜测传入对象的类型（yyyy-MM-dd HH:mm:ss） 包含三种：String\java.util.Date\Timestamp
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String formatDateTime(Object o) {
		if (o.getClass() == String.class)
			return formatDateTime((String) o);
		else if (o.getClass() == java.util.Date.class)
			return formatDateTime((java.util.Date) o);
		else if (o.getClass() == Timestamp.class) {
			return formatDateTime(new java.util.Date(((Timestamp) o).getTime()));
		} else
			return o.toString();
	}

	/**
	 * 获取日期的时间日期区间（String参数）
	 * 
	 * @param fromdate
	 *            开始时间
	 * @param todate
	 *            结束时间
	 * @param format
	 *            日期转换格式
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateZone(String fromdate, String todate) {
		String format = "yyyy-MM-dd";
		List<String> dateZone = new ArrayList<String>(10);

		long timediff = 1000 * 60 * 60 * 24;
		dateZone.add(fromdate);
		java.util.Date nowdate = null;
		java.util.Date fd = parseDate(fromdate, format);
		java.util.Date td = parseDate(todate, format);
		nowdate = new java.util.Date(fd.getTime() + timediff);

		while (nowdate.getTime() < td.getTime()) {
			dateZone.add(formatDate(nowdate, format));
			nowdate = new java.util.Date(nowdate.getTime() + timediff);
		}
		dateZone.add(todate);

		return dateZone;
	}

	/**
	 * 获取日期的时间区间（Date 参数）
	 * 
	 * @param fromdate
	 * @param todate
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateZone(java.util.Date fromdate,
			java.util.Date todate) {
		return getDateZone(formatDate(fromdate), formatDate(todate));
	}

	/**
	 * 获取日期的时间小时区间（String参数）
	 * 
	 * @param fromdate
	 * @param todate
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static List<String> getHourZone(String fromdate, String todate) {
		String format = "yyyy-MM-dd HH:mm:ss";
		List<String> dateZone = new ArrayList<String>(10);

		long timediff = 1000 * 60 * 60;
		dateZone.add(fromdate);
		SimpleDateFormat sf = new SimpleDateFormat(format);
		java.util.Date nowdate = null;
		java.util.Date fd = null;
		java.util.Date td = null;
		try {
			fd = sf.parse(fromdate);
			td = sf.parse(todate);
		} catch (ParseException e) {
			return null;
		}
		nowdate = new java.util.Date(fd.getTime() + timediff);

		while (nowdate.getTime() < td.getTime()) {
			dateZone.add(sf.format(nowdate));
			nowdate = new java.util.Date(nowdate.getTime() + timediff);
		}

		dateZone.add(todate);

		return dateZone;
	}

	public static List<String> getHourZone(java.util.Date fromdate,
			java.util.Date todate) {
		return getHourZone(formatDate(fromdate), formatDate(todate));
	}

	/**
	 * 获取日期时间
	 * 
	 * @param date
	 *            默认今天
	 * @param datepart
	 * @return
	 * @throws Exception
	 */
	public static int getDatePart(java.util.Date date, int datepart) {
		if (date == null)
			date = now();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(datepart);
	}

	public static int getYear(java.util.Date date) {
		return getDatePart(date, DateUtil.YEAR);
	}

	public static int getMonth(java.util.Date date) {
		return getDatePart(date, DateUtil.MONTH);
	}

	public static int getDate(java.util.Date date) {
		return getDatePart(date, DateUtil.DATE);
	}

	public static int getHour(java.util.Date date) {
		return getDatePart(date, DateUtil.HOUR);
	}

	public static int getMinute(java.util.Date date) {
		return getDatePart(date, DateUtil.MINUTE);
	}

	public static int getSecond(java.util.Date date) {
		return getDatePart(date, DateUtil.SECOND);
	}

	// -----------------------------------------------------

	// --------------------- 1. --------------------------------
	// -----------------------------------------------------

	// --------------------- 1. --------------------------------
	// -----------------------------------------------------

}
