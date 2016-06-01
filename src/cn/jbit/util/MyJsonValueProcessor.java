package cn.jbit.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class MyJsonValueProcessor implements JsonValueProcessor {

	private  String format ="yyyy-MM-dd"; //将时间格式化为yyyy-MM-dd 

	public MyJsonValueProcessor() {  
		super();  
	}  

	public MyJsonValueProcessor(String format) {  
		super();  
		this.format = format;  
	}  

	/*public String processDate(Date date) {

		return DateFormatUtils.format(date, format);
	}

	public JSONObject processBean(Object bean, JsonConfig jsonConfig) {
		JSONObject jsonObject = null;
		if (bean instanceof java.sql.Date) {
			bean = new Date(((java.sql.Date) bean).getTime());
		}
		if (bean instanceof Date) {
			Calendar c = Calendar.getInstance();
			c.setTime((Date) bean);
			//jsonObject = JSONObject.fromObject(processDate((Date) bean) );
			jsonObject = new JSONObject().element("year", c.get(Calendar.YEAR))
					.element("month", c.get(Calendar.MONTH))
					.element("day", c.get(Calendar.DAY_OF_MONTH))
					.element("hours", c.get(Calendar.HOUR_OF_DAY))
					.element("minutes", c.get(Calendar.MINUTE))
					.element("seconds", c.get(Calendar.SECOND))
					.element("milliseconds", c.get(Calendar.MILLISECOND))
					.element("time", c.getTimeInMillis());

		} else {
			jsonObject = new JSONObject(true);
		}
		return jsonObject;
	}*/
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return process(arg0); 
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO Auto-generated method stub
		return process(arg1); 
	}
	private Object process(Object value){
		// TODO Auto-generated method stub
		if(value instanceof Date){    
			SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);    
			return sdf.format(value);  
		}    
		return value == null ? "" : value.toString();    
	}  
}
