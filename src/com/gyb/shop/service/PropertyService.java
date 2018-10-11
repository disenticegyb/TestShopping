package com.gyb.shop.service;

/**
 * 创建属性接口标准
 * 属性的查询可以根据分类查询根据分类下的所有属性——点击显示
 * public List listByParent(Object o);
 * 根据父类分页查询子类的对象——页面显示
 * public List(Page page,Object parent);
 * 根据父类查询属性的数量
 * int total(Object parentObject)
 * 及在BaseService要增加其方法；
 * @author disentice
 *
 */
public interface PropertyService extends BaseService{
}
