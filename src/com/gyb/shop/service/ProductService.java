package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Category;
import com.gyb.shop.pojo.Product;

/**
 * ������Ʒҵ���ӿڼ̳�BeanService�ӿڣ���ȡ�ӿ��еķ�����
 * @author disentice
 *
 */
public interface ProductService extends BaseService{
	
	//���ݶ����������Ʒ����
	public void fill(List<Category> categorys);
	//���ݷ�������Ʒ����
	public void fill(Category category);
	//���ݶ����������Ƽ���Ʒ����
	public void fillByRow(List<Category> categorys);
	//���ݲ�Ʒ��ѯ����������
	public void setSaleAndReviewNumber(Product product);
	//���ݲ�Ʒ���ϲ�ѯ����������
	public void setSaleAndReviewNumber(List<Product> products);	
	//�������������������ݹؼ��ֽ���ģ����ѯ
	public List<Product> search(String keyword, int start, int count);	
	
}
