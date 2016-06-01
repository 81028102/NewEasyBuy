package cn.jbit.test;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzMain {
	//主方法测试
	public static void main(String[] args){
		try {
			new ClassPathXmlApplicationContext("applicationContext.xml");
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}
