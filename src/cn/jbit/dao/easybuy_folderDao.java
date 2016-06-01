package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_folder;
/**
 * @author 任锯东
 */
public interface easybuy_folderDao {

	public int save(easybuy_folder folder);//新建文件夹 
	public List<easybuy_folder> querylist(int cpage, int pageSize,String fname);//分页查询文档列表
	public easybuy_folder querybyid(int fid);//根据id查询
	public int update(easybuy_folder folder);//修改文件夹
	public int upd(easybuy_folder folder);//删除（(修改)加入回收站） 还原（从回收站还原）	
	public List<easybuy_folder> queryrecycle(int cpage, int pageSize,String fname);//分页查询回收站文档列表
	public int dele(int fid);//根据Id删除	
	public int getFolderCount(int fdele,String fname);//获取文件总数
	public boolean boolfolder(String fname);//判断是否存在指定文档信息
}
