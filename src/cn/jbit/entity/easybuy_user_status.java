package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东	用户状态权限表
 */
@Entity
@Table(name="easybuy_user_status")
@SuppressWarnings("serial")
public class easybuy_user_status implements Serializable{
	
	private int eus_Id;				//权限编号
	private String eus_StatusName;	//权限名称
	private String eus_Create_time;	//创建时间
	
	public String getEus_Create_time() {
		return eus_Create_time;
	}
	public void setEus_Create_time(String eus_Create_time) {
		this.eus_Create_time = eus_Create_time;
	}
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
