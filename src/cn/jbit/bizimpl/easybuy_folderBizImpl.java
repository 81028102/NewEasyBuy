package cn.jbit.bizimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_folderBiz;
import cn.jbit.dao.easybuy_folderDao;
import cn.jbit.daoimpl.eaybuy_folderDaoImpl;
import cn.jbit.entity.easybuy_folder;

/**
 * @author 任锯东
 */
@Service
public class easybuy_folderBizImpl implements easybuy_folderBiz {
	
	easybuy_folderDao fd=new eaybuy_folderDaoImpl();

	@Override
	public int save(easybuy_folder folder) {
		// TODO Auto-generated method stub
		return fd.save(folder);
	}
	@Override
	public easybuy_folder querybyid(int fid) {
		// TODO Auto-generated method stub
		return fd.querybyid(fid);
	}
	@Override
	public int update(easybuy_folder folder) {
		// TODO Auto-generated method stub
		return fd.update(folder);
	}
	@Override
	public int upd(easybuy_folder folder) {
		// TODO Auto-generated method stub
		return fd.upd(folder);
	}
	@Override
	public int dele(int fid) {
		// TODO Auto-generated method stub
		return fd.dele(fid);
	}
	@Override
	public List<easybuy_folder> querylist(int cpage, int pageSize,String fname) {
		// TODO Auto-generated method stub
		return fd.querylist(cpage,pageSize,fname);
	}
	@Override
	public List<easybuy_folder> queryrecycle(int cpage, int pageSize,String fname) {
		// TODO Auto-generated method stub
		return fd.queryrecycle(cpage,pageSize,fname);
	}
	@Override
	public int getFolderCount(int fdele,String fname) {
		// TODO Auto-generated method stub
		return fd.getFolderCount(fdele,fname);
	}
	@Override
	public boolean boolfolder(String fname) {
		// TODO Auto-generated method stub
		return fd.boolfolder(fname);
	}
}