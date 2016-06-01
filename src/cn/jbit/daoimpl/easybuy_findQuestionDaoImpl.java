package cn.jbit.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_findQuestionDao;
import cn.jbit.entity.easybuy_question;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_findQuestionDaoImpl extends BaseDao implements easybuy_findQuestionDao {

	/**
	 * 功能:根据easybuy_question获取所有问题
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<easybuy_question> findQuestion() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select distinct eu_question from easybuy_question";
		return this.getList(easybuy_question.class, sql);
	}
}
