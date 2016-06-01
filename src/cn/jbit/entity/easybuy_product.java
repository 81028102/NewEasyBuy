package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东    商品实体类
 */
@SuppressWarnings("serial")
public class easybuy_product implements Serializable{
	
	private int ep_Id;				//商品编号
	private String ep_Name;			//商品名字
	private String ep_Description;	//商品描述
	private float ep_Price;			//商品价格
	private int ep_Stock;			//商品库存
	private int epc_id;				//商品所属分类ID
	private int epc_Child_id;		//商品所属二级分类ID
	private String ep_File_name;	//上传的文件名
	private String ep_Address;		//原产地
	private String ep_Create_time;	//商品上架时间
	private int ep_Sales;			//商品销量

	public int getEp_Sales() {
		return ep_Sales;
	}
	public void setEp_Sales(int ep_Sales) {
		this.ep_Sales = ep_Sales;
	}
	public String getEp_Create_time() {
		return ep_Create_time;
	}
	public void setEp_Create_time(String ep_Create_time) {
		this.ep_Create_time = ep_Create_time;
	}
	public int getEp_Id() {
		return ep_Id;
	}
	public void setEp_Id(int ep_Id) {
		this.ep_Id = ep_Id;
	}
	public String getEp_Name() {
		return ep_Name;
	}
	public void setEp_Name(String ep_Name) {
		this.ep_Name = ep_Name;
	}
	public String getEp_Description() {
		return ep_Description;
	}
	public void setEp_Description(String ep_Description) {
		this.ep_Description = ep_Description;
	}
	public float getEp_Price() {
		return ep_Price;
	}
	public void setEp_Price(float ep_Price) {
		this.ep_Price = ep_Price;
	}
	public int getEp_Stock() {
		return ep_Stock;
	}
	public void setEp_Stock(int ep_Stock) {
		this.ep_Stock = ep_Stock;
	}
	public int getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(int epc_id) {
		this.epc_id = epc_id;
	}
	public int getEpc_Child_id() {
		return epc_Child_id;
	}
	public void setEpc_Child_id(int epc_Child_id) {
		this.epc_Child_id = epc_Child_id;
	}
	public String getEp_File_name() {
		return ep_File_name;
	}
	public void setEp_File_name(String ep_File_name) {
		this.ep_File_name = ep_File_name;
	}
	public String getEp_Address() {
		return ep_Address;
	}
	public void setEp_Address(String ep_Address) {
		this.ep_Address = ep_Address;
	}
}