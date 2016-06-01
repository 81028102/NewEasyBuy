package cn.jbit.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 将List、Set或Map对象里的导致死循环的对象设置为null，可选的将日期类型的属性值设为null，最后将对象转换为String类型。
 * 局限：实体类全限定名要以com开头，List、Set或Map对象里的存放的只能是实体类对象，不能是list或set等等集合类型。
 * 但是实体类里可以有List或Set类型的属性。
 * @author bob
 *
 */
public class JsonUtil {

	public static String format ="yyyy-MM-dd"; //将时间格式化为yyyy-MM-dd

	public static boolean  dateToNull=false;

	public JsonUtil(){}
	public JsonUtil(boolean dateToNull){
		JsonUtil.dateToNull=dateToNull;
	}

	/**
	 * 返回被ObjectMapper处理后的json字符串：能够格式化json的日期格式为yyyy-MM-dd
	 * @return
	 */
	public static String toJson(Object obj) {

		toNull(obj);
		ObjectMapper mapper = new ObjectMapper();
		// 添加功能，让时间格式更具有可读性
		SimpleDateFormat dft = new SimpleDateFormat(format);
		mapper.setDateFormat(dft);
		String str=null;
		try {
			str= mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 返回被ObjectMapper处理后的json字符串：能够格式化json的日期格式为dateFormat指定的格式，例如yyyy-MM-dd
	 * @return
	 */
	public static String toJson(Object obj,String dateFormat) {

		toNull(obj);
		ObjectMapper mapper = new ObjectMapper();

		String str=null;
		try {
			// 添加功能，让时间格式更具有可读性
			SimpleDateFormat dft = new SimpleDateFormat(dateFormat);
			mapper.setDateFormat(dft);
			str= mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return str;
	}

	/**
	 * 将object转换为json格式的String对象：能够格式化json的日期格式
	 * @return
	 */
	public static String toJsonFromObject(Object obj) {

		JsonConfig jc = new JsonConfig();
		//jc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jc.registerJsonValueProcessor( Date.class,new MyJsonValueProcessor() );//调用MyJsonValueProcessor类
		toNull(obj);
		if(obj instanceof Collection){
			JSONArray object = JSONArray.fromObject(obj,jc);
			return object.toString();
		}else {
			JSONObject object = JSONObject.fromObject(obj,jc);
			return object.toString();
		}
	}


	/**
	 * 将List、Set或Map对象里的导致死循环的对象设置为null，还将日期类型的属性设为null。
	 * @param obj list或set或map或实体类对象
	 */
	@SuppressWarnings("rawtypes")
	public static void toNull(Object obj){

		if(obj==null)return;
		if(obj instanceof Collection){
			Collection list =(Collection)obj;
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object v = (Object) it.next();
				dealEntityOneLevel(v);
			}
		}else if(obj instanceof Map){
			Map m =(Map)obj;
			Set entrySet = m.entrySet();
			Iterator it = entrySet.iterator();
			while (it.hasNext()) {
				Map.Entry next =(Map.Entry) it.next();
				Object value = next.getValue();
				toNull(value);
			}
		}else {
			dealEntityOneLevel(obj);
		}
	}

	/**
	 * 处理实体类
	 * @param entity
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static void dealEntityOneLevel(Object entity){

		if(entity==null)return;
		String entityClassName = entity.getClass().getName();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldClassName = fields[i].getType().getName();
			Object fieldValue = valField(entity,fields[i]);

			if(fieldValue instanceof java.util.Date && dateToNull){
				valField( entity,fields[i],null);
			}else if (fieldValue instanceof Collection) {
				Collection list =(Collection)fieldValue;
				Iterator it = list.iterator();
				while (it.hasNext()) {
					Object t =it.next();
					dealEntityTwoLevel(t);
				}
			}else if(fieldClassName.startsWith("cn")){//判断是否实体类，实体类全限定名一般以com开头(这里使用cn开头)
				dealEntityTwoLevel(fieldValue);
			}
		}
	}

	/**
	 * 处理实体类,将实体类型和Collection类型的属性，都变成null，其他类型的属性值不改变。
	 * @param entity
	 */
	@SuppressWarnings("unused")
	private static void dealEntityTwoLevel(Object entity){

		if(entity==null)return;
		String entityClassName = entity.getClass().getName();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldClassName = fields[i].getType().getName();
			Object fieldValue = valField(entity,fields[i]);

			if(fieldValue instanceof java.util.Date && dateToNull){
				valField( entity,fields[i],null);
			}else if (fieldValue instanceof Collection) {
				valField( entity,fields[i],null);//将实体类对象的List或set属性设置为null
			}else if(fieldClassName.startsWith("cn")){//判断是否实体类，实体类全限定名一般以com开头(这里使用cn开头)
				valField( entity,fields[i],null);
			}
		}
	}

	public static void valField(Object entity,Field field,Object value){

		field.setAccessible(true);
		try {
			field.set(entity,value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static Object valField(Object entity,Field field){

		field.setAccessible(true);
		Object fieldValue=null;
		try {
			fieldValue = field.get(entity);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return fieldValue;
	}
}
