package com.gyb.shop.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ��������ʵ���࣬�������ݿ���ϵ
 * @author disentice
 *
 */
@Entity//��ע����bean
@Table(name="category")//ӳ�����ݿ��е�category��
public class Category {
	
	/**
	 * ����ֵid\name
	 * ʹ��ע��ӳ�����ݿ��еı�
	 */
	@Id//��ע����
	@GeneratedValue(strategy = GenerationType.IDENTITY)//��������������ʽ���������ݿ�����
	private int id;
	
	private String name;
	
	//˲ʱ�ֶΣ�Ϊ��ǰ����ʾҳ��ķ����Ʒ�б�
    @Transient
	List<Product> products;
    //˲ʱ�ֶΣ�����ǰ�˲�Ʒ�б���ʾ�Ƽ���Ʒ����ŵ��ǲ�Ʒ������ʾ���ǲ�Ʒ��һЩ���ԣ�
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
