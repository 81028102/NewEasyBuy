package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东   订单状态类(待审核,审核通过,配货,发货,收货确认)
 */
@SuppressWarnings("serial")
public class easybuy_order_status implements Serializable{
	
	private int es_Id;				//编号
	private String es_StatusName;	//订单状态
	
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

}
