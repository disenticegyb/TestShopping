package com.gyb.shop.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gyb.shop.service.CategoryService;
import com.gyb.shop.service.OrderItemService;
import com.gyb.shop.service.OrderService;
import com.gyb.shop.service.ProductImageService;
import com.gyb.shop.service.ProductService;
import com.gyb.shop.service.PropertyService;
import com.gyb.shop.service.PropertyValueService;
import com.gyb.shop.service.ReviewService;
import com.gyb.shop.service.UserService;

/**
 * 创建Action4Service用于提供服务注入
 * @author disentice
 *
 */
public class Action4Service extends Action4Pojo{

	@Autowired
    CategoryService categoryService;
	
	@Autowired
	PropertyService propertyService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
    @Autowired
    PropertyValueService propertyValueService; 
    
    @Autowired
    UserService userService;
    
    @Autowired
    OrderService orderService;
    
    @Autowired
	OrderItemService orderItemService;
    
    @Autowired
    ReviewService reviewService;
    
	//transient to persistent 瞬时对象转换为持久对象
    public void t2p(Object o){
            try {
                Class clazz = o.getClass();//通过反射获取类对象；
                int id = (Integer) clazz.getMethod("getId").invoke(o);//通过对象getid方法获取对象id；
                Object persistentBean = categoryService.get(clazz, id);//通过id获取持久化对象；
                String beanName = clazz.getSimpleName();//获取对象的全路径
                Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);//获取对象的set方法，继承了Action4pojo类
                setMethod.invoke(this, persistentBean);//返回一个持久化对象
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
