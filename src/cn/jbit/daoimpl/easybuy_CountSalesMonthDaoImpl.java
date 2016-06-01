package cn.jbit.daoimpl;

import java.util.Date;
import java.util.List;
/*import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;*/
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.easybuy_CountSalesMonthDao;
import cn.jbit.entity.easybuy_product;
import cn.jbit.util.DateUtil;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_CountSalesMonthDaoImpl extends HibernateDaoSupport implements easybuy_CountSalesMonthDao{

	/**
	 * 功能:任务调度查看数据
	 */
	public List<easybuy_product> countProduct(){

		/*return super.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session){
						StringBuffer sql = new StringBuffer();
						sql.append("select ep_name,ep_price,ep_stock,ep_address,ep_create_time,ep_sales ");
						sql.append("from easybuy_product ");
						sql.append("order by ep_sales desc");
						Query q = session.createQuery(sql.toString());
						return q.list();
					}
				});*/
		Date today = new Date();
		StringBuffer sql = new StringBuffer();
		sql.append("select ep_name,ep_price,ep_stock,ep_address,ep_sales ");
		sql.append("from easybuy_product ");
		sql.append("order by ep_sales desc " );

		String[] paramNames = new String[]{"today","firstDate"};

		Date[] values = new Date[]{today,DateUtil.getDateAfter(today, -7)};
		@SuppressWarnings("unchecked")
		List<easybuy_product> list = this.getHibernateTemplate().findByNamedParam(sql.toString(), paramNames, values);
		return list;
	}
}
