package cn.jbit.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.bizimpl.CityBizImpl;
import cn.jbit.entity.City;
import cn.jbit.util.JsonUtil;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/cityServlet")
public class cityServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CityBizImpl	cb = new CityBizImpl();
		//获取数据
		List<City> cList=cb.getCity(request.getParameter("pid"));
		String json = JsonUtil.toJson(cList);
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	}
}