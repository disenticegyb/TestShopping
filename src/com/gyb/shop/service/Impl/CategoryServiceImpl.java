package com.gyb.shop.service.Impl;

import org.springframework.stereotype.Service;

import com.gyb.shop.service.CategoryService;

/*
 * �̳�BaseServiceImpl��ȡ����д�ķ�����
 */
@Service//ҵ���ע��bean��
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService{

/*	@Autowired  DAOImpl dao;//ע���ⲿ��Դ��ͨ�����ͣ�
	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);//ʹ��hibernate���߲�ѯ����
		dc.addOrder(Order.desc("id"));//���Ӳ�ѯ����������id�ݼ���ѯ
		return dao.findByCriteria(dc);//���ز�ѯ����
	}
	@Override
	public int total() {
		String hql = "select count(*) from Category";
		List<Long> l = dao.find(hql);//��ѯ�������ؼ��ϣ�
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);//��ȡ��һ�����ݣ���Ϊ����������
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
