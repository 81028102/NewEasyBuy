package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东  新闻分类类
 */
@SuppressWarnings("serial")
public class easybuy_news_type implements Serializable{

	private int ent_Id;				//新闻分类编号
	private String ent_Name;		//新闻分类名称
	private String ent_Create_time;	//分类创建时间
	
	public String getEnt_Create_time() {
		return ent_Create_time;
	}
	public void setEnt_Create_time(String ent_Create_time) {
		this.ent_Create_time = ent_Create_time;
	}
	public int getEnt_Id() {
		return ent_Id;
	}
	public void setEnt_Id(int ent_Id) {
		this.ent_Id = ent_Id;
	}
	public String getEnt_Name() {
		return ent_Name;
	}
	public void setEnt_Name(String ent_Name) {
		this.ent_Name = ent_Name;
	}
}

