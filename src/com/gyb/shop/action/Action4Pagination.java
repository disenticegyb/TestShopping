package com.gyb.shop.action;

import com.gyb.shop.util.Page;

/**
 * ����Action4Pagination�����ҳ
 * �̳�upload��ʵ�ֶ�ͼƬ�ϴ��Ĵ���
 * @author disentice
 *
 */
public class Action4Pagination extends Action4Upload{

    protected Page page;
    public Page getPage() {
        return page;
    }
 
    public void setPage(Page page) {
        this.page = page;
    }
}
