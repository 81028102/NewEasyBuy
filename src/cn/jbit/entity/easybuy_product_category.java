package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东   商品分类实体类
 */
@Entity
@Table(name="easybuy_product_category")
@SuppressWarnings("serial")
public class easybuy_product_category implements Serializable{
	
	private int epc_Id;				//商品分类编号
	private String epc_Name;		//商品分类名称
	private int epc_Parent_id;		//商品分类父分类ID
	private String ep_Name;     	//商品名字
	private int epc_Click_Count;	//分类点击率
	private int epc_Child_id;   	//商品所属二级分类ID
	
	public int getEpc_Click_Count() {
		return epc_Click_Count;
	}
	public void setEpc_Click_Count(int epc_Click_Count) {
		this.epc_Click_Count = epc_Click_Count;
	}
	public int getEpc_Child_id() {
		return epc_Child_id;
	}
	public void setEpc_Child_id(int epc_Child_id) {
		this.epc_Child_id = epc_Child_id;
	}
	public String getEp_Name() {
		return ep_Name;
	}
	public void setEp_Name(String ep_Name) {
		this.ep_Name = ep_Name;
	}
	public int getEpc_Id() {
		return epc_Id;
	}
	public void setEpc_Id(int epc_Id) {
		this.epc_Id = epc_Id;
	}
	public String getEpc_Name() {
		return epc_Name;
	}
	public void setEpc_Name(String epc_Name) {
		this.epc_Name = epc_Name;
	}
	public int getEpc_Parent_id() {
		return epc_Parent_id;
	}
	public void setEpc_Parent_id(int epc_Parent_id) {
		this.epc_Parent_id = epc_Parent_id;
	}
}
