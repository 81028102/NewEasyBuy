package cn.jbit.bizimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_commentBiz;
import cn.jbit.dao.easybuy_commentDao;
import cn.jbit.daoimpl.easybuy_commentDaoImpl;
import cn.jbit.entity.easybuy_comment;

/**
 * @author 任锯东
 */
@Service
public class easybuy_commentBizImpl implements easybuy_commentBiz {
	
	@Autowired
	easybuy_commentDao ecd=new easybuy_commentDaoImpl();

	@Override
	public List<easybuy_comment> getComments(int cpage, int pageSize,String ec_nick_name) {
		// TODO Auto-generated method stub
		return ecd.getComments(cpage, pageSize,ec_nick_name);
	}

	@Override
	public easybuy_comment getComment(int ec_id) {
		// TODO Auto-generated method stub
		return ecd.getComment(ec_id);
	}

	@Override
	public int addComment(easybuy_comment ec) {
		// TODO Auto-generated method stub
		return ecd.addComment(ec);
	}

	@Override
	public int updateComment(easybuy_comment ec) {
		// TODO Auto-generated method stub
		return ecd.updateComment(ec);
	}

	@Override
	public int delComment(String ec_id) {
		// TODO Auto-generated method stub
		return ecd.delComment(ec_id);
	}

	@Override
	public int countcomments(String ec_nick_name) {
		// TODO Auto-generated method stub
		return ecd.countcomments(ec_nick_name);
	}

	@Override
	public int delCommentsAll() {
		// TODO Auto-generated method stub
		return ecd.delCommentsAll();
	}
}
