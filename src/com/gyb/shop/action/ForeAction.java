package com.gyb.shop.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.math.RandomUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.gyb.shop.comparator.ProductAllComparator;
import com.gyb.shop.comparator.ProductDateComparator;
import com.gyb.shop.comparator.ProductPriceComparator;
import com.gyb.shop.comparator.ProductReviewComparator;
import com.gyb.shop.comparator.ProductSaleCountComparator;
import com.gyb.shop.pojo.OrderItem;
import com.gyb.shop.pojo.Product;
import com.gyb.shop.pojo.User;
import com.gyb.shop.service.OrderService;
import com.gyb.shop.service.ProductImageService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 由于前端需要处理的信息比较杂，有产品、分类、图片、用户信息等 故创建一个专门处理前端请求的action；
 * 
 * @author disentice
 *
 */
public class ForeAction extends Action4Result {

	// 需要传递参数，故设置参数msg，
	// 随着后续开发参数会越来越多，破坏action充当控制器的结构性
	// 及创建Action4Parameter类，专门进行参数的处理，通过继承实现获取
//	String msg;

	// 登陆
	@Action("forelogin")
	public String login() {
		// 对账号进行转义——防止恶意注册；
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		// 根据账号密码获取对象uuser_session
		User user_session = userService.get(user.getName(), user.getPassword());
		if (null == user_session) {// 为获取则跳转登陆界面
			msg = "账号密码错误";
			return "login.jsp";
		}
		// 获取则将对象保存在session中
		ActionContext.getContext().getSession().put("user", user_session);
		return "homePage";
	}

	// 登陆判断
	@Action("foreregister")
	public String register() {// 登陆判断
		user.setName(HtmlUtils.htmlEscape(user.getName()));// 界面获取name进行转义；
		boolean exist = userService.isExist(user.getName());// 传参数

		if (exist) {
			msg = "用户名已经被使用,不能使用";
			return "register.jsp"; // 返回登陆界面
		}
		userService.save(user);// 不存在保存user至数据库
		return "registerSuccessPage";// 返回登陆成功界面
	}

	// 查询首页面要显示的信息
	@Action("forehome")
	public String home() {
		categorys = categoryService.list();// 获取所有分类
		productService.fill(categorys);// 将分类填入产品集合
		productService.fillByRow(categorys);// 将分类分行填入推荐产品集合
		return "home.jsp";
	}

	// 退出
	@Action("forelogout")
	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		return "homePage";
	}

	// 产品页面显示
	@Action("foreproduct")
	public String product() {
		t2p(product);// 持久化对象

		productImageService.setFirstProdutImage(product);// 设置单张照片
		//获取商品展示图片
		productSingleImages = productImageService.list("product", product, "type", ProductImageService.type_single);
		//获取商品详情图片
		productDetailImages = productImageService.list("product", product, "type", ProductImageService.type_detail);
		//赋值
		product.setProductSingleImages(productSingleImages);
		product.setProductDetailImages(productDetailImages);
		//获取商品属性值集合
		propertyValues = propertyValueService.listByParent(product);
		//获取评论集合
		reviews = reviewService.listByParent(product);
		//设置销量和评价数量
		productService.setSaleAndReviewNumber(product);
		return "product.jsp";
	}
	
	//判断是否为登陆状态
	@Action("forecheckLogin")
	public String checkLogin() {
	    User u =(User) ActionContext.getContext().getSession().get("user");//从session中获取对象user
	    if(null==u)
	        return "fail.jsp";
	    else
	        return "success.jsp";
	}
	
	//路径/foreloginAjax登陆调用
	@Action("foreloginAjax")
	public String loginAjax() {
	     
	    user.setName(HtmlUtils.htmlEscape(user.getName()));
	    User user_session = userService.get(user.getName(),user.getPassword());
	       
	    if(null==user_session)
	        return "fail.jsp";
	     
	    ActionContext.getContext().getSession().put("user", user_session);
	    return "success.jsp";      
	}
	
	//产品排序
	@Action("forecategory")
	public String category(){
	    t2p(category);//持久化对象
	    productService.fill(category);//填充此类型产品
	    productService.setSaleAndReviewNumber(category.getProducts());//获取产品销量和评价      
	      
	    if(null!=sort){//判断排序变量是否为空
	    switch(sort){//
	        case "review"://根据评价
	            Collections.sort(category.getProducts(),new ProductReviewComparator());
	            break;
	        case "date" ://根据创建日期
	            Collections.sort(category.getProducts(),new ProductDateComparator());
	            break;
	        case "saleCount" ://根据销量
	            Collections.sort(category.getProducts(),new ProductSaleCountComparator());
	            break;
	        case "price"://根据价格
	            Collections.sort(category.getProducts(),new ProductPriceComparator());
	            break;
	        case "all"://根据销量*评论
	            Collections.sort(category.getProducts(),new ProductAllComparator());
	            break;
	        }
	    }
	    return "category.jsp";         
	}
	
	//模糊查询返回结果
	@Action("foresearch")
	public String search(){
	    products= productService.search(keyword,0,20);//根据关键字返回前20条数据
	    productService.setSaleAndReviewNumber(products);//设置评价和评论
	    for (Product product : products)
	        productImageService.setFirstProdutImage(product);  //设置图片
	     
	    return "searchResult.jsp";
	}
	
	//立即购买
	@Action("forebuyone")
	public String buyone() {
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    boolean found = false;//如果有的话，数量增加num；
	    List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	    for (OrderItem oi : ois) {
	        if(oi.getProduct().getId()==product.getId()){
	            oi.setNumber(oi.getNumber()+num);
	            orderItemService.update(oi);
	            found = true;
	            oiid = oi.getId();//获取id
	            break;
	        }
	    }        
	    if(!found){//订单项中没有找到及创建新的订单项
	        OrderItem oi = new OrderItem();
	        oi.setUser(user);
	        oi.setNumber(num);
	        oi.setProduct(product);
	        orderItemService.save(oi);
	        oiid = oi.getId();//获取id
	    }
	    return "buyPage";
	}
	
	//结算页面
	@Action("forebuy")
	public String buy() {
	    orderItems = new ArrayList<>();
	    for (int oiid : oiids) {
	        OrderItem oi= (OrderItem) orderItemService.get(oiid);
	        total +=oi.getProduct().getPromotePrice()*oi.getNumber();
	        orderItems.add(oi);
	        productImageService.setFirstProdutImage(oi.getProduct());
	    }
	      
	    ActionContext.getContext().getSession().put("orderItems", orderItems);
	    return "buy.jsp";
	}
	
	//增加订单项
	@Action("foreaddCart")
	public String addCart() {
	     
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    boolean found = false;
	 
	    List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	    for (OrderItem oi : ois) {
	        if(oi.getProduct().getId()==product.getId()){
	            oi.setNumber(oi.getNumber()+num);
	            orderItemService.update(oi);
	            found = true;
	            break;
	        }
	    }      
	      
	    if(!found){
	        OrderItem oi = new OrderItem();
	        oi.setUser(user);
	        oi.setNumber(num);
	        oi.setProduct(product);
	        orderItemService.save(oi);
	    }
	    return "success.jsp";
	}
	
	//购物车
	@Action("forecart")
	public String cart() {
	    User user =(User) ActionContext.getContext().getSession().get("user");
	    orderItems = orderItemService.list("user",user,"order", null);
	    for (OrderItem orderItem : orderItems)
	        productImageService.setFirstProdutImage(orderItem.getProduct());
	    return "cart.jsp";
	}
	
	//订单项数量调整
	 @Action("forechangeOrderItem")
	 public String changeOrderItem() {
	     User user =(User) ActionContext.getContext().getSession().get("user");
	     List<OrderItem> ois = orderItemService.list("user",user,"order", null);
	     for (OrderItem oi : ois) {
	         if(oi.getProduct().getId()==product.getId()){
	             oi.setNumber(num);
	             orderItemService.update(oi);
	             break;
	         }   
	     }      
	     return "success.jsp";
	 } 
	 
	 //确认删除
	 @Action("foredeleteOrderItem")
	 public String deleteOrderItem(){
	     orderItemService.delete(orderItem);
	     return "success.jsp";
	 }
	 
	 //创建订单
	 @Action("forecreateOrder")
	 public String createOrder(){
	     List<OrderItem> ois= (List<OrderItem>) ActionContext.getContext().getSession().get("orderItems");
	     if(ois.isEmpty())
	         return "login.jsp";
	   
	     User user =(User) ActionContext.getContext().getSession().get("user");
	     String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +RandomUtils.nextInt(10000);
	      
	     order.setOrderCode(orderCode);
	     order.setCreateDate(new Date());
	     order.setUser(user);
	     order.setStatus(OrderService.waitPay);
	      
	     total = orderService.createOrder(order, ois);
	     return "alipayPage";
	 }
	 
	 //确认支付界面
	   @Action("forealipay")
	    public String forealipay(){
	        return "alipay.jsp";
	    }
	   //支付成功页面
	   @Action("forepayed")
	   public String payed() {
	       t2p(order);
	       order.setStatus(OrderService.waitDelivery);
	       order.setPayDate(new Date());
	       orderService.update(order);
	       return "payed.jsp";    
	   }
	   //查询所有非删除的订单；
	   @Action("forebought")
	   public String bought() {
	       User user =(User) ActionContext.getContext().getSession().get("user");
	       orders= orderService.listByUserWithoutDelete(user);
	       orderItemService.fill(orders);
	       return "bought.jsp";       
	   } 
	   //确认收货
	   @Action("foreconfirmPay")
	   public String confirmPay() {
	       t2p(order);
	       orderItemService.fill(order);
	       return "confirmPay.jsp";       
	   }
	   //确认收获成功
	   @Action("foreorderConfirmed")
	   public String orderConfirmed() {
	       t2p(order);
	       order.setStatus(OrderService.waitReview);
	       order.setConfirmDate(new Date());
	       orderService.update(order);
	       return "orderConfirmed.jsp";
	   }
	   //删除订单
	   @Action("foredeleteOrder")
	   public String deleteOrder(){
	       t2p(order);
	       order.setStatus(OrderService.delete);
	       orderService.update(order);
	       return "success.jsp";      
	   }
	   //评价产品
	   @Action("forereview")
	   public String review() {
	       t2p(order);
	       orderItemService.fill(order);
	       product = order.getOrderItems().get(0).getProduct();
	       reviews = reviewService.listByParent(product);
	       productService.setSaleAndReviewNumber(product);
	       return "review.jsp";       
	   }
	   //提交评价
	   @Action("foredoreview")
	   public String doreview() {
	       t2p(order);
	       t2p(product);
	        
	       order.setStatus(OrderService.finish);
	        
	       String content = review.getContent();
	       content = HtmlUtils.htmlEscape(content);    
	       User user =(User) ActionContext.getContext().getSession().get("user");     
	       review.setContent(content);
	       review.setProduct(product);
	       review.setCreateDate(new Date());
	       review.setUser(user);
	        
	       reviewService.saveReviewAndUpdateOrderStatus(review,order);
	         
	       showonly = true;
	       return "reviewPage";
	   } 
}
