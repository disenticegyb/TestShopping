package com.gyb.shop.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
/**
 * 创建dao层连接数据库；继承hibernateTemplate，使用所提供的增删改查操作；
 * @author disentice
 *
 */
@Repository("dao")//表述访问数据库
public class DAOImpl extends HibernateTemplate{
	@Resource(name="sf")//按名称进行装配bean,注入sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
