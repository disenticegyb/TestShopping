package com.gyb.shop.util;
/**
 * 分页
 * @author disentice
 *
 */
public class Page {
	private int start;//开始页数
	private int count;//每页显示个数
	private int total;//总个数
	private String param;//参数
	
	private static final int defaultCount = 5;//默认每页显示5条；
	
	//判断是否还有前一页
	public boolean isHasPreviouse() {
		if (start==0) {
			return false;
		}
		return true;
	}
	//判断是否还有最后一页
	public boolean isHasNext() {
		if (start==getLast()) {
			return false;
		}
		return true;
	}

	public int getLast() {
		int last;
		//例：50/5余数为零，则最后一页就是45，基数为零；
		if (0 == total % count) {
			last = total-count;
		}else {
			last = total - total % count;//若不能整除则：减去余数；
		}
		return last;
	}
	//共计多少页
	public int getTotalPage() {
		int totalPage;
		if (0==total % count) {//不能整除页数+1；
			totalPage = total/count;
		}else {
			totalPage = total/count+1;
		}
		return totalPage;
	}
	
	public Page(int start, int count) {
		super();
		this.start = start;
		this.count = count;
	}

	public Page() {
		super();
		count = defaultCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", param=" + param
				+ ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()=" + isHasNext() + ", getLast()=" + getLast()
				+ ", getTotalPage()=" + getTotalPage() + ", getStart()=" + getStart() + ", getCount()=" + getCount()
				+ ", getTotal()=" + getTotal() + ", getParam()=" + getParam() + "]";
	}
}
