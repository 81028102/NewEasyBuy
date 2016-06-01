package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东 手机找回密码实体类
 */
@SuppressWarnings("serial")
public class easybuy_tel implements Serializable{
	
	private int id;			//编号
	private String mobile;	//手机号
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
