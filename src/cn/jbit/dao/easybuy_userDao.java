package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
public interface easybuy_userDao {

	public int getUserCount(String eu_user_id);//根据Id获取用户总数
	public boolean getUser(String eu_user_id);//根据Id判断用户是否存在
	public int addUser(easybuy_user eu);//用户注册
	public  List<easybuy_user> getAllUser(int cpage, int pageSize,String eu_user_id);//分页获取所有用户信息并显示
	public  List<easybuy_user> getMyInfo(String eu_user_id);//根据Id获取本人用户信息
	public easybuy_user getEasyBuyUser(String eu_user_id);//根据Id获取指定用户信息
	public int delUser(String eu_user_id);//根据Id删除用户
	public int updateUser(easybuy_user eu);//根据Id修改用户信息
	public boolean login(String eu_user_id, String eu_password);//用户登录
	public int getConsumeCount(String eu_user_id);//获取用户消费总数
	public  List<easybuy_user> getConsumeAllUser(int cpage, int pageSize,String eu_user_id);//分页获取用户消费信息并显示
	public easybuy_user getSelectMore(String eu_user_id);//根据Id查看更多信息
	public int SelectMores(easybuy_user eu);//根据Id修改用户信息
}