package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东  新闻实体类
 */
@SuppressWarnings("serial")
public class easybuy_news implements Serializable {

	private int en_Id;				//新闻编号
	private String en_Title;		//新闻标题
	private String en_Content;		//新闻内容
	private String en_Create_time;	//新闻录入日期
	private int en_Click_Count;     //新闻点击率(数量)
	private int enc_Id;				//新闻分类编号
	private String enc_Name;		//新闻分类名称
	private int ent_Id;				//新闻栏目编号
	private String ent_Name;		//新闻栏目名称

	public int getEnc_Id() {
		return enc_Id;
	}
	public void setEnc_Id(int enc_Id) {
		this.enc_Id = enc_Id;
	}
	public String getEnc_Name() {
		return enc_Name;
	}
	public void setEnc_Name(String enc_Name) {
		this.enc_Name = enc_Name;
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
	public int getEn_Click_Count() {
		return en_Click_Count;
	}
	public void setEn_Click_Count(int en_Click_Count) {
		this.en_Click_Count = en_Click_Count;
	}
	public int getEn_Id() {
		return en_Id;
	}
	public void setEn_Id(int en_Id) {
		this.en_Id = en_Id;
	}
	public String getEn_Title() {
		return en_Title;
	}
	public void setEn_Title(String en_Title) {
		this.en_Title = en_Title;
	}
	public String getEn_Content() {
		return en_Content;
	}
	public void setEn_Content(String en_Content) {
		this.en_Content = en_Content;
	}
	public String getEn_Create_time() {
		return en_Create_time;
	}
	public void setEn_Create_time(String en_Create_time) {
		this.en_Create_time = en_Create_time;
	}
}
