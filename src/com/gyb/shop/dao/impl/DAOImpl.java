package com.gyb.shop.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
/**
 * ����dao���������ݿ⣻�̳�hibernateTemplate��ʹ�����ṩ����ɾ�Ĳ������
 * @author disentice
 *
 */
@Repository("dao")//�����������ݿ�
public class DAOImpl extends HibernateTemplate{
	@Resource(name="sf")//�����ƽ���װ��bean,ע��sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
