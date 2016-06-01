package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class City implements Serializable{
	
	//三级联动省市县
	private int id;          //编号
	private int pid;         //选择编号
	private String cityname; //城市名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public City(int id, int pid, String cityname) {
		super();
		this.id = id;
		this.pid = pid;
		this.cityname = cityname;
	}
	public City() {

	}
}