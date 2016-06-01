package cn.jbit.biz;

import java.util.List;
import cn.jbit.entity.easybuy_comment;

/**
 * @author 任锯东
 */
public interface easybuy_commentBiz {

	public int countcomments(String ec_nick_name);//留言总数量
	public List<easybuy_comment> getComments(int cpage, int pageSize,String ec_nick_name);//分页查询留言信息
	public easybuy_comment getComment(int ec_id);//根据Id获取单条留言信息
	public int addComment(easybuy_comment ec);//添加留言信息
	public int updateComment(easybuy_comment ec);//更新留言信息
	public int delComment(String ec_id);//根据Id删除留言信息
	public int delCommentsAll();//删除全部留言信息
}
