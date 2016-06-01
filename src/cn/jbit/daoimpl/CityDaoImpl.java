package cn.jbit.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.CityDao;
import cn.jbit.entity.City;

/**
 * @author 任锯东
 */
@Repository
public class CityDaoImpl extends BaseDao implements CityDao {

	/**
	 * 功能:实现三级联动 --查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCity(String pid) {
		//创建一个List集合将City表放进List集合中
		//sql语句根据pid查询City表并且根据id升序排列
		String sql="select id,pid,cityname from city where pid ="+pid+" order by id";
		return this.getList(City.class, sql);
	}
}
