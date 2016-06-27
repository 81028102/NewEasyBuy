package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东 商品实体类
 */
@Entity
@Table(name="easybuy_products")
@SuppressWarnings("serial")
public class easybuy_products extends easybuy_product implements Serializable{
	
	private int epc_id; 			//分类编号
	private String epc_name; 		//商品分类名称
	private int epc_parent_id;		//父级编号

	public int getEpc_id() {
		return epc_id;
	}

	public void setEpc_id(int epc_id) {
		this.epc_id = epc_id;
	}

	public String getEpc_name() {
		return epc_name;
	}

	public void setEpc_name(String epc_name) {
		this.epc_name = epc_name;
	}

	public int getEpc_parent_id() {
		return epc_parent_id;
	}

	public void setEpc_parent_id(int epc_parent_id) {
		this.epc_parent_id = epc_parent_id;
	}
}
