package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_question;

/**
 * @author 任锯东
 */
public interface easybuy_findQuestionDao {

	public List<easybuy_question> findQuestion();//根据easybuy获取所有问题
}
