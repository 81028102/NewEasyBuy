package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东  订单详情实体类
 */
@Entity
@Table(name="easybuy_order_detail")
@SuppressWarnings("serial")
public class easybuy_order_detail implements Serializable{
	
	private int eod_Id;				//订单详情编号
	private int eo_Id;				//订单ID
	private int ep_Id;				//商品ID
	private int eod_Quantity;		//购买数量
	private float eod_Cost;			//金额
	private String ep_Name;			//商品名称
	
	public String getEp_Name() {
		return ep_Name;
	}
	public void setEp_Name(String ep_Name) {
		this.ep_Name = ep_Name;
	}
	public String getEp_File_name() {
		return ep_File_name;
	}
	public void setEp_File_name(String ep_File_name) {
		this.ep_File_name = ep_File_name;
	}
	private String ep_File_name;		//商品图片路径
	public int getEod_Id() {
		return eod_Id;
	}
	public void setEod_Id(int eod_Id) {
		this.eod_Id = eod_Id;
	}
	public int getEo_Id() {
		return eo_Id;
	}
	public void setEo_Id(int eo_Id) {
		this.eo_Id = eo_Id;
	}
	public int getEp_Id() {
		return ep_Id;
	}
	public void setEp_Id(int ep_Id) {
		this.ep_Id = ep_Id;
	}
	public int getEod_Quantity() {
		return eod_Quantity;
	}
	public void setEod_Quantity(int eod_Quantity) {
		this.eod_Quantity = eod_Quantity;
	}
	public float getEod_Cost() {
		return eod_Cost;
	}
	public void setEod_Cost(float eod_Cost) {
		this.eod_Cost = eod_Cost;
	}
}
