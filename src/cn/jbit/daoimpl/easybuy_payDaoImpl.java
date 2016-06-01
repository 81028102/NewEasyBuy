package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_payDao;
import cn.jbit.entity.easybuy_pay;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_payDaoImpl extends BaseDao implements easybuy_payDao {

	/**
	 * 功能:查询银行卡余额
	 */
	@Override
	public String getEasyBuyPay(String eu_user_name, String paycardid,String paypwd) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select b.money from easybuy_user u,easybuy_bank b where u.payid=b.payid and u.eu_user_name=? and b.paycardid=? and paypwd=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_name);
			ps.setString(2, paycardid);
			ps.setString(3, paypwd);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的姓名和银行卡号不对应!";//表示输入的用户名和银行卡号不对应
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			result="您输入的姓名和银行卡号不对应!";//表示输入的用户名和银行卡号不对应
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}

	/**
	 * 功能:付款功能
	 */
	@Override
	public int getEasyBuyPayProduct(easybuy_pay epay) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_bank set money=? where paycardid=? and paypwd=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(epay.getMoney());
		list.add(epay.getPaycardid());
		list.add(epay.getPaypwd());
		return this.executeUpdata(sql,list);
	}

	/**
	 * 功能:修改数量
	 */
	@Override
	public int getEasyBuyPayCount(easybuy_product epdt) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_product set ep_stock=?,ep_sales=? where ep_price=? and ep_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(epdt.getEp_Stock());
		list.add(epdt.getEp_Sales());
		list.add(epdt.getEp_Price());
		list.add(epdt.getEp_Id());
		return this.executeUpdata(sql,list);
	}
}
