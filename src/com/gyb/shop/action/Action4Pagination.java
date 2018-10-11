package com.gyb.shop.action;

import com.gyb.shop.util.Page;

/**
 * 创建Action4Pagination负责分页
 * 继承upload，实现对图片上传的处理；
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
