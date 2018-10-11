package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.util.Page;

/**
 * 对CRUD功能的抽象
 * @author disentice
 *
 */
public interface BaseService {
	//增加对象
	public Integer save(Object object);
	//更新对象
	public void update(Object object);
	//删除对象
	public void delete(Object object);
	//通过类名和id获取
	public Object get(Class clazz,int id);
	//通过id直接获取
	public Object get(int id);
	//查询返回返回集合
	public List list();
	//分页查询返回集合
	public List listByPage(Page page);
	//获取总页数
	public int total();
	
	//根据分类对象查询所有的属性；
	public List listByParent(Object o);
	 //根据父类分页查询子类的对象——页面显示
	public List list(Page page,Object parent);
	 //根据父类查询属性的数量
	public int total(Object parentObject);
	//增加可变参数的查询方法；
	public List list(Object ...pairParms);
}
