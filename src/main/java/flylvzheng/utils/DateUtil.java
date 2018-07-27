package flylvzheng.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class DateUtil {
	/**
	 * 返回一段时间之间所有的时间段
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	 public static List<Date> findDates(Date dBegin, Date dEnd){  
	  List<Date> lDate = new ArrayList<Date>();  
	  lDate.add(dBegin);  
	  Calendar calBegin = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calBegin.setTime(dBegin);  
	  Calendar calEnd = Calendar.getInstance();  
	  // 使用给定的 Date 设置此 Calendar 的时间  
	  calEnd.setTime(dEnd);  
	  // 测试此日期是否在指定日期之后  
	  while (dEnd.after(calBegin.getTime()))  
	  {  
	   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
	   calBegin.add(Calendar.DAY_OF_MONTH, 1);  
	   lDate.add(calBegin.getTime());  
	  }  
	  return lDate;  
	 }  
	 
	 /**
		 * 返回一天中的所有小时字段
		 * @param dBegin
		 * @param dEnd
		 * @return
		 */
		 public static List<Date> findDatesHH(Date dBegin, Date dEnd){  
		  List<Date> lDate = new ArrayList<Date>();  
		  lDate.add(dBegin);  
		  Calendar calBegin = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calBegin.setTime(dBegin);  
		  Calendar calEnd = Calendar.getInstance();  
		  // 使用给定的 Date 设置此 Calendar 的时间  
		  calEnd.setTime(dEnd);  
		  // 测试此日期是否在指定日期之后  
		  while (dEnd.after(calBegin.getTime()))  
		  {  
		   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
		   calBegin.add(Calendar.HOUR_OF_DAY, 1);  
		   lDate.add(calBegin.getTime());  
		  }  
		  return lDate;  
		 }  
	 
	 
	/**
	 * 返回当前时间的毫秒数
	 * "yyyy-MM-dd HH:mm:ss"
	 * @return Long
	 */
	public static final long getDateLong(String format) {
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		String datetime1 = new SimpleDateFormat(format)
				.format(Calendar.getInstance().getTime());
		return getDateLong(datetime1,format);
	}
	
	/**
	 * 返回指定日期字符串的毫秒数
	 * @return Long 1毫秒=1000毫秒
	 */
	public static final long getDateLong(String date,String format) {
		SimpleDateFormat d1 = new SimpleDateFormat(format);
		long time = 0;
		try {
			Date date2 = d1.parse(date);
			time = date2.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
	
	/**
	 * 返回毫秒数格式的日期字符串
	 * @return Long 1毫秒=1000毫秒
	 */
	public static final String LongToDate(long mill,String format) {
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		return SDF.format(new Date(mill));
	}
	
	/**
	 * 返回两个毫秒时间的相差天数
	 *
	 * @return Long 1毫秒=1000毫秒
	 */
	public static long getDateDays(long date1, long date2) {
		long days = 0;
		long day = 86400000L;
		long dateCha = date1 - date2;
		if (dateCha < 0){
			dateCha = dateCha * -1;
		}
		days = dateCha / day;
		return days;
	}
	
	
	/**
	 * 得到两个日期之间相差的天数
	 *
	 * @param newDate
	 *            大的日期
	 * @param oldDate
	 *            小的日期
	 * @return newDate-oldDate相差的天数
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;
		if (days < 0){
			days = days * -1;
		}
		return days;
	}
	
	/**
	 * 获取当前时间字符串
	 * 格式如1999-12-02的当前时间
	 *
	 * @return String
	 */
	public static String getCurrentDateTime(String format) {
		TimeZone tz = TimeZone.getTimeZone("GMT+8");
		TimeZone.setDefault(tz);
		String datetime1 = new SimpleDateFormat(format)
				.format(Calendar.getInstance().getTime());
		
		return datetime1;
	}
	
	public static String createKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	/**
	 * 返回指定字符串日期的前daynum天字符串
	 * 格式如1999-12-02的当前时间
	 * "yyyy-MM-dd"
	 * @return String
	 */
	public static String getCurrentDatePreDays(String date,String format,int daynum) {
		long time = getDateLong(date,format);
		time = time - (1000L*60*60*24*daynum);
		String datetime = LongToDate(time,format);
		return datetime;
	}
	
	/*****************
	 * 第三方调用url传递过来的解析字符串
	 * swtx_key=xxxxxxxxx
	 * @return
	 */
	public static String getMD5KeyByUrl(){
		long cur = getDateLong("yyyy-MM-dd"); //当前日期的毫秒数
		long cur2 = Long.parseLong(getCurrentDateTime("yyyyMMdd")); //当前日期的整数格式
		long cur3 = cur-cur2;
		//MD5 md5 = new MD5();
	//	String str = md5.getMD5ofStr(cur3+"_SWTX"); //两个数字相减后加_SWTX经过MD5加密后的字符串
		return null;
	}
	
	public static String nextMonthFirstDate(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }
	
	/*public static void main(String[] args){
		String format = "yyyy-MM-dd";
		String curDate = getCurrentDateTime(format);//当天
		String startDate = getCurrentDatePreDays(curDate,format,1); //昨天
		String endDate = getCurrentDatePreDays(curDate,format,7); //7天前
		
		System.out.println("今天："+curDate);
		System.out.println("昨天："+startDate);
		System.out.println("7天前："+endDate);
		

		System.out.println(getMD5KeyByUrl());
		
		System.out.println(nextMonthFirstDate("yyyyMM"));
	}*/
	
}
