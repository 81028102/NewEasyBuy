package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.dao.easybuy_userDao;
import cn.jbit.daoimpl.easybuy_userDaoImpl;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@Service
public class easybuy_userBizImpl implements easybuy_userBiz {
	
	@Autowired
	easybuy_userDao eud=new easybuy_userDaoImpl();

	@Override
	public boolean getUser(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getUser(eu_user_id);
	}

	@Override
	public int addUser(easybuy_user eu) {
		// TODO Auto-generated method stub
		return eud.addUser(eu);
	}

	@Override
	public List<easybuy_user> getAllUser(int cpage, int pageSize,String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getAllUser(cpage, pageSize,eu_user_id);
	}

	@Override
	public easybuy_user getEasyBuyUser(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getEasyBuyUser(eu_user_id);
	}
	@Override
	public int delUser(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.delUser(eu_user_id);
	}
	@Override
	public int updateUser(easybuy_user eu) {
		// TODO Auto-generated method stub
		return eud.updateUser(eu);
	}

	@Override
	public boolean login(String eu_user_id, String eu_password) {
		// TODO Auto-generated method stub
		return eud.login(eu_user_id, eu_password);
	}

	@Override
	public int getUserCount(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getUserCount(eu_user_id);
	}

	@Override
	public List<easybuy_user> getMyInfo(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getMyInfo(eu_user_id);
	}

	@Override
	public int getConsumeCount(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getConsumeCount(eu_user_id);
	}

	@Override
	public List<easybuy_user> getConsumeAllUser(int cpage, int pageSize,String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getConsumeAllUser(cpage,pageSize,eu_user_id);
	}

	@Override
	public easybuy_user getSelectMore(String eu_user_id) {
		// TODO Auto-generated method stub
		return eud.getSelectMore(eu_user_id);
	}

	@Override
	public int SelectMores(easybuy_user eu) {
		// TODO Auto-generated method stub
		return eud.SelectMores(eu);
	}
}
