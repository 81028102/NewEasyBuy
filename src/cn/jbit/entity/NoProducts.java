package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东 商品实体类
 */
@Entity
@Table(name="NoProducts")
@SuppressWarnings("serial")
public class NoProducts implements Serializable {
	
	private int eod_id;			 // 编号
	private int ep_id; 			 // 商品id
	private String ep_name; 	 // 商品名字

	public String getEp_name() {
		return ep_name;
	}

	public void setEp_name(String ep_name) {
		this.ep_name = ep_name;
	}

	public int getEod_id() {
		return eod_id;
	}

	public void setEod_id(int eod_id) {
		this.eod_id = eod_id;
	}

	public int getEp_id() {
		return ep_id;
	}

	public void setEp_id(int ep_id) {
		this.ep_id = ep_id;
	}
}