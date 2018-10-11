package com.gyb.shop.util;
/**
 * ��ҳ
 * @author disentice
 *
 */
public class Page {
	private int start;//��ʼҳ��
	private int count;//ÿҳ��ʾ����
	private int total;//�ܸ���
	private String param;//����
	
	private static final int defaultCount = 5;//Ĭ��ÿҳ��ʾ5����
	
	//�ж��Ƿ���ǰһҳ
	public boolean isHasPreviouse() {
		if (start==0) {
			return false;
		}
		return true;
	}
	//�ж��Ƿ������һҳ
	public boolean isHasNext() {
		if (start==getLast()) {
			return false;
		}
		return true;
	}

	public int getLast() {
		int last;
		//����50/5����Ϊ�㣬�����һҳ����45������Ϊ�㣻
		if (0 == total % count) {
			last = total-count;
		}else {
			last = total - total % count;//�����������򣺼�ȥ������
		}
		return last;
	}
	//���ƶ���ҳ
	public int getTotalPage() {
		int totalPage;
		if (0==total % count) {//��������ҳ��+1��
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
