package cn.jbit.entity;

import java.io.Serializable;
/**
 * @author 任锯东 文件夹类
 */
@SuppressWarnings("serial")
public class easybuy_folder implements Serializable{

	private int fid;  			//文件夹编号
	private String fname;  		//文件名
	private String fusername;	//创建人
	private String fremark;  	//备注
	private String ftime;  		//创建时间
	private int fdele; 			//状态(是否删除或存在)
	private String fpath;  		//存储路径
	private String fuptime;  	//删除时间
	
	/*
	 * 新增字段
	 */
	private String oldname;  	//老文件夹名字
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public int getFdele() {
		return fdele;
	}
	public void setFdele(int fdele) {
		this.fdele = fdele;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFuptime() {
		return fuptime;
	}
	public void setFuptime(String fuptime) {
		this.fuptime = fuptime;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
}