package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东  新闻栏目类
 */
@Entity
@Table(name="easybuy_news_column")
@SuppressWarnings("serial")
public class easybuy_news_column implements Serializable{

	private int enc_Id;				//新闻栏目编号
	private String enc_Name;		//新闻栏目名称
	private String enc_Create_time;	//栏目创建时间
	
	public String getEnc_Create_time() {
		return enc_Create_time;
	}
	public void setEnc_Create_time(String enc_Create_time) {
		this.enc_Create_time = enc_Create_time;
	}
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
}

