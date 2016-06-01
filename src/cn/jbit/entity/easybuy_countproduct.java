package cn.jbit.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 任锯东    商品实体类
 */
@SuppressWarnings("serial")
public class easybuy_countproduct implements Serializable{

	private Long ep_Id;				//商品编号
	private String ep_Name;			//商品名字
	private Double ep_Price;		//商品价格
	private Long ep_Stock;			//商品库存
	private String ep_Address;		//原产地
	private Date ep_Create_time;	//创建商品时间
	private Long ep_Sales;			//商品销量

	// Constructors

	/** default constructor */
	public easybuy_countproduct() {
	}
	/** full constructor */
	public easybuy_countproduct(Long ep_Id, String ep_Name, Double ep_Price,
			Long ep_Stock, String ep_Address, Date ep_Create_time, Long ep_Sales) {
		this.ep_Id = ep_Id;
		this.ep_Name = ep_Name;
		this.ep_Price = ep_Price;
		this.ep_Stock = ep_Stock;
		this.ep_Address = ep_Address;
		this.ep_Create_time = ep_Create_time;
		this.ep_Sales = ep_Sales;
	}
	// Property accessors

	public Long getEp_Id() {
		return ep_Id;
	}

	public void setEp_Id(Long ep_Id) {
		this.ep_Id = ep_Id;
	}

	public String getEp_Name() {
		return ep_Name;
	}

	public void setEp_Name(String ep_Name) {
		this.ep_Name = ep_Name;
	}

	public Double getEp_Price() {
		return ep_Price;
	}

	public void setEp_Price(Double ep_Price) {
		this.ep_Price = ep_Price;
	}

	public Long getEp_Stock() {
		return ep_Stock;
	}

	public void setEp_Stock(Long ep_Stock) {
		this.ep_Stock = ep_Stock;
	}

	public String getEp_Address() {
		return ep_Address;
	}

	public void setEp_Address(String ep_Address) {
		this.ep_Address = ep_Address;
	}

	public Date getEp_Create_time() {
		return ep_Create_time;
	}

	public void setEp_Create_time(Date ep_Create_time) {
		this.ep_Create_time = ep_Create_time;
	}

	public Long getEp_Sales() {
		return ep_Sales;
	}

	public void setEp_Sales(Long ep_Sales) {
		this.ep_Sales = ep_Sales;
	}
}
