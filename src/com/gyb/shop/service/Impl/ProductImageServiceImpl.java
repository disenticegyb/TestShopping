package com.gyb.shop.service.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.gyb.shop.pojo.Product;
import com.gyb.shop.pojo.ProductImage;
import com.gyb.shop.service.ProductImageService;

@Service
public class ProductImageServiceImpl extends BaseServiceImpl implements ProductImageService{

	//�����ع�֮��Ĳ�ѯ����
    @Override
    public void setFirstProdutImage(Product product) {
        if(null!=product.getFirstProductImage())
            return;
        List<ProductImage> pis= list("product", product, "type", ProductImageService.type_single);
        if(!pis.isEmpty())
            product.setFirstProductImage(pis.get(0));
    }
    /*@Override
    public void setFirstProdutImage(Product product) {
        if(null!=product.getFirstProductImage())//�жϲ�Ʒ��ʾҳ���Ƿ���ͼƬ����Ϊ����������
            return;
        //��ѯ�����͵�ͼƬ���ؼ���
        List<ProductImage> pis= list("product", product, "type", ProductImageService.type_single);
        if(!pis.isEmpty())//��β�Ϊ�գ��򷵻ص�һ��ͼƬ
            product.setFirstProductImage(pis.get(0));
    }
 
    public List<ProductImage> list(String key_product, Product product, String key_type, String type) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq(key_product, product));
        dc.add(Restrictions.eq(key_type, type));
        dc.addOrder(Order.desc("id"));
        return this.findByCriteria(dc);
    }
*/
}
