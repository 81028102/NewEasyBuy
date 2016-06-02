package cn.jbit.entity;

import java.io.Serializable;

/**
 * @author 任锯东 用户实体类 存放用户基本信息
 */
@SuppressWarnings("serial")
public class easybuy_user implements Serializable{
	
	private int eu_Id;					//用户编号
	private String eu_User_id;			//用户名
	private String eu_User_name;		//用户真实姓名
	private String eu_Password;			//密码
	private String eu_Sex;				//性别
	private String eu_Birthday;			//出生日期
	private String eu_Identity_code;	//身份证号
	private String eu_Email;			//Email邮箱
	private String eu_Mobile;			//手机
	private String eu_Address;			//收货地址
	private int eu_Login;				//是否登录
	private int eu_Status;				//用户类型
	private String eu_question;         //密码提示问题
	private String eu_answer;           //提示问题答案
	private String eu_question1;        //密码提示问题1
	private String eu_answer1;          //提示问题答案1
	private String eu_question2;        //密码提示问题2
	private String eu_answer2;          //提示问题答案2
	private int payid; 					//银行卡编号
	private String eu_Create_time;      //创建用户时间
	private float eu_Cost;				//总消费
	private float eu_Score;				//总消费积分
	private String eu_File_name;		//用户头像
	
	public String getEu_File_name() {
		return eu_File_name;
	}
	public void setEu_File_name(String eu_File_name) {
		this.eu_File_name = eu_File_name;
	}
	public float getEu_Cost() {
		return eu_Cost;
	}
	public void setEu_Cost(float eu_Cost) {
		this.eu_Cost = eu_Cost;
	}
	public float getEu_Score() {
		return eu_Score;
	}
	public void setEu_Score(float eu_Score) {
		this.eu_Score = eu_Score;
	}
	//新增字段
	private String eo_User_id;			//消费用户名
	private float eo_Cost;				//订单金额
	private float eo_Score;				//消费积分
	public float getEo_Score() {
		return eo_Score;
	}
	public void setEo_Score(float eo_Score) {
		this.eo_Score = eo_Score;
	}
	public String getEo_User_id() {
		return eo_User_id;
	}
	public void setEo_User_id(String eo_User_id) {
		this.eo_User_id = eo_User_id;
	}
	public float getEo_Cost() {
		return eo_Cost;
	}
	public void setEo_Cost(float eo_Cost) {
		this.eo_Cost = eo_Cost;
	}
	public String getEu_Create_time() {
		return eu_Create_time;
	}
	public void setEu_Create_time(String eu_Create_time) {
		this.eu_Create_time = eu_Create_time;
	}
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	public String getEu_question1() {
		return eu_question1;
	}
	public void setEu_question1(String eu_question1) {
		this.eu_question1 = eu_question1;
	}
	public String getEu_answer1() {
		return eu_answer1;
	}
	public void setEu_answer1(String eu_answer1) {
		this.eu_answer1 = eu_answer1;
	}
	public String getEu_question2() {
		return eu_question2;
	}
	public void setEu_question2(String eu_question2) {
		this.eu_question2 = eu_question2;
	}
	public String getEu_answer2() {
		return eu_answer2;
	}
	public void setEu_answer2(String eu_answer2) {
		this.eu_answer2 = eu_answer2;
	}
	public String getEu_question() {
		return eu_question;
	}
	public void setEu_question(String eu_question) {
		this.eu_question = eu_question;
	}
	public String getEu_answer() {
		return eu_answer;
	}
	public void setEu_answer(String eu_answer) {
		this.eu_answer = eu_answer;
	}
	public int getEu_Id() {
		return eu_Id;
	}
	public void setEu_Id(int eu_Id) {
		this.eu_Id = eu_Id;
	}
	public String getEu_User_id() {
		return eu_User_id;
	}
	public void setEu_User_id(String eu_User_id) {
		this.eu_User_id = eu_User_id;
	}
	public String getEu_User_name() {
		return eu_User_name;
	}
	public void setEu_User_name(String eu_User_name) {
		this.eu_User_name = eu_User_name;
	}
	public String getEu_Password() {
		return eu_Password;
	}
	public void setEu_Password(String eu_Password) {
		this.eu_Password = eu_Password;
	}
	public String getEu_Sex() {
		return eu_Sex;
	}
	public void setEu_Sex(String eu_Sex) {
		this.eu_Sex = eu_Sex;
	}
	public String getEu_Birthday() {
		return eu_Birthday;
	}
	public void setEu_Birthday(String eu_Birthday) {
		this.eu_Birthday = eu_Birthday;
	}
	public String getEu_Identity_code() {
		return eu_Identity_code;
	}
	public void setEu_Identity_code(String eu_Identity_code) {
		this.eu_Identity_code = eu_Identity_code;
	}
	public String getEu_Email() {
		return eu_Email;
	}
	public void setEu_Email(String eu_Email) {
		this.eu_Email = eu_Email;
	}
	public String getEu_Mobile() {
		return eu_Mobile;
	}
	public void setEu_Mobile(String eu_Mobile) {
		this.eu_Mobile = eu_Mobile;
	}
	public String getEu_Address() {
		return eu_Address;
	}
	public void setEu_Address(String eu_Address) {
		this.eu_Address = eu_Address;
	}
	public int getEu_Login() {
		return eu_Login;
	}
	public void setEu_Login(int eu_Login) {
		this.eu_Login = eu_Login;
	}
	public int getEu_Status() {
		return eu_Status;
	}
	public void setEu_Status(int eu_Status) {
		this.eu_Status = eu_Status;
	}
}
