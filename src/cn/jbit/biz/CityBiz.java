package cn.jbit.biz;

import java.util.List;
import cn.jbit.entity.City;

/**
 * @author 任锯东
 */
public interface CityBiz {

	public List<City> getCity(String pid);//根据id查询下拉 三级联动
}
