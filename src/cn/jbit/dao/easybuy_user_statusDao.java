package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_user_status;

/**
 * @author 任锯东  Easybuy_user_status(分层)Layering
 */
public interface easybuy_user_statusDao{
	public List<easybuy_user_status> getUser_Status(int cpage, int pageSize,String eus_StatusName);//模糊查询分页获取用户的所有状态名称
	public int getUser_StatusCount(String eus_StatusName);//获取状态的总数量
	public int delUser_StatusById(String eus_Id);//根据Id删除状态
	public int delUser_StatusAll();//删除全部状态
	public int addUser_Status(easybuy_user_status eus);//添加状态信息
	public easybuy_user_status getUser_StatusById(int eus_Id);//获取指定ID状态
	public int updateUser_Status(easybuy_user_status eus);//更新状态信息
	public boolean boolStatus(String eus_StatusName);//判断是否存在指定状态信息
}

