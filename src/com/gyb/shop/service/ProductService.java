package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Category;
import com.gyb.shop.pojo.Product;

/**
 * 创建产品业务层接口继承BeanService接口，获取接口中的方法；
 * @author disentice
 *
 */
public interface ProductService extends BaseService{
	
	//根据多个分类填充产品集合
	public void fill(List<Category> categorys);
	//根据分类填充产品集合
	public void fill(Category category);
	//根据多个分类填充推荐产品集合
	public void fillByRow(List<Category> categorys);
	//根据产品查询销量和评论
	public void setSaleAndReviewNumber(Product product);
	//根据产品集合查询销量和评论
	public void setSaleAndReviewNumber(List<Product> products);	
	//增加搜索方法——根据关键字进行模糊查询
	public List<Product> search(String keyword, int start, int count);	
	
}
