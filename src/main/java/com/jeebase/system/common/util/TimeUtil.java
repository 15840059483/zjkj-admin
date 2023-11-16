package com.jeebase.system.common.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TimeUtil {

	public static int timeLayered() {
	      new HashMap();
	      Date date = new Date();
	      SimpleDateFormat df = new SimpleDateFormat("HH");
	      String str = df.format(date);
	      int a = Integer.parseInt(str);
	      return a >= 0 && a <= 6?5003:(a > 6 && a <= 12?5001:(a > 12 && a <= 13?5001:(a > 13 && a <= 18?5002:(a > 18 && a <= 24?5003:5001))));
	   }

	   public static String TomorrowOrYesterday(String timeType, int count) {
	      SimpleDateFormat f = new SimpleDateFormat(timeType);
	      Calendar calendar = Calendar.getInstance();
	      Date date = new Date();
	      calendar.setTime(date);
	      int day = calendar.get(5);
	      calendar.set(5, day + count);
	      String lastDay = f.format(calendar.getTime());
	      return lastDay;
	   }

	public static  LocalDateTime toLocalDateTime(String time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Instant instant = parse.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	public static  LocalDateTime toLocalDateTime(String time,String type){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Instant instant = parse.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	   public static String Today(String timeType) {
	      SimpleDateFormat f = new SimpleDateFormat(timeType);
	      Date date = new Date();
	      String lastDay = f.format(date);
	      return lastDay;
	   }

	   public static String ConversionTimeFormat(String Time, String firstformat, String conversionFormat) throws ParseException {
	      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(firstformat);
	      SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(conversionFormat);
	      Date parse = simpleDateFormat1.parse(Time);
	      String format = simpleDateFormat2.format(parse);
	      return format;
	   }
	   public static String getInAFewSecond(String type,int second) {
		   Calendar beforeTime = Calendar.getInstance();
		   beforeTime.add(Calendar.SECOND, second);
		   Date time = beforeTime.getTime();
		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
		   String format = simpleDateFormat.format(time);
		   return format;
	   }

	public static String toTime(String date,String datetype,int DAY_OF_MONTH,String todatetype) throws ParseException {
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(datetype);
		Date parse = simpleDateFormat1.parse(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringUtils.isEmpty(todatetype)?datetype:todatetype);
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.setTime(parse);
		beforeTime.add(Calendar.DAY_OF_MONTH, DAY_OF_MONTH);
		String format = simpleDateFormat.format(beforeTime.getTime());
		return format;
	}

	   public static String monthDay(String timeType,String year,int  count) {
		   SimpleDateFormat  sd = new    SimpleDateFormat(timeType);
		   String format = "";
	        try {
	            String payoffYearMonth = year;
	            Date  currdate = sd.parse(payoffYearMonth);
	            Calendar   calendar= Calendar.getInstance();
	            calendar.setTime(currdate);
	            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+count);
	            format = sd.format(calendar.getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return format;
	   }
	   public static  int getAge(Date birthDay) throws Exception {
	        Calendar cal = Calendar.getInstance(); 
	        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
	            throw new IllegalArgumentException(
	                    "The birthDay is before Now.It's unbelievable!");
	        }
	        int yearNow = cal.get(Calendar.YEAR);  //当前年份
	        int monthNow = cal.get(Calendar.MONTH);  //当前月份
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
	        cal.setTime(birthDay); 
	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
	        int age = yearNow - yearBirth;   //计算整岁数
	            if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
	            }else{
	                age--;//当前月份在生日之前，年龄减一
	} } return age; }

	   public static void main(String[] args) {
//		   String date = "2022/4/1 8:00:00";
//		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		   try {
//			   Date parse = simpleDateFormat.parse(date);
//			   Instant instant = parse.toInstant();
//			   ZoneId zone = ZoneId.systemDefault();
//			   LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
//			   DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			   System.out.println(ofPattern.format(localDateTime));
//		   } catch (ParseException e) {
//			   e.printStackTrace();
//		   }
		   try {
			   String yyyyMMddHHmmss = TimeUtil.toTime("2022/08/01", "yyyy/MM/dd", 1, null);
			   System.out.println(yyyyMMddHHmmss);
		   } catch (ParseException e) {
			   e.printStackTrace();
		   }

	   }
	}

	
