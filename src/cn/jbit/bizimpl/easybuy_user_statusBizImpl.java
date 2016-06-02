package cn.jbit.bizimpl;

import java.util.List;
import cn.jbit.biz.easybuy_user_statusBiz;
import cn.jbit.dao.easybuy_user_statusDao;
import cn.jbit.daoimpl.easybuy_user_statusDaoImpl;
import cn.jbit.entity.easybuy_user_status;

/**
 * @author 任锯东  Easybuy_user_status(分层)Layering
 */
public class easybuy_user_statusBizImpl implements easybuy_user_statusBiz{

	easybuy_user_statusDao eusd=new easybuy_user_statusDaoImpl();
	
	@Override
	public List<easybuy_user_status> getUser_Status(int cpage, int pageSize,
			String eus_StatusName) {
		// TODO Auto-generated method stub
		return eusd.getUser_Status(cpage, pageSize, eus_StatusName);
	}

	@Override
	public int getUser_StatusCount(String eus_StatusName) {
		// TODO Auto-generated method stub
		return eusd.getUser_StatusCount(eus_StatusName);
	}

	@Override
	public int delUser_StatusById(String eus_Id) {
		// TODO Auto-generated method stub
		return eusd.delUser_StatusById(eus_Id);
	}

	@Override
	public int delUser_StatusAll() {
		// TODO Auto-generated method stub
		return eusd.delUser_StatusAll();
	}

	@Override
	public int addUser_Status(easybuy_user_status eus) {
		// TODO Auto-generated method stub
		return eusd.addUser_Status(eus);
	}

	@Override
	public easybuy_user_status getUser_StatusById(int eus_Id) {
		// TODO Auto-generated method stub
		return eusd.getUser_StatusById(eus_Id);
	}

	@Override
	public int updateUser_Status(easybuy_user_status eus) {
		// TODO Auto-generated method stub
		return eusd.updateUser_Status(eus);
	}

	@Override
	public boolean boolStatus(String eus_StatusName) {
		// TODO Auto-generated method stub
		return eusd.boolStatus(eus_StatusName);
	}
}

