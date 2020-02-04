package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String YEAR = "yyyy";
	public static final String MONTH = "MM";
	public static final String DATE = "yyyyMMdd";
	public static final String DATE_DASH = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String DATE_TIME_FULL = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String TIME = "HH:mm:ss";
	public static final String TIMETomm = "yyyyMMddHHmm";
	public static final String TIMETommsssss = "yyyyMMddHHmmssSSS";
	public static final String TIMEDATE_SHORT = "yyMMdd";
	public static final String TIME_HHmm = "HH:mm";
	public static final long ONEDAY = 24L * 60L * 60L * 1000L;
	public static final long THREE_MONTH = 90L * ONEDAY;

	public static final int SECOND = 1000;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;

	public static final String[] ENG_MONTH_SHORT = { "Jan", "Feb", "Mar",
			"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	public static final String[] NUM_MONTH = { "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12" };

	/**
	 * //日期to字符串
	 * 
	 * @param date
	 * @param strFormat
	 *            yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String dateToStr(Date date, String strFormat) {
		String result = null;
		try {
			if (date != null) {
				SimpleDateFormat format = new SimpleDateFormat(strFormat);
				result = format.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String dateToStr(Date date) {
		String result = null;
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(date);
		}
		return result;
	}

	public static String toString(Date date, String strFormat) {
		return toString(date, new SimpleDateFormat(strFormat));
	}

	private static String toString(Date date, DateFormat format) {
		String result = null;
		try {
			if (date != null) {
				result = format.format(date);
			}
		} catch (Exception e) {
			logger.error("unable to format date: ", e);
		}
		return result;
	}

	public static String toYYYYMMDDWithDash(Date date) {
		return toString(date, DATE_DASH);
	}

	public static String toYYYYMMDDWithNoDash(Date date) {
		return toString(date, DATE);
	}

	public static String toTime(Date date) {
		return toString(date, TIME);
	}

	public static String toDateTime(Date date) {
		return toString(date, DATE_TIME);
	}

	/*
	 * 将日期格式为yyyy-MM-DD hh:mm:ss param date 日期时间
	 */
	public static String toYYYYMMDDHHMMSSString(Date date) {
		return toString(date, DATE_TIME);
	}

	public static Date toDate(String dateStr, String strFormat) {
		return toDate(dateStr, new SimpleDateFormat(strFormat));
	}

	// 比如2012-12-12
	private static Date toDate(String dateStr, DateFormat format) {
		Date date = null;

		try {
			date = format.parse(dateStr);
		} catch (Exception e) {
			logger.error("unable to convert string to date:" + dateStr, e);
			return null;
		}

		return date;
	}

	// 比如2012-12-12
	public static Date toDateWithDash(String dateStr) {
		return toDate(dateStr, DATE_DASH);
	}

	// 比如2012-12-12 11:11:00
	public static Date toDateTimeWithDash(String dateStr) {
		return toDate(dateStr, DATE_TIME);
	}

	public static boolean isDate(String value) {
		String regex = "^[0-9]{2,4}[-][0-9]{1,2}[-][0-9]{1,2}$";
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public static boolean isDateTime(String value) {
		String regex = "^[0-9]{2,4}[-][0-9]{1,2}[-][0-9]{1,2}\\s*[0-9]{2}[:][0-9]{2}[:][0-9]{2}$";
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	/*
	 * 获取日期中的年份
	 */
	public static String getYear(Date date) {
		return toString(date, YEAR);
	}

	/*
	 * 获取日期中的月份
	 */
	public static String getMonth(Date date) {
		return toString(date, MONTH);
	}

	public static boolean isOneMinuteAgo(Date pastDate) {
		Date newDate = addMinutes(pastDate, 1);

		Date now = new Date();

		return now.after(newDate);
	}

	public static Date addMinutes(Date date, int minutesToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutesToAdd);

		return new Date(c.getTimeInMillis());
	}

	public static Date addDays(Date date, int daysToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, daysToAdd);

		return new Date(c.getTimeInMillis());
	}

	public static Date addDaysAndMinusOneSecond(Date date, int daysToAdd) {
		date = addDays(date, daysToAdd);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, -1);

		return new Date(c.getTimeInMillis());
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 分钟之差
	 */
	public static long minuteDifference(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / MINUTE;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 小时之差
	 */
	public static long hoursDifference(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / HOUR;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 自然天的天数之差
	 */
	public static int naturalDaysDifference(Date startDate, Date endDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);

		int days = (int) ((endDate.getTime() - c.getTimeInMillis()) / DAY);

		if (days == 0 && (endDate.getTime() > c.getTimeInMillis())) {
			days = 1;
		}

		return days;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 时间格式（1分钟前、1小时前，1天前。。。）
	 */
	public static String formatDateToShow(Date startDate, Date endDate) {

		/* 开始时间和结束时间 都不能为空 */
		if (startDate != null && endDate != null) {
			String s = "刚刚";
			long min = minuteDifference(startDate, endDate);
			if (min <= 1) {
				s = "1分钟前";
			} else if (min > 1 && min < 60) {
				s = min + "分钟前";
			} else if (min >= 60) {
				long hour = hoursDifference(startDate, endDate);
				if (hour <= 1) {
					s = "1小时前";
				} else if (hour > 1 && hour < 24) {
					s = hour + "小时前";
				} else if (hour >= 24) {
					int day = naturalDaysDifference(startDate, endDate);
					if (day <= 1) {
						s = "1天前";
					} else if (day > 1 && day < 4) {
						s = day + "天前";
					} else if (day >= 4) {
						s = dateToStr(startDate, DATE_DASH);
					}
				}
			}
			return s;
		} else {
			return null;
		}
	}

	public static long hoursToNow(Date startDate) {
		return hoursDifference(startDate, now());
	}

	public static Date now() {
		return new Date();
	}

	public static Date getBeginingOfTheDay(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}

		return toDateTimeWithDash(date + " 0:00:00");
	}

	public static Date getBeginingOfTheDay(Date date) {
		if (date == null) {
			return null;
		}

		return getBeginingOfTheDay(toYYYYMMDDWithDash(date));
	}

	public static Date getEndOfTheDay(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}

		return toDateTimeWithDash(date + " 23:59:59");
	}

	public static Date getEndOfTheDay(Date date) {
		if (date == null) {
			return null;
		}

		return getEndOfTheDay(toYYYYMMDDWithDash(date));
	}

	public static Date tomorrow() {
		return addDays(now(), 1);
	}

	public static Date addMonths(Date start, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, months);

		return new Date(c.getTimeInMillis());
	}

	public static Date addSeconds(Date start, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.SECOND, seconds);

		return new Date(c.getTimeInMillis());
	}

	public static Date addHours(Date start, int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.HOUR_OF_DAY, hours);

		return new Date(c.getTimeInMillis());
	}

	public static Date getFirstDayOfMonthByGivenDate(int year, int month) {
		Date now = now();

		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);

		return new Date(c.getTimeInMillis());
	}

	public static Date getFirDayOfCurrentMonth() {
		Date now = now();

		Calendar c = Calendar.getInstance();
		c.setTime(now);

		return getFirstDayOfMonthByGivenDate(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH));
	}

	public static Date getFirstDayOfNextMonth() {
		Date now = now();

		Calendar c = Calendar.getInstance();
		c.setTime(now);

		return getFirstDayOfNextMonthByGivenDate(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH));
	}

	public static Date getFirstDayOfNextMonthByGivenDate(int year, int month) {
		Date date = getFirstDayOfMonthByGivenDate(year, month);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);

		return new Date(c.getTimeInMillis());
	}

	public static Date addOneMonthMinusOneDay(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);

		return new Date(c.getTimeInMillis());
	}

	public static Date addOneMonthAndOneDayMinusOneSecond(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, 1);
		c.add(Calendar.SECOND, -1);

		return new Date(c.getTimeInMillis());
	}

	public static int getLastDayOfCurrentMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// 将日期设置到下个月的第一天
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 日期间一天（即返回当前月的最后一天）
		c.add(Calendar.DATE, -1);

		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean isBetweenTime(Date startTime, Date dueTime, Date time) {
		if (startTime == null || dueTime == null || time == null) {
			return false;
		}

		return time.after(startTime) && time.before(dueTime);
	}

	public static boolean isPastBeginingOfDay(Date dueDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DATE, 1);

		return DateUtil.now().after(c.getTime());
	}

	public static boolean isPastNow(Date time) {
		if (time != null) {
			return now().after(time);
		}
		return false;
	}

	public static String getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age + "";
	}

	public static boolean isToday(Date date) {
		// 系统时间
		if (toDateTime(now()).equals(toDateTime(date))) {
			return true;
		}
		return false;
	}

	// 是否为同一天
	public static boolean areSameDay(Date dateA, Date dateB) {
		Calendar calDateA = Calendar.getInstance();
		calDateA.setTime(dateA);

		Calendar calDateB = Calendar.getInstance();
		calDateB.setTime(dateB);

		return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
				&& calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
				&& calDateA.get(Calendar.DAY_OF_MONTH) == calDateB
						.get(Calendar.DAY_OF_MONTH);
	}

	// 两个时间 相差分钟
	public static Integer differDate(Date starttime, Date endtime) {
		long seconds = endtime.getTime() - starttime.getTime();
		return Integer.valueOf(seconds / 1000 * 60 + "");
	}

	// 分 转 时/分
	public static String hourToStr(Integer hour) {
		StringBuffer sf = new StringBuffer();
		if (hour >= 3600) {
			sf.append(hour / 3600 + " H ");
		}
		int m = (hour % 3600) / 60;
		sf.append(m + " M");
		return sf.toString();
	}

	// 2: 发布时间:今天的显示today,超过今天显示年-月-日 ------------
	public static String postDate(Date date) {
		if (null == date) {
			return null;
		}
		// 判断是否为今天 : 时:分
		if (isToday(date)) {
			return "Today-" + dateToStr(date, "HH:mm");
		}
		// 年-月-日
		return toYYYYMMDDWithDash(date);
	}

	// 秒转时：分：秒
	public static String secondToStr(Integer second) {
		StringBuffer sf = new StringBuffer();
		if (second == 0 || second == null) {
			return "0";
		}
		if (second >= 3600) {
			sf.append(second / 3600 + "H ");
			if ((second % 3600) >= 60) {
				sf.append((second % 3600) / 60 + "m ");
			}
		} else if (second >= 60) {
			sf.append((second % 3600) / 60 + "m ");
		} else {
			sf.append(second + "s");
			return sf.toString();
		}
		int s = second % 60;
		if (s > 0) {
			sf.append(s + "s");
		}
		return sf.toString();
	}

	/**
	 * 获取某月的最后一天
	 * 
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	/**
	 * 当前 是时间 加15
	 * 
	 * @param start
	 * @return
	 */
	public static Date AddNestMonth(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 15);

		return new Date(c.getTimeInMillis());
	}

	/**
	 * 时间戳转换为日期
	 * 
	 * @Title: transForDate
	 * @param ms
	 * @return Date
	 * @author Cheng
	 * @date 2018年4月27日上午9:57:25
	 */
	public static String timestampToDate(String ms) {
		if (ms == null) {
			ms = "0";
		}
		Integer time = Integer.parseInt(ms);
		long msl = (long) time * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
		if (ms != null) {
			try {
				String str = sdf.format(msl);
				return str;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @Title: getNowTime
	 * @return Date
	 * @author ding
	 * @date 2018年4月28日上午9:57:25
	 */
	public static Long getNowTime() {
		return now().getTime();
	}

}
