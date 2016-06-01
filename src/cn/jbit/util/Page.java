package cn.jbit.util;

/**
 * @author 任锯东  实现分页
 */
public class Page {

	//总页数
	private int totalPageCount=1;
	//页面大小
	private int pageSize=0;
	//记录总数
	private int totalCount=0;
	//当前页码
	private int currPageNo=1;
	public int getTotalPageCount() {
		if(totalPageCount==0)
			return 0;
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize>0)
			this.pageSize = pageSize;
	}
	public int getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		if(this.currPageNo>0)
			this.currPageNo = currPageNo;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount>0){
			this.totalCount = totalCount;
			//计算总页数
			totalPageCount=this.totalCount%pageSize==0?(this.totalCount/pageSize):this.totalCount/pageSize+1;
		}
	}
}
