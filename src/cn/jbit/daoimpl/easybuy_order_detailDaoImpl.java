package cn.jbit.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_order_detailDao;
import cn.jbit.entity.easybuy_order_detail;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_order_detailDaoImpl extends BaseDao implements easybuy_order_detailDao {

	/**
	 * 功能:添加订单信息
	 */
	@Override
	public int adddetail(easybuy_order_detail eoo) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="insert easybuy_order_detail values((select MAX(eo_id) from easybuy_order),?,?,?)";
		//创建一个List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(eoo.getEp_Id());
		list.add(eoo.getEod_Quantity());
		list.add(eoo.getEod_Cost());
		return this.executeUpdata(sql, list);
	}

	/**
	 * 功能:根据id删除订单详情
	 */
	@Override
	public int delOrderDetailById(int eo_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_order_detail where eo_id="+eo_id+"";
		return this.executeSql(sql);
	}
}
