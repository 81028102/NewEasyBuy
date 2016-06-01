package cn.jbit.biz;

import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
public interface easybuy_forgetBiz {

	public String getEasyBuyQuestion(String eu_user_id);//根据Id获取指定用户信息
	public String getEasyBuyQuestion1(String eu_user_id);//根据Id获取指定用户信息
	public String getEasyBuyQuestion2(String eu_user_id);//根据Id获取指定用户信息
	public String getEasyBuyAnswer(String eu_user_id, String eu_question,
			String eu_answer, String eu_question1, String eu_answer1, String eu_question2, String eu_answer2);//用于判断提示问题答案是否正确
	public int updateForget(easybuy_user eu);//根据问题答案修改用户密码
	public String getMobiles(String eu_mobile);//根据手机号及输入发送返回的验证码成功后再次查询手机号，根据手机号修改密码
	public String getMobile(String eu_user_id);//根据用户名Id获取手机号
	public int updateTelForget(easybuy_user eu);//根据手机号及输入发送返回的验证码修改用户密码
}
