package cn.jbit.bizimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.dao.CityDao;
import cn.jbit.daoimpl.CityDaoImpl;
import cn.jbit.entity.City;

/**
 * @author 任锯东
 */
@Service
public class CityBizImpl implements CityDao {
	
	@Autowired
	CityDao cd=new CityDaoImpl();

	@Override
	public List<City> getCity(String pid) {
		// TODO Auto-generated method stub
		return cd.getCity(pid);
	}
}
