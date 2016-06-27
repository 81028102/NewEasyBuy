package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东 评价实体类
 */
@Entity
@Table(name="easybuy_assess")
@SuppressWarnings("serial")
public class easybuy_assess implements Serializable  {
	
	private int ea_Assessid;		//评价编号
	private String ea_Assess;		//评价内容
	private String ea_Create_time;	//评价时间
	private String ea_Nike_name; 	//评价姓名
	private int ea_Upid;			//上升主键编号
	
	public int getEa_Upid() {
		return ea_Upid;
	}
	public void setEa_Upid(int ea_Upid) {
		this.ea_Upid = ea_Upid;
	}
	public int getEa_Assessid() {
		return ea_Assessid;
	}
	public void setEa_Assessid(int ea_Assessid) {
		this.ea_Assessid = ea_Assessid;
	}
	public String getEa_Assess() {
		return ea_Assess;
	}
	public void setEa_Assess(String ea_Assess) {
		this.ea_Assess = ea_Assess;
	}
	public String getEa_Create_time() {
		return ea_Create_time;
	}
	public void setEa_Create_time(String ea_Create_time) {
		this.ea_Create_time = ea_Create_time;
	}
	public String getEa_Nike_name() {
		return ea_Nike_name;
	}
	public void setEa_Nike_name(String ea_Nike_name) {
		this.ea_Nike_name = ea_Nike_name;
	}
}
