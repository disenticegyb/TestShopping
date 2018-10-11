package com.gyb.shop.service.Impl;

import org.springframework.stereotype.Service;

import com.gyb.shop.service.CategoryService;

/*
 * 继承BaseServiceImpl获取其重写的方法；
 */
@Service//业务层注解bean；
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService{

/*	@Autowired  DAOImpl dao;//注入外部资源，通过类型；
	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);//使用hibernate离线查询对象
		dc.addOrder(Order.desc("id"));//增加查询条件，按照id递减查询
		return dao.findByCriteria(dc);//返回查询数据
	}
	@Override
	public int total() {
		String hql = "select count(*) from Category";
		List<Long> l = dao.find(hql);//查询条数返回集合；
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);//获取第一条数据；即为数据条数；
		return result.intValue();
	}
	@Override
	public List<Category> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc, page.getStart(), page.getCount());
	}
	@Override
	public void save(Category category) {
		dao.save(category);
	}
	@Override
	public void delete(Category category) {
		dao.delete(category);
		
	}
	@Override
	public Category get(Class clazz, int id) {
		return (Category)dao.get(clazz, id);
	}
	@Override
	public void update(Category category) {
		dao.update(category);
		
	}*/

}
