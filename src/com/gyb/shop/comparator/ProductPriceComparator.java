package com.gyb.shop.comparator;

import java.util.Comparator;

import com.gyb.shop.pojo.Product;

/**
 * ���ݼ۸�������򣬼۸����ǰ
 * @author disentice
 *
 */
public class ProductPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return (int) (p1.getPromotePrice()-p2.getPromotePrice());
	}

}
