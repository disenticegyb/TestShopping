package com.gyb.shop.comparator;

import java.util.Comparator;

import com.gyb.shop.pojo.Product;

/**
 * ProductAllComparator 综合比较器销量*评价最高的；
 */
public class ProductAllComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount();
	}

}
