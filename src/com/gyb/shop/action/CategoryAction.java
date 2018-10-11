package com.gyb.shop.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.gyb.shop.pojo.Category;
import com.gyb.shop.service.CategoryService;
import com.gyb.shop.util.ImageUtil;
import com.gyb.shop.util.Page;

/**
 * action层在mvc设计模式中控制作用
 * @author disentice
 *
 */
/*@Namespace("/")//struts2命名空间
@ParentPackage("basicstruts")//继承基本包；
@Results({
	根据返回值跳转
		@Result(name="listCategory",location="/admin/listCategory.jsp"),
	跳转页面重定向至admin_category_list
		@Result(name="listCategoryPage",type="redirect" ,location="/admin_category_list"),
		增加编辑页面跳转
		@Result(name="editCategory",location="/admin/editCategory.jsp"),
})*/
public class CategoryAction extends Action4Result{

	@Action("admin_category_list")//设置跳转方法和制定url\并进行分页处理；
	public String list() {
		if (page==null) {
			page = new Page();
		}
		int total = categoryService.total();//获取总页数
		page.setTotal(total);
		categorys = categoryService.listByPage(page);
		System.out.println(categorys+"categoryAction中的list方法");
		return "listCategory";
	}
	/**
	 * 增加方法add映射/admin_category_add,
	 * @return
	 */
	@Action("admin_category_add")
	public String add() {
		/*   	categoryService.save(category);
	        File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
	        File file = new File(imageFolder,category.getId()+".jpg");
	        try {
	            FileUtils.copyFile(img, file);
	            BufferedImage img = ImageUtil.change2jpg(file);
	            ImageIO.write(img, "jpg", file);           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "listCategoryPage";
	    }  */
		System.out.println("-------------------add方法中的"+category);
		categoryService.save(category);//保存数据至数据库
		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,category.getId()+".jpg");//获取id，并计算其对应的图片名称：id.jpg;
		try {
			FileUtils.copyFile(img, file);//将临时文件复制id.jpg
			BufferedImage img = ImageUtil.change2jpg(file);//转换格式jpg；
			ImageIO.write(img, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listCategoryPage";
	}
	
	@Action("admin_category_delete")
	public String delete() {
		categoryService.delete(category);
		return "listCategoryPage";
	}
	
	@Action("admin_category_edit")
	public String edit() {
		//使用Action4Service中的方法
		t2p(category);
		/*int id = category.getId();
		categoryService.get(Category.class,id);
		category = (Category)categoryService.get(id);*/
		return "editCategory";
	}
	
	@Action("admin_category_update")
	public String update() {
		categoryService.update(category);
		if (null!=img) {
			File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
			File file = new File(imageFolder, category.getId()+".jpg");//获取id，并计算其对应的图片名称：id.jpg;
			try {
				FileUtils.copyFile(img, file);//将临时文件复制id.jpg
				BufferedImage img = ImageUtil.change2jpg(file);//转换格式jpg；
				ImageIO.write(img, "jpg", file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "listCategoryPage";
	}
/*	@Autowired//注入外部资源，根据类型
	CategoryService categoryService;
	
	List<Category> categorys;
	
	//增加属性page，用于分页查询；
	Page page;
	//增加分类实体类属性，用于接收页面上传来的分类名称；
	Category category;
	//增加属性img，用来接收浏览器提交的图片文件；
	File img;*/

}
