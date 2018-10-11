package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.pojo.Product;
import com.gyb.shop.pojo.ProductImage;

public interface ProductImageService extends BaseService {

	//设置两个常量single：单一的；detail：细节、详情；
	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";

	public void setFirstProdutImage(Product product);
	/*//增加查询方法，查询某种产品下某种类型的图片；
	public List<ProductImage> list(String key_product, Product product, String key_type, String type);

	//1. 查询出某个产品下的类型是type_single的图片集合
	//2. 如果这个集合不为空，那么取出其中的第一个图片，作为这个产品的图片：firstProdutImage.
	public void setFirstProdutImage(Product product);
*/
}

