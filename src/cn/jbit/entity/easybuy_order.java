package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东  订单实体类
 */
@Entity
@Table(name="easybuy_order")
@SuppressWarnings("serial")
public class easybuy_order implements Serializable{
	
	private int eo_Id;					//订单编号
	private String eo_User_id;			//用户ID
	private String eo_User_name;		//用户名
	private String eo_User_address;		//用户地址
	private String eo_Create_time;		//创建订单时间
	private float eo_Cost;				//订单金额
	private int eo_Status;				//订单状态
	private int es_Id;					//编号
	private String es_StatusName;		//订单状态名称
	private float eo_Score;				//会员积分
	
	public float getEo_Score() {
		return eo_Score;
	}
	public void setEo_Score(float eo_Score) {
		this.eo_Score = eo_Score;
	}
	public int getEs_Id() {
		return es_Id;
	}
	public void setEs_Id(int es_Id) {
		this.es_Id = es_Id;
	}
	public String getEs_StatusName() {
		return es_StatusName;
	}
	public void setEs_StatusName(String es_StatusName) {
		this.es_StatusName = es_StatusName;
	}
	public int getEo_Id() {
		return eo_Id;
	}
	public void setEo_Id(int eo_Id) {
		this.eo_Id = eo_Id;
	}
	public String getEo_User_id() {
		return eo_User_id;
	}
	public void setEo_User_id(String eo_User_id) {
		this.eo_User_id = eo_User_id;
	}
	public String getEo_User_name() {
		return eo_User_name;
	}
	public void setEo_User_name(String eo_User_name) {
		this.eo_User_name = eo_User_name;
	}
	public String getEo_User_address() {
		return eo_User_address;
	}
	public void setEo_User_address(String eo_User_address) {
		this.eo_User_address = eo_User_address;
	}
	public String getEo_Create_time() {
		return eo_Create_time;
	}
	public void setEo_Create_time(String eo_Create_time) {
		this.eo_Create_time = eo_Create_time;
	}
	public float getEo_Cost() {
		return eo_Cost;
	}
	public void setEo_Cost(float eo_Cost) {
		this.eo_Cost = eo_Cost;
	}
	public int getEo_Status() {
		return eo_Status;
	}
	public void setEo_Status(int eo_Status) {
		this.eo_Status = eo_Status;
	}
}
