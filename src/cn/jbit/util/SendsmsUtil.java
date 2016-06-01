package cn.jbit.util;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;   
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   

/**
 * @author 任锯东 实现手机验证码
 */
public class SendsmsUtil {
	
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";//调用接口url
	
	public static void main(String [] args) {
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		
		int mobile_code = (int)((Math.random()*9+1)*100000);
		//System.out.println(mobile);
		
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_981028102"), 
			    new NameValuePair("password", "qq981028102"), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", ""), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();

			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			if(code == "2"){
				System.out.println("短信提交成功");
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
}