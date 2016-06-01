package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东   留言实体类
 */
@SuppressWarnings("serial")
public class easybuy_comment implements Serializable{
	
	private int ec_Id;				//留言编号
	private String ec_Content;		//发表的留言内容
	private String ec_Create_time;	//留言创建时间
	private String ec_Reply;		//针对留言的回复
	private String ec_Reply_time;	//留言回复时间
	private String ec_Nick_name;	//留言用户昵称
	
	public int getEc_Id() {
		return ec_Id;
	}
	public void setEc_Id(int ec_Id) {
		this.ec_Id = ec_Id;
	}
	public String getEc_Content() {
		return ec_Content;
	}
	public void setEc_Content(String ec_Content) {
		this.ec_Content = ec_Content;
	}
	public String getEc_Create_time() {
		return ec_Create_time;
	}
	public void setEc_Create_time(String ec_Create_time) {
		this.ec_Create_time = ec_Create_time;
	}
	public String getEc_Reply() {
		return ec_Reply;
	}
	public void setEc_Reply(String ec_Reply) {
		this.ec_Reply = ec_Reply;
	}
	public String getEc_Reply_time() {
		return ec_Reply_time;
	}
	public void setEc_Reply_time(String ec_Reply_time) {
		this.ec_Reply_time = ec_Reply_time;
	}
	public String getEc_Nick_name() {
		return ec_Nick_name;
	}
	public void setEc_Nick_name(String ec_Nick_name) {
		this.ec_Nick_name = ec_Nick_name;
	}
}