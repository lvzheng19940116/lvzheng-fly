package flylvzheng.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateWeekOfMonth {

	/**
	 * @Title: getDateOfWeek
	 * @Description: 获取两个时间内所有周之间的时间段，并且获得是今年第几周（同一年内）
	 * @param start_time
	 *            开始时间
	 * @param end_time
	 *            结束时间
	 * @return 
	 */
	public static List<String> getDateOfWeek(String start_time, String end_time) {
		List<String> list = new ArrayList<String>();
		List<String> lastList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar s_c = Calendar.getInstance();
			Calendar e_c = Calendar.getInstance();
			Date s_time = sdf.parse(start_time);
			Date e_time = sdf.parse(end_time);
			// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			String first_week = getFirstDayOfWeek(start_time);// 取得开始日期指定日期所在周的第一天
			String last_week = getLastDayOfWeek(end_time);// 取得结束日期指定日期所在周的最后一天
			s_c.setTime(s_time);
			e_c.setTime(e_time);
			int currentWeekOfYear = s_c.get(Calendar.WEEK_OF_YEAR);
			int currentWeekOfYear_e = e_c.get(Calendar.WEEK_OF_YEAR);
			if (currentWeekOfYear_e == 1) {
				currentWeekOfYear_e = 53;
			}
			int j = 12;
			for (int i = 0; i < currentWeekOfYear_e; i++) {
				int dayOfWeek = e_c.get(Calendar.DAY_OF_WEEK) - 2;
				e_c.add(Calendar.DATE, -dayOfWeek); // 得到本周的第一天
				String s_date = sdf.format(e_c.getTime());
				e_c.add(Calendar.DATE, 6); // 得到本周的最后一天
				String e_date = sdf.format(e_c.getTime());
				e_c.add(Calendar.DATE, -j); // 减去增加的日期
				// 只取两个日期之间的周
				if (currentWeekOfYear == currentWeekOfYear_e - i + 2) {
					break;
				}
				// 只取两个日期之间的周
				if (compareDate(first_week, s_date) && compareDate(s_date, last_week) && compareDate(first_week, e_date)
						&& compareDate(e_date, last_week)) {
					// 超过选择的日期，按选择日期来算
					if (!compareDate(start_time, s_date)) {
						s_date = start_time;
					}
					if (!compareDate(e_date, end_time)) {
						e_date = end_time;
					}
					String s = s_date + "~" + e_date;
					list.add(s);
				}
			}
			if(list !=null){
				for(int i=list.size()-1;i>=0;i--){
					lastList.add(list.get(i));
				}	
			}
			if(lastList != null){
				for(int i=0;i<lastList.size();i++){
					int days = DateUtil.daysBetweenDates(sdf.parse(lastList.get(i).split("~")[1]),sdf.parse(lastList.get(i).split("~")[0]));
					if(days ==6){
						dateList.add(lastList.get(i));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dateList;
	}
	/** 
     * 获取时间段内所有的年月集合 
     * 
     * @param minDate 最小时间  2017-01 
     * @param maxDate 最大时间 2017-10 
     * @return 日期集合 格式为 年-月 
     * @throws Exception 
     */  
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {  
        ArrayList<String> result = new ArrayList<String>();  
        ArrayList<String> resultList = new ArrayList<String>();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月  
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月  
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");//格式化为年月  
        Calendar min = Calendar.getInstance();  
        Calendar max = Calendar.getInstance();  
        min.setTime(sdf.parse(minDate));  
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);  
        max.setTime(sdf.parse(maxDate));  
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);  
        Calendar curr = min;  
        while (curr.before(max)) {  
            result.add(sdf.format(curr.getTime()));  
            curr.add(Calendar.MONTH, 1);  
        } 
        if(result.size() != 0){
        	if(sdf2.format(sdf1.parse(minDate)).equals("01")){
    			resultList.add(result.get(0));
    		}
        	for(int i=1;i<result.size()-1;i++){
        		resultList.add(result.get(i));
        	}
        	boolean flag = lastDayOfMonth(maxDate);
        	if(flag){
        		resultList.add(result.get(result.size()-1));
        	}
        }
        return resultList;  
    }
    /**
     * 判断输入的年月日是不是当月的最后一日
     * @param dateTime
     * @return
     */
	public static boolean lastDayOfMonth(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		boolean flag =false;
		try {
			 Calendar lastDate = Calendar.getInstance();
			  lastDate.setTime(sdf.parse(dateTime));
			  lastDate.set(Calendar.DATE, 1);
			  lastDate.add(Calendar.MONTH, 1);
			  lastDate.add(Calendar.DATE, -1);
			  String lastDayOfMonth = sdf1.format(lastDate.getTime());
			if(sdf1.format(sdf.parse(dateTime)).equals(lastDayOfMonth)){
				flag=true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return flag;
	}
	/**
	 * 返回输入的日期的月份一共多少天
	 * @param dateTime
	 * @return
	 */
	public static String getLastDayOfMonth(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		String lastDayOfMonth =null;
		try {
			  Calendar lastDate = Calendar.getInstance();
			  lastDate.setTime(sdf.parse(dateTime));
			  lastDate.set(Calendar.DATE, 1);
			  lastDate.add(Calendar.MONTH, 1);
			  lastDate.add(Calendar.DATE, -1);
			  lastDayOfMonth = sdf1.format(lastDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return lastDayOfMonth;
	}
	/**
	 * 取得指定日期所在周的第一天
	 */
	public static String getFirstDayOfWeek(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date time = sdf.parse(date);
			Calendar c = new GregorianCalendar();
			c.setFirstDayOfWeek(Calendar.MONDAY);

			c.setTime(time);
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 取得指定日期所在周的最后一天
	 */
	public static String getLastDayOfWeek(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date time = sdf.parse(date);
			Calendar c = new GregorianCalendar();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setTime(time);
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * compareDate方法
	 * <p>
	 * 方法说明： 比较endDate是否是晚于startDate； 如果是，返回true， 否则返回false
	 * </p>
	 */
	public static boolean compareDate(String startDate, String endDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = dateFormat.parse(startDate);
			java.util.Date date2 = dateFormat.parse(endDate);
			if (date1.getTime() > date2.getTime()){
				return false;
			}
			return true; // startDate时间上早于endDate

		} catch (Exception e) {
			return false;
		}
	}
}
