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
 * action����mvc���ģʽ�п�������
 * @author disentice
 *
 */
/*@Namespace("/")//struts2�����ռ�
@ParentPackage("basicstruts")//�̳л�������
@Results({
	���ݷ���ֵ��ת
		@Result(name="listCategory",location="/admin/listCategory.jsp"),
	��תҳ���ض�����admin_category_list
		@Result(name="listCategoryPage",type="redirect" ,location="/admin_category_list"),
		���ӱ༭ҳ����ת
		@Result(name="editCategory",location="/admin/editCategory.jsp"),
})*/
public class CategoryAction extends Action4Result{

	@Action("admin_category_list")//������ת�������ƶ�url\�����з�ҳ����
	public String list() {
		if (page==null) {
			page = new Page();
		}
		int total = categoryService.total();//��ȡ��ҳ��
		page.setTotal(total);
		categorys = categoryService.listByPage(page);
		System.out.println(categorys+"categoryAction�е�list����");
		return "listCategory";
	}
	/**
	 * ���ӷ���addӳ��/admin_category_add,
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
		System.out.println("-------------------add�����е�"+category);
		categoryService.save(category);//�������������ݿ�
		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,category.getId()+".jpg");//��ȡid�����������Ӧ��ͼƬ���ƣ�id.jpg;
		try {
			FileUtils.copyFile(img, file);//����ʱ�ļ�����id.jpg
			BufferedImage img = ImageUtil.change2jpg(file);//ת����ʽjpg��
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
		//ʹ��Action4Service�еķ���
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
			File file = new File(imageFolder, category.getId()+".jpg");//��ȡid�����������Ӧ��ͼƬ���ƣ�id.jpg;
			try {
				FileUtils.copyFile(img, file);//����ʱ�ļ�����id.jpg
				BufferedImage img = ImageUtil.change2jpg(file);//ת����ʽjpg��
				ImageIO.write(img, "jpg", file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "listCategoryPage";
	}
/*	@Autowired//ע���ⲿ��Դ����������
	CategoryService categoryService;
	
	List<Category> categorys;
	
	//��������page�����ڷ�ҳ��ѯ��
	Page page;
	//���ӷ���ʵ�������ԣ����ڽ���ҳ���ϴ����ķ������ƣ�
	Category category;
	//��������img����������������ύ��ͼƬ�ļ���
	File img;*/

}
