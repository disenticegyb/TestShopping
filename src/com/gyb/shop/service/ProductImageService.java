package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Product;
import com.gyb.shop.pojo.ProductImage;

public interface ProductImageService extends BaseService {

	//������������single����һ�ģ�detail��ϸ�ڡ����飻
	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";

	public void setFirstProdutImage(Product product);
	/*//���Ӳ�ѯ��������ѯĳ�ֲ�Ʒ��ĳ�����͵�ͼƬ��
	public List<ProductImage> list(String key_product, Product product, String key_type, String type);

	//1. ��ѯ��ĳ����Ʒ�µ�������type_single��ͼƬ����
	//2. ���������ϲ�Ϊ�գ���ôȡ�����еĵ�һ��ͼƬ����Ϊ�����Ʒ��ͼƬ��firstProdutImage.
	public void setFirstProdutImage(Product product);
*/
}

