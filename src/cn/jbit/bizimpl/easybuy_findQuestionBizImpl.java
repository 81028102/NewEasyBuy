package cn.jbit.bizimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_findQuestionBiz;
import cn.jbit.dao.easybuy_findQuestionDao;
import cn.jbit.daoimpl.easybuy_findQuestionDaoImpl;
import cn.jbit.entity.easybuy_question;

/**
 * @author 任锯东
 */
@Service
public class easybuy_findQuestionBizImpl implements easybuy_findQuestionBiz {

	@Autowired
	easybuy_findQuestionDao fqd=new easybuy_findQuestionDaoImpl();
	
	@Override
	public List<easybuy_question> findQuestion() {
		// TODO Auto-generated method stub
		return fqd.findQuestion();
	}
}
