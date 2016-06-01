package cn.jbit.bizimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_forgetBiz;
import cn.jbit.dao.easybuy_forgetDao;
import cn.jbit.daoimpl.easybuy_forgetDaoImpl;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@Service
public class easybuy_forgetBizImpl implements easybuy_forgetBiz {
	
	@Autowired
	easybuy_forgetDao efd=new easybuy_forgetDaoImpl();
	
	@Override
	public String getEasyBuyQuestion(String eu_user_id) {
		// TODO Auto-generated method stub
		return efd.getEasyBuyQuestion(eu_user_id);
	}
	@Override
	public String getEasyBuyQuestion1(String eu_user_id){
		// TODO Auto-generated method stub
		return efd.getEasyBuyQuestion1(eu_user_id);
	}
	@Override
	public String getEasyBuyQuestion2(String eu_user_id){
		// TODO Auto-generated method stub
		return efd.getEasyBuyQuestion2(eu_user_id);
	}
	@Override
	public String getEasyBuyAnswer(String eu_user_id, String eu_question,
			String eu_answer,String eu_question1,
			String eu_answer1,String eu_question2,
			String eu_answer2) {
		// TODO Auto-generated method stub
		return efd.getEasyBuyAnswer(eu_user_id, eu_question, eu_answer, eu_question1, eu_answer1, eu_question2, eu_answer2);
	}

	@Override
	public int updateForget(easybuy_user eu) {
		// TODO Auto-generated method stub
		return efd.updateForget(eu);
	}
	
	@Override
	public String getMobile(String eu_user_id) {
		// TODO Auto-generated method stub
		return efd.getMobile(eu_user_id);
	}
	@Override
	public int updateTelForget(easybuy_user eu){
		// TODO Auto-generated method stub
		return efd.updateTelForget(eu);
	}
	@Override
	public String getMobiles(String eu_mobile) {
		// TODO Auto-generated method stub
		return efd.getMobiles(eu_mobile);
	}
}
