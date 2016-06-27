package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东 支付实体类
 */
@Entity
@Table(name="easybuy_bank")
@SuppressWarnings("serial")
public class easybuy_pay implements Serializable {
	
	private int payid; 			//支付编号
	private String paycardid; 	//支付卡号
	private String paypwd;		//支付密码
	private float money;		//卡内余额
	
	public String getPaypwd() {
		return paypwd;
	}
	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	public String getPaycardid() {
		return paycardid;
	}
	public void setPaycardid(String paycardid) {
		this.paycardid = paycardid;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
}
