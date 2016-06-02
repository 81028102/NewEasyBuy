package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class easybuy_user_status implements Serializable{
	
	private int eus_Id;				//权限编号
	private String eus_StatusName;	//权限名称
	
	public int getEus_Id() {
		return eus_Id;
	}
	public void setEus_Id(int eus_Id) {
		this.eus_Id = eus_Id;
	}
	public String getEus_StatusName() {
		return eus_StatusName;
	}
	public void setEus_StatusName(String eus_StatusName) {
		this.eus_StatusName = eus_StatusName;
	}

}
