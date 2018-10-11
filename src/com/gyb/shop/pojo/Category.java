package com.gyb.shop.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 创建类型实体类，并和数据库联系
 * @author disentice
 *
 */
@Entity//标注数以bean
@Table(name="category")//映射数据库中的category表
public class Category {
	
	/**
	 * 属性值id\name
	 * 使用注解映射数据库中的表；
	 */
	@Id//标注主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置主键增长方式，根据数据库自增
	private int id;
	
	private String name;
	
	//瞬时字段，为了前端显示页面的分类产品列表
    @Transient
	List<Product> products;
    //瞬时字段，根据前端产品列表显示推荐产品；存放的是产品，但显示的是产品的一些特性；
    @Transient
	List<List<Product>> productsByRow;    
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<List<Product>> getProductsByRow() {
		return productsByRow;
	}
	public void setProductsByRow(List<List<Product>> productsByRow) {
		this.productsByRow = productsByRow;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products + ", productsByRow=" + productsByRow
				+ "]";
	}
	
	
}
