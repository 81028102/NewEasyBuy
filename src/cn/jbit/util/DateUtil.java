package cn.jbit.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	/**
	 * 获取指定日期的后几天/前几天的时间
	 *@param date  指定日期
	 *@param day   相差天数
	 *@return主方法   指定日期的后几天的时间
	 */
    public static synchronized Date getDateAfter(Date date,int day){
    	
    	GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
    	gc.setTime(date);
    	gc.add(Calendar.DATE, day);
    	return gc.getTime();
    }
}
