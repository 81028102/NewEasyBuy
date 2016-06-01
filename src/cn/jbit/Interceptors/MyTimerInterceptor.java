package cn.jbit.Interceptors;

import java.text.SimpleDateFormat;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class MyTimerInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		// TODO Auto-generated method stub
		Object object1 = ServletActionContext.getRequest().getParameter("subject");
		Object object2 = ServletActionContext.getRequest().getParameter("content");
		//Object object3 = ServletActionContext.getRequest().getParameter("upload");
		if(object1!=null&&object2!=null){
			long startTime=System.currentTimeMillis();
			System.out.println("------->>>开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
			String result=invo.invoke();
			long endTime=System.currentTimeMillis();
			System.out.println("------->>>结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
			long execTime=endTime-startTime;
			System.out.println("------->>>所用时间:"+execTime+" ms");
			return result;
		}else{
			return "error";
		}
	}
}
