package com.gyb.shop.comparator;
/**
 * 根据销量进行排序，销量高的在前
 * @author disentice
 *
 */

import java.util.Comparator;

import com.gyb.shop.pojo.Product;


public class ProductSaleCountComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getSaleCount()-p1.getSaleCount();
	}
}
